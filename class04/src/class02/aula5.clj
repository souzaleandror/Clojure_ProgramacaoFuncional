(ns class02.aula5)

(def estoque {"Mochila" 10, "Camiseta" 5})
(def estoque {"Mochila" 10 "Camiseta" 5})

(println estoque)

(println "Temos" (count estoque) "elementos")
(println "Chaves sao:" (keys estoque))
(println "Valores sao:" (vals estoque))

(def estoque {:mochile 10
              :camiseta 5})

(println (assoc estoque :cadeira 3))
(println estoque)
(println (assoc estoque :mochile 1))
(println estoque)

(println (update estoque :mochile inc))

(println estoque)

(defn tira-um
  [valor]
  (println "Tirando um de" valor)
  (- valor 1))

(println estoque)

(println (update estoque :mochile tira-um))
(println (update estoque :mochile #(- % 3)))

(println (dissoc estoque :mochile))

(println estoque)

(def pedido {:mochila {:quantidade 2, :preco 80}
             :camiseta {:quantidade 3, :preco 40}})

(println "\n\n\n\n")

(println pedido)

(def pedido (assoc pedido :chaveiro {:qunantidade 1, :preco 10}))

(println pedido)


(println (get pedido :mochila))
(println (get pedido :cadeira))
(println (get pedido :cadeira {}))

(println (:mochila pedido))
(println (:cadeira pedido))
(println (:cadeira pedido {}))

(println (:quantidade (:mochila pedido)))

(println (update-in pedido [:mochila :quantidade] inc))

(println (-> pedido :mochila :quantidade))
