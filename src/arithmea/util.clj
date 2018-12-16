(ns arithmea.util
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn- blank->dash [s] (str/replace s #" " "-"))

(defn upper-case-letter? [c] (contains? (set (map char (range 65 91))) c))

(defn- valid? [s] (or (= "-" s) (upper-case-letter? s)))

(defn word? [s] (every? upper-case-letter? (seq (str/upper-case s))))

(defn prime? [n] (every? false? (map #(= 0 (mod n %)) (range 2 (Math/sqrt n)))))

(defn clean-up [s]
  (->> s str str/trim str/upper-case
       (filter upper-case-letter?)
       (apply str)))

(defn slurp-file [file]
  (-> file slurp
      (->> (apply str))
      (str/split #"[\n\r\s]+")
      (->> (map str/upper-case)
           (filter word?)
           distinct sort)))

(defn slurp-resource [res] (-> res io/resource slurp-file))
