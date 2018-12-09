(ns arithmea.bot.client
  (:require [clj-http.client :as client]
            [arithmea.bot.command :as command]
            [clojure.tools.logging :as log]
            [clojure.string :as str]
            [clojure.data.json :as json]
            [arithmea.gematria.calculator :as gem]
            [clojure.java.io :as io]))

(def secret-token (slurp (io/resource "secret-token.txt")))

(defn- bot-url "See https://core.telegram.org/bots/api#authorizing-your-bot" []
  (let [api-url "https://api.telegram.org/bot"]
    (str api-url secret-token)))

(defn- do-query [request params]
  (client/get (str (bot-url) request)
              {:query-params params}
              {:accept :json}
              {:as :clojure}))

(defn- do-request [req] (do-query req {}))

(defn test-bot-setup [] (do-request "/getMe"))

(defn get-chat [chat-id]
  (let [params {"chat_id" chat-id}]
    (do-query "/getChat" params)))

(defn get-updates [offset type]
  (let [params {"offset" offset "allowed_updates" type}]
    (do-query "/getUpdates" params)))

(defn send-channel-message [chat-id text]
  (let [params {"chat_id" chat-id "text" text "parse_mode" "Markdown"}]
    (if (not (str/blank? text)) (do-query "/sendMessage" params))))

(defn- ref-command? [text] (and (str/starts-with? text "/") (str/includes? text "@ArithmeA_bot")))
(defn- direct-command? [text] (and (str/starts-with? text "/arithmea") (str/includes? text " ")))
(defn- command? [text] (and (not (str/blank? text)) (or (ref-command? text) (direct-command? text))))

(defn- handle-ref [dict chat-id text]
  (let [raw (str/replace text #"/" "")
        command (str/replace raw #"@ArithmeA_bot" "")]
    (if (str/includes? command "_")
      (let [pair (str/split command #"_" 2)
            method (gem/find-method (get pair 0))
            value (read-string (get pair 1))
            result (command/show-value dict method value)]
        (send-channel-message chat-id result))
      (let [result (command/multi-method dict command)]
        (send-channel-message chat-id result))
      )))

(defn- handle-direct [dict chat-id text]
  (let [command-line (str/split text #" ")
        command (get command-line 1)
        input (get command-line 2)
        result (command/exec dict command input)]
    (send-channel-message chat-id result)))

(defn- handle-command [dict chat-id from-user text]
  (log/debug (str "@" chat-id " " from-user ": " text))
  (if (command? text)
    (cond
      (direct-command? text) (handle-direct dict chat-id text)
      (ref-command? text) (handle-ref dict chat-id text))))

(defn- handle-messages [dict chat-id messages last-update-id]
  (log/debug "handle-messages: " last-update-id)
  (if (not (empty? messages))
    (let [msg (first (seq messages))
          update-id (get msg "update_id")
          message (get msg "message")
          from-user (get (get message "from") "username")
          text (get message "text")]
      (println (str "@" chat-id " " from-user ": " text))
      (handle-command dict chat-id from-user text)
      (recur dict chat-id (rest messages) update-id))
    last-update-id
    ))

(defn- handle-chats [dict chat-ids messages offset]
  (apply int (map max (map #(handle-messages dict % messages offset) chat-ids))))

(defn update-state [dict chat-ids offset]
  (let [posts (get-updates offset "channel_post")
        status-code (posts :status)
        body (json/read-str (posts :body))
        ok? (get body "ok")
        messages (get body "result")]
    (if (and (= status-code 200) ok?)
      (+ (handle-chats dict chat-ids messages offset) 1)
      (do (log/error "HTTP-Status:" status-code) offset)
      )))
