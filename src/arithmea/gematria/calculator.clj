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
(defn calculate [method s] (latin-reduce method s))

(def gematria-methods [:ia :chal :pyth :naeq :tq :eq :ger])
(def active-methods [:ia :chal :pyth :naeq :tq :eq])

(def method-names {:ia "Simple" :chal "Chal" :pyth "Pyth" :naeq "NAEQ" :tq "TQ" :eq "EQ26" :ger "German"})
(defn find-method [name] (get (set/map-invert method-names) name))
