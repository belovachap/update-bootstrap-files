(ns update-bootstrap-files.core)
(require '[clojure.java.io :as io])
(require '[clojure.java.shell :as shell])
(require '[clojure.string :as string])
(require '[clj-time.core :as t])
(require '[clj-time.format :as f])


(defn files-in-folder [folder]
  (->> folder
       io/file
       file-seq
       (filter #(.isFile %))))

(defn extract-date [file]
  (try
    (f/parse (f/formatters :year-month-day) (subs (.getName file) 0 10))
    (catch Exception e)))

(defn extract-dates [files]
  (map #(hash-map :file % :date (extract-date %)) files))

(defn delete-file [file]
  (io/delete-file (.getPath (:file file))))

(defn gzip-file [file]
  (shell/sh "gzip" "--keep" (.getPath file)))

(defn zip-file [file]
  (shell/sh "zip" (str (.getPath file) ".zip") (.getPath file)))

(defn make-torrent [file]
  (println (str "Pretend torrent-ing " file "!!!")))

(defn delete-old-files [days-ago files]
  (->> files
       (extract-dates)
       (filter #(t/before? (:date %) (t/minus (t/now) (t/days days-ago))))
       (map delete-file)))

(defn process-bootstrap-file [file]
  (gzip-file file)
  (zip-file file)
  (make-torrent file)
  (make-torrent (str file ".gz"))
  (make-torrent (str file ".zip")))

(defn process-bootstrap-files [folder]
  (->> (files-in-folder folder) (delete-old-files 2))
  (->> (files-in-folder folder)
       (filter #(string/ends-with? (.getName %) ".dat"))
       (map process-bootstrap-file)))

(defn update-index-file [files]
  ;; Uses a template and most recent files to build index.html
  )

(defn -main [folder]
  (process-bootstrap-files (str folder "/bootstrap_main"))
  (process-bootstrap-files (str folder "/bootstrap_test")))
