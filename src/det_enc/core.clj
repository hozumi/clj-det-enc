(ns ^{:doc "A detect encoding utility. juniversalchardet wrapper for Clojure."}
  det-enc.core
  (:require [clojure.java.io :as io]))

(defn- judge-seq! [buf ^java.io.InputStream istream
                   ^org.mozilla.universalchardet.UniversalDetector detector]
  (let [n        (.read istream buf)
        proceed? (and (< 0 n)
                      (not (do (.handleData detector buf 0 n)
                               (.isDone detector))))]
    (if proceed?
      (recur buf istream detector)
      nil)))

(defn detect
  ([target]
     (detect target nil))
  ([target encodingname-when-unknown]
     (let [buf      (make-array Byte/TYPE 4096)
           detector (org.mozilla.universalchardet.UniversalDetector. nil)]
       (with-open [istream (io/input-stream target)]
         (dorun (judge-seq! buf istream detector))
         (.dataEnd detector)
         (or (.getDetectedCharset detector)
             (if (= encodingname-when-unknown :default)
               (.displayName (java.nio.charset.Charset/defaultCharset))
               encodingname-when-unknown))))))