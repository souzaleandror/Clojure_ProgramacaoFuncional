(ns class02.aula3)

(defn valor-descontado
  "Retorna o valor com desconto de 10%"
  [valor-bruto]
  (let [taxa-de-desconto (/ 10 100)
        desconto (* valor-bruto taxa-de-desconto)]
    (println "Calculando desconto de" desconto)
    (- valor-bruto desconto)))

(println (valor-descontado 1000))

(println (valor-descontado 100))

(defn aplica-desconto?
  [valor-bruto]
  (if (> valor-bruto 100)
    true
    false
    ))

(println (aplica-desconto? 1000))

(println (aplica-desconto? 100))

(defn valor-descontado
  "Retorna o valor com desconto de 10%"
  [valor-bruto]
  (if (aplica-desconto? valor-bruto)
  (let [taxa-de-desconto (/ 10 100)
        desconto (* valor-bruto taxa-de-desconto)]
    (- valor-bruto desconto))
  valor-bruto))

(println (valor-descontado 1000))

(println (valor-descontado 100))

(defn aplica-desconto?
  [valor-bruto]
  (if (> valor-bruto 100)
    true
    ))

(println (aplica-desconto? 1000))

(println (aplica-desconto? 100))

(println (valor-descontado 1000))

(println (valor-descontado 100))

(defn aplica-desconto?
  [valor-bruto]
  (when (> valor-bruto 100)
    true
    ))

(println (aplica-desconto? 1000))

(println (aplica-desconto? 100))

(println (valor-descontado 1000))

(println (valor-descontado 100))

(defn aplica-desconto?
  [valor-bruto]
  (> valor-bruto 100))

(println (aplica-desconto? 1000))

(println (aplica-desconto? 100))

(println (valor-descontado 1000))

(println (valor-descontado 100))

(defn mais-caro-que-100?
  [valor-bruto]
  (println "deixando claro invocacao de mais-caro-que-100?")
  (> valor-bruto 100))

(defn valor-descontado
  "Retorna o valor com desconto de 10%"
  [aplica? valor-bruto]
  (if (aplica? valor-bruto)
    (let [taxa-de-desconto (/ 10 100)
          desconto (* valor-bruto taxa-de-desconto)]
      (- valor-bruto desconto))
    valor-bruto))

(println (valor-descontado mais-caro-que-100? 1000))

(println (valor-descontado mais-caro-que-100? 100))

(println (valor-descontado (fn [valor-bruto] (> valor-bruto 1000)) 1000))
(println (valor-descontado (fn [valor-bruto] (> valor-bruto 100)) 100))

(println (valor-descontado (fn [v] (> v 1000)) 1000))
(println (valor-descontado (fn [v] (> v 100)) 100))

(println (valor-descontado #(> %1 1000) 1000))
(println (valor-descontado #(> %1 100) 100))

(println (valor-descontado #(> % 1000) 1000))
(println (valor-descontado #(> % 100) 100))