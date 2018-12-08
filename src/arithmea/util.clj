(ns arithmea.util
  (:require [clojure.string :as str]))

(defn upper-case-letter? [c] (contains? (set (map char (range 65 91))) c))
(defn word? [s] (every? upper-case-letter? (seq (str/upper-case s))))
(defn clean-up [s] (apply str (filter upper-case-letter? (str/upper-case (str/trim (str s))))))
