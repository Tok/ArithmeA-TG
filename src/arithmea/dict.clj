(ns arithmea.dict
  (:require [arithmea.gematria.calculator :as gem]
            [arithmea.config :as config]
            [arithmea.util :as util]
            [clojure.string :as str]
            [clojure.tools.logging :as log]))

(defn- clean-data [d] (->> d (map str/upper-case) (filter util/word?) distinct sort))
(def words (clean-data config/dict-data))
(defn longer-words [min] (filter #(>= (count %) min) words))

(defn- make-groups [method data] {method (group-by #(gem/calculate method %) data)})
(defn- create-dict []
  (let [dict-map (map #(make-groups % words) config/active-methods)]
    (log/info "Loading dict with" (count words) "words.")
    (into (sorted-map) dict-map)))

(def dict (create-dict))

(defn word-count [] (->> dict :ia vals (map #(count %)) (reduce +)))
