(ns arithmea.bot.md)

(defn bold [text] (str "*" text "*"))
(defn italic [text] (str "_" text "_"))
(defn url [text url] (str "[" text "](" url ")"))
(defn mention [text uid] (str "[" text "](tg://user?id=" uid ")"))
(defn fixed [text] (str "`" text "`"))
(defn block [text] (str "```" text "```"))

(defn ref-link [match-count self-ref] (str " " self-ref " _" match-count "_"))
