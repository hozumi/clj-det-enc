(ns det-enc.test-core
  (:use [det-enc.core] :reload)
  (:use [clojure.test])
  (:require [clojure.java.io :as io :only [input-stream]])
  (:import [java.io File]))

(deftest test-detect
  (are [expected target] (= expected (detect target))
       "UTF-8"        "test/resources/utf_8.txt"
       "SHIFT_JIS"    "test/resources/shift_jis.txt"
       "EUC-JP"       "test/resources/euc_jp.txt"
       "WINDOWS-1251" "test/resources/cp1251.txt"
       "ISO-2022-JP"  "test/resources/iso2022jp.txt"
       "GB18030"      "test/resources/gbk.txt"
       "WINDOWS-1252" "test/resources/latin1.txt"
       "UTF-8"     (File. "test/resources/utf_8.txt")
       "SHIFT_JIS" (File. "test/resources/shift_jis.txt")
       "EUC-JP"    (File. "test/resources/euc_jp.txt"))
  (let [f (io/input-stream "test/resources/utf_8.txt")]
    (detect f)
    (is (thrown? java.io.IOException (.read f)))))
