(ns petrglad.numeric-ranges)

(defn empty? [[l u]]
  (> l u))

(defn- ranked [rs f]
  (apply f (->>
             (remove empty? rs)
             (sort-by first))))

(defn contains? [[l u] x]
  (and (<= l x) (< x u)))

(defn intersect? [[l1 u1 :as r1] [l2 u2 :as r2]]
  (or (contains? r1 l2)
    (contains? r2 l1)))

(defn union [[l1 u1 :as r1] [l2 u2 :as r2]]
  (cond
    (= u1 l2) [[l1 u2]]
    (= u2 l1) [[l2 u1]]
    (intersect? r1 r2) [[(min l1 l2) (max u1 u2)]]
    true [r1 r2]))

(defn intersection [[l1 u1 :as r1] [l2 u2 :as r2]]
  (let [result [(max l1 l2) (min u1 u2)]]
    (if (empty? result)
      []
      (if (not= ;; when intersecting ([x x] [x x]) or ([x a] [a y])
            (= u1 l2)
            (= u2 l1))
        []
        [result]))))
