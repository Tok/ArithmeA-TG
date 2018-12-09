(ns arithmea.bot.command
  (:require [arithmea.gematria.calculator :as gem]
            [arithmea.bot.md :as md]
            [arithmea.util :as util]
            [arithmea.config :as config]
            [clojure.string :as str]
            [arithmea.gematria.transliterator :as trans]))

(defn- single-output [dict word method]
  (let [value (gem/calculate method word)
        matches (gem/find-matches dict method value)
        match-count (str "[" (count matches) "]")
        method-name (get gem/method-names method)
        self-ref (str "/" method-name "\\_" value)]
    (str method-name ": " (md/bold value) " " (md/ref-link match-count self-ref) \newline)
    ))

(defn multi-method [dict word]
  (let [clean-word (util/clean-up word)
        hebrew (trans/lat-to-heb clean-word)]
    (str "Input: " (md/bold clean-word) " Transliteration: " (md/bold hebrew) \newline
         (apply str (map #(single-output dict clean-word %) config/active-methods))
         )))

(defn- display-matches [matches]
  (let [total-count (count matches)]
    (if (> total-count 0)
      (let [shuffled (shuffle matches)
            selection (take config/result-limit shuffled)
            refs (map #(str "/" %) selection)]
        (str "Matches: (" (count selection) " of " total-count "): " (str/join ", " refs)))
      "No Matches Found.")))

(defn show-value [dict method value]
  (let [matches (gem/find-matches dict method value)
        method-name (get gem/method-names method)]
    (str "Method: " (md/bold method-name) " Value: " (md/bold value) \newline
         (display-matches matches))))

(defn- make-output [dict word method]
  (let [clean-word (util/clean-up word)
        value (gem/calculate method clean-word)
        matches (gem/find-matches dict method value)
        method-name (get gem/method-names method)]
    (str "Input: " (md/bold clean-word) " Method: " (md/bold method-name) " Value: " (md/bold value) \newline
         (md/fixed (apply str (map #(str % "(" (gem/calculate method (str %)) ") ") (seq clean-word)))) \newline
         (display-matches matches))))

(defn- output-status [dict]
  (let [method-count (count dict)
        word-count (reduce + (map #(count %) (vals (:chal dict))))]
    (str "Dict contains " (md/bold word-count) " words and " (md/bold method-count) " active methods.")))

(defn exec [dict command input]
  (if (not (str/blank? command))
    (let [comm (str/lower-case command)]
      (cond (= "echo" comm) input
            (= "status" comm) (output-status dict)
            (some #{comm} '("chal" "chaldean")) (make-output dict input :chal)
            (some #{comm} '("pyth" "pythagorean")) (make-output dict input :pyth)
            (some #{comm} '("simple" "ia")) (make-output dict input :ia)
            (some #{comm} '("naeq")) (make-output dict input :naeq)
            (some #{comm} '("tq" "trigrammaton")) (make-output dict input :tq)
            (some #{comm} '("schluessel")) (make-output dict input :ger)
            (some #{comm} '("english" "azure" "eq" "eq26")) (make-output dict input :eq)
            (some #{comm} '("full" "hebrew")) (make-output dict input :full)
            (some #{comm} '("ordinal")) (make-output dict input :ordinal)
            (some #{comm} '("katan")) (make-output dict input :katan)
            :else (multi-method dict comm)
            )) ""))
