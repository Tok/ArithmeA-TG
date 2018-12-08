(ns arithmea.gematria.alphabet.hebrew)

(def hebrew-table
  {:ALEPH        {:char \u05D0 :final? false :ordinal 1 :full 1 :katan 1}
   :BETH         {:char \u05D1 :final? false :ordinal 2 :full 2 :katan 2}
   :GIMEL        {:char \u05D2 :final? false :ordinal 3 :full 3 :katan 3}
   :DALETH       {:char \u05D3 :final? false :ordinal 4 :full 4 :katan 4}
   :HEH          {:char \u05D4 :final? false :ordinal 5 :full 5 :katan 5}
   :VAV          {:char \u05D5 :final? false :ordinal 6 :full 6 :katan 6}
   :ZAIN         {:char \u05D6 :final? false :ordinal 7 :full 7 :katan 7}
   :CHETH        {:char \u05D7 :final? false :ordinal 8 :full 8 :katan 8}
   :TETH         {:char \u05D8 :final? false :ordinal 9 :full 9 :katan 9}
   :YUD          {:char \u05D9 :final? false :ordinal 10 :full 10 :katan 1}
   :KAPH         {:char \u05DB :final? false :ordinal 11 :full 20 :katan 2}
   :LAMED        {:char \u05DC :final? false :ordinal 12 :full 30 :katan 3}
   :MEM          {:char \u05DE :final? false :ordinal 13 :full 40 :katan 4}
   :NUN          {:char \u05E0 :final? false :ordinal 14 :full 50 :katan 5}
   :SAMEKH       {:char \u05E1 :final? false :ordinal 15 :full 60 :katan 6}
   :AYIN         {:char \u05E2 :final? false :ordinal 16 :full 70 :katan 7}
   :PEH          {:char \u05E4 :final? false :ordinal 17 :full 80 :katan 8}
   :TZADDI       {:char \u05E6 :final? false :ordinal 18 :full 90 :katan 9} ;*
   :QOPH         {:char \u05E7 :final? false :ordinal 19 :full 100 :katan 1}
   :RESH         {:char \u05E8 :final? false :ordinal 20 :full 200 :katan 2}
   :SHIN         {:char \u05E9 :final? false :ordinal 21 :full 300 :katan 3}
   :TAV          {:char \u05EA :final? false :ordinal 22 :full 400 :katan 4}
   :KAPH_FINAL   {:char \u05DA :final? true :ordinal 23 :full 500 :katan 5}
   :MEM_FINAL    {:char \u05DD :final? true :ordinal 24 :full 600 :katan 6}
   :NUN_FINAL    {:char \u05DF :final? true :ordinal 25 :full 700 :katan 7}
   :PEH_FINAL    {:char \u05E3 :final? true :ordinal 26 :full 800 :katan 8}
   :TZADDI_FINAL {:char \u05E5 :final? true :ordinal 27 :full 900 :katan 9}})

(def hebrew-letters [:ALEPH :BETH :GIMEL :DALETH :HEH :VAV :ZAIN :CHETH :TETH :YUD :KAPH
                     :LAMED :MEM :NUN :SAMEKH :AYIN :PEH :TZADDI :QOPH :RESH :SHIN :TAV
                     :KAPH_FINAL :MEM_FINAL :NUN_FINAL :PEH_FINAL :TZADDI_FINAL])
