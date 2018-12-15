(ns arithmea.dict
  (:require [arithmea.gematria.calculator :as gem]
            [arithmea.config :as config]
            [arithmea.util :as util]
            [clojure.string :as str]
            [clojure.tools.logging :as log]))

(defn- make-groups [method data] {method (group-by #(gem/calculate method %) data)})
(defn- clean-data [d] (->> d (map str/upper-case) (filter util/word?) distinct sort))
(defn- create-dict []
  (let [data (clean-data config/dict-data)
        dict-map (map #(make-groups % data) config/active-methods)]
    (log/info "Loading dict with" (count data) "words.")
    (into (sorted-map) dict-map)))

(def dict (create-dict))

(defn word-count [] (->> dict :ia vals (map #(count %)) (reduce +)))
