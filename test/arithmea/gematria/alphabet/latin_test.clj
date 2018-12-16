(ns arithmea.gematria.alphabet.latin-test
  (:require [arithmea.gematria.alphabet.latin :refer :all]
            [clojure.test :refer :all]))

(deftest number-value-test
  (let [upper-case-chars (map char (range 65 91))]
    (doseq [c upper-case-chars]
      (let [m (rand-nth (vec latin-method))
            name (str "latin number-value " m " " c)]
        (testing name (is (int? (number-value m c))))))))
