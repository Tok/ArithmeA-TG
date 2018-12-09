(ns arithmea.gematria.matcher
  (:require [arithmea.dict :as dict]))

(defn find-matches [method number] (get (get dict/dict method) number))

(defn find-anagrams [word]
  )
