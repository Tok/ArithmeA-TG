(ns arithmea.wizmetria.axis-qualifier
  (:require [arithmea.dict :as dict]))

(defn- ordinal "A=1, B=2, C=3 .. Z=26" [c] (- (int c) (int \@)))
(defn ord->id [ord] (-> ord (- 1) (* 2) (mod 26)))
(defn sum->id [sum] (-> sum (- 2) (mod 26)))
(defn on-axis? [c sum] (= (sum->id sum) (ord->id (ordinal c))))
(defn id->name [id]
  (let [v (-> id (mod 26) (/ 2) (+ (int \A)))
        c0 (char v) c1 (char (+ v 1)) c13 (char (+ v 13)) c14 (char (+ v 14))]
    (->> (if (even? id) (str c0 \- c13) (str c0 c1 \- c13 c14))
         (replace {\[ \A \@ \Z}) (apply str))))

(defn- accept? [f l s sum] (if (= f l) (on-axis? (first s) sum) (= (+ f l) sum)))
(defn- symm? [s sum]
  (case (count s)
    0 true
    1 (on-axis? (first s) sum)
    (let [upper (clojure.string/upper-case s)
          f (-> upper first ordinal)
          l (-> upper last ordinal)
          next (apply str (-> upper (subs 1) drop-last))]
      (and (accept? f l upper sum) (recur next sum)))))
(defn symmetric? [s sum] (not (->> [sum (+ sum 26) (+ sum 52)] (filter #(symm? s %)) empty?)))
(defn az-symmetric? [s] (symm? s 27))                       ;27 is the number of letters in the alphabet + 1

(defn- pad [s n] (format (str "%" n "s") s))
(defn- filter-sym [id words] (filter #(symmetric? % (+ id 2)) words))
(defn- find-sym [id words] (vec [id (->> words (filter-sym id) (sort-by count) vec)]))
(defn- filter-results [results] (filter #(not-empty (second %)) results))

(defn process []
  (let [words (dict/longer-words 4)]
    (println "Checking" (count words) "words..")
    (doseq [id--sym (->> (range 0 26) (map #(find-sym % words)) filter-results)]
      (let [id (first id--sym) sym (second id--sym)]
        (println (count sym) "on axis" (pad (id->name id) 5) sym)))))

(defn -main "Wizmetria" [& _] (process))

(defn parallel? [word] false)                               ;TODO
(defn parallels [word] 0)                                   ;TODO
