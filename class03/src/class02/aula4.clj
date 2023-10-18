(ns class02.aula4)

(def precos [30 700 1000])

(println precos)
(println (precos 0))
(println (get precos 0))
(println (get precos 2))
(println "Valor padrao nil " (get precos 17))
;(println (precos 17))
(println "Valor padrao 0"(get precos 17 0))
(println "Valor padrao 0, mas existe"(get precos 2 0))

(println (conj precos 5))

(println precos)

;(println (conj 5 precos ))

(println (+ 5 1))
(println (inc 5))

(println (update precos 0 inc))
(println (update precos 1 inc))

(println precos)

(defn soma-1
  [valor]
  (println "Estou somando um em", valor)
  (+ valor 1)
  )

(println (update precos 0 soma-1))

(defn soma-3
  [valor]
  (println "Estou somando um em", valor)
  (+ valor 3)
  )

(println (update precos 0 soma-3))

;codigo da aula anterior

(defn aplica-desconto?
  [valor-bruto]
  (> valor-bruto 100))

(defn valor-descontado
  "Retorna o valor com desconto de 10%"
  [valor-bruto]
  (if (aplica-desconto? valor-bruto)
    (let [taxa-de-desconto (/ 10 100)
          desconto (* valor-bruto taxa-de-desconto)]
      (- valor-bruto desconto))
    valor-bruto))

(println precos)

(println "Map " (map valor-descontado precos))

(println (range 10))

(println (filter even? (range 10)))

(println (filter odd? (range 10)))

(println "map apos o filter" (map valor-descontado (filter aplica-desconto? precos)))

(println (reduce + precos))

(defn minha-soma
  [valor-1 valoe-2]
  (println "Somando" valor-1 valoe-2)
  (+ valor-1 valoe-2)
  )

(println (reduce minha-soma precos))

(println (reduce minha-soma (range 10)))
(println (reduce minha-soma [15]))

(println (reduce minha-soma 0 precos))


(println (reduce minha-soma 0 []))

