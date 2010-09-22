(defproject detect-encoding-clj "1.0.0-SNAPSHOT"
  :description "A detect encoding utility. juniversalchardet wrapper."
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
		 [org.mozilla/juniversalchardet "1.0.3"]]
  :dev-dependencies [[swank-clojure "1.3.0-SNAPSHOT"]
		     [lein-clojars "0.5.0"]]
  :repositories {"archive.org" "http://builds.archive.org:8080/maven2"})
