(ns arithmea.core
  (:require
    [clojure.tools.logging :as log]
    [clojure.string :as str]
    [arithmea.bot.client :as bot]
    [arithmea.util :as util]
    [arithmea.gematria.calculator :as gem]
    [arithmea.config :as config])
  (:gen-class))

(defn- make-groups [method data] {method (group-by #(gem/calculate method %) data)})

(defn- create-dict []
  (let [upper (map str/upper-case config/dict-data)
        clean-data (sort (distinct (filter util/word? upper)))]
    (log/info "Loading dict with" (count clean-data) "words.")
    (into (sorted-map) (map #(make-groups % clean-data) config/active-methods))
    ))

(defn- main-loop [dict offset]
  (Thread/sleep config/polling-time-ms)
  (recur dict (bot/update-state dict config/chat-ids offset)))

(defn -main "ArithmeA" [& args]
  (let [dict (create-dict)]
    (log/debug dict)
    (log/debug (reduce + (map #(count %) (vals (:chal dict)))) "Words.")
    (log/info (count config/active-methods) "active Methods.")
    (main-loop dict 0)))
