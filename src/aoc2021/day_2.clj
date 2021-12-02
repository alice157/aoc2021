(ns aoc2021.day-2
  (:require
    [clojure.edn :as edn]
    [clojure.string :as str]))


(def part-1-input (str/split-lines (slurp "resources/day2.1.txt")))


(def initial-position
  {:depth 0
   :horizontal 0})


(defn sub-command-reducer
  [position command-string]
  (let [[action n] (str/split command-string #" ")
        n (edn/read-string n)]
    (case action
      "forward" (update position :horizontal + n)
      "down" (update position :depth + n)
      "up" (update position :depth - n))))


(def part-1
  (let [{:keys [depth horizontal]}
        (reduce sub-command-reducer initial-position part-1-input)]
    (* depth horizontal)))
