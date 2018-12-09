(ns arithmea.gematria.calculator
  (:require [arithmea.util :as util]
            [arithmea.gematria.transliterator :as trans]
            [arithmea.gematria.alphabet.latin :as latin]
            [arithmea.gematria.alphabet.hebrew :as hebrew]
            [clojure.set :as set]))

(defn find-matches [dict method number] (get (get dict method) number))

;test should be same as ia
;(defn- lat-ordinal-value [c] (- (int (char c)) 64))
;(defn- lat-ordinal [s] (reduce + 0 (map lat-ordinal-value (util/clean-up s))))


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

(def gematria-methods [:ia :chal :pyth :naeq :tq :eq :ger :full :ordinal :katan])

(def method-names {:ia      "Simple" :chal "Chal" :pyth "Pyth"
                   :naeq    "NAEQ" :tq "TQ" :eq "EQ" :ger "German"
                   :ordinal "Ordinal" :full "Full" :katan "Katan"})

(defn find-method [name] (get (set/map-invert method-names) name))
