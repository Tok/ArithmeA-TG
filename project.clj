(defproject arithmea "0.7.7-SNAPSHOT"
  :description "ArithmeA Telegram Bot"
  :url "https://github.com/Tok/ArithmeA-TG"
  :profiles {:dev {:dependencies [[org.clojure/test.check "1.1.0"]]
                   :resource-paths ["resources-test"]}}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [org.clojure/data.json "2.4.0"]
                 [org.clojure/tools.logging "1.2.1"]
                 [clj-http "3.10.0"]]
  :aot [arithmea.core]
  :main arithmea.core)
