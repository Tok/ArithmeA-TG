(ns arithmea.wizmetria.axis-qualifier-test
  (:require [arithmea.wizmetria.axis-qualifier :refer :all]
            [clojure.test :refer :all]))

(defn- test-id->name [id name] (-> id id->name (= name) is testing))
(deftest even-id->name-test
  (test-id->name 0 "A-N")
  (test-id->name 2 "B-O")
  (test-id->name 4 "C-P")
  (test-id->name 6 "D-Q")
  (test-id->name 14 "H-U")
  (test-id->name 24 "M-Z")
  (test-id->name 26 (id->name 0)))
(deftest odd-id->name-test
  (test-id->name 1 "AB-NO")
  (test-id->name 3 "BC-OP")
  (test-id->name 5 "CD-PQ")
  (test-id->name 25 "MN-ZA")
  (test-id->name 27 (id->name 1)))

(defn- test-sum->id [sum id] (-> sum sum->id (= id) is testing))
(deftest sum->id-test
  (test-sum->id 28 0)
  (test-sum->id 3 1)
  (test-sum->id 29 1)
  (test-sum->id 4 2)
  (test-sum->id 30 2)
  (test-sum->id 5 3)
  (test-sum->id 31 3)
  (test-sum->id 6 4)
  (test-sum->id 7 5)
  (test-sum->id 16 14)
  (test-sum->id 42 14))

(defn- test-ord->id [ord id] (-> ord ord->id (= id) is testing))
(deftest ordinal->id-test
  (test-ord->id 1 0)
  (test-ord->id 2 2)
  (test-ord->id 3 4)
  (test-ord->id 4 6)
  (test-ord->id 5 8)
  (test-ord->id 13 24)
  (test-ord->id 14 0))

(deftest on-axis-test
  (-> (on-axis? \A 28) is testing)
  (-> (on-axis? \N 28) is testing)
  (-> (on-axis? \H 16) is testing)
  (-> (on-axis? \U 16) is testing)
  (-> (on-axis? \H 42) is testing)
  (-> (on-axis? \U 42) is testing))

(deftest az-symmetry-test (-> "Wizard" az-symmetric? is testing))

(deftest symmetry-az-pair-test
  (let [az-sum 27]
    (-> (symmetric? "AZ" az-sum) is testing)
    (-> (symmetric? "ZA" az-sum) is testing)
    (-> (symmetric? "BY" az-sum) is testing)
    (-> (symmetric? "YB" az-sum) is testing)
    (-> (symmetric? "CX" az-sum) is testing)
    (-> (symmetric? "XC" az-sum) is testing)
    (-> (symmetric? "NM" az-sum) is testing)
    (-> (symmetric? "MN" az-sum) is testing)))

(deftest symmetry-az-pair-test
  (let [az-sum 27]
    (-> (not (symmetric? "A" az-sum)) is testing)
    (-> (not (symmetric? "B" az-sum)) is testing)
    (-> (not (symmetric? "C" az-sum)) is testing)))

(deftest symmetry-test
  (let [sum 28]
    (-> (symmetric? "XAD" sum) is testing)
    (-> (symmetric? "DAX" sum) is testing)
    (-> (not (symmetric? "XADA" sum)) is testing)           ;TODO allow?
    (-> (not (symmetric? "AXAD" sum)) is testing)
    (-> (symmetric? "AXDA" sum) is testing)
    (-> (symmetric? "ADXA" sum) is testing)
    (-> (not (symmetric? "AADXA" sum)) is testing)          ;TODO allow?
    (-> (not (symmetric? "ADXAA" sum)) is testing)
    (-> (not (symmetric? "XDA" sum)) is testing)
    (-> (not (symmetric? "DXA" sum)) is testing)
    (-> (not (symmetric? "ADX" sum)) is testing)
    (-> (not (symmetric? "AXD" sum)) is testing)))
