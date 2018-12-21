(ns arithmea.gematria.calculator-test
  (:require [arithmea.gematria.calculator :refer :all]
            [clojure.test :refer :all]))

(defn- calc [method s] (calculate method s))
(defn- test-calc [expected method s] (is (= expected (calc method s))))

(deftest chaldean-test
  (let [method :chal]
    (testing (let [s "ABC"] s (test-calc 6 method s)))
    (testing (let [s "ARITHMEA"] s (test-calc 23 method s)))
    (testing (let [s "DER-SCHLUESSEL"] s (test-calc 50 method s)))))

(deftest pythagorean-test
  (let [method :pyth]
    (testing (let [s "ABC"] s (test-calc 6 method s)))
    (testing (let [s "ARITHMEA"] s (test-calc 39 method s)))
    (testing (let [s "DER-SCHLUESSEL"] s (test-calc 51 method s)))))

(deftest simple-test
  (let [method :ia]
    (testing (let [s "ABC"] s (test-calc 6 method s)))
    (testing (let [s "ARITHMEA"] s (test-calc 75 method s)))
    (testing (let [s "DER-SCHLUESSEL"] s (test-calc 150 method s)))))

(deftest naeq-test
  (let [method :naeq]
    (testing (let [s "ABC"] s (test-calc 34 method s)))
    (testing (let [s "ARITHMEA"] s (test-calc 111 method s)))
    (testing (let [s "DER-SCHLUESSEL"] s (test-calc 146 method s)))))

(deftest tq-test
  (let [method :tq]
    (testing (let [s "ABC"] s (test-calc 27 method s)))
    (testing (let [s "ARITHMEA"] s (test-calc 96 method s)))
    (testing (let [s "DER-SCHLUESSEL"] s (test-calc 153 method s)))))

(deftest german-test
  (let [method :ger]
    (testing (let [s "DER-SCHLUESSEL"] s (test-calc 55 method s)))))

(deftest azzure-lidde-woman-cipher-test
  (let [method :eq]
    (testing (let [s "AADHAAR"] s (test-calc 44 method s)))
    (testing (let [s "VOODOO"] s (test-calc 44 method s)))
    (testing (let [s "BLOOD"] s (test-calc 222 method s)))
    (testing (let [s "WILL"] s (test-calc 507 method s)))
    (testing (let [s "ABRAXAS"] s (test-calc 638 method s)))
    (testing (let [s "BABALON"] s (test-calc 461 method s)))))

(deftest ccru-aq-test
  (let [method :aq]
    "Testcases from the CCRU writings."
    (testing (let [s "CHRONO"] s (test-calc 127 method s)))
    (testing (let [s "CHRONOLOGY"] s (test-calc 222 method s)))
    (testing (let [s "CHRONOS"] s (test-calc 155 method s)))))

(deftest full-test
  (testing (let [s "DERSCHLUESSEL"] s (test-calc 577 :full s))))

(deftest ordinal-test
  (testing (let [s "DERSCHLUESSEL"] s (test-calc 82 :ordinal s))))

(deftest katan-test
  (testing (let [s "DERSCHLUESSEL"] s (test-calc 28 :katan s))))

(defn- test-lat-ordinal [i]
  (let [expected (lat-ordinal-value i)
        actual (calc :ia (str (char i)))]
    (testing (is (= expected actual)))))
(deftest simple-ordinal-test (doseq [i (range 65 90)] (test-lat-ordinal i)))
