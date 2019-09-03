(defproject arithmea "0.7.7-SNAPSHOT"
  :description "ArithmeA Telegram Bot"
  :url "https://github.com/Tok/ArithmeA-TG"
  :profiles {:dev {:dependencies [[org.clojure/test.check "0.9.0"]]
                   :resource-paths ["resources-test"]}}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/data.json "0.2.6"]
                 [org.clojure/tools.logging "0.5.0"]
                 [clj-http "3.10.0"]]
  :aot [arithmea.core]
  :main arithmea.core)
