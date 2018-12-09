(ns arithmea.config
  (:require [arithmea.util :as util]))

(def chat-ids ["-1001280104881"])

(def active-latin-methods [:ia :chal :pyth :naeq :tq :eq])
(def active-hebrew-methods [:full :ordinal :katan])
(def active-methods (concat active-latin-methods active-hebrew-methods))
(def result-limit 10)

(def polling-time-ms 2000)

(def dict-data
  (let [dir "wordlists/"
        common-words (util/slurp-words (str dir "1000-most-common-words.txt"))
        arithmea-list (util/slurp-words (str dir "arithmea-2000-words.txt"))
        test-list (util/slurp-words (str dir "test-words.txt"))]
    (concat common-words arithmea-list test-list)))
