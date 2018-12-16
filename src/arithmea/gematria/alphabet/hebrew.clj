(ns arithmea.gematria.alphabet.hebrew
  (:require [clojure.set :as set]
            [clojure.spec.alpha :as s]))

(def hebrew-method #{:ordinal :full :katan})
(s/def ::hebrew-method hebrew-method)

(def letters #{:aleph :beth :gimel :daleth :heh :vav
               :zain :cheth :teth :yud :kaph :lamed
               :mem :nun :samekh :ayin :peh :tzaddi
               :qoph :resh :shin :tav
               :nun-final :kaph-final :mem-final
               :peh-final :tzaddi-final})
(s/def ::hebrew-letter letters)

(defn- row [char final? ordinal full katan]
  {:char char :final? final? :ordinal ordinal :full full :katan katan})

(def ^:private hebrew-table
  {:aleph        (row \u05D0 false 1 1 1)
   :beth         (row \u05D1 false 2 2 2)
   :gimel        (row \u05D2 false 3 3 3)
   :daleth       (row \u05D3 false 4 4 4)
   :heh          (row \u05D4 false 5 5 5)
   :vav          (row \u05D5 false 6 6 6)
   :zain         (row \u05D6 false 7 7 7)
   :cheth        (row \u05D7 false 8 8 8)
   :teth         (row \u05D8 false 9 9 9)
   :yud          (row \u05D9 false 10 10 1)
   :kaph         (row \u05DB false 11 20 2)
   :lamed        (row \u05DC false 12 30 3)
   :mem          (row \u05DE false 13 40 4)
   :nun          (row \u05E0 false 14 50 5)
   :samekh       (row \u05E1 false 15 60 6)
   :ayin         (row \u05E2 false 16 70 7)
   :peh          (row \u05E4 false 17 80 8)
   :tzaddi       (row \u05E6 false 18 90 9)                 ;*
   :qoph         (row \u05E7 false 19 100 1)
   :resh         (row \u05E8 false 20 200 2)
   :shin         (row \u05E9 false 21 300 3)
   :tav          (row \u05EA false 22 400 4)
   :kaph-final   (row \u05DA true 23 500 5)
   :mem-final    (row \u05DD true 24 600 6)
   :nun-final    (row \u05DF true 25 700 7)
   :peh-final    (row \u05E3 true 26 800 8)
   :tzaddi-final (row \u05E5 true 27 900 9)})

(def ^:private char-table
  {\u05D0 :aleph
   \u05D1 :beth
   \u05D2 :gimel
   \u05D3 :daleth
   \u05D4 :heh
   \u05D5 :vav
   \u05D6 :zain
   \u05D7 :cheth
   \u05D8 :teth
   \u05D9 :yud
   \u05DB :kaph
   \u05DC :lamed
   \u05DE :mem
   \u05E0 :nun
   \u05E1 :samekh
   \u05E2 :ayin
   \u05E4 :peh
   \u05E6 :tzaddi
   \u05E7 :qoph
   \u05E8 :resh
   \u05E9 :shin
   \u05EA :tav
   \u05DF :nun-final
   \u05DA :kaph-final
   \u05DD :mem-final
   \u05E3 :peh-final
   \u05E5 :tzaddi-final})

(def letter-table (set/map-invert char-table))

(defn to-final [letter]
  {:pre  [(s/valid? ::hebrew-letter letter)]
   :post [(s/valid? ::hebrew-letter %)]}
  (case letter
    :nun :nun-final
    :kaph :kaph-final
    :mem :mem-final
    :peh :peh-final
    :tzaddi :tzaddi-final
    letter))

(defn number-value [method letter]
  {:pre  [(s/valid? ::hebrew-method method)
          (s/valid? ::hebrew-letter letter)]
   :post [(s/valid? pos-int? %)]}
  (let [values (get hebrew-table letter)] (method values)))
