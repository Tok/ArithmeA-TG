(ns arithmea.wizmetria.wiz-qualifier
  (:require [arithmea.dict :as dict]
            [clojure.string :as str]))

(defn- ordinal [c] (- (int (char c)) 64))                   ;64 is the ASCII value of capital A - 1
(defn az-symmetric? [s]
  (if (<= (count s) 1)
    true
    (let [upper (str/upper-case s)
          f (-> upper first ordinal)
          l (-> upper last ordinal)
          next (apply str (-> upper (subs 1) drop-last))]
      (and (= (+ f l) 27) (recur next)))))                  ;27 is the number of letters in the alphabet + 1

(defn- long-word? [s] (> (count s) 3))
(defn- even-word? [s] (even? (count s)))
(defn- long-and-even? [s] (and (long-word? s) (even-word? s)))
(defn -main "Wizmetria" [& _]
  (let [pre-selection (filter long-and-even? dict/words)]
    (println "Checking" (count pre-selection) "preselected words for A-Z axis symmetry.")
    (let [symmetric (->> pre-selection (filter az-symmetric?) (sort-by count))]
      (println "Found" (count symmetric) "results:")
      (doseq [s symmetric] (println s)))))

(defn parallel? [word] false)                               ;TODO
(defn parallels [word] 0)                                   ;TODO
