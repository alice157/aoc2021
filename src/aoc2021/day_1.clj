(ns aoc2021.day-1
  (:require
    [clojure.edn :as edn]
    [clojure.string :as str]))


(defonce part-1-input (slurp "resources/day1.1.txt"))


(defn increments
  [xs]
  (first (reduce (fn [[incs prev] n]
                   [(cond-> incs (< prev n) inc)
                    n])
                 [0 (first xs)]
                 (rest xs))))


(def part-1
  (->> part-1-input
       (str/split-lines)
       (map edn/read-string)
       (increments)))
