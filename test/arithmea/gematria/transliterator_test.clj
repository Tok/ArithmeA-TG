(ns arithmea.gematria.transliterator-test
  (:require [arithmea.gematria.transliterator :as trans]
            [clojure.test :refer :all]))

(deftest basic
  (testing (let [s "AAA"] s (is (= [:ALEPH :ALEPH :ALEPH] (trans/lat-to-heb-vec s)))))
  )

(deftest single-letters
  (testing (let [s "A"] s (is (= [:ALEPH] (trans/lat-to-heb-vec s)))))
  (testing (let [s "B"] s (is (= [:BETH] (trans/lat-to-heb-vec s)))))
  (testing (let [s "G"] s (is (= [:GIMEL] (trans/lat-to-heb-vec s)))))
  (testing (let [s "D"] s (is (= [:DALETH] (trans/lat-to-heb-vec s)))))
  (testing (let [s "H"] s (is (= [:HEH] (trans/lat-to-heb-vec s)))))
  (testing (let [s "V"] s (is (= [:VAV] (trans/lat-to-heb-vec s)))))
  (testing (let [s "Z"] s (is (= [:ZAIN] (trans/lat-to-heb-vec s)))))
  (testing (let [s "CH"] s (is (= [:CHETH] (trans/lat-to-heb-vec s)))))
  (testing (let [s "T"] s (is (= [:TETH] (trans/lat-to-heb-vec s)))))
  (testing (let [s "I"] s (is (= [:YUD] (trans/lat-to-heb-vec s)))))
  (testing (let [s "K"] s (is (= [:KAPH] (trans/lat-to-heb-vec s)))))
  (testing (let [s "L"] s (is (= [:LAMED] (trans/lat-to-heb-vec s)))))
  (testing (let [s "M"] s (is (= [:MEM] (trans/lat-to-heb-vec s)))))
  (testing (let [s "N"] s (is (= [:NUN] (trans/lat-to-heb-vec s)))))
  (testing (let [s "S"] s (is (= [:SAMEKH] (trans/lat-to-heb-vec s)))))
  (testing (let [s "O"] s (is (= [:AYIN] (trans/lat-to-heb-vec s)))))
  (testing (let [s "P"] s (is (= [:PEH] (trans/lat-to-heb-vec s)))))
  (testing (let [s "TZ"] s (is (= [:TZADDI] (trans/lat-to-heb-vec s)))))
  (testing (let [s "Q"] s (is (= [:QOPH] (trans/lat-to-heb-vec s)))))
  (testing (let [s "R"] s (is (= [:RESH] (trans/lat-to-heb-vec s)))))
  (testing (let [s "SH"] s (is (= [:SHIN] (trans/lat-to-heb-vec s)))))
  (testing (let [s "TH"] s (is (= [:TAV] (trans/lat-to-heb-vec s)))))
  )

(deftest empty-text (testing (let [s ""] s (is (= [] (trans/lat-to-heb-vec s))))))

(deftest cheth-and-kaph
  (testing (let [s "CH"] s (is [:CHETH] (trans/lat-to-heb-vec s))))
  (testing (let [s "C"] s (is [:KAPH] (trans/lat-to-heb-vec s))))
  (testing (let [s "CK"] s (is [:KAPH] (trans/lat-to-heb-vec s))))
  (testing (let [s "CC"] s (is [:KAPH] (trans/lat-to-heb-vec s))))
  )

(deftest heh
  (testing (let [s "H"] s (is (= [:HEH] (trans/lat-to-heb-vec s)))))
  (testing (let [s "E"] s (is (= [:HEH] (trans/lat-to-heb-vec s)))))
  (testing (let [s "EE"] s (is (= [:HEH] (trans/lat-to-heb-vec s)))))
  )

(deftest ayin-and-vav
  (testing (let [s "U"] s (is [:VAV] (trans/lat-to-heb-vec s))))
  (testing (let [s "V"] s (is [:VAV] (trans/lat-to-heb-vec s))))
  (testing (let [s "W"] s (is [:VAV] (trans/lat-to-heb-vec s))))
  (testing (let [s "O"] s (is [:AYIN] (trans/lat-to-heb-vec s))))
  (testing (let [s "OO"] s (is [:AYIN] (trans/lat-to-heb-vec s))))
  (testing (let [s "OU"] s (is [:AYIN] (trans/lat-to-heb-vec s))))
  )

(deftest peh
  (testing (let [s "PH"] s (is [:PEH] (trans/lat-to-heb-vec s))))
  )

