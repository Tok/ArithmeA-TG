(ns arithmea.bot.command
  (:require [arithmea.gematria.calculator :as gem]
            [arithmea.gematria.matcher :as matcher]
            [arithmea.gematria.transliterator :as trans]
            [arithmea.gematria.qualifier :as quali]
            [arithmea.bot.emoji :as emoji]
            [arithmea.bot.md :as md]
            [arithmea.config :as config]
            [arithmea.dict :as dict]
            [arithmea.util :as util]
            [clojure.string :as str]))

(defn- single-output [word method]
  (let [value (gem/calculate method word)
        matches (matcher/find-matches method value)
        shuffled (if (not-empty matches) (shuffle matches) matches)
        match-count (str "[" (count matches) "]")
        method-name (get gem/method-names method)
        self-ref (str "/" method-name "\\_" value)
        selected-matches (vec (take config/display-limit shuffled))
        match-refs (map #(str "/" %) selected-matches)
        ref-display (apply str (interpose "|" match-refs))
        count-display (md/italic (str "[" match-count "] "))
        qualifier (quali/highlight-symbol value)]
    (str qualifier " " self-ref ": (" ref-display ") "
         (md/italic count-display) \newline)))

(defn display-anagrams [word]
  (let [anagrams (matcher/find-anagrams word)]
    (when (seq anagrams) (md/block anagrams))))

(defn multi-method [word]
  (let [clean-word (util/clean-up word)
        hebrew (trans/lat->heb-str clean-word)]
    (str (md/bold clean-word) \newline
         (apply str (map #(single-output clean-word %) config/active-latin-methods))
         (display-anagrams word)
         (md/bold hebrew) \newline
         (apply str (map #(single-output clean-word %) config/active-hebrew-methods)))))

(defn- display-matches [matches]
  (let [total-count (count matches)]
    (if (> total-count 0)
      (let [shuffled (shuffle matches)
            selection (take config/result-limit shuffled)
            refs (map #(str "/" %) selection)]
        (str "Matches: (" (count selection)
             " of " total-count "): " (str/join ", " refs)))
      "No Matches Found.")))

(defn show-value [method value]
  (let [matches (matcher/find-matches method value)
        method-name (get gem/method-names method)]
    (str (emoji/emojify (str value)) " Method: " (md/bold method-name) \newline
         (display-matches matches))))

(defn- output-status []
  (let [method-count (count dict/dict)
        word-count (reduce + (map #(count %) (vals (:chal dict/dict))))]
    (str "Dict contains " (md/bold word-count) " words and "
         (md/bold method-count) " active methods." \newline
         "Active in " (md/bold (count config/chat-ids))
         " chats. Polling Delay (s): " (md/bold config/polling-time-s))))

(defn- output-qualifiers []
  (str (emoji/exclamation-mark) " Master Number / Multiple Of 11" \newline
       (emoji/double-exclamation-mark) " Super Master Number / Multiple Of 111" \newline
       (emoji/hundred) " Multiple Of 100" \newline
       (emoji/brightness) " Other Highlight Reason" \newline
       (emoji/green-asterisk) " Fibonacci Number" \newline
       (emoji/orange-asterisk) " Cube Number" \newline
       (emoji/blue-p) " Prime Number" \newline
       (emoji/white-square) " Even Number" \newline
       (emoji/white-circle) " Odd Number"))

(defn exec [command input]
  (if-not (str/blank? command)
    (let [comm (str/lower-case command)]
      (cond (= "echo" comm) input
            (= "status" comm) (output-status)
            (= "qualifiers" comm) (output-qualifiers)
            :else (multi-method comm)
            )) ""))
