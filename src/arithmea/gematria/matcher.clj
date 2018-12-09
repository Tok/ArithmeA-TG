(ns arithmea.gematria.matcher
  (:require [arithmea.dict :as dict]
            [arithmea.util :as util]
            [clojure.string :as str]
            [arithmea.gematria.calculator :as gem]))

(defn find-matches [method number] (get (get dict/dict method) number))

(defn- compare-groups [word]
  (let [no-blank (str/replace word #" " "")]
    (frequencies no-blank)))

(defn anagram? [word compare]
  (let [word-group (compare-groups (util/clean-up word))
        compare-group (compare-groups (util/clean-up compare))]
    (and (not (= word compare)) (= word-group compare-group))))

(defn find-anagrams [word]
  (let [clean (util/clean-up word)
        simple-eq (gem/calculate :ia clean)
        simple-eq-matches (find-matches :ia simple-eq)]
    (vec (filter #(anagram? % clean) simple-eq-matches))))
