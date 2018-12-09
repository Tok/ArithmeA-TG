(ns arithmea.gematria.matcher-test
  (:require [arithmea.gematria.matcher :refer :all]
            [clojure.test :refer :all]))

(deftest check-simple-anagrams
  (testing (let [a "Cat" b "Act"] (str a "is" b) (is (anagram? a b))))
  (testing (let [a "Act" b "Cat"] (str a "is" b) (is (anagram? a b))))
  (testing (let [a "Elvis" b "Lives"] (str a "is" b) (is (anagram? a b))))
  (testing (let [a "Lives" b "Elvis"] (str a "is" b) (is (anagram? a b))))
  )

(deftest check-anagrams
  (testing (let [a "Astronomer" b "MOON STARER"] (str a "is" b) (is (anagram? a b))))
  (testing (let [a "Moon starer" b "ASTRONOMER"] (str a "is" b) (is (anagram? a b))))
  (testing (let [a "The Morse Code" b "HERE COME DOTS"] (str a "is" b) (is (anagram? a b))))
  (testing (let [a "Here come dots" b "THE MORSE CODE"] (str a "is" b) (is (anagram? a b))))
  )

(deftest simple-anagrams
  (testing (let [a "Arc" b "CAR"] (str a "finds" b) (is (some #{b} (find-anagrams a)))))
  (testing (let [a "Car" b "ARC"] (str a "finds" b) (is (some #{b} (find-anagrams a)))))
  (testing (let [a "Elvis" b "LIVES"] (str a "finds" b) (is (some #{b} (find-anagrams a)))))
  (testing (let [a "Lives" b "ELVIS"] (str a "finds" b) (is (some #{b} (find-anagrams a)))))
  )

;TODO implement multiword anagrams
; (deftest anagrams
;  (testing (let [a "Astronomer" b "MOON STARER"] (str a "finds" b) (is (contains? (find-anagrams a) b))))
;  (testing (let [a "Moon starer" b "ASTRONOMER"] (str a "finds" b) (is (contains? (find-anagrams a) b))))
;  (testing (let [a "The Morse Code" b "HERE COMES DOTS"] (str a "finds" b) (is (contains? (find-anagrams a) b))))
;  (testing (let [a "Here comes dots" b "THE MORSE CODE"] (str a "finds" b) (is (contains? (find-anagrams a) b))))
;  )
