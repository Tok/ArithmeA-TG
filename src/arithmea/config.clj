(ns arithmea.config
  (:require [arithmea.util :as util]))

(def chat-ids ["-1001280104881"])

(def active-latin-methods [:ia :chal :pyth :naeq :tq :eq])
(def active-hebrew-methods [:full :ordinal :katan])
(def active-methods (concat active-latin-methods active-hebrew-methods))
(def result-limit 10)

(def polling-time-ms 200)

(def dict-data
  (let [common-words (util/slurp-words "1000-most-common-words.txt")
        arithmea-list (util/slurp-words "arithmea-2000-words.txt")]
    (concat common-words arithmea-list)))
