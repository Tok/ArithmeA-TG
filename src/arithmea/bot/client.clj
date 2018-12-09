(ns arithmea.bot.client
  (:require [arithmea.bot.command :as command]
            [arithmea.gematria.calculator :as gem]
            [clj-http.client :as client]
            [clojure.data.json :as json]
            [clojure.java.io :as io]
            [clojure.tools.logging :as log]
            [clojure.string :as str]))

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

(defn- handle-ref [chat-id text]
  (let [raw (str/replace text #"/" "")
        command (str/replace raw #"@ArithmeA_bot" "")]
    (if (str/includes? command "_")
      (let [pair (str/split command #"_" 2)
            method (gem/find-method (get pair 0))
            value (read-string (get pair 1))
            result (command/show-value method value)]
        (send-channel-message chat-id result))
      (let [result (command/multi-method command)]
        (send-channel-message chat-id result))
      )))

(defn- handle-direct [chat-id text]
  (let [command-line (str/split text #" ")
        command (get command-line 1)
        input (get command-line 2)
        result (command/exec command input)]
    (send-channel-message chat-id result)))

(defn- handle-command [chat-id from-user text]
  (log/debug (str "@" chat-id " " from-user ": " text))
  (if (command? text)
    (cond
      (direct-command? text) (handle-direct chat-id text)
      (ref-command? text) (handle-ref chat-id text))))

(defn- handle-messages [chat-id messages last-update-id]
  (log/debug "handle-messages: " last-update-id)
  (if (not (empty? messages))
    (let [msg (first (seq messages))
          update-id (get msg "update_id")
          message (get msg "message")
          from-user (get (get message "from") "username")
          text (get message "text")]
      (println (str "@" chat-id " " from-user ": " text))
      (handle-command chat-id from-user text)
      (recur chat-id (rest messages) update-id))
    last-update-id
    ))

(defn- handle-chats [chat-ids messages offset]
  (apply int (map max (map #(handle-messages % messages offset) chat-ids))))

(defn update-state [chat-ids offset]
  (let [posts (get-updates offset "channel_post")
        status-code (posts :status)
        body (json/read-str (posts :body))
        ok? (get body "ok")
        messages (get body "result")]
    (if (and (= status-code 200) ok?)
      (+ (handle-chats chat-ids messages offset) 1)
      (do (log/error "HTTP-Status:" status-code) offset)
      )))
