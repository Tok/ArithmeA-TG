(ns arithmea.gematria.alphabet.hebrew
  (:require [clojure.set :as set]))

(defn- row [char final? ordinal full katan]
  {:char char :final? final? :ordinal ordinal :full full :katan katan})
(def ^:private hebrew-table
  {:ALEPH        (row \u05D0 false 1 1 1)
   :BETH         (row \u05D1 false 2 2 2)
   :GIMEL        (row \u05D2 false 3 3 3)
   :DALETH       (row \u05D3 false 4 4 4)
   :HEH          (row \u05D4 false 5 5 5)
   :VAV          (row \u05D5 false 6 6 6)
   :ZAIN         (row \u05D6 false 7 7 7)
   :CHETH        (row \u05D7 false 8 8 8)
   :TETH         (row \u05D8 false 9 9 9)
   :YUD          (row \u05D9 false 10 10 1)
   :KAPH         (row \u05DB false 11 20 2)
   :LAMED        (row \u05DC false 12 30 3)
   :MEM          (row \u05DE false 13 40 4)
   :NUN          (row \u05E0 false 14 50 5)
   :SAMEKH       (row \u05E1 false 15 60 6)
   :AYIN         (row \u05E2 false 16 70 7)
   :PEH          (row \u05E4 false 17 80 8)
   :TZADDI       (row \u05E6 false 18 90 9)                 ;*
   :QOPH         (row \u05E7 false 19 100 1)
   :RESH         (row \u05E8 false 20 200 2)
   :SHIN         (row \u05E9 false 21 300 3)
   :TAV          (row \u05EA false 22 400 4)
   :KAPH_FINAL   (row \u05DA true 23 500 5)
   :MEM_FINAL    (row \u05DD true 24 600 6)
   :NUN_FINAL    (row \u05DF true 25 700 7)
   :PEH_FINAL    (row \u05E3 true 26 800 8)
   :TZADDI_FINAL (row \u05E5 true 27 900 9)})

(def ^:private char-table
  {\u05D0 :ALEPH
   \u05D1 :BETH
   \u05D2 :GIMEL
   \u05D3 :DALETH
   \u05D4 :HEH
   \u05D5 :VAV
   \u05D6 :ZAIN
   \u05D7 :CHETH
   \u05D8 :TETH
   \u05D9 :YUD
   \u05DB :KAPH
   \u05DC :LAMED
   \u05DE :MEM
   \u05E0 :NUN
   \u05E1 :SAMEKH
   \u05E2 :AYIN
   \u05E4 :PEH
   \u05E6 :TZADDI
   \u05E7 :QOPH
   \u05E8 :RESH
   \u05E9 :SHIN
   \u05EA :TAV
   \u05DF :NUN_FINAL
   \u05DA :KAPH_FINAL
   \u05DD :MEM_FINAL
   \u05E3 :PEH_FINAL
   \u05E5 :TZADDI_FINAL})

(def letter-table (set/map-invert char-table))

(defn to-final [letter]
  (case letter
    :NUN :NUN_FINAL
    :KAPH :KAPH_FINAL
    :MEM :MEM_FINAL
    :PEH :PEH_FINAL
    :TZADDI :TZADDI_FINAL))

(defn number-value [method letter]
  (let [values (get hebrew-table letter)] (method values)))
