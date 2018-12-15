(ns arithmea.gematria.transliterator
  (:require [arithmea.gematria.alphabet.hebrew :as hebrew]
            [clojure.string :as str]))

(defn- is-first? [last] (or (= nil last) (= \- last)))
(defn- is-last? [next] (or (= nil next) (= \- next)))
(defn- make-final? [last next] (and (is-last? next) (not (is-first? last))))

(defn- final-if-last [letter last next]
  (if (make-final? last next) (hebrew/to-final letter) letter))

(defn- handle-e [last next]
  (case next
    \E [:HEH 1]
    (if (is-first? last) [:HEH 0] [:MARKER 0])))

(defn- handle-c [last next]
  (case next
    \H [:CHETH 1]
    \C [(final-if-last :KAPH last next) 1]
    \K [(final-if-last :KAPH last next) 1]
    [(final-if-last :KAPH last next) 0]))

(defn- handle-ayin-or-vav [last] (if (is-first? last) :AYIN :VAV))
(defn- handle-o [last next]
  (case next
    \O [:AYIN 1]
    \U [:AYIN 1]
    [(handle-ayin-or-vav last) 0]))

(defn- handle-p [last next after-next]
  (case next
    \H [(final-if-last :PEH next after-next) 1]
    [(final-if-last :PEH last next) 0]))

(defn- handle-q [next]
  (case next
    \U [:QOPH 1]
    [:QOPH 0]))

(defn- handle-s [next after-next]
  (case next
    \C (if (= after-next \H) [:SHIN 2] [:SAMEKH 0])
    \H [:SHIN 1]
    \S [:ZAIN 1]
    [:SAMEKH 0]))

(defn- handle-t [next after-next]
  (case next
    \Z [:TZADDI 1]
    \X [:TZADDI 1]
    \H [:TAV 1]
    \S [:ZAIN 1]
    [:TETH 0]))

(defn hebrew-letter [last current next after-next]
  (case current
    nil [current 0]
    \- [current 0]
    \A [:ALEPH 0]
    \B [:BETH 0]
    \C (handle-c last next)
    \D [:DALETH 0]
    \E (handle-e last next)
    \F [(final-if-last :PEH last next) 0]
    \G [:GIMEL 0]
    \H [:HEH 0]
    \I [:YUD 0]
    \J [:GIMEL 0]
    \K [(final-if-last :KAPH last next) 0]
    \L [:LAMED 0]
    \M [(final-if-last :MEM last next) 0]
    \N [(final-if-last :NUN last next) 0]
    \O (handle-o last next)
    \P (handle-p last next after-next)
    \Q (handle-q next)
    \R [:RESH 0]
    \S (handle-s next after-next)
    \T (handle-t next after-next)
    \U [:VAV 0]
    \V [:VAV 0]
    \W [:VAV 0]
    \X [(final-if-last :TZADDI last next) 0]
    \Y [:YUD 0]
    \Z [:ZAIN 0]
    :else [current 0])
  )

(defn evaluate [parts accu]
  (if (= nil (get parts 1))
    (vec accu)
    (let [last (get parts 0)
          current (get parts 1)
          next (get parts 2)
          after-next (get parts 3)
          result (hebrew-letter last current next after-next)
          translit (get result 0)
          drop-count (get result 1)]
      (recur (vec (rest (drop drop-count parts))) (conj accu translit)))))

(defn lat->heb [lat]
  (let [parts (vec (concat [nil] lat [nil] [nil] [nil]))]
    (filter #(not (= :MARKER %)) (evaluate parts []))))

(defn lat->heb-str [lat]
  (let [vec (lat->heb lat)]
    (apply str (map #(hebrew/letter-table %) vec))))
