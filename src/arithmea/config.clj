(ns arithmea.config
  (:require [arithmea.util :as util]
            [clojure.java.io :as io]))

(def chat-ids ["-1001280104881"])

(def active-latin-methods [:ia :chal :pyth :naeq :tq :eq :aq])
(def active-hebrew-methods [:full :ordinal :katan])
(def active-methods (concat active-latin-methods active-hebrew-methods))

(def result-limit 10)
(def display-limit 3)

(def polling-time-s 8)

(def dict-data
  (let [path (-> "resources/wordlists/" io/file)
        files (filter #(.isFile %) (file-seq path))]
    (distinct (apply concat (map #(util/slurp-file %) files)))))
