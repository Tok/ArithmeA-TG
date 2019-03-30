(ns arithmea.wizmetria.wiz-qualifier-test
  (:require [arithmea.wizmetria.wiz-qualifier :refer :all]
            [clojure.test :refer :all]))

(deftest az-symmetry-test
  (testing (is (az-symmetric? "Wizard"))))
