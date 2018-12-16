(ns arithmea.gematria.transliterator-test
  (:require [arithmea.gematria.transliterator :refer :all]
            [clojure.test :refer :all]))

(deftest basic-test
  (testing (let [s "AAA"] s (is (= [:aleph :aleph :aleph] (lat->heb s))))))

(deftest single-letters-test
  (testing (let [s "A"] s (is (= [:aleph] (lat->heb s)))))
  (testing (let [s "B"] s (is (= [:beth] (lat->heb s)))))
  (testing (let [s "G"] s (is (= [:gimel] (lat->heb s)))))
  (testing (let [s "D"] s (is (= [:daleth] (lat->heb s)))))
  (testing (let [s "H"] s (is (= [:heh] (lat->heb s)))))
  (testing (let [s "V"] s (is (= [:vav] (lat->heb s)))))
  (testing (let [s "Z"] s (is (= [:zain] (lat->heb s)))))
  (testing (let [s "CH"] s (is (= [:cheth] (lat->heb s)))))
  (testing (let [s "T"] s (is (= [:teth] (lat->heb s)))))
  (testing (let [s "I"] s (is (= [:yud] (lat->heb s)))))
  (testing (let [s "K"] s (is (= [:kaph] (lat->heb s)))))
  (testing (let [s "L"] s (is (= [:lamed] (lat->heb s)))))
  (testing (let [s "M"] s (is (= [:mem] (lat->heb s)))))
  (testing (let [s "N"] s (is (= [:nun] (lat->heb s)))))
  (testing (let [s "S"] s (is (= [:samekh] (lat->heb s)))))
  (testing (let [s "O"] s (is (= [:ayin] (lat->heb s)))))
  (testing (let [s "P"] s (is (= [:peh] (lat->heb s)))))
  (testing (let [s "TZ"] s (is (= [:tzaddi] (lat->heb s)))))
  (testing (let [s "Q"] s (is (= [:qoph] (lat->heb s)))))
  (testing (let [s "R"] s (is (= [:resh] (lat->heb s)))))
  (testing (let [s "SH"] s (is (= [:shin] (lat->heb s)))))
  (testing (let [s "TH"] s (is (= [:tav] (lat->heb s))))))

(deftest empty-text-test (testing (let [s ""] s (is (= [] (lat->heb s))))))

(deftest cheth-and-kaph-test
  (testing (let [s "CH"] s (is [:cheth] (lat->heb s))))
  (testing (let [s "C"] s (is [:kaph] (lat->heb s))))
  (testing (let [s "CK"] s (is [:kaph] (lat->heb s))))
  (testing (let [s "CC"] s (is [:kaph] (lat->heb s)))))

(deftest heh-test
  (testing (let [s "H"] s (is (= [:heh] (lat->heb s)))))
  (testing (let [s "E"] s (is (= [:heh] (lat->heb s)))))
  (testing (let [s "EE"] s (is (= [:heh] (lat->heb s))))))

(deftest ayin-and-vav-test
  (testing (let [s "U"] s (is [:vav] (lat->heb s))))
  (testing (let [s "V"] s (is [:vav] (lat->heb s))))
  (testing (let [s "W"] s (is [:vav] (lat->heb s))))
  (testing (let [s "O"] s (is [:ayin] (lat->heb s))))
  (testing (let [s "OO"] s (is [:ayin] (lat->heb s))))
  (testing (let [s "OU"] s (is [:ayin] (lat->heb s)))))

(deftest peh-test
  (testing (let [s "PH"] s (is [:peh] (lat->heb s)))))

(deftest qoph-test
  (testing (let [s "Q"] s (is [:qoph] (lat->heb s))))
  (testing (let [s "QU"] s (is [:qoph] (lat->heb s)))))

(deftest shin-samekh-zain-test
  (testing (let [s "SH"] s (is [:shin] (lat->heb s))))
  (testing (let [s "SCH"] s (is [:shin] (lat->heb s))))
  (testing (let [s "S"] s (is [:samekh] (lat->heb s))))
  (testing (let [s "Z"] s (is [:zain] (lat->heb s))))
  (testing (let [s "SS"] s (is [:zain] (lat->heb s)))))

(deftest tzaddi-teth-zain-tav-test
  (testing (let [s "TZ"] s (is [:tzaddi] (lat->heb s))))
  (testing (let [s "TX"] s (is [:tzaddi] (lat->heb s))))
  (testing (let [s "X"] s (is [:tzaddi] (lat->heb s))))
  (testing (let [s "TH"] s (is [:tav] (lat->heb s))))
  (testing (let [s "TS"] s (is [:zain] (lat->heb s))))
  (testing (let [s "T"] s (is [:teth] (lat->heb s)))))

(deftest final-kaph-test
  (testing (let [s "KA"] s (is [:kaph] (lat->heb s))))
  (testing (let [s "AK"] s (is [:kaph-final] (lat->heb s)))))

(deftest final-mem-test
  (testing (let [s "MA"] s (is [:mem] (lat->heb s))))
  (testing (let [s "AM"] s (is [:mem-final] (lat->heb s))))
  (testing (let [s "AMA"] s (is [:mem-final] (lat->heb s)))))

(deftest final-nun-test
  (testing (let [s "NA"] s (is [:nun] (lat->heb s))))
  (testing (let [s "AN"] s (is [:nun-final] (lat->heb s))))
  (testing (let [s "ANA"] s (is [:nun-final] (lat->heb s)))))

(deftest final-peh-test
  (testing (let [s "PH"] s (is [:peh] (lat->heb s))))
  (testing (let [s "PA"] s (is [:peh] (lat->heb s))))
  (testing (let [s "PHA"] s (is [:peh] (lat->heb s))))
  (testing (let [s "AP"] s (is [:peh-final] (lat->heb s))))
  (testing (let [s "APH"] s (is [:peh-final] (lat->heb s))))
  (testing (let [s "APA"] s (is [:peh] (lat->heb s))))
  (testing (let [s "APHA"] s (is [:peh] (lat->heb s)))))

(deftest final-tzaddi-test
  (testing (let [s "X"] s (is [:tzaddi] (lat->heb s))))
  (testing (let [s "XA"] s (is [:tzaddi] (lat->heb s))))
  (testing (let [s "TZA"] s (is [:tzaddi] (lat->heb s))))
  (testing (let [s "AX"] s (is [:tzaddi-final] (lat->heb s))))
  (testing (let [s "ATZ"] s (is [:tzaddi-final] (lat->heb s))))
  (testing (let [s "AXA"] s (is [:tzaddi] (lat->heb s))))
  (testing (let [s "ATZA"] s (is [:tzaddi] (lat->heb s)))))

(deftest words-test
  (testing (let [s "ARITHMEA"] s (is (= "אריתמא" (lat->heb-str s)))))
  (testing (let [s "HERMETICS"] s (is (= "הרמטיכס" (lat->heb-str s)))))
  (testing (let [s "TRICKS"] s (is (= "טריכס" (lat->heb-str s)))))
  (testing (let [s "ABRAHADABRA"] s (is (= "אבראהאדאברא" (lat->heb-str s))))))
