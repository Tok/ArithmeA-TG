(defproject arithmea "0.7.7-SNAPSHOT"
  :description "ArithmeA Telegram Bot"
  :url "https://github.com/Tok/ArithmeA-TG"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.logging "0.4.1"]
                 [org.clojure/data.json "0.2.6"]
                 [clj-http "3.9.1"]]
  :aot [arithmea.core]
  :main arithmea.core
  )