(deftest qoph
  (testing (let [s "Q"] s (is [:QOPH] (trans/lat-to-heb-vec s))))
  (testing (let [s "QU"] s (is [:QOPH] (trans/lat-to-heb-vec s))))
  )

(deftest shin-samekh-zain
  (testing (let [s "SH"] s (is [:SHIN] (trans/lat-to-heb-vec s))))
  (testing (let [s "SCH"] s (is [:SHIN] (trans/lat-to-heb-vec s))))
  (testing (let [s "S"] s (is [:SAMEKH] (trans/lat-to-heb-vec s))))
  (testing (let [s "Z"] s (is [:ZAIN] (trans/lat-to-heb-vec s))))
  (testing (let [s "SS"] s (is [:ZAIN] (trans/lat-to-heb-vec s))))
  )

(deftest tzaddi-teth-zain-tav
  (testing (let [s "TZ"] s (is [:TZADDI] (trans/lat-to-heb-vec s))))
  (testing (let [s "TX"] s (is [:TZADDI] (trans/lat-to-heb-vec s))))
  (testing (let [s "X"] s (is [:TZADDI] (trans/lat-to-heb-vec s))))
  (testing (let [s "TH"] s (is [:TAV] (trans/lat-to-heb-vec s))))
  (testing (let [s "TS"] s (is [:ZAIN] (trans/lat-to-heb-vec s))))
  (testing (let [s "T"] s (is [:TETH] (trans/lat-to-heb-vec s))))
  )

(deftest final-kaph
  (testing (let [s "KA"] s (is [:KAPH] (trans/lat-to-heb-vec s))))
  (testing (let [s "AK"] s (is [:KAPH_FINAL] (trans/lat-to-heb-vec s))))
  )

(deftest final-mem
  (testing (let [s "MA"] s (is [:MEM] (trans/lat-to-heb-vec s))))
  (testing (let [s "AM"] s (is [:MEM_FINAL] (trans/lat-to-heb-vec s))))
  (testing (let [s "AMA"] s (is [:MEM_FINAL] (trans/lat-to-heb-vec s))))
  )

(deftest final-nun
  (testing (let [s "NA"] s (is [:NUN] (trans/lat-to-heb-vec s))))
  (testing (let [s "AN"] s (is [:NUN_FINAL] (trans/lat-to-heb-vec s))))
  (testing (let [s "ANA"] s (is [:NUN_FINAL] (trans/lat-to-heb-vec s))))
  )

(deftest final-peh
  (testing (let [s "PH"] s (is [:PEH] (trans/lat-to-heb-vec s))))
  (testing (let [s "PA"] s (is [:PEH] (trans/lat-to-heb-vec s))))
  (testing (let [s "PHA"] s (is [:PEH] (trans/lat-to-heb-vec s))))
  (testing (let [s "AP"] s (is [:PEH_FINAL] (trans/lat-to-heb-vec s))))
  (testing (let [s "APH"] s (is [:PEH_FINAL] (trans/lat-to-heb-vec s))))
  (testing (let [s "APA"] s (is [:PEH] (trans/lat-to-heb-vec s))))
  (testing (let [s "APHA"] s (is [:PEH] (trans/lat-to-heb-vec s))))
  )

(deftest final-tzaddi
  (testing (let [s "X"] s (is [:TZADDI] (trans/lat-to-heb-vec s))))
  (testing (let [s "XA"] s (is [:TZADDI] (trans/lat-to-heb-vec s))))
  (testing (let [s "TZA"] s (is [:TZADDI] (trans/lat-to-heb-vec s))))
  (testing (let [s "AX"] s (is [:TZADDI_FINAL] (trans/lat-to-heb-vec s))))
  (testing (let [s "ATZ"] s (is [:TZADDI_FINAL] (trans/lat-to-heb-vec s))))
  (testing (let [s "AXA"] s (is [:TZADDI] (trans/lat-to-heb-vec s))))
  (testing (let [s "ATZA"] s (is [:TZADDI] (trans/lat-to-heb-vec s))))
  )

(deftest words
  (testing (let [s "ARITHMEA"] s (is (= "אריתמא" (trans/lat-to-heb s)))))
  (testing (let [s "HERMETICS"] s (is (= "הרמטיכס" (trans/lat-to-heb s)))))
  (testing (let [s "TRICKS"] s (is (= "טריכס" (trans/lat-to-heb s)))))
  (testing (let [s "ABRAHADABRA"] s (is (= "אבראהאדאברא" (trans/lat-to-heb s)))))
  )
