(defproject arithmea "0.7.7-SNAPSHOT"
  :description "ArithmeA Telegram Bot"
  :url "https://github.com/Tok/ArithmeA-TG"
  :profiles {:dev {:dependencies [[org.clojure/test.check "0.9.0"]]}}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/data.json "0.2.6"]
                 [org.clojure/tools.logging "0.4.1"]
                 [clj-http "3.9.1"]]
  :aot [arithmea.core]
  :main arithmea.core)
