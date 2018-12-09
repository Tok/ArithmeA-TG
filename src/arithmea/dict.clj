(ns arithmea.dict
  (:require [arithmea.gematria.calculator :as gem]
            [arithmea.config :as config]
            [arithmea.util :as util]
            [clojure.string :as str]
            [clojure.tools.logging :as log]))

(defn- make-groups [method data] {method (group-by #(gem/calculate method %) data)})
(defn- create-dict []
  (let [upper (map str/upper-case config/dict-data)
        clean-data (sort (distinct (filter util/word? upper)))]
    (log/info "Loading dict with" (count clean-data) "words.")
    (into (sorted-map) (map #(make-groups % clean-data) config/active-methods))
    ))

(def dict (create-dict))
