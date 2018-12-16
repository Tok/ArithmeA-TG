(ns arithmea.gematria.alphabet.hebrew-test
  (:require [arithmea.gematria.alphabet.hebrew :refer :all]
            [clojure.test :refer :all]))

(defn- test-same [letter]
  (testing (is (= letter (to-final letter)))))

(deftest non-final-test
  (test-same :aleph)
  (test-same :beth)
  (test-same :gimel))

(defn- do-test [expect letter]
  (testing (is (= expect (to-final letter)))))

(deftest final-test
  (do-test :kaph-final :kaph)
  (do-test :mem-final :mem)
  (do-test :nun-final :nun)
  (do-test :peh-final :peh)
  (do-test :tzaddi-final :tzaddi))

(deftest number-value-test
  (let [limit 100]
    (doseq [i (range 0 limit)]
      (let [method (rand-nth (vec hebrew-method))
            letter (rand-nth (vec letters))
            name (str i " latin number-value " method " " letter)]
        (testing name (is (int? (number-value method letter))))))))
