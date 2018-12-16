(ns arithmea.gematria.transliterator
  (:require [arithmea.gematria.alphabet.hebrew :as hebrew]))

(defn- is-first? [last] (or (= nil last) (= \- last)))
(defn- is-last? [next] (or (= nil next) (= \- next)))
(defn- make-final? [last next] (and (is-last? next) (not (is-first? last))))

(defn- final-if-last [letter last next]
  (if (make-final? last next) (hebrew/to-final letter) letter))

(defn- handle-e [last next]
  (case next
    \E [:heh 1]
    (if (is-first? last) [:heh 0] [:MARKER 0])))

(defn- handle-c [last next]
  (case next
    \H [:cheth 1]
    \C [(final-if-last :kaph last next) 1]
    \K [(final-if-last :kaph last next) 1]
    [(final-if-last :kaph last next) 0]))

(defn- handle-ayin-or-vav [last] (if (is-first? last) :ayin :vav))
(defn- handle-o [last next]
  (case next
    \O [:ayin 1]
    \U [:ayin 1]
    [(handle-ayin-or-vav last) 0]))

(defn- handle-p [last next after-next]
  (case next
    \H [(final-if-last :peh next after-next) 1]
    [(final-if-last :peh last next) 0]))

(defn- handle-q [next]
  (case next
    \U [:qoph 1]
    [:qoph 0]))

(defn- handle-s [next after-next]
  (case next
    \C (if (= after-next \H) [:shin 2] [:samekh 0])
    \H [:shin 1]
    \S [:zain 1]
    [:samekh 0]))

(defn- handle-t [next after-next]
  (case next
    \Z [:tzaddi 1]
    \X [:tzaddi 1]
    \H [:tav 1]
    \S [:zain 1]
    [:teth 0]))

(defn hebrew-letter [last current next after-next]
  (case current
    nil [current 0]
    \- [current 0]
    \A [:aleph 0]
    \B [:beth 0]
    \C (handle-c last next)
    \D [:daleth 0]
    \E (handle-e last next)
    \F [(final-if-last :peh last next) 0]
    \G [:gimel 0]
    \H [:heh 0]
    \I [:yud 0]
    \J [:gimel 0]
    \K [(final-if-last :kaph last next) 0]
    \L [:lamed 0]
    \M [(final-if-last :mem last next) 0]
    \N [(final-if-last :nun last next) 0]
    \O (handle-o last next)
    \P (handle-p last next after-next)
    \Q (handle-q next)
    \R [:resh 0]
    \S (handle-s next after-next)
    \T (handle-t next after-next)
    \U [:vav 0]
    \V [:vav 0]
    \W [:vav 0]
    \X [(final-if-last :tzaddi last next) 0]
    \Y [:yud 0]
    \Z [:zain 0]
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
