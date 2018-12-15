(ns arithmea.gematria.transliterator-test
  (:require [arithmea.gematria.transliterator :refer :all]
            [clojure.test :refer :all]))

(deftest basic-test
  (testing (let [s "AAA"] s (is (= [:ALEPH :ALEPH :ALEPH] (lat->heb s))))))

(deftest single-letters-test
  (testing (let [s "A"] s (is (= [:ALEPH] (lat->heb s)))))
  (testing (let [s "B"] s (is (= [:BETH] (lat->heb s)))))
  (testing (let [s "G"] s (is (= [:GIMEL] (lat->heb s)))))
  (testing (let [s "D"] s (is (= [:DALETH] (lat->heb s)))))
  (testing (let [s "H"] s (is (= [:HEH] (lat->heb s)))))
  (testing (let [s "V"] s (is (= [:VAV] (lat->heb s)))))
  (testing (let [s "Z"] s (is (= [:ZAIN] (lat->heb s)))))
  (testing (let [s "CH"] s (is (= [:CHETH] (lat->heb s)))))
  (testing (let [s "T"] s (is (= [:TETH] (lat->heb s)))))
  (testing (let [s "I"] s (is (= [:YUD] (lat->heb s)))))
  (testing (let [s "K"] s (is (= [:KAPH] (lat->heb s)))))
  (testing (let [s "L"] s (is (= [:LAMED] (lat->heb s)))))
  (testing (let [s "M"] s (is (= [:MEM] (lat->heb s)))))
  (testing (let [s "N"] s (is (= [:NUN] (lat->heb s)))))
  (testing (let [s "S"] s (is (= [:SAMEKH] (lat->heb s)))))
  (testing (let [s "O"] s (is (= [:AYIN] (lat->heb s)))))
  (testing (let [s "P"] s (is (= [:PEH] (lat->heb s)))))
  (testing (let [s "TZ"] s (is (= [:TZADDI] (lat->heb s)))))
  (testing (let [s "Q"] s (is (= [:QOPH] (lat->heb s)))))
  (testing (let [s "R"] s (is (= [:RESH] (lat->heb s)))))
  (testing (let [s "SH"] s (is (= [:SHIN] (lat->heb s)))))
  (testing (let [s "TH"] s (is (= [:TAV] (lat->heb s))))))

(deftest empty-text-test (testing (let [s ""] s (is (= [] (lat->heb s))))))

(deftest cheth-and-kaph-test
  (testing (let [s "CH"] s (is [:CHETH] (lat->heb s))))
  (testing (let [s "C"] s (is [:KAPH] (lat->heb s))))
  (testing (let [s "CK"] s (is [:KAPH] (lat->heb s))))
  (testing (let [s "CC"] s (is [:KAPH] (lat->heb s)))))

(deftest heh-test
  (testing (let [s "H"] s (is (= [:HEH] (lat->heb s)))))
  (testing (let [s "E"] s (is (= [:HEH] (lat->heb s)))))
  (testing (let [s "EE"] s (is (= [:HEH] (lat->heb s))))))

(deftest ayin-and-vav-test
  (testing (let [s "U"] s (is [:VAV] (lat->heb s))))
  (testing (let [s "V"] s (is [:VAV] (lat->heb s))))
  (testing (let [s "W"] s (is [:VAV] (lat->heb s))))
  (testing (let [s "O"] s (is [:AYIN] (lat->heb s))))
  (testing (let [s "OO"] s (is [:AYIN] (lat->heb s))))
  (testing (let [s "OU"] s (is [:AYIN] (lat->heb s)))))

(deftest peh-test
  (testing (let [s "PH"] s (is [:PEH] (lat->heb s)))))

(deftest qoph-test
  (testing (let [s "Q"] s (is [:QOPH] (lat->heb s))))
  (testing (let [s "QU"] s (is [:QOPH] (lat->heb s)))))

(deftest shin-samekh-zain-test
  (testing (let [s "SH"] s (is [:SHIN] (lat->heb s))))
  (testing (let [s "SCH"] s (is [:SHIN] (lat->heb s))))
  (testing (let [s "S"] s (is [:SAMEKH] (lat->heb s))))
  (testing (let [s "Z"] s (is [:ZAIN] (lat->heb s))))
  (testing (let [s "SS"] s (is [:ZAIN] (lat->heb s)))))

(deftest tzaddi-teth-zain-tav-test
  (testing (let [s "TZ"] s (is [:TZADDI] (lat->heb s))))
  (testing (let [s "TX"] s (is [:TZADDI] (lat->heb s))))
  (testing (let [s "X"] s (is [:TZADDI] (lat->heb s))))
  (testing (let [s "TH"] s (is [:TAV] (lat->heb s))))
  (testing (let [s "TS"] s (is [:ZAIN] (lat->heb s))))
  (testing (let [s "T"] s (is [:TETH] (lat->heb s)))))

(deftest final-kaph-test
  (testing (let [s "KA"] s (is [:KAPH] (lat->heb s))))
  (testing (let [s "AK"] s (is [:KAPH_FINAL] (lat->heb s)))))

(deftest final-mem-test
  (testing (let [s "MA"] s (is [:MEM] (lat->heb s))))
  (testing (let [s "AM"] s (is [:MEM_FINAL] (lat->heb s))))
  (testing (let [s "AMA"] s (is [:MEM_FINAL] (lat->heb s)))))

(deftest final-nun-test
  (testing (let [s "NA"] s (is [:NUN] (lat->heb s))))
  (testing (let [s "AN"] s (is [:NUN_FINAL] (lat->heb s))))
  (testing (let [s "ANA"] s (is [:NUN_FINAL] (lat->heb s)))))

(deftest final-peh-test
  (testing (let [s "PH"] s (is [:PEH] (lat->heb s))))
  (testing (let [s "PA"] s (is [:PEH] (lat->heb s))))
  (testing (let [s "PHA"] s (is [:PEH] (lat->heb s))))
  (testing (let [s "AP"] s (is [:PEH_FINAL] (lat->heb s))))
  (testing (let [s "APH"] s (is [:PEH_FINAL] (lat->heb s))))
  (testing (let [s "APA"] s (is [:PEH] (lat->heb s))))
  (testing (let [s "APHA"] s (is [:PEH] (lat->heb s)))))

(deftest final-tzaddi-test
  (testing (let [s "X"] s (is [:TZADDI] (lat->heb s))))
  (testing (let [s "XA"] s (is [:TZADDI] (lat->heb s))))
  (testing (let [s "TZA"] s (is [:TZADDI] (lat->heb s))))
  (testing (let [s "AX"] s (is [:TZADDI_FINAL] (lat->heb s))))
  (testing (let [s "ATZ"] s (is [:TZADDI_FINAL] (lat->heb s))))
  (testing (let [s "AXA"] s (is [:TZADDI] (lat->heb s))))
  (testing (let [s "ATZA"] s (is [:TZADDI] (lat->heb s)))))

(deftest words-test
  (testing (let [s "ARITHMEA"] s (is (= "אריתמא" (lat->heb-str s)))))
  (testing (let [s "HERMETICS"] s (is (= "הרמטיכס" (lat->heb-str s)))))
  (testing (let [s "TRICKS"] s (is (= "טריכס" (lat->heb-str s)))))
  (testing (let [s "ABRAHADABRA"] s (is (= "אבראהאדאברא" (lat->heb-str s))))))
