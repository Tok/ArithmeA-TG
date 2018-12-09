(ns arithmea.util
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn upper-case-letter? [c] (contains? (set (map char (range 65 91))) c))
(defn word? [s] (every? upper-case-letter? (seq (str/upper-case s))))
(defn clean-up [s] (apply str (filter upper-case-letter? (str/upper-case (str/trim (str s))))))

(defn slurp-words [res]
  (let [content (-> (io/resource res) slurp str/split-lines)
        separated (str/split (apply str content) #" ")]
    (sort (distinct (filter word? (map str/upper-case separated))))))
