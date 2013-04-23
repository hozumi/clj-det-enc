(ns
    ^{:author "Takahiro Hozumi",
      :doc "A detect encoding utility. juniversalchardet wrapper for Clojure."}
  det-enc.core
  (:require [clojure.java.io :as io :only [input-stream]])
  (:import [org.mozilla.universalchardet UniversalDetector]
           [java.io InputStream]
           [java.nio.charset Charset]))

(defn- judge-seq! [buf ^InputStream istream ^UniversalDetector detector]
  (let [n        (.read istream buf)
        proceed? (and (> n 0)
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
           detector (UniversalDetector. nil)]
       (with-open [istream (io/input-stream target)]
         (dorun (judge-seq! buf istream detector))
         (.dataEnd detector)
         (or (.getDetectedCharset detector)
             (if (= encodingname-when-unknown :default)
               (.displayName (Charset/defaultCharset))
               encodingname-when-unknown))))))