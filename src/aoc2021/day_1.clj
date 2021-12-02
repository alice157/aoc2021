(ns aoc2021.day-1
  (:require
    [clojure.edn :as edn]
    [clojure.string :as str]))


(defonce part-1-input (slurp "resources/day1.1.txt"))


(def part-1-numbers
  (->> part-1-input
       (str/split-lines)
       (map edn/read-string)))


(defn increments
  [xs]
  (first (reduce (fn [[incs prev] n]
                   [(cond-> incs (< prev n) inc)
                    n])
                 [0 (first xs)]
                 (rest xs))))


(def part-1 (increments part-1-numbers))


(defn sliding-windows
  ([ns window-size]
   (sliding-windows (drop (dec window-size) ns)
                    window-size
                    (into (conj clojure.lang.PersistentQueue/EMPTY 0)
                          (take (dec window-size) ns))))
  ([ns window-size window]
   (when-let [n (first ns)]
     (let [window' (-> window
                       (conj n)
                       pop)]
       (cons (seq window')
             (lazy-seq (sliding-windows
                         (rest ns)
                         window-size
                         window')))))))


(def part-2
  (->> (sliding-windows part-1-numbers 3)
       (map #(apply + %))
       (increments)))
