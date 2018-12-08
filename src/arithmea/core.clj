(ns arithmea.core
  (:require
    [clojure.tools.logging :as log]
    [clojure.string :as str]
    [clojure.java.io :as io]
    [arithmea.bot.client :as bot]
    [arithmea.util :as util]
    [arithmea.gematria.calculator :as gem]))

(defn- slurp-words [res]
  (let [content (-> (io/resource res) slurp str/split-lines)
        separated (str/split (apply str content) #" ")]
    (sort (distinct (filter util/word? (map str/upper-case separated))))))

(defn- make-groups [method data] {method (group-by #(gem/calculate method %) data)})

(defn- create-dict []
  (let [common-words (slurp-words "1000-most-common-words.txt")
        arithmea-list (slurp-words "arithmea-2000-words.txt")
        dict-data (concat common-words arithmea-list)
        clean-data (sort (distinct (filter util/word? (map str/upper-case dict-data))))]
    (log/info "Loading dict with" (count clean-data) "words.")
    (into (sorted-map) (map #(make-groups % clean-data) gem/active-methods))
    ))

(defn- main-loop [dict offset]
  (let [chat-id "-1001280104881"]
    (Thread/sleep 3000)
    (recur dict (bot/update-state dict chat-id offset))))

(defn -main "ArithmeA" [& args]
  (let [dict (create-dict)]
    (log/debug dict)
    (log/debug (reduce + (map #(count %) (vals (:chal dict)))) "Words.")
    (log/info (count gem/active-methods) "active Methods.")
    (main-loop dict 0)))
