(ns class02.aula)

(defn valor-descontado
  "Retorna o valor com desconto de 10%"
  [valor-bruto]
  (let [taxa-de-desconto (/ 10 100)
        desconto (* valor-bruto taxa-de-desconto)]
    (println "Calculando desconto de" desconto)
    (- valor-bruto desconto)))


(valor-descontado 100)