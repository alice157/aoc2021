(ns aoc2021.day-3
  (:require
    [clojure.edn :as edn]
    [clojure.string :as str]))


(def part-1-input (str/split-lines (slurp "resources/day3.txt")))


(defn s->bin-vec
  [s]
  (mapv {\0 0 \1 1} s))


(def diagnostic-data
  (mapv s->bin-vec part-1-input))


(def counts-by-bit
  (reduce #(map + %1 %2) diagnostic-data))


(def most-common-bit-in-position
  (let [cutoff (/ (count part-1-input) 2)]
    (map (comp {true 0 false 1}
               #(< % cutoff))
         counts-by-bit)))


(def least-common-bit-in-position
  (map {0 1 1 0} most-common-bit-in-position))


(defn bin-vec->dec
  [v]
  (loop [v (reverse v)
         ret 0
         exp 1]
    (if-not (seq v)
      ret
      (recur (rest v)
             (+ ret (* (first v) exp))
             (* exp 2)))))


(def part-1
  (* (bin-vec->dec most-common-bit-in-position)
     (bin-vec->dec least-common-bit-in-position)))
