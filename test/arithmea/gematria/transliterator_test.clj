(ns arithmea.gematria.transliterator-test
  (:require [arithmea.gematria.transliterator :refer :all]
            [clojure.test :refer :all]))

(deftest basic
  (testing (let [s "AAA"] s (is (= [:ALEPH :ALEPH :ALEPH] (lat-to-heb-vec s)))))
  )

(deftest single-letters
  (testing (let [s "A"] s (is (= [:ALEPH] (lat-to-heb-vec s)))))
  (testing (let [s "B"] s (is (= [:BETH] (lat-to-heb-vec s)))))
  (testing (let [s "G"] s (is (= [:GIMEL] (lat-to-heb-vec s)))))
  (testing (let [s "D"] s (is (= [:DALETH] (lat-to-heb-vec s)))))
  (testing (let [s "H"] s (is (= [:HEH] (lat-to-heb-vec s)))))
  (testing (let [s "V"] s (is (= [:VAV] (lat-to-heb-vec s)))))
  (testing (let [s "Z"] s (is (= [:ZAIN] (lat-to-heb-vec s)))))
  (testing (let [s "CH"] s (is (= [:CHETH] (lat-to-heb-vec s)))))
  (testing (let [s "T"] s (is (= [:TETH] (lat-to-heb-vec s)))))
  (testing (let [s "I"] s (is (= [:YUD] (lat-to-heb-vec s)))))
  (testing (let [s "K"] s (is (= [:KAPH] (lat-to-heb-vec s)))))
  (testing (let [s "L"] s (is (= [:LAMED] (lat-to-heb-vec s)))))
  (testing (let [s "M"] s (is (= [:MEM] (lat-to-heb-vec s)))))
  (testing (let [s "N"] s (is (= [:NUN] (lat-to-heb-vec s)))))
  (testing (let [s "S"] s (is (= [:SAMEKH] (lat-to-heb-vec s)))))
  (testing (let [s "O"] s (is (= [:AYIN] (lat-to-heb-vec s)))))
  (testing (let [s "P"] s (is (= [:PEH] (lat-to-heb-vec s)))))
  (testing (let [s "TZ"] s (is (= [:TZADDI] (lat-to-heb-vec s)))))
  (testing (let [s "Q"] s (is (= [:QOPH] (lat-to-heb-vec s)))))
  (testing (let [s "R"] s (is (= [:RESH] (lat-to-heb-vec s)))))
  (testing (let [s "SH"] s (is (= [:SHIN] (lat-to-heb-vec s)))))
  (testing (let [s "TH"] s (is (= [:TAV] (lat-to-heb-vec s)))))
  )

(deftest empty-text (testing (let [s ""] s (is (= [] (lat-to-heb-vec s))))))

(deftest cheth-and-kaph
  (testing (let [s "CH"] s (is [:CHETH] (lat-to-heb-vec s))))
  (testing (let [s "C"] s (is [:KAPH] (lat-to-heb-vec s))))
  (testing (let [s "CK"] s (is [:KAPH] (lat-to-heb-vec s))))
  (testing (let [s "CC"] s (is [:KAPH] (lat-to-heb-vec s))))
  )

(deftest heh
  (testing (let [s "H"] s (is (= [:HEH] (lat-to-heb-vec s)))))
  (testing (let [s "E"] s (is (= [:HEH] (lat-to-heb-vec s)))))
  (testing (let [s "EE"] s (is (= [:HEH] (lat-to-heb-vec s)))))
  )

(deftest ayin-and-vav
  (testing (let [s "U"] s (is [:VAV] (lat-to-heb-vec s))))
  (testing (let [s "V"] s (is [:VAV] (lat-to-heb-vec s))))
  (testing (let [s "W"] s (is [:VAV] (lat-to-heb-vec s))))
  (testing (let [s "O"] s (is [:AYIN] (lat-to-heb-vec s))))
  (testing (let [s "OO"] s (is [:AYIN] (lat-to-heb-vec s))))
  (testing (let [s "OU"] s (is [:AYIN] (lat-to-heb-vec s))))
  )

(deftest peh
  (testing (let [s "PH"] s (is [:PEH] (lat-to-heb-vec s))))
  )

