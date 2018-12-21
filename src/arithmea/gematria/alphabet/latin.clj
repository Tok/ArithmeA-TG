(ns arithmea.gematria.alphabet.latin
  (:require [clojure.spec.alpha :as s]))

(def latin-method #{:ia :chal :pyth :naeq :tq :ger :eq :aq})
(s/def ::latin-method latin-method)

(defn- row [ia chal pyth naeq tq ger eq aq]
  {:ia ia :chal chal :pyth pyth :naeq naeq :tq tq :ger ger :eq eq :aq aq})
(def ^:private latin-table
  {\A (row 1 1 1 1 5 1 1 10)
   \B (row 2 2 2 20 20 0 200 11)
   \C (row 3 3 3 13 2 0 40 12)
   \D (row 4 4 4 6 23 0 6 13)
   \E (row 5 5 5 25 13 2 700 14)
   \F (row 6 8 6 18 12 0 90 15)
   \G (row 7 3 7 11 11 7 20 16)
   \H (row 8 5 8 4 3 8 4 17)
   \I (row 9 1 9 23 26 3 500 18)
   \J (row 10 1 1 16 7 0 70 19)
   \K (row 11 2 2 9 17 0 9 20)
   \L (row 12 3 3 2 1 9 2 21)
   \M (row 13 4 4 21 21 0 300 22)
   \N (row 14 5 5 14 24 0 50 23)
   \O (row 15 7 6 19 10 4 7 24)
   \P (row 16 8 7 26 4 0 800 25)
   \Q (row 17 1 8 17 16 0 100 26)
   \R (row 18 2 9 12 14 0 30 27)
   \S (row 19 3 1 5 15 6 5 28)
   \T (row 20 4 2 24 9 0 600 29)
   \U (row 21 6 3 17 25 5 80 30)
   \V (row 22 6 4 10 22 0 10 31)
   \W (row 23 6 5 3 8 0 3 32)
   \X (row 24 5 6 22 6 0 400 33)
   \Y (row 25 1 7 15 18 7 60 34)
   \Z (row 26 7 8 8 19 0 8 35)})

(defn number-value [method c]
  {:pre  [(s/valid? ::latin-method method)
          (s/valid? char? c)]
   :post [(s/valid? nat-int? %)]}
  (let [values (get latin-table c)] (method values)))
