(ns arithmea.gematria.alphabet.latin)

(def latin-table
  {\A {:ia 1 :chal 1 :pyth 1 :naeq 1 :tq 5 :ger 1 :eq 1}
   \B {:ia 2 :chal 2 :pyth 2 :naeq 20 :tq 20 :ger 0 :eq 200}
   \C {:ia 3 :chal 3 :pyth 3 :naeq 13 :tq 2 :ger 0 :eq 40}
   \D {:ia 4 :chal 4 :pyth 4 :naeq 6 :tq 23 :ger 0 :eq 6}
   \E {:ia 5 :chal 5 :pyth 5 :naeq 25 :tq 13 :ger 2 :eq 700}
   \F {:ia 6 :chal 8 :pyth 6 :naeq 18 :tq 12 :ger 0 :eq 90}
   \G {:ia 7 :chal 3 :pyth 7 :naeq 11 :tq 11 :ger 7 :eq 20}
   \H {:ia 8 :chal 5 :pyth 8 :naeq 4 :tq 3 :ger 8 :eq 4}
   \I {:ia 9 :chal 1 :pyth 9 :naeq 23 :tq 26 :ger 3 :eq 500}
   \J {:ia 10 :chal 1 :pyth 1 :naeq 16 :tq 7 :ger 0 :eq 70}
   \K {:ia 11 :chal 2 :pyth 2 :naeq 9 :tq 17 :ger 0 :eq 9}
   \L {:ia 12 :chal 3 :pyth 3 :naeq 2 :tq 1 :ger 9 :eq 2}
   \M {:ia 13 :chal 4 :pyth 4 :naeq 21 :tq 21 :ger 0 :eq 300}
   \N {:ia 14 :chal 5 :pyth 5 :naeq 14 :tq 24 :ger 0 :eq 50}
   \O {:ia 15 :chal 7 :pyth 6 :naeq 19 :tq 10 :ger 4 :eq 7}
   \P {:ia 16 :chal 8 :pyth 7 :naeq 26 :tq 4 :ger 0 :eq 800}
   \Q {:ia 17 :chal 1 :pyth 8 :naeq 17 :tq 16 :ger 0 :eq 100}
   \R {:ia 18 :chal 2 :pyth 9 :naeq 12 :tq 14 :ger 0 :eq 30}
   \S {:ia 19 :chal 3 :pyth 1 :naeq 5 :tq 15 :ger 6 :eq 5}
   \T {:ia 20 :chal 4 :pyth 2 :naeq 24 :tq 9 :ger 0 :eq 600}
   \U {:ia 21 :chal 6 :pyth 3 :naeq 17 :tq 25 :ger 5 :eq 80}
   \V {:ia 22 :chal 6 :pyth 4 :naeq 10 :tq 22 :ger 0 :eq 10}
   \W {:ia 23 :chal 6 :pyth 5 :naeq 3 :tq 8 :ger 0 :eq 3}
   \X {:ia 24 :chal 5 :pyth 6 :naeq 22 :tq 6 :ger 0 :eq 400}
   \Y {:ia 25 :chal 1 :pyth 7 :naeq 15 :tq 18 :ger 7 :eq 60}
   \Z {:ia 26 :chal 7 :pyth 8 :naeq 8 :tq 19 :ger 0 :eq 8}})

(defn number-value [method c] (let [values (get latin-table c)] (method values)))
