(defproject update-bootstrap-files "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [clj-time "0.15.0"]
                 [org.clojure/tools.logging "0.4.1"]
                 [log4j/log4j "1.2.17"]]
  :repl-options {:init-ns update-bootstrap-files.core}
  :main update-bootstrap-files.core/-main)
