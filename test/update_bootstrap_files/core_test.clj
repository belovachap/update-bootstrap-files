(ns update-bootstrap-files.core-test
  (:require [clojure.test :refer :all]
            [update-bootstrap-files.core :refer :all]))

(deftest extracts-date-prefix
  (testing "returns date from prefix"
    (is (= "")))
  (testing "returns nil when string too short")
  (testing "returns nil when string doesn't have date prefix"))
(deftest categorizes-files
  (testing "categorizes files from the bootstrap folder"
    (let [input ["2019-05-11_bootstrap.dat"
                 "2019-05-10_bootstrap.dat"
                 "2019-05-10_bootstrap.dat.gz"
                 "2019-05-10_bootstrap.dat.zip"
                 "2019-05-10_bootstrap.dat.torrent"
                 "2019-05-10_bootstrap.dat.gz.torrent"
                 "2019-05-10_bootstrap.dat.zip.torrent"
                 "junk.txt"]
          output {:bootstrap [{:dat "2019-05-11_bootstrap.dat"}
                              {:dat "2019-05-10_bootstrap.dat"
                               :gz "2019-05-10_bootstrap.dat.gz"
                               :zip "2019-05-10_bootstrap.dat.zip"
                               :torrent "2019-05-10_bootstrap.dat.torrent"
                               :gz-torrent "2019-05-10_bootstrap.dat.gz.torrent"
                               :zip-torrent "2019-05-10_bootstrap.dat.zip.torrent"}]
                  :unrecognized ["junk.txt",]}]
      (is (= output (categorize-files input))))))
