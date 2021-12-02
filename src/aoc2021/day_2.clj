(ns aoc2021.day-2
  (:require
    [clojure.edn :as edn]
    [clojure.string :as str]))


(def part-1-input (str/split-lines (slurp "resources/day2.1.txt")))


(def initial-position
  {:depth 0
   :horizontal 0
   :aim 0})


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


(defn sub-command-reducer-2
  [position command-string]
  (let [[action n] (str/split command-string #" ")
        n (edn/read-string n)]
    (case action
      "forward" (-> position
                    (update :horizontal + n)
                    (update :depth + (* (:aim position) n)))
      "down" (update position :aim + n)
      "up" (update position :aim - n))))


(def part-2
  (let [{:keys [depth horizontal]}
        (reduce sub-command-reducer-2 initial-position part-1-input)]
    (* depth horizontal)))
