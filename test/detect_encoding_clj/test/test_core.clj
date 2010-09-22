(ns detect-encoding-clj.test.test-core
  (:use [detect-encoding-clj.core] :reload)
  (:use [clojure.test])
  (:require [clojure.java.io :as io :only [input-stream]])
  (:import [java.io File]))

(deftest test-detect
  (are [expected target] (= expected (detect target))
       "UTF-8"     "test/resources/utf_8.txt"
       "SHIFT_JIS" "test/resources/shift_jis.txt"
       "EUC-JP"    "test/resources/euc_jp.txt"
       "UTF-8"     (File. "test/resources/utf_8.txt")
       "SHIFT_JIS" (File. "test/resources/shift_jis.txt")
       "EUC-JP"    (File. "test/resources/euc_jp.txt"))
  (let [f (io/input-stream "test/resources/utf_8.txt")]
    (detect f)
    (is (thrown? java.io.IOException (.read f)))))
