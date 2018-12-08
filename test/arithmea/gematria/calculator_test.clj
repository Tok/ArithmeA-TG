(ns arithmea.gematria.calculator-test
  (:require [clojure.test :refer :all]
            [arithmea.gematria.calculator :as calc]))

(defn- calc [method s] (calc/calculate method s))
(defn- test-calc [expected method s] (is (= expected (calc method s))))

(deftest chaldean-test
  (let [method :chal]
    (testing (let [s "ABC"] s (test-calc 6 method s)))
    (testing (let [s "ARITHMEA"] s (test-calc 23 method s)))
    (testing (let [s "DER-SCHLUESSEL"] s (test-calc 50 method s)))
    ))

(deftest pythagorean
  (let [method :pyth]
    (testing (let [s "ABC"] s (test-calc 6 method s)))
    (testing (let [s "ARITHMEA"] s (test-calc 39 method s)))
    (testing (let [s "DER-SCHLUESSEL"] s (test-calc 51 method s)))
    ))

(deftest simple
  (let [method :ia]
    (testing (let [s "ABC"] s (test-calc 6 method s)))
    (testing (let [s "ARITHMEA"] s (test-calc 75 method s)))
    (testing (let [s "DER-SCHLUESSEL"] s (test-calc 150 method s)))
    ))

(deftest naeq
  (let [method :naeq]
    (testing (let [s "ABC"] s (test-calc 34 method s)))
    (testing (let [s "ARITHMEA"] s (test-calc 111 method s)))
    (testing (let [s "DER-SCHLUESSEL"] s (test-calc 146 method s)))
    ))

(deftest tq
  (let [method :tq]
    (testing (let [s "ABC"] s (test-calc 27 method s)))
    (testing (let [s "ARITHMEA"] s (test-calc 96 method s)))
    (testing (let [s "DER-SCHLUESSEL"] s (test-calc 153 method s)))
    ))

(deftest german
  (let [method :ger]
    (testing (let [s "DER-SCHLUESSEL"] s (test-calc 55 method s)))
    ))

(deftest azzure-lidde-woman-cipher
  (let [method :eq]
    (testing (let [s "AADHAAR"] s (test-calc 44 method s)))
    (testing (let [s "VOODOO"] s (test-calc 44 method s)))
    (testing (let [s "BLOOD"] s (test-calc 222 method s)))
    (testing (let [s "WILL"] s (test-calc 507 method s)))
    (testing (let [s "ABRAXAS"] s (test-calc 638 method s)))
    (testing (let [s "BABALON"] s (test-calc 461 method s)))
    ))
