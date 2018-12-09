(ns arithmea.bot.command
  (:require [arithmea.gematria.calculator :as gem]
            [arithmea.gematria.matcher :as mat]
            [arithmea.gematria.transliterator :as trans]
            [arithmea.bot.md :as md]
            [arithmea.config :as config]
            [arithmea.dict :as dict]
            [arithmea.util :as util]
            [clojure.string :as str]))

(defn- single-output [word method]
  (let [value (gem/calculate method word)
        matches (mat/find-matches method value)
        match-count (str "[" (count matches) "]")
        method-name (get gem/method-names method)
        self-ref (str "/" method-name "\\_" value)]
    (str method-name ": " (md/bold value) " " (md/ref-link match-count self-ref) \newline)
    ))

(defn multi-method [word]
  (let [clean-word (util/clean-up word)
        hebrew (trans/lat-to-heb clean-word)]
    (str "Input: " (md/bold clean-word) \newline
         (apply str (map #(single-output clean-word %) config/active-latin-methods))
         "Transliteration: " (md/bold hebrew) \newline
         (apply str (map #(single-output clean-word %) config/active-hebrew-methods))
         )))

(defn- display-matches [matches]
  (let [total-count (count matches)]
    (if (> total-count 0)
      (let [shuffled (shuffle matches)
            selection (take config/result-limit shuffled)
            refs (map #(str "/" %) selection)]
        (str "Matches: (" (count selection) " of " total-count "): " (str/join ", " refs)))
      "No Matches Found.")))

(defn show-value [method value]
  (let [matches (mat/find-matches method value)
        method-name (get gem/method-names method)]
    (str "Method: " (md/bold method-name) " Value: " (md/bold value) \newline
         (display-matches matches))))

(defn- make-output [word method]
  (let [clean-word (util/clean-up word)
        value (gem/calculate method clean-word)
        matches (mat/find-matches method value)
        method-name (get gem/method-names method)]
    (str "Input: " (md/bold clean-word) " Method: " (md/bold method-name) " Value: " (md/bold value) \newline
         (md/fixed (apply str (map #(str % "(" (gem/calculate method (str %)) ") ") (seq clean-word)))) \newline
         (display-matches matches))))

(defn- output-status []
  (let [method-count (count dict/dict)
        word-count (reduce + (map #(count %) (vals (:chal dict/dict))))]
    (str "Dict contains " (md/bold word-count) " words and " (md/bold method-count) " active methods." \newline
         "Active in " (md/bold (count config/chat-ids)) " chats. Polling Delay (ms): " (md/bold config/polling-time-ms))))

(defn exec [command input]
  (if (not (str/blank? command))
    (let [comm (str/lower-case command)]
      (cond (= "echo" comm) input
            (= "status" comm) (output-status)
            (some #{comm} '("chal" "chaldean")) (make-output input :chal)
            (some #{comm} '("pyth" "pythagorean")) (make-output input :pyth)
            (some #{comm} '("simple" "ia")) (make-output input :ia)
            (some #{comm} '("naeq")) (make-output input :naeq)
            (some #{comm} '("tq" "trigrammaton")) (make-output input :tq)
            (some #{comm} '("schluessel")) (make-output input :ger)
            (some #{comm} '("english" "azure" "eq" "eq26")) (make-output input :eq)
            (some #{comm} '("full" "hebrew")) (make-output input :full)
            (some #{comm} '("ordinal")) (make-output input :ordinal)
            (some #{comm} '("katan")) (make-output input :katan)
            :else (multi-method comm)
            )) ""))
