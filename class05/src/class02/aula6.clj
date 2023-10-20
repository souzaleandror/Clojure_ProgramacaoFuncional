(ns class02.aula6)

(def pedido {:mochila {:quantidade 2, :preco 80}
             :camiseta {:quantidade 3, :preco 40}})

(defn imprime-e-16 [valor]
  (println "valor" valor (class valor) valor)
  16)

(println (map imprime-e-16 pedido))

(defn imprime-e-15 [[chave valor]]
  (println chave "<e>" valor)
  15)

(println (map imprime-e-15 pedido))

(defn imprime-e-17 [[chave valor]]
  valor)

(println (map imprime-e-17 pedido))


(defn preco-dos-produtos [[chave valor]]
  (* (:quantidade valor) (:preco valor)))

(println (map preco-dos-produtos pedido))
(println (reduce + (map preco-dos-produtos pedido)))

(defn total-do-pedido [pedido]
  (reduce + (map preco-dos-produtos pedido)))

(println (total-do-pedido pedido))

; THREAD LAST
(defn total-do-pedido
  [pedido]
  ( ->> pedido
    (map preco-dos-produtos ,,,)
  (reduce + ,,,)))

(println (total-do-pedido pedido))

(def pedido {:mochila {:quantidade 2, :preco 80}
             :camiseta {:quantidade 3, :preco 40}
             :chaveiro {:quantidade 1}})

(defn gratuito? [item]
  (<= (get item :preco 0) 0))
(println "Filtrando gratuitos")
(println (filter gratuito? (vals pedido)))

(defn gratuito2? [[chave item]]
  (<= (get item :preco 0) 0))
(println "Filtrando gratuitos")
(println (filter gratuito2? pedido))



(defn gratuito3? [item]
  (<= (get item :preco 0) 0))
(println "Filtrando gratuitos")
(println (filter (fn [[chave item]] (gratuito3? item)) pedido))
(println (filter #(gratuito3? (second %)) pedido))

(defn pago? [item] (not (gratuito3? item)))

(println (pago? {:preco 50}))
(println (pago? {:preco 0}))

(println ((comp not gratuito3?) {:preco 50}))

(def pago2? (comp not gratuito3?))
(println (pago2? {:preco 50}))
(println (pago2? {:preco 0}))

