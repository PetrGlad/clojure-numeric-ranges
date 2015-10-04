(ns petrglad.numeric-ranges-test
  (:require [clojure.test :refer :all]
            [petrglad.numeric-ranges :refer :all]))

(deftest empty?-test
  (testing "empty?"
    (is (empty? [1 -1]))
    (is (not (empty? [1 1])))))

(deftest contains?-test
  (testing "contains?"
    (is (contains? [1 3] 1))
    (is (contains? [1 3] 2))
    (is (not (contains? [1 3] 3)))
    (is (not (contains? [1 3] 0)))))

(deftest intersection-test
  (testing "intersection"
    (is (= [] (intersection [1 2] [2 4])))
    (is (= [] (intersection [2 4] [1 2])))
    (is (= [] (intersection [3 4] [1 2])))
    (is (= [] (intersection [1 4] [2 1])))
    (is (= [[1 1]] (intersection [1 1] [1 1])))
    (is (= [] (intersection [1 1] [2 2])))
    (is (= [[2 3]] (intersection [1 3] [2 4])))
    (is (= [[2 3]] (intersection [1 4] [2 3])))))

(deftest union-test
  (testing "union"
    (is (= [[1 4]] (union [1 2] [2 4])))
    (is (= [[1 4]] (union [2 4] [1 2])))
    (is (= [[1 2]] (union [1 2] [1 2])))
    (is (= [[1 2] [3 4]] (union [1 2] [3 4])))
    (is (= [[1 4]] (union [1 4] [2 4])))))
