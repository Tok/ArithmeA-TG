(ns arithmea.gematria.transliterator
  (:require [clojure.string :as str]
            [arithmea.gematria.alphabet.hebrew :as hebrew]))

(defn final-or-first [letter next]
  (case next nil :final :else letter))

(defn- is-first? [last] (or (= nil last) (= \- last)))
(defn- is-last? [next] (or (= nil next) (= \- next)))
(defn- make-final? [last next] (and (is-last? next) (not (is-first? last))))

(defn- final-if-last [letter last next]
  (if (make-final? last next) (hebrew/to-final letter) letter))

(defn- handle-e [last next]
  (cond
    (= \E next) [:HEH 1]
    :else (if (is-first? last) [:HEH 0] [:MARKER 0])))

(defn- handle-c [last next]
  (cond
    (= \H next) [:CHETH 1]
    (= \C next) [(final-if-last :KAPH last next) 1]
    (= \K next) [(final-if-last :KAPH last next) 1]
    :else [(final-if-last :KAPH last next) 0]
    ))

(defn- handle-ayin-or-vav [last] (if (is-first? last) :AYIN :VAV))
(defn- handle-o [last next]
  (cond
    (= \O next) [:AYIN 1]
    (= \U next) [:AYIN 1]
    :else [(handle-ayin-or-vav last) 0]))

(defn- handle-p [last next after-next]
  (cond
    (= \H next) [(final-if-last :PEH next after-next) 1]
    :else [(final-if-last :PEH last next) 0]
    ))

(defn- handle-q [next]
  (cond
    (= \U next) [:QOPH 1]
    :else [:QOPH 0]))

(defn- handle-s [next after-next]
  (cond
    (= \C next) (if (= after-next \H) [:SHIN 2] [:SAMEKH 0])
    (= \H next) [:SHIN 1]
    (= \S next) [:ZAIN 1]
    :else [:SAMEKH 0]))

(defn- handle-t [next after-next]
  (cond
    (= \Z next) [:TZADDI 1]
    (= \X next) [:TZADDI 1]
    (= \H next) [:TAV 1]
    (= \S next) [:ZAIN 1]
    :else [:TETH 0]))

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
      ;(println translit " -- " drop-count)
      (recur (vec (rest (drop drop-count parts))) (conj accu translit)))))

(defn lat-to-heb-vec [lat]
  (let [parts (vec (concat [nil] lat [nil] [nil] [nil]))]
    (filter #(not (= :MARKER %)) (evaluate parts []))))

(defn lat-to-heb [lat]
  (let [vec (lat-to-heb-vec lat)]
    (apply str (map #(hebrew/letter-table %) vec))))
