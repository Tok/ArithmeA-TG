(ns arithmea.core
  (:require [arithmea.bot.client :as bot]
            [arithmea.config :as config]
            [arithmea.dict :as dict]
            [clojure.tools.logging :as log])
  (:gen-class))

(defn- main-loop [offset] (recur (bot/update-state config/chat-ids offset)))

(defn -main "ArithmeA" [& _]
  (log/debug dict/word-count "Words.")
  (log/info (count config/active-methods) "active Methods.")
  (main-loop 0))
