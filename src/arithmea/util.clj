(ns arithmea.util
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn- upper-case-letter? [c] (contains? (set (map char (range 65 91))) c))
(defn- blank-to-dash [s] (str/replace s #" " "-"))
(defn- valid? [s] (or (= "-" s) (upper-case-letter? s)))

(defn word? [s] (every? upper-case-letter? (seq (str/upper-case s))))

(defn prime? [n] (every? false? (map #(= 0 (mod n %)) (range 2 (Math/sqrt n)))))

(defn clean-up [s]
  (let [upper (str/upper-case (str/trim (str s)))]
    (apply str (filter upper-case-letter? upper))))

(defn slurp-words [res]
  (let [content (slurp (io/resource res))
        separated (str/split (apply str content) #"[\n\r\s]+")
        upper (map str/upper-case separated)]
    (sort (distinct (filter word? upper)))))
