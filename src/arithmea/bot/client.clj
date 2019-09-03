(ns arithmea.bot.client
  (:require [arithmea.bot.command :as command]
            [arithmea.gematria.calculator :as gem]
            [clj-http.client :as client]
            [clojure.data.json :as json]
            [clojure.java.io :as io]
            [clojure.tools.logging :as log]
            [clojure.string :as str]
            [arithmea.config :as config]))

(def ^:private secret-token (-> "secret-token.txt" io/resource slurp))

(defn- bot-url
  "See https://core.telegram.org/bots/api#authorizing-your-bot" []
  (let [api-url "https://api.telegram.org/bot"]
    (str api-url secret-token)))

(defn- file-url [file-path]
  "https://core.telegram.org/bots/api#getfile"
  (str "https://api.telegram.org/file/bot" secret-token "/" file-path))

(defn- do-query [request params]
  (client/get (str (bot-url) request)
              {:query-params params}
              {:accept :json}
              {:as :clojure}))

(defn- do-request [req] (do-query req {}))

(defn test-bot-setup [] (do-request "/getMe"))

(defn- get-chat [chat-id]
  "https://core.telegram.org/bots/api#getchat"
  (do-query "/getChat" {"chat_id" chat-id}))

(defn- get-updates [offset type]
  "https://core.telegram.org/bots/api#getupdates"
  (do-query "/getUpdates"
            {"offset"          offset
             "timeout"         config/polling-time-s
             "allowed_updates" type}))

(defn- send-channel-message [chat-id text]
  "https://core.telegram.org/bots/api#sendmessage"
  (log/debug "==>" text)
  (let [params {"chat_id" chat-id "text" text "parse_mode" "Markdown"}]
    (if-not (str/blank? text) (do-query "/sendMessage" params))))

(defn- get-file [file-id]
  "https://core.telegram.org/bots/api#getchat"
  (do-query "/getFile" {"file_id" file-id}))

(defn- ref-command? [text]
  (and (str/starts-with? text "/") (str/includes? text "@ArithmeA_bot")))
(defn- direct-command? [text]
  (and (str/starts-with? text "/arithmea") (str/includes? text " ")))
(defn- command? [text]
  (and (not (str/blank? text)) (or (ref-command? text) (direct-command? text))))

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
        (send-channel-message chat-id result)))))

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
  (if (seq messages)
    (let [msg (first (seq messages))
          update-id (get msg "update_id")
          message (get msg "message")
          from-user (get (get message "from") "username")
          text (get message "text")]
      (log/info (str "@" chat-id " " from-user ": " text))
      (handle-command chat-id from-user text)
      (recur chat-id (rest messages) (+ update-id 1)))
    last-update-id))

(defn- handle-chats [chat-ids messages offset]
  (apply int (map max (map #(handle-messages % messages offset) chat-ids))))

(defn update-state [chat-ids offset]
  (let [posts (get-updates offset "channel_post")
        status-code (posts :status)
        body (json/read-str (posts :body))
        ok? (get body "ok")
        messages (get body "result")]
    (if (and (= status-code 200) ok?)
      (handle-chats chat-ids messages offset)
      (do (log/error "HTTP-Status:" status-code) offset))))
