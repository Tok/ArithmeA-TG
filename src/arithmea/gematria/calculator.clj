(ns arithmea.gematria.calculator
  (:require [arithmea.gematria.alphabet.hebrew :as hebrew]
            [arithmea.gematria.alphabet.latin :as latin]
            [arithmea.gematria.transliterator :as trans]
            [arithmea.util :as util]
            [clojure.set :as set]))

(defn latin-reduce [method s] (reduce + 0 (map #(latin/number-value method %) (util/clean-up s))))
(defn hebrew-reduce [method vec] (reduce + 0 (map #(hebrew/number-value method %) vec)))
(defn calculate [method s]
  (let [heb (trans/lat-to-heb-vec s)]
    (cond
      (= :ordinal method) (hebrew-reduce method heb)
      (= :full method) (hebrew-reduce method heb)
      (= :katan method) (hebrew-reduce method heb)
      :else (latin-reduce method s)
      )))

(def method-names {:ia      "Simple" :chal "Chal" :pyth "Pyth"
                   :naeq    "NAEQ" :tq "TQ" :eq "EQ" :ger "German"
                   :ordinal "Ordinal" :full "Full" :katan "Katan"})

(defn find-method [name] (get (set/map-invert method-names) name))

(defn lat-ordinal-value [c] (- (int (char c)) 64))
