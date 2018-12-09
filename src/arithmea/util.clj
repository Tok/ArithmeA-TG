(ns arithmea.util
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn upper-case-letter? [c] (contains? (set (map char (range 65 91))) c))
(defn word? [s] (every? upper-case-letter? (seq (str/upper-case s))))
(defn clean-up [s] (apply str (filter upper-case-letter? (str/upper-case (str/trim (str s))))))

(defn slurp-words [res]
  (let [content (slurp (io/resource res))
        separated (str/split (apply str content) #" ")]
    (sort (distinct (filter word? (map str/upper-case separated))))))