(deftest qoph
  (testing (let [s "Q"] s (is [:QOPH] (lat-to-heb-vec s))))
  (testing (let [s "QU"] s (is [:QOPH] (lat-to-heb-vec s))))
  )

(deftest shin-samekh-zain
  (testing (let [s "SH"] s (is [:SHIN] (lat-to-heb-vec s))))
  (testing (let [s "SCH"] s (is [:SHIN] (lat-to-heb-vec s))))
  (testing (let [s "S"] s (is [:SAMEKH] (lat-to-heb-vec s))))
  (testing (let [s "Z"] s (is [:ZAIN] (lat-to-heb-vec s))))
  (testing (let [s "SS"] s (is [:ZAIN] (lat-to-heb-vec s))))
  )

(deftest tzaddi-teth-zain-tav
  (testing (let [s "TZ"] s (is [:TZADDI] (lat-to-heb-vec s))))
  (testing (let [s "TX"] s (is [:TZADDI] (lat-to-heb-vec s))))
  (testing (let [s "X"] s (is [:TZADDI] (lat-to-heb-vec s))))
  (testing (let [s "TH"] s (is [:TAV] (lat-to-heb-vec s))))
  (testing (let [s "TS"] s (is [:ZAIN] (lat-to-heb-vec s))))
  (testing (let [s "T"] s (is [:TETH] (lat-to-heb-vec s))))
  )

(deftest final-kaph
  (testing (let [s "KA"] s (is [:KAPH] (lat-to-heb-vec s))))
  (testing (let [s "AK"] s (is [:KAPH_FINAL] (lat-to-heb-vec s))))
  )

(deftest final-mem
  (testing (let [s "MA"] s (is [:MEM] (lat-to-heb-vec s))))
  (testing (let [s "AM"] s (is [:MEM_FINAL] (lat-to-heb-vec s))))
  (testing (let [s "AMA"] s (is [:MEM_FINAL] (lat-to-heb-vec s))))
  )

(deftest final-nun
  (testing (let [s "NA"] s (is [:NUN] (lat-to-heb-vec s))))
  (testing (let [s "AN"] s (is [:NUN_FINAL] (lat-to-heb-vec s))))
  (testing (let [s "ANA"] s (is [:NUN_FINAL] (lat-to-heb-vec s))))
  )

(deftest final-peh
  (testing (let [s "PH"] s (is [:PEH] (lat-to-heb-vec s))))
  (testing (let [s "PA"] s (is [:PEH] (lat-to-heb-vec s))))
  (testing (let [s "PHA"] s (is [:PEH] (lat-to-heb-vec s))))
  (testing (let [s "AP"] s (is [:PEH_FINAL] (lat-to-heb-vec s))))
  (testing (let [s "APH"] s (is [:PEH_FINAL] (lat-to-heb-vec s))))
  (testing (let [s "APA"] s (is [:PEH] (lat-to-heb-vec s))))
  (testing (let [s "APHA"] s (is [:PEH] (lat-to-heb-vec s))))
  )

(deftest final-tzaddi
  (testing (let [s "X"] s (is [:TZADDI] (lat-to-heb-vec s))))
  (testing (let [s "XA"] s (is [:TZADDI] (lat-to-heb-vec s))))
  (testing (let [s "TZA"] s (is [:TZADDI] (lat-to-heb-vec s))))
  (testing (let [s "AX"] s (is [:TZADDI_FINAL] (lat-to-heb-vec s))))
  (testing (let [s "ATZ"] s (is [:TZADDI_FINAL] (lat-to-heb-vec s))))
  (testing (let [s "AXA"] s (is [:TZADDI] (lat-to-heb-vec s))))
  (testing (let [s "ATZA"] s (is [:TZADDI] (lat-to-heb-vec s))))
  )

(deftest words
  (testing (let [s "ARITHMEA"] s (is (= "אריתמא" (lat-to-heb s)))))
  (testing (let [s "HERMETICS"] s (is (= "הרמטיכס" (lat-to-heb s)))))
  (testing (let [s "TRICKS"] s (is (= "טריכס" (lat-to-heb s)))))
  (testing (let [s "ABRAHADABRA"] s (is (= "אבראהאדאברא" (lat-to-heb s)))))
  )
