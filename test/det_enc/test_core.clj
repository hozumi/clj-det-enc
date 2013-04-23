(ns det-enc.test-core
  (:require [det-enc.core :as det]
            [clojure.test :as t]
            [clojure.java.io :as io]))

(t/deftest test-detect
  (t/are [target expected] (= expected (det/detect target))
         "test/resources/utf_8.txt"     "UTF-8"
         "test/resources/shift_jis.txt" "SHIFT_JIS"
         "test/resources/euc_jp.txt"    "EUC-JP"
         "test/resources/cp1251.txt"    "WINDOWS-1251"
         "test/resources/iso2022jp.txt" "ISO-2022-JP"
         "test/resources/gbk.txt"       "GB18030"
         "test/resources/latin1.txt"    "WINDOWS-1252"
         (java.io.File. "test/resources/utf_8.txt") "UTF-8")
  (let [f (io/input-stream "test/resources/utf_8.txt")]
    (det/detect f)
    (t/is (thrown? java.io.IOException (.read f)))))