#### 15/10/2023

@01-Primeiras funcoes 

@@01
Introdução

Bem vindo(a) ao nosso curso de introdução ao Clojure! Aqui trabalharemos de maneira funcional, tentando entender quais são as vantagens, as desvantagens, as facilidades e as dificuldades que uma abordagem funcional e imutável nos traz utilizando a linguagem Clojure. Devemos ter em mente o Clojure não é limitado a uma abordagem 100% funcional, sendo possível trazer componentes de orientação a objetos, por exemplo, que vieram principalmente da Virtual Machine por trás do Clojure, que é o Java.
Ao longo das aulas, passaremos por diversos conceitos, como as estruturas básicas de dados utilizadas na linguagem (vetores e hashs/mapas), além de filter, reduce, criar e devolver funções, High Order Functions, funções como parâmetros, etc., ao mesmo tempo em que discutimos boas práticas e facilidades do Clojure.

Vamos começar?

@@02
Preparando o ambiente

Acesse o site https://clojure.org/guides/getting_started e execute o processo de instalação de acordo com o seu sistema operacional. No Mac você pode usar o homebrew https://brew.sh/. No Linux, basta baixar e descompactar o tar gz, e no Windows, usar o instalador próprio.

@@03
Funções e vetores def

Vamos começar o nosso curso! Para instalar o Clojure, siga as intruções da atividade de instalação. Em resumo: no Mac, basta um brew install clojure; no Linux, basta baixar o arquivo TARGZ, descompactá-lo e adicioná-lo ao PATH; e no Windows, basta utilizar o instalador específico.
Após a instalação, executaremos um interpretador do Clojure. Nosso primeiro objetivo é imprimirmos uma mensagem. Para isso, chamaremos a função println, que recebe alguns parâmetros - por exemplo, uma string com a mensagem "Bem vindo ao sistema de estoque". Em Closure, quando invocamos uma função, precisamos informar o nome dela e os seus vários parâmetros - nesse caso, println com um único parâmetro. Além disso, toda a invocação deve ser colocada em parênteses. Sendo assim, teremos:

(println "Bem vindo ao sistema de estoque")COPIAR CÓDIGO
Um pouco diferente de outros tipos de linguagem, nas quais utilizamos os parênteses depois do nome da função, englobando somente os parâmetros. Isso não parece fazer muita diferença por enquanto, mas no futuro, à medida que praticarmos, fará bastante! Assim, aprendemos que a operação que queremos executar será sempre o primeiro "argumento", usando um termo mais genérico, da sequência dentro dos parênteses.

Ao executarmos, a mensagem será impressa na tela, além de um nil, "nulo", que também é devolvido pela função println. Ou seja, além de executar a função, o interpretador do Clojure também devolverá o retorno dela.

Agora queremos imprimir um total de produtos no estoque, por exemplo 15. Para isso, começaremos definindo o símbolo total-de-produtos com esse valor, algo que é feito utilizando o def. Como o valor referenciado pode mudar, costumamos chamar esse símbolo de "variável".

(def total-de-produtos 15)COPIAR CÓDIGO
Como retorno, teremos:

#'user/total-de-produtos
Nesse caso, o user o espaço de memória em que estamos trabalhando - um "namespace", que abordaremos no futuro. Agora que definimos esse valor, se executarmos (println total-de-produtos), teremos como retorno:

15
nil

Como a função println pode receber mais de um parâmetro, podemos chamar (println "Total" total-de-produtos) para imprimirmos a palavra "total" e o valor que está sendo referenciado pelo símbolo total-de-produtos. Como retorno, teremos:

Total 15
nil

Repare que o próprio println adiciona um espaço entre os parâmetros. Agora queremos redefinir o valor de total-de-produtos para 13. Para isso, faremos (def total-de-produtos 13). Em seguida, imprimiremos novamente o valor com (println "Total" total-de-produtos).

Total 13
nil

Parece que, da maneira que definimos esses símbolos, estamos trabalhando com "variáveis globais" - que, em muitas linguagens, é uma má prática. Porém, para explorarmos o Clojure, continuaremos a trabalhar dessa forma por enquanto. Já aprendemos a chamar uma função e a definir um símbolo. Agora queremos, utilizando outra função, somar três produtos ao nosso total original, que é 13, no seguinte modelo:

13 + 3COPIAR CÓDIGO
Porém, como aprendemos anteriormente, a operação/função a ser executada é sempre o primeiro "argumento" a fiturar dentro do parênteses. Sendo assim, deveríamos passar o + no início:

(+ 13 3)COPIAR CÓDIGO
Ou seja, aquilo que vamos executar é sempre o primeiro argumento dentro de uma lista de argumentos contida entre parênteses, e o restante são os parâmetros da função a ser executada - lembrando que estamos utilizando termos genéricos. Se executarmos o código acima, teremos como retorno:

16
Para quem está acostumando com parsing de matemática, essa é a notação "prefix", que primeiro lista a operação a ser feita e depois os parâmetros dela. Pensando nisso, se fizermos (13 + 3), o Clojure tratará 13 como uma função, o que ele não é. Se executarmos dessa forma, teremos:

Execution error (ClassCastException) at user/eval7 (REPL:1). java.base/java.lang.Long cannot be cast to clojure.lang.IFn
Isso significa que um Long não pode ser transformado em uma IFn, que é uma interface de função. Repare também que o Clojure está, em tempo de execução transformando o nosso código, que é uma string, em tipos do próprio Clojure, executando o código em seguida. Ao tentar transformar o 13, que é um Long, em uma função, recebemos um erro.

Se executarmos (- 13 3), a operação será feita corretamente, nos retornando um 10. Note que os operadores (+ e -, por exemplo) também costumam ser funções. Além disso, é possível passar vários valores ao mesmo tempo utilizando um único operador - por exemplo, (+ 13 3 3), que nos retornará 19.

Se quisermos somar o valor 3 ao total-de-produtos, faremos (+ total-de-produtos 3), recebendo como retorno 16 - afinal, definimos total-de-produtos como 13. Mas e se quisermos definir o símbolo total-de-produtos com esse resultado? Para isso, usaremos (def total-de-produtos) passando, como segundo parâmetro, (+ total-de-produtos 3), que é a soma do valor atual de total-de-produtos com o valor 3.

(def total-de-produtos (+ total-de-produtos 3))COPIAR CÓDIGO
Feito isso, o valor da nossa "variável" será redefinido. Se executarmos (println total-de-produtos) para recuperá-lo, teremos como retorno 16. Assim, aprendemos que é possível utilizar o resultado da invocação de uma função como parâmetro para outra função (ou outra coisa que estivermos executando). Com isso, conseguiremos criar programas da maneira que estamos acostumados.

Mas já aprendemos Clojure? Certamente não! Repare, por exemplo, que estamos trabalhando de maneira semelhante a uma variável, alterando seu valor constantemente, e o temos grandes ganhos com o Clojure à medida em que percebemos que as coisas são mais difíceis de mudar.

Para pensarmos nessa questão, vamos trabalhar com um conjunto de elementos bem específico, que é um vetor. Definiremos então o nosso estoque como um vetor contendo uma Mochila e uma Camiseta.

(def estoque ["Mochila", "Camiseta"])COPIAR CÓDIGO
Note que utilizamos os colchetes, símbolos que definem um vetor, nesse caso de apenas dois elementos. Podemos imprimir esse vetor com (println estoque), nos retornando [Mochila Camiseta], sem as aspas da nossa string e sem a vírgula. Isso porque, no Clojure, a vírgula é considerada um espaço, e a separação de elementos em um vetor é feita da mesma forma que em uma lista de argumentos. Ou seja, poderíamos executar (def estoque ["Mochila" "Camiseta"]) e obteríamos o mesmo resultado. Porém, ainda que opcional, é uma boa prática utilizarmos a vírgula quando escrevemos o código na mesma linha, afinal isso melhora a legibilidade.

Podemos fazer várias coisas com um vetor, e começaremos com algo simples. Primeiro, é possível imprimir o estoque diretamente, da mesma que um valor numérico:

estoque`COPIAR CÓDIGO
["Mochila" "Camiseta"]
Repare que, diferente do println, o estoque foi passado para o interpretador do Clojure, que mostrou o nosso vetor na tela. Já se executarmos (estoque), receberemos um erro, afinal estoque não é uma função, mas sim um vetor.

Ainda assim, o estoque pode ser utilizado como uma função. Veja o exemplo:

(estoque 0)COPIAR CÓDIGO
Executando esse código, nosso retorno será "Mochila". Ou seja, estamos chamando o primeiro elemento do vetor estoque. Se utilizarmos (estoque 1), conseguiremos o segundo elemento do vetor, que é "Camiseta"; já se utilizarmos (estoque 2), receberemos uma IndexOutOfBoundsException, uma exceção informando que o valor pedido está além das informações contidas no vetor.

Isso significa que o estoque pode se comportar como uma função, e é até mesmo uma IFn, uma interface de função do Clojure. Em realidade, quase tudo, com exceção de valores numéricos, pode funcionar como uma função, tendo o comportamento esperado daquele tipo - se temos um vetor, é esperado que, ao passarmos um índice, recebamos justamente o valor desse índice, e é justamente isso que acontece.

Se quisermos contar a quantidade de elementos em um vetor, podemos utilizar a função count, passando como parâmetro o que queremos contar - nesse caso, o estoque.

(count estoque)COPIAR CÓDIGO
Como retorno, teremos:

2
Ou seja, chamamos a função count passando como parâmetro o nosso estoque, que tem 2 valores. E se quisermos adicionar um novo valor a esse vetor? Para isso, podemos utilizar a função conj. Nesse caso, adicionaremos o valor "Cadeira".

(conj estoque "Cadeira")COPIAR CÓDIGO
Como retorno, teremos:

["Mochila" "Camiseta" "Cadeira"]
Ou seja, o resultado da função conj é um vetor contendo "Mochila", "Camiseta" e "Cadeira". Porém, se imprimirmos o nosso estoque com (println estoque), teremos:

Mochila Camiseta
Isso significa que o conj não altera o conteúdo do vetor original, e voltamos ao ponto comentado sobre a imutabilidade no Clojure. Quando temos um número, um vetor ou outro tipo de objeto, ele não muda de valor. É possível redefinir um símbolo, que passará a representar outro valor, mas, por exemplo, 15 sempre será 15. Isso é meio óbvio quando estamos falando de um valor numérico, mas não costumamos trabalhar dessa forma em outras linguagem, onde, quando adicionamos um elemento a um vetor, ele passa a representar também esse novo elemento.

Sendo assim, não importa quantas vezes chamarmos a função conj e adicionarmos novos itens ao vetor estoque, ele sempre manterá somente os dois elementos originais, afinal não informarmos que a referência estoque deverá apontar a outro valor. Se quiséssemos redefini-la, poderíamos fazer (def estoque(conj estoque "Cadeira"). Dessa forma, usaríamos o novo vetor com três valores retornado por conj como referência para o símbolo estoque. Se fizermos (println estoque), os três elementos serão retornados.

Quando estamos trabalhando com valores imutáveis, podemos passá-los para qualquer pessoa sem medo que a função invocada vá alterá-lo de alguma forma, nos permitindo um controle maior sobre o nosso código. Essa é uma dentre as muitas vantagens da imutabilidade.

Já que o Clojure sabe que quase tudo será trabalhado com a imutabilidade em mente, as coleções, como o vetor, são implementadas com isso em mente. Sendo assim, a cópia de um vetor para um novo vetor com os mesmos valores e um a mais não irá passar por todos os elementos, sendo feita de maneira otimizada para que o custo seja baixo.

A partir de agora nos aprofundaremos mais nessa linguagem e entenderemos as consequências dos conceitos apresentados.

https://cursos.alura.com.br/course/clojure-introducao-a-programacao-funcional/task/60374

@@04
Funções e parâmetros

Para continuarmos nossos estudos, abriremos um editor de texto - no nosso caso, o Sublime, mas você pode utilizar o editor da sua preferência - para aprendermos alguns outros conceitos da linguagem antes de passarmos para uma IDE com mais funcionalidades. A ideia agora é definirmos nossa própria função - afinal, já sabemos definir valores e invocar funções, e podemos explorar a documentação para conhecermos outros tipos de valores e como trabalhar com outras funções.
Uma das maneiras de definir uma função no Clojure é por meio do atalho defn. Para o nosso teste, queremos que a função se chame imprime-mensagem, resultando em (defn imprime-mensagem). Repare que é padrão no Clojure utilizar hífen (-) ao invés de underline. Ainda dentro dos parênteses, adicionaremos dois espaços (), outro padrão do Clojure (que a IDE futuramente nos ajudará a manter), antes de chamarmos (println "Bem vindo(a) ao estoque!"), ou seja, a função println com a mensagem que queremos imprimir.

(defn imprime-mensagem
  (println "Bem vindo(a) ao estoque!"))COPIAR CÓDIGO
Se colarmos esse código no nosso interpretador, receberemos um erro de sintaxe. Isso porque sempre que definimos uma função com defn, precisamos passar também os parâmetros que ela recebe. Como nesse caso a função não recebe nenhum parâmetro, passaremos um "vetor" vazio [].

(defn imprime-mensagem []
  (println "Bem vindo(a) ao estoque!"))COPIAR CÓDIGO
Assim conseguiremos definir com sucesso um símbolo imprime-mensagem que referencia uma função. Dessa forma, se executarmos (imprime-mensagem), a mensagem "Bem vindo(a) ao estoque!" será exibida na tela.

Mas e se quisermos executar mais de uma instrução na nossa função? Para isso, basta seguirmos a mesma sintaxe que aprendemos anteriormente, como no exemplo:

(defn imprime-mensagem []
  (println "---------")
  (println "Bem vindo(a) ao estoque!"))COPIAR CÓDIGO
Definindo a função (imprime-mensagem) dessa forma, quando chamarmos o (imprime-mensagem), as duas ocorrências de (println) serão executadas, na ordem em que aparecem, imprimindo as duas linhas. Agora queremos criar uma função um pouco mais complexa, que trabalhe realmente com os nossos pedidos e produtos.

A ideia é definirmos uma regra na qual o preço de um produto após a aplicação do desconto é 90% do valor dele. Ou seja, vamos multiplicar o valor bruto de um produto ou compra por 0.9.

Definiremos então a função aplica-desconto recebendo como parâmetro um [valor-bruto].Na linha seguinte, essa função deverá devolver o valor-bruto multiplicado por 0.9, o que faremos com auxílio do operador *, que, como aprendemos anteriormente, deverá ser o primeiro "parâmetro" da chamada dessa função.

(defn aplica-desconto [valor-bruto]
    (* valor-bruto 0.9))COPIAR CÓDIGO
Após definirmos a função no interpretador, chamaremos (aplica-desconto 100). Como resultado, teremos 90.0. Já se chamarmos (aplica-desconto 1000), o valor retornado será 900.0. Repare que, como estamos multiplicando por um Double, a devolução também parece ser um Double.

Também podemos trabalhar com outros tipos "primitivos" - entre aspas pois eles não necessariamente são primitivos. Quando tentamos chamar (15), por exemplo, podemos ver que esse valor é um Long, que, apesar de ser um escalar, não é um tipo primitivo do Java. Da mesma forma, o 15.0 é um Double, com "d" maiúsculo. É possível trabalharmos com os tipos primitivos do Java por trás dos panos, uma questão de otimização que pode ser feita caso ela faça sentido para o osso projeto.

Agora que aprendemos a definir e chamar uma função, repare que existe um detalhe na nomenclatura: a função é descrita de maneira imperativa, como em (aplica-desconto). Porém, em alguns livros ou projetos, é possível encontrar casos em que a nomenclatura parece mais declarativa, como em (valor-descontado).

(defn valor-descontado [valor-bruto]
    (* valor-bruto 0.9))COPIAR CÓDIGO
Temos exatamente o mesmo código, mas transmitimos a ideia de que estamos pedindo o valor-descontado, ao invés de mandarmos executar a aplicação de um desconto. Na prática, verbos imperativos estão ligados a ações que possuem um efeito colateral, como imprimir uma mensagem, abrir um arquivo, tocar uma música ou lançar um foguete. Já quando retornarmos um valor, não parece existir um efeito colateral.

Repare que não importa quantas vezes chamarmos a função (valor-descontado), independentemente do nome dela, ela sempre retornará o mesmo resultado, e seu retorno será somente esse. Podemos chamar esse tipo de função de "função pura", e elas nos trazem diversas vantagens. Quando pensamos em efeitos colaterais, estamos falando de ações que, quando feitas duas vezes, não trazem o mesmo resultado, ou que fazem outras coisas (como enviar uma mensagem ou disparar um e-mail).

Antes de prosseguirmos, vamos colocar o primeiro parâmetro da lista de parâmetros na linha seguinte, a formatação preferida pelo nosso instrutor. Também é póssível descrever, entre aspas duplas, o que essa função faz.

(defn valor-descontado 
  "Retorna o valor descontado que é 90% do valor bruto"
    [valor-bruto]
    (* valor-bruto 0.9))COPIAR CÓDIGO
Dessa forma poderemos definir a função juntamente com a sua documentação, e ela poderá ser invocada normalmente. Em verdade, poderíamos definir tudo isso em uma linha só, sem a sua descrição.

(defn valor-descontado [valor bruto]  (* valor-bruto 0.9)COPIAR CÓDIGO
Esse código também funciona, mas nosso instrutor, Guilherme, pessoalmente acredita que esse volume de informação em uma só linha não é adequado, pois atrapalha a análise do corpo da função.

Vamos finalizar esse capítulo com mais uma variação dessa mesma função, um pouco mais complexa. Sabemos que 0.9 é 10% de desconto sobre o valor-bruto, certo? Sendo assim, definiremos novamente nosso símbolo valor-descontado, que recebe um [valor-bruto] e retorna a multiplicação desse valor por - 1 0.1, ou seja, 1 menos 10% de desconto, que é 0.9. Também podemos definir a documentação como "Retorna o valor com desconto de 10%".

(defn valor-descontado
"Retorna o valor com desconto de 10%"
  [valor-bruto]
  (* valor-bruto (- 1 0.1)))COPIAR CÓDIGO
Como esperado, se chamarmos (valor-descontado 100), teremos como retorno 90.0.

@@05
Faça como eu fiz na aula

Chegou a hora de você seguir todos os passos realizados por mim durantes esta aula. Caso já tenha feito, excelente. Se ainda não, é importante que você implemente o que foi visto no vídeo para poder continuar com a próxima aula, que tem como pré-requisito todo o código aqui escrito. Se por acaso você já domina essa parte, em cada capítulo, você poderá baixar o projeto feito até aquele ponto.

@@06
Para saber mais

clojure.lang.IFn é uma indicação de que o código fonte de clojure é, pelo menos, parcialmente implementado em Java. No Github você pode encontrá-lo: https://github.com/clojure/clojure/.
Para quem já programa em Java, percebe alguns padrões: os desenvolvedores e desenvolvedoras de Clojure usaram o padrão I para definir uma interface e abreviaram Function para Fn, para condizer com o termo fn de Clojure ao invés do conceito de Function em si. Para quem quiser se aprofundar na implementação da linguagem, como uma função é invocável, esperamos que ela tenha um método a ser invocado, e olhando o código fonte dela encontramos o método invoke com suas n variações: https://github.com/clojure/clojure/blob/master/src/jvm/clojure/lang/IFn.java.

https://github.com/clojure/clojure/

https://github.com/clojure/clojure/blob/master/src/jvm/clojure/lang/IFn.java

@@07
Imutabilidade

Sendo imutabilidade um conceito tão importante em Clojure, temos ou não um grande problema?

Independentemente de seu relacionamento a bugs, a imutabilidade facilita a manutenção do código quando passamos a ter garantias que se alguém invocou uma função, ela não alterou os valores que você possuia em mãos.
 
Essa é uma das ideias vendidas como positivas pela imutabilidade. Como não nos preocupamos com o que foi alterado (pois nada foi), podemos olhar um código e mantê-lo com menos medo.
Alternativa correta
Imutabilidade causa mais erros (bugs) pois é mais difícil de trabalhar que com valores globais alteráveis como é padrão em outras linguagens.
 
Alternativa correta
Imutabilidade causa menos erros (bugs) pois garante que nada que não pode será alterado. Mas é mais lento pois cada alteração e reatribuição envolve uma cópia completa dos dados em memória.

@@08
Nesta aula aprendemos:

O que aprendemos nesta aula:
Mostrar uma mensagem na tela com o comando println;
Que toda invocação de função colocamos parênteses entre ela;
Definir uma variável global com (def sua variavel );
Criar um vetor (def exemploVetor ["1","2"]);
Que para o Clojure a vírgula é considerada um espaço;
Contar quantos elementos tem em um vetor com o count;
Adicionar elementos ao vetor com o conj;
Que o Clojure é imutável;
Criar função com defn.

#### 16/10/2023

@02-Simbolos e condicionais

@@01
Símbolos locais versus globais ao namespace e a existência de bigint e bigdecimal

Terminamos a aula anterior criando a seguinte função:
(defn valor-descontado
  "Retorna o valor com desconto de 10%."
  [valor-bruto]
  (* valor-bruto (- 1 0.10)))COPIAR CÓDIGO
Porém, essa operação (- 1 0.10) parece meio estranha, não? O 0.10 é o desconto que queremos aplicar, e talvez fosse melhor definirmos um símbolo que deixasse isso mais claro. Sendo assim, depois de passarmos o parâmetro [valor-bruto], usaremos (def desconto 0.10) para definirmos esse símbolo, que passaremos adiante na execução de `(* valor-bruto (- 1 desconto))

(defn valor-descontado
  "Retorna o valor com desconto de 10%."
  [valor-bruto]
  (def desconto 0.10)
  (* valor-bruto (- 1 desconto)))COPIAR CÓDIGO
Feito isso, se executarmos (valor-descontado 100), teremos como retorno 90.0, o que significa que nosso código continua funcionando. Lembra que, anteriormente, vimos que o def define algo que parece uma variável global no nosso namespace? Vamos testar isso chamando o símbolo desconto. Como retorno, teremos 0.1.

Acabamos de descobrir uma péssima infração das boas práticas em programação, afinal definimos um símbolo global dentro de uma função. Isso inclusive poderia significar que esse símbolo talvez já existisse, e nós somente o redefinimos, pois não temos controle total do nosso espaço de desenvolvimento para termos certeza que um símbolo desconto já não havia sido definido.

O ideal seria definirmos um símbolo local ao espaço da função (valor-descontado), e não a todo o nosso namespace. Para isso, ao invés de utilizarmos def, que define um símbolo, usaremos let, que recebe um vetor - no nosso caso, [desconto 0.10].

(defn valor-descontado
  "Retorna o valor com desconto de 10%."
  [valor-bruto]
  (let [desconto 0.10])
  (* valor-bruto (- 1 desconto)))COPIAR CÓDIGO
Se executarmos esse código no nosso interpretador e em seguida chamarmos (valor-descontado 100), tudo parecerá funcionará como esperado. Porém, se sairmos do interpretador ("Ctrl + D", "Ctrl + C" ou "exit", dependendo do seu sistema operacional) e tentaros definir a função (valor-descontado) novamente, receberemos um erro informando que não foi possível resolver o símbolo desconto nesse contexto.

Por que isso acontece? Estamos em um espaço de memória completamente novo, e o let define a variável desconto somente dentro do próprio parênteses. Sendo assim, quando tentamos usar o símbolo desconto em (* valor-bruto (- 1 desconto)), ele não existe. Ou seja, o let trabalha apenas em escopo local.

Pensando nisso, por que o nosso código havia funcionado anteriormente? Na realidade, havíamos definido um símbolo chamado desconto anteriormente, de maneira global, e ele ficou na memória. Isso também serve para nos mostrar o quão inapropriado é criarmos um símbolo global quando queremos trabalhar localmente, gerando efeitos inesperados no nosso código.

Para resolver esse problema, o (let) recebe, depois do símbolo que irá definir, uma série de instruções a serem executadas. Sendo assim, passaremos a função (* valor-bruto (- 1 desconto)) para dentro dele.

(defn valor-descontado
  "Retorna o valor com desconto de 10%."
  [valor-bruto]
  (let [desconto 0.10]
  (* valor-bruto (- 1 desconto))
 )
)

(valor-descontado 100)COPIAR CÓDIGO
Assim, o (let) executará as operações dentro dele e retornará o resultado da última instrução. Como só temos uma instrução, ele retornará o desconto. Se executarmos o código acima, teremos como retorno 90.0, do jeito que esperávamos. Para testarmos a devolução do (let) e a existência do símbolo desconto na memória, faremos um println com a mensagem "Calculando desconto de" e o símbolo desconto.

(defn valor-descontado
  "Retorna o valor com desconto de 10%."
  [valor-bruto]
  (let [desconto 0.10]
  (println "Calculando desconto de" desconto)
  (* valor-bruto (- 1 desconto))
   )
   )

(valor-descontado 100)COPIAR CÓDIGO
Como retorno, teremos:

Calculando desconto de 0.1
90.0

Ou seja, o retorno do (let) é o resultado da última instrução, nesse caso o desconto. Note também que o retorno do (println) é ignorado. Se quisermos, podemos adicionar uma série de instruções ao nosso (let).

Os vários parênteses no nosso código nos remetem a diversas outras linguagens, mas devemos tomar cuidado com a organização do código, que é diferente no Clojure. Normalmente abrimos parênteses em uma nova linha, exceto em instruções curtas, ainda que algumas pessoas (como nosso instrutor) prefiram não fazer isso. Já o fechamento dos parênteses é feito todo na mesma linha.

(defn valor-descontado
  "Retorna o valor com desconto de 10%."
  [valor-bruto]
  (let [desconto 0.10]
    (println "Calculando desconto de" desconto)
    (* valor-bruto (- 1 desconto))))COPIAR CÓDIGO
Uma das vantagens dessa formatação é deixar bastante claro qual é a última instrução de retorno que está sendo executada. Lembre-se que a ideia não é mantermos menos caracteres, afinal um Enter a mais ou a menos não faz diferença nenhuma no nosso código, mesmo visualmente.

Para continuarmos nossos testes, vamos alterar o valor do desconto para (/ 10 100), ou seja, 10 dividido por 100, que é 10%.

(defn valor-descontado
  "Retorna o valor com desconto de 10%."
  [valor-bruto]
  (let [desconto (/ 10 100)]
    (println "Calculando desconto de" desconto)
    (* valor-bruto (- 1 desconto))))COPIAR CÓDIGO
Se executarmos esse código e rodarmos (valor descontado 100), teremos como retorno:

90N
No exemplo anterior, nossa devolução era 90.0, um Double. Uma maneira de confirmarmos isso é com a função (class 90.0), que nos retornará java.lang.Double. Mas e o 90N? Se verificarmos com (class 90N), veremos que ele é um clojure.lang.BigInt. Ou seja, o Clojure nos dá suporte para trabalhar com BigInt e BigDecimal diretamente. Ainda assim, existem alguns cuidados importantes que devemos tomar quando trabalhamos com eles. Como por padrão não faremos isso, não precisarems nos preocupar tanto.

Assim como N representa o BigInt, o M representa o BigDecimal, que trabalha com precisões maiores (até infinita) de números com ponto flutuante.

Com isso, aprendemos a trabalhar com funções que lidam com símbolos locais no Clojure, evitando símbolos locais que atrapalhem o nosso espaço de desenvolvimento. Claro, ainda há muito o que aprender dessa linguagem, e continuaremos nossos estudos no próximo vídeo.

@@02
Let múltiplo e condicionais

Vamos continuar trabalhando na função que criamos anteriormente, explorando alguns casos diferentes. Começaremos alterando o símbolo desconto, que criamos usando o let, para taxa-de-desconto, de modo a deixarmos mais claro o que ele representa. Já o desconto real é a multiplicação de 0.1 pelo valor bruto. Sendo assim, da mesma maneira que definimos o símbolo taxa-de-desconto, podemos definir outros símbolos usando o let novamente, por exemplo o nosso desconto.
(defn valor-descontado
  "Retorna o valor com desconto de 10%."
  [valor-bruto]
  (let [taxa-de-desconto (/ 10 100)]
    (let [desconto (* valor-bruto taxa-de-desconto)])
    (println "Calculando desconto de" desconto)
    (* valor-bruto (- 1 desconto))))
COPIAR CÓDIGO
Com isso teríamos vários (let), um dentro do outro. Mas será que essa é a melhor forma? Na verdade, como o (let) é um vetor, ele pode receber mais de dois valores. Pensando nisso, além da taxa-de-desconto, poderemos definir o desconto como sendo a multiplicação de valor-bruto pela taxa-de-desconto.

(defn valor-descontado
  "Retorna o valor com desconto de 10%."
  [valor-bruto]
  (let [taxa-de-desconto (/ 10 100)
        desconto (* valor-bruto taxa-de-desconto)]
    (println "Calculando desconto de" desconto)
    (* valor-bruto (- 1 desconto))))COPIAR CÓDIGO
Assim, se o valor-bruto for 100, o desconto será a multiplicação de 0.1 por 100, que é 10. Por último, substituiremos 1 pelo valor-bruto.

(defn valor-descontado
  "Retorna o valor com desconto de 10%."
  [valor-bruto]
  (let [taxa-de-desconto (/ 10 100)
        desconto (* valor-bruto taxa-de-desconto)]
    (println "Calculando desconto de" desconto)
    (* valor-bruto (- valor-bruto desconto))))COPIAR CÓDIGO
Se definirmos a função dessa forma e executarmos (valor-descontado) no terminal, teremos como retorno... 9000N? Isso acontece pois nos esquecermos de remover o trecho (* valor bruto (...)), que faz uma multiplicação extra dos nossos valores.

(defn valor-descontado
  "Retorna o valor com desconto de 10%."
  [valor-bruto]
  (let [taxa-de-desconto (/ 10 100)
        desconto (* valor-bruto taxa-de-desconto)]
    (println "Calculando desconto de" desconto)
    (- valor-bruto desconto)))COPIAR CÓDIGO
Feita a redefinição, se executarmos (valor-descontado 100), o retorno será 90N, do jeito que esperávamos. Com isso, aprendemos que o (let) pode definir vários símbolos.

Agora determinaremos que a nossa regra de desconto só será aplicada para valores acima de 100 reais, excluindo o próprio 100. Mas como fazer um if em Clojure? Existem várias maneiras de trabalharmos com condições, e a mais simples delas é o (if), um condicional após o qual escrevemos uma situação que devolverá verdadeiro (true) ou falso (false), da mesma forma que estamos acostumados a trabalhar.

Por exemplo, se executarmos (> 500 100), teremos como retorno true, afinal 500 é maior do que 100. Já se executarmos (< 500 100), o retorno será false, pois essa condição não é verdadeira. Criaremos então o nosso (if) dessa maneira, verificando se 500 é maior do que 100 e, em caso positivo, imprimindo na tela a mensagem maior.

(if (> 500 100)
  (println "maior"))COPIAR CÓDIGO
Executando esse código, teremos como retorno:

maior
nil

No Clojure, o (if) recebe três argumentos: a verificação, o que deverá ser executado caso o retorno da verificação seja verdadeiro, e o que deverá executado caso esse retorno seja falso, da mesma maneira que o else já conhecido da programação. Para testarmos, vamos verificar se o número 500 é maior do que 100.

(if (> 50 100)
  (println "maior")
  (println "menor ou igual"))COPIAR CÓDIGO
Como retorno, teremos "menor ou igual", assim como esperávamos. Agora, queremos que a nossa função (valor-descontado) retorno o valor com desconto de 10% se o valor bruto for estritamente maior que 100, deixando claro que 100 não está incluso. Para isso, depois de recebermos o [valor-bruto], abriremos o nosso (if) e incluiremos nele a nossa verificação. Em seguida, como retorno em caso verdadeiro, executaremos todo o código a partir do (let).

(defn valor-descontado
  "Retorna o valor com desconto de 10% se o valor bruto for estritamente maior que 100."
  [valor-bruto]
  (if (> valor-bruto 100)
    (let [taxa-de-desconto (/ 10 100)
          desconto (* valor-bruto taxa-de-desconto)]
      (println "Calculando desconto de" desconto)
      (- valor-bruto desconto))))COPIAR CÓDIGO
Feita essa definição, se executarmos (valor-descontado 1000), teremos como retorno 900. Já se executarmos (valor-descontado 100), o retorno será nil. Isso porque o (if) executa um código em caso verdadeiro, mas não definimos nada para ser executado em caso falso, e, por padrão, a devolução é nula. Além disso, o nil é considerado falso, algo que pode ser verificado com o seguinte exemplo:

(if nil "verdadeiro" "falso")COPIAR CÓDIGO
Como esperado, o retorno dessa execução será "falso". Isso pode nos ajudar bastante quando queremos verificar se algo existe,já que, na prática, qualquer coisa exceto false e nil é considerado verdadeiro (true) pelo (if).

Como não queremos que a devolução do nosso (valor-descontado) para valores brutos menores ou iguais a 100 seja nula, vamos definir que, nesses casos, deveremos receber somente o valor-bruto.

(defn valor-descontado
  "Retorna o valor com desconto de 10% se o valor bruto for estritamente maior que 100."
  [valor-bruto]
  (if (> valor-bruto 100)
    (let [taxa-de-desconto (/ 10 100)
          desconto (* valor-bruto taxa-de-desconto)]
      (println "Calculando desconto de" desconto)
      (- valor-bruto desconto))
      valor-bruto))COPIAR CÓDIGO
Note que está ficando cada vez mais difícil trabalhar com o encadeamento de parênteses, mas isso é algo que resolveremos mais tarde, quando passarmos para uma IDE. Depois de definirmos a função, se executarmos (valor-descontado 1000), o retorno será 900N. Já se executarmos (valor-descontado 100), o retorno será 100.

Voltando à questão da dificuldade de lidar com esse volume de código da maneira que estamos trabalhando agora, podemos salvar o arquivo como aula2.clj para que as funções, os operadores e os valores sejam coloridos, facilitando a leitura.

Porém, isso não é o suficiente, já que ainda é muito fácil errarmos os parênteses. Resolveremos esse problema trabalhando com IDEs como o IntelliJ, que nos dará suporte ao longo do curso.

@@03
Faça como eu fiz na aula

Chegou a hora de você seguir todos os passos realizados por mim durantes esta aula. Caso já tenha feito, excelente. Se ainda não, é importante que você implemente o que foi visto no vídeo para poder continuar com a próxima aula, que tem como pré-requisito todo o código aqui escrito. Se por acaso você já domina essa parte, em cada capítulo, você poderá baixar o projeto feito até aquele ponto.

O gabarito deste exercício é o passo a passo demonstrado no vídeo. Tenha certeza de que tudo está certo antes de continuar. Ficou com dúvida? Podemos te ajudar pelo nosso fórum.

@@04
Para saber mais

BigInt e BigDecimal não vão apresentar os tradicionais erros silenciosos de estouro em algumas linguagens, isto é, pegue o maior número de um Long e some 1, ele estoura. Pegue o menor Long possível e tire 1, ele estoura. Em Clojure, se o tipo de seu dado é Long ou Double ele automaticamente é passado para BigInt e BigDecimal, evitando tais situações, entre outros benefícios.

@@05
Para saber mais

Para ser um pouco mais formal, if é uma forma, não é uma função, e é uma forma especial https://clojure.org/reference/special_forms#if. Na prática, formas especiais podem ser utilizadas em nosso código e se misturam com as funções que invocamos em diversos momentos. Em geral, serão formas especiais aquelas que formam a base mínima da linguagem, uma maneira tradicional que as linguagens encontram para que a maior parte da mesma seja implementada na própria linguagem. Por exemplo, a função count é escrita na linguagem Clojure utilizando condicionais. Na prática, formas como if, let etc e funções aparecem misturadas em nosso código o tempo todo e por vício de linguagem é comum chamar uma forma especial de função, apesar da mesma ser uma forma.

https://clojure.org/reference/special_forms#if

@@06
Símbolos locais ou globais?

Em muitas linguagens é comum definirmos variáveis locais a um método, ou globais a um namespace ou todo o código. Em Clojure, assim como em outras linguagens, qual a boa prática considerando símbolos locais e globais?

Usar símbolos globais sempre que possível, facilitando o acesso ao que é necessário de qualquer ponto da aplicação.
 
Se qualquer ponto da aplicação pode ler um valor, mesmo que somente leitura, você perde controle do que pode ser feito com ele. Perda de encapsulamento.
Alternativa correta
Usar símbolos globais somente quando locais não resolvem o problema, raramente.
 
É muito raro ser necessário um símbolo global que represente valores que não são funções. Isso acontece da mesma forma que em outras linguagens de programação tentamos evitar variáveis globais o máximo possível. Quanto maior o escopo, menor o controle, mais difícil de manter o código.
Alternativa correta
Usar símbolos locais somente quando globais não resolvem o problema.
 
Quase sempre podemos usar símbolos globais, mas o código resultante fica uma bagunça, uma vez que não controlamos o que está sendo acessado.

@@07
O que aprendemos?

O que aprendemos nesta aula:
Definir uma variável com def ela tem o escopo global, dependendo do namespace;
Algumas boas práticas com Clojure;
Criar uma variável de escopo local com o let;
Utilizar o class para descobrir o tipo da variável;
Trabalhar com condicionais if;
Que o nil(Nulo) é considerado false dentro do if.



```
(defn valor-descontado 
  "Retorna o valor com desconto de 10%"
  [valor-bruto]
  (def desconto 0.10)
  (* valor-bruto (- 1 desconto))
)

(valor-descontado 100)
```

```
(defn valor-descontado 
  "Retorna o valor com desconto de 10%"
  [valor-bruto]
  (let [desconto 0.10])
  (* valor-bruto (- 1 desconto))
)

(valor-descontado 100)
```

```
(defn valor-descontado 
  "Retorna o valor com desconto de 10%"
  [valor-bruto]
  (let [desconto 0.10]
  (* valor-bruto (- 1 desconto))
  )
)

(valor-descontado 100)
```

```
(defn valor-descontado 
  "Retorna o valor com desconto de 10%"
  [valor-bruto]
  (let [desconto 0.10]
  (println "Calculando desconto de" desconto)
  (* valor-bruto (- 1 desconto))
  )
)

(valor-descontado 100)
```

```
(defn valor-descontado 
  "Retorna o valor com desconto de 10%"
  [valor-bruto]
  (let [desconto (/ 10 100)]
  (println "Calculando desconto de" desconto)
  (* valor-bruto (- 1 desconto))
  )
)

(valor-descontado 100)
```

```
(defn valor-descontado 
  "Retorna o valor com desconto de 10%"
  [valor-bruto]
  (let [taxa-de-desconto (/ 10 100)
    desconto (* valor-bruto taxa-de-desconto)]
    (println "Calculando desconto de" desconto)
    (- valor-bruto desconto))
)

(valor-descontado 100)
```

#### 17/10/2023

@03-Primeiros passos com colecoes

@@01
Projeto da aula anterior

Caso queira, você pode baixar o projeto do curso no ponto em que paramos na aula anterior.

https://github.com/alura-cursos/clojure-introducao/archive/aula2.zip

@@02
Intellij, Leiningen e atalhos

Como citado na aula anterior, a partir de agora passaremos a utilizar o IntelliJ IDEA, uma IDE que possui uma versão paga e outra gratuita para comunidades, e ambas podem ser baixadas aqui.
Após a instalação, da primeira vez que o IntelliJ é executado, seremos perguntados se queremos importar as configurações de algum outro lugar. Se é a primeira vez que você está trabalhando com essa IDE, não recomendamos fazer isso, para que você já se acostume a essa configuração. Na tela de customização, podemos clicar em "Skip Remaining and Set Defaults" para mantermos a configuração padrão.

Para podermos trabalhar com o Clojure, será necessário instalarmos um plugin chamado "Cursive", o que pode ser feito acessando "Configure > Plugins" e pesquisando por esse nome. Após a instalação, reiniciaremos a IDE.

Criaremos então um novo projeto do tipo "Clojure", selecionando essa opção do lado esquerdo da tela. Dentro dela, usaremos o Leiningen, uma ferramenta que serve para criar aplicativos do tipo Clojure. O nome do projeto será "estoque" e criaremos um diretório "curso" para salvá-lo, clicando em "Finish" em seguida.

Na nova tela, fecharemos as janelas desnecessárias. Repare que, no canto inferior direito da tela, algumas atualizações do Cursive estarão sendo feitas em segundo plano. Para acessarmos o nosso projeto, clicaremos em "1.Project" no canto esquerdo da tela. Logo no início, teremos somente um arquivo curso.iml dentro do diretório escolhido. À medida em que o projeto é propriamente criado, os outros arquivos e diretórios aparecerão na tela.

Ao longo do curso trabalharemos somente com aquilo que é interessante para nossos estudos. Em "src", por exemplo, colocaremos o nosso código fonte. Dentro dessa pasta, teremos um diretório "curso" contendo um arquivo core.clj, o núcleo do projeto, onde poderemos trabalhar. Por enquanto ele contém uma definição simples de uma função (foo), que removeremos.

(ns curso.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
COPIAR CÓDIGO
Acessando "IntelliJ IDEA > Preferences" no Mac ou "File > Settings" no Windows, podemos fazer algumas configurações de visualização, como aumentar o tamanho da fonte da interface ou do editor de texto. Também é possível utilizar o atalho "Ctrl + Shift + A", ou "Command + Shift + A", para acessar o buscador dessa IDE. Depois disso, podemos pesquisar por "Increase Font Size" para aumentar em dois pontos a fonte. Esse buscador também pode ser acessado com "Shift > Shift".

Note que o arquivo curso.core está utilizando o namespace curso.core. Sendo assim, da mesma forma que quando trabalhamos com o namespace user no terminal, todo o código que escrevermos nesse arquivo estará em um espaço chamado curso.core, como os packages ou namespaces de outras linguagens.

Para começarmos a trabalhar, faremos um (println) da mensagem "Bem vindo ao sistema de estoque" e salvaremos as alterações com "Command + S" ou "Ctrl + S".

(ns curso.core)

(println "Bem vindo ao sistema de estoque")COPIAR CÓDIGO
Em seguida, clicaremos com o botão direito em project.clj e então em "Run 'REPL for curso'". O REPL (read-eval-print-loop) é como uma linha de comando na qual podemos executar e avaliar o nosso código durante o desenvolvimento. Já que ele funciona como um terminal, também podemos executar código diretamente da janela do REPL.

Quando executamos o REPL pela primeira vez, o curso.core foi carregado automaticamente, imprimindo na tela a mensagem que escrevemos. Sendo assim, todo o código que criarmos nesse arquivo será carregado toda vez que o REPL for executado.

Para continuarmos trabalhando, moveremos o arquivo aula2.clj, que criamos na aula anterior, para o diretório "src/curso". Nesse arquivo, podemos utilizar o ; para comentarmos uma linha, o que faremos com a instrução * valor-bruto 0.9.

Antes de executarmos o arquivo, é uma boa prática do Clojure colocarmos esse código em um namespace, cujo padrão é o nome do arquivo. Como estamos dentro do diretório "curso", nosso namespace (ns) será curso.aula2.

(ns curso.aula2)COPIAR CÓDIGO
Para carregarmos esse arquivo e executarmos todo o conteúdo contido nele no REPL, podemos utilizar o comando "Ctrl + Shift + F10" ou "Command + Shift + L". Devemos tomar alguns cuidados, por exemplo com o (aplica-desconto). Quando estávamos no terminal, o resultado das funções era mostrado. Agora, somente os valores que pedirmos para imprimir é que serão mostrados na tela.

(println (aplica-desconto 100))COPIAR CÓDIGO
Na interface encontraremos diversas opções de execução e configuração do REPL. Na janela dele, por exemplo, podemos limpar a tela clicando no ícone de lixeira. Essa IDE nos traz diversas vantagens para trabalharmos com o Clojure. Por exemplo, podemos selecionar um trecho de código e abrir parênteses para que ele seja automaticamente englobado por eles, facilitando a criação de novas funções.

Também é possível criarmos uma função, por exemplo (println), fora de uma instrução, e utilizarmos o atalho "Command + Shift + K" ("Alt + Shift + K", no Windows) para a "engolirmos" para dentro da função (println) recém-criada, o que é chamado de "slurp". Da mesma forma, o "Command + Shift + J" ("Alt + Shift + J") move a última fórmula para fora da função, o que chamamos de "barf". Esses e outros atalhos podem ser encontrados em "Edit > Structural Editing".

Se quisermos executar somente uma fórmula específica, colocamos o curso nela e utilizamos o atalho Command + Shift + P. Note que esse atalho executa o código já no escopo atual, nesse caso o namespace curso.aula2. Para utilizarmos esse namespace no REPL, podemos executar (use curso.aula2).

No Mac, o atalho "Command + 1" serve para ocultar a aba lateral do projeto, permitindo uma visualização melhor do nosso código. Vários desses atalhos podem ser alterados nas preferências do IntelliJ ("Preferences/Settings > Keymap").

No próximo vídeo voltaremos a trabalhar com código.

https://www.jetbrains.com/idea/download/

https://cursive-ide.com/userguide/

@@03
Predicados, when e binding em tempo de execução

Para continuarmos a trabalhar, criaremos um novo arquivo chamado aula3.clj, no qual adicionaremos o namespace curso.aula3, além da última versão da função (valor-descontado).
(ns curso.aula3)

(defn valor-descontado
  "Retorna o valor com desconto de 10% se o valor bruto for estritamente maior que 100."
  [valor-bruto]
  (if (> valor-bruto 100)
    (let [taxa-de-desconto (/ 10 100)
          desconto         (* valor-bruto taxa-de-desconto)]
      (println "Calculando desconto de " desconto)
      (- valor-bruto desconto))
    valor-bruto))

(valor-descontado 1000)

(valor-descontado 100)COPIAR CÓDIGO
Antes de prosseguirmos, removeremos o (println) da nossa função, que estávamos utilizando somente para entendermos o que estava acontecendo na execução. Adicionaremos então o (println) às linhas que executam (valor-descontado 1000) e (valor-descontado 100).

(defn valor-descontado
  "Retorna o valor com desconto de 10% se o valor bruto for estritamente maior que 100."
  [valor-bruto]
  (if (> valor-bruto 100)
    (let [taxa-de-desconto (/ 10 100)
          desconto         (* valor-bruto taxa-de-desconto)]
      (- valor-bruto desconto))
    valor-bruto))

(println (valor-descontado 1000))

(println (valor-descontado 100))COPIAR CÓDIGO
Agora vamos propor uma pergunta: na prática, é comum fazermos tanta coisa dentro de uma única função? Na verdade depende. Em qualquer linguagem de programação, uma função que tem muitos caminhos lógicos tem alta complexidade de compreensão - existe até um termo formal, "complexidade ciclomática", que aparece nos nossos cursos de qualidade de código.

A complexidade ciclomática faz com que seja difícil entender o que está acontecendo na nossa função, afinal, ela inclui um if e um else, além da definição de duas variáveis, operações matemáticas e impressão de dados na tela. Podemos julgar que essa complexidade é razoável e manter a função como está, mas também é possível quebrá-la em pedaços menores.

Algumas vantagens de trabalharmos com funções pequenas são a reutilização, a composição e a facilidade de compreensão. Pensando nesses pontos, a ideia é extrairmos o (if) para uma nova função.

É comum que funções que devolverão um comportamento do tipo "verdadeiro ou falso" (mas não necessariamente true ou false) tenham um ponto de interrogação no final. Sendo assim, faremos (defn deve-aplicar-desconto?) para criarmos uma função com esse nome. Ela deverá receber um [valor-bruto] e, em seguida, verificar ((if)) se valor-bruto é maior do que 100. Em caso positivo, ela retornará true, e em caso negativo retornará false.

Para testarmos, faremos um (println) da execução de deve-aplicar-desconto? para os valores 1000 e 100.

(defn deve-aplicar-desconto?
  [valor-bruto]
  (if (> valor-bruto 100)
    true
    false))
(println (deve-aplicar-desconto? 1000))
(println (deve-aplicar-desconto? 100))COPIAR CÓDIGO
Repare que, agora que estamos utilizando a IDE, escrever esse código ficou bem mais simples, já que o alimento é feito automaticamente e recebemos sugestões de autopreenchimento ao longo do processo. Uma dica: com a tecla "TAB" podemos confirmar a opção selecionada de autopreenchimento.

Ao executarmos, receberemos na tela true e false, o que indica que nosso código está funcionando. Funções que devolvem esses valores, e que normalmente recebem ponto de interrogação, são chamadas de "Predicates". Pensando sobre sua nomenclatura, podemos renomeá-la como aplica-desconto?, que já passa a ideia de uma verificação do tipo "verdadeiro ou falso".

; PREDICATE
(defn aplica-desconto?
  [valor-bruto]
  (if (> valor-bruto 100)
    true
    false))
(println (aplica-desconto? 1000))
(println (aplica-desconto? 100))
(deve-aplicar-desconto?)COPIAR CÓDIGO
Vamos então redefinir a função (valor-desconto). Agora, ao invés de fazermos a verificação (> valor-bruto 100), simplesmente chamaremos a função (aplica-desconto) passando como argumento o valor-bruto.

; PREDICATE
(defn aplica-desconto?
  [valor-bruto]
  (if (> valor-bruto 100)
    true
    false))
(println (aplica-desconto? 1000))
(println (aplica-desconto? 100))

(defn valor-descontado
  "Retorna o valor com desconto de 10% se o valor bruto for estritamente maior que 100."
  [valor-bruto]
  (if (aplica-desconto? valor-bruto)
    (let [taxa-de-desconto (/ 10 100)
          desconto         (* valor-bruto taxa-de-desconto)]
      (- valor-bruto desconto))
    valor-bruto))

(println (valor-descontado 1000))

(println (valor-descontado 100))
COPIAR CÓDIGO
Ao longo da escrita do código, repare que, enquanto não passamos um parâmetro valor-bruto para a função (aplica-desconto?), sua chamada é sublinhada em vermelho. Colocando o mouse sobre ela, a mensagem "Incorret arity 0 for curso.aula?/aplica-desconto?" será exibida. "Arity", ou "aridade", é o número de parâmetros passados, e "aridade 0" se refere à primeira posição, ou seja, ao primeiro parâmetro. Se segurarmos "Command" (ou "Ctrl" no Windows) e clicarmos na chamada da função, seremos levados diretamente ao código fonte dela.

Como resultado da execução desse código, os valores 900N e 100 serão exibidos corretamente na tela. Nossa aplicação funcona, mas o (if) está retornando true e false. Existem casos em que é muito comum que a devolução da situação contrária, ou seja, false, seja na verdade nulo, como no exemplo:

; PREDICATE
(defn aplica-desconto?
  [valor-bruto]
  (if (> valor-bruto 100)
    true))
(println (aplica-desconto? 1000))
(println (aplica-desconto? 100))
COPIAR CÓDIGO
Ao executarmos o código dessa forma, receberemos true e nil. Vamos verificar se a função (valor-descontado) continua funcionando dessa forma. Antes disso, para garantirmos que a redefinição de (aplica-desconto?) está sendo chamada, adicionaremos um (println) com a mensagem "chamando a versão redefinida".


; PREDICATE
(defn aplica-desconto?
  [valor-bruto]
  (println "chamando a versão redefinida")
  (if (> valor-bruto 100)
    true))
(println (aplica-desconto? 1000))
(println (aplica-desconto? 100))

(println (valor-descontado 1000))
(println (valor-descontado 100))COPIAR CÓDIGO
Executando o código, as últimas chamadas no console serão:

chamando a versão redefinida
true

chamando a versão redefinida

nil

chamando a versão redefinida

900N

chamando a versão redefinida

100

Ou seja, nossa função está sendo chamada corretamente. Isso acontece pois, por mais que (valor-descontado) tenha sido definida anteriormente, a chamada de (aplica-desconto?) é feita em tempo de execução, e não em tempo de compilação do código.

Continuando, utilizar somente o (if), sem um else, é tão comum que existe o operador when, que devolve somente o caso verdadeiro.

; PREDICATE
(defn aplica-desconto?
  [valor-bruto]
  (println "chamando a versão redefinida")
  (when (> valor-bruto 100)
    true))
(println (aplica-desconto? 1000))
(println (aplica-desconto? 100))

(println (valor-descontado 1000))
(println (valor-descontado 100))COPIAR CÓDIGO
È uma outra maneira de construirmos o código, que funciona exatamente da mesma maneira. Mas se somente estamos devolvendo true ou false na verificação (> valor-bruto 100), por que não simplesmente chamamos essa verificação?

; PREDICATE
(defn aplica-desconto?
  [valor-bruto]
  (println "chamando a versão redefinida")
  (> valor-bruto 100))

(println (aplica-desconto? 1000))
(println (aplica-desconto? 100))

(println (valor-descontado 1000))
(println (valor-descontado 100))COPIAR CÓDIGO
Assim, de maneira mais econômica e direta, executaremos tudo o que gostaríamos. Para limparmos ainda mais o código, removeremos o (println).

; PREDICATE
(defn aplica-desconto?
  [valor-bruto]
  (> valor-bruto 100))COPIAR CÓDIGO
É importante citar que o modo de edição automática dos parênteses que o IntelliJ nos proporciona às vezes pode atrapalhar. Por exemplo, no seguinte cenário esquecemos de fechar o primeiro parênteses:

; PREDICATE
(defn aplica-desconto?
  [valor-bruto]
  (> valor-bruto 100)COPIAR CÓDIGO
Se tentarmos colocar o parênteses ao final, não funcionará. Isso porque, nesse modo de edição automática, não conseguimos fechar ou apagar os parênteses. Para solucionar eventuais prolemas com parênteses, uma "gambiarra" do nosso instrutor é copiar um parênteses fechado e colá-lo em seguida, já que o "paste" (colar ou "Ctrl + V") não é bloqueado. Da mesma forma, o "cut" (recortar, cujo atalho é "Command + X") também pode ser utilizado.

Agora imagine que o código ficou desalinhado, como no exemplo:

; PREDICATE
(defn aplica-desconto?
            [valor-bruto]
      (> valor-bruto 100))COPIAR CÓDIGO
Nessas situações, podemos clicar em "Code > Reformat Code" (atalho "Command + Alt + L") para que o código seja alinhado automaticamente.

@@04
Funções como parâmetros, funções anonimas e lambdas

Até agora nós compomos o comportamento da aplicação com uma função invocando a outra, e a busca de uma função por outra, o que chamamos de "lookup", é feito em tempo de execução. Porém, a chamada da função (aplica-desconto?) ocorre diretamente do código de (valor-descontado).
(defn valor-descontado
  "Retorna o valor com desconto de 10% se o valor bruto for estritamente maior que 100."
  [valor-bruto]
  (if (aplica? valor-bruto)
    (let [taxa-de-desconto (/ 10 100)
          desconto         (* valor-bruto taxa-de-desconto)]
      (- valor-bruto desconto))
    valor-bruto))

(println (valor-descontado 1000))
(println (valor-descontado 100))

; PREDICATE
(defn aplica-desconto?
  [valor-bruto]
  (> valor-bruto 100))

(println (aplica-desconto? 1000))
(println (aplica-desconto? 100))COPIAR CÓDIGO
Uma outra grande sacada da programação funcional, algo que encontramos no Clojure e também em outras linguagens, é trabalharmos com funções como se elas fossem "coisas". Ou seja, valor-descontado é um símbolo, ou uma "coisa", que referencia uma função e que pode ser passado para frente. Inclusive, podemos tentar fazer isso.

A função (aplica-desconto?), por exemplo, poderia se chamar (mais-caro-que-100?). Então, poderíamos ter uma função (valor-descontado) que, ao invés de receber somente o valor-bruto, só aplicaria esse desconto caso uma condição fosse verdadeira. Nesse caso, teríamos uma função aplica?.

(defn valor-descontado
  "Retorna o valor com desconto de 10% se o valor bruto for estritamente maior que 100."
  [aplica? valor-bruto]
  (if (aplica-desconto? valor-bruto)
    (let [taxa-de-desconto (/ 10 100)
          desconto         (* valor-bruto taxa-de-desconto)]
      (- valor-bruto desconto))
    valor-bruto))COPIAR CÓDIGO
Repare que o parâmetro (aplica?) será realçado em cinza, assim como (mais-caro-que-100?), indicando que eles não estão sendo utilizados. Chamaremos a função aplica? no nosso (if). A lógica da função (valor-descontado) então mudará para "Retorna o valor com desconto de 10% se deve aplicar desconto.", e definiremos se o desconto deverá ser aplicado chamando a função (aplica?).

(defn valor-descontado
  "Retorna o valor com desconto de 10% se deve aplicar desconto."
  [aplica? valor-bruto]
  (if (aplica? valor-bruto)
    (let [taxa-de-desconto (/ 10 100)
          desconto         (* valor-bruto taxa-de-desconto)]
      (- valor-bruto desconto))
    valor-bruto))

(println (valor-descontado 1000))
(println (valor-descontado 100))COPIAR CÓDIGO
Agora, (valor-descontado) precisará receber dois parâmetros, e acima estamos passando somente um. Corrigiremos isso passando a função mais-caro-que-100? como primeiro parâmetro. Assim, invocaremos o (valor-descontado) passando como parâmetro um símbolo que referencia uma função.

Para testarmos isso, antes da execução, adicionaremos um (println) em (mais-caro-que-100?) com a mensagem "deixando claro invocação de mais-caro-que-100?". Também faremos outro (println), dessa vez em (valor-descontado), logo antes de chamarmos essa função, com a mensagem "função como parâmetro".

(defn mais-caro-que-100?
  [valor-bruto]
  (println "deixando claro invocação de mais-caro-que-100?")
  (> valor-bruto 100))

(defn valor-descontado
  "Retorna o valor com desconto de 10% se deve aplicar desconto."
  [aplica? valor-bruto]
  (if (aplica? valor-bruto)
    (let [taxa-de-desconto (/ 10 100)
          desconto         (* valor-bruto taxa-de-desconto)]
      (- valor-bruto desconto))
    valor-bruto))

(println "função como parâmetro")
(println (valor-descontado mais-caro-que-100? 1000))
(println (valor-descontado mais-caro-que-100? 100))COPIAR CÓDIGO
Ao executarmos, as últimas saídas no console serão:

função como parâmetro
deixando claro invocação de mais-caro-que-100?

900N

deixando claro invocação de mais-caro-que-100?

100

Assim, aprendemos que funções podem funcionar da mesma forma que um número ou uma string, e que podemos invocá-las utilizando símbolos como referências. A abstração de uma função, que é o que fizemos agora, pode ser feita em várias linguagens. Em C#, por exemplo, temos situações análogas quando passamos um ponteiro para uma função, e em Java podemos passar referências de funções, como métodos estáticos ou métodos de objeto específicos.

Por isso, a definição de uma linguagem funcional costuma abranger mais conceitos do que simplesmente trabalhar com "Higher Order Functions", o tipo de funções que acabamos de aprender. Um desses conceitos é a imutabilidade, que trabalhamos nos nossos vetores e que veremos mais no futuro.

Ainda sobre as funções, repare que (mais-caro-que-100?) é uma função bastante simples, e inclusive é possível encontrá-la escrita da seguinte forma:

(defn mais-caro-que-100?  [valor-bruto]  (> valor-bruto 100))COPIAR CÓDIGO
Já que ela é tão simples, podemos imaginar que talvez ela seja utilizada uma única vez. Sendo assim, ao invés de criarmos a função e passá-la como parâmetro, será que poderíamos simplesmente chamar uma função sem nome, ou seja, uma função anônima? Isso é possível, por exemplo, por meio da construção (fn [valor-bruto] (> valor-bruto 100)).

(println "função sem nome")
(fn [valor-bruto] (> valor-bruto 100))
(println (valor-descontado mais-caro-que-100? 1000))
(println (valor-descontado mais-caro-que-100? 100))COPIAR CÓDIGO
Repare que estamos fazendo exatamente a mesma coisa que em (mais-caro-que-100?), mas dessa vez utilizando (fn) para definirmos uma função sem darmos um símbolo a ela, e simplesmente passando os parâmetros em seguida. Para testarmos, vamos passá-la diretamente como um dos parâmetros das nossas chamadas de (valor-descontado).

(println "função sem nome")

(println (valor-descontado (fn [valor-bruto] (> valor-bruto 100)) 1000))
(println (valor-descontado (fn [valor-bruto] (> valor-bruto 100)) 100))COPIAR CÓDIGO
Executando o código, as últimas saídas no console serão:

função sem nome
900N

100

Funções anônimas (também chamadas de "funções lambda"), além de classes anônimas e outras "coisas" anônimas, podem ser encontradas tanto em Clojure quanto em outras linguagens. Uma das vantagens da construção acima, utilizando somente uma linha, é ser bastante curta. Porém, em contrapartida, estamos compondo com mais complexidade, dificultando a manutenção do código.

Entretanto, é possível nos depararmos com formas ainda mais curtas desse tipo de código. Por exemplo, o parâmetro valor-bruto poderia ser simplesmente chamado de v:

(println (valor-descontado (fn [v] (> v 100)) 1000))
(println (valor-descontado (fn [v] (> v 100)) 100))COPIAR CÓDIGO
Esse tipo de construção com funções anônimas não parece ideal, pois os argumentos e símbolos estão sendo cada vez mais abreviados para caberem em uma linha. Pode parecer legível em número de caracteres, mas não é em compreensão. Quer ver um exemplo ainda mais radical? Que tal, já que só temos um argumento, não declararmos esses argumentos e nem mesmo a função que queremos executar?

(println (valor-descontado #(> %1 100) 1000))
(println (valor-descontado #(> %1 100) 100))COPIAR CÓDIGO
Dessa forma, estamos utilizando a cerquilha (#) para definir a função e utilizando o primeiro argumento (%1) para executá-la. Se a função recebesse dois argumentos, usaríamos %1 e %2, e assim por diante. Executando esse código teremos o mesmo resultado encontrado anteriormente.

Mas não acabou! Se a função só recebe um argumento, podemos simplesmente passar o %.

(println (valor-descontado #(> % 100) 1000))
(println (valor-descontado #(> % 100) 100))COPIAR CÓDIGO
O resultado, como esperado, continuará o mesmo. A vantagem dessa sequência lógica é ter cada vez menos caracteres, mas a leitura é cada vez mais complexa e ambígua. Esse é um exemplo simples no qual temos somente uma comparação, mas podem existir funções lambda muito mais complexas.

Um possível "meio termo" seria a construção a seguir:

(def mais-caro-que-100?  (fn [valor-bruto] (> valor-bruto 100)))COPIAR CÓDIGO
Nela, estamos utilizando o def para definirmos o símbolo mais-caro-que-100? como a função que é executada em seguida. Poderíamos construir a mesma lógica da seguinte forma:

(def mais-caro-que-100?  #(> % 100))COPIAR CÓDIGO
Da mesma maneira, estamos definindo o símbolo mais-caro-que-100? como a função que é criada. Dessa forma, pelo menos teríamos um nome (ou símbolo) para a função lambda, e poderíamos chamá-la utilizando sua referência.

(def mais-caro-que-100?  #(> % 100))
(println (valor-descontado mais-caro-que-100? 1000))COPIAR CÓDIGO
Essas são várias maneiras de executarmos o mesmo código, todas elas possíveis devido às Higher Order Functions. Essas, sendo trabalhadas como "coisas", podem nos trazer vantagens e desvantagens.

@@05
Faça como eu fiz na aula

Chegou a hora de você seguir todos os passos realizados por mim durantes esta aula. Caso já tenha feito, excelente. Se ainda não, é importante que você implemente o que foi visto no vídeo para poder continuar com a próxima aula, que tem como pré-requisito todo o código aqui escrito. Se por acaso você já domina essa parte, em cada capítulo, você poderá baixar o projeto feito até aquele ponto.

O gabarito deste exercício é o passo a passo demonstrado no vídeo. Tenha certeza de que tudo está certo antes de continuar. Ficou com dúvida? Podemos te ajudar pelo nosso fórum.

@@06
Para saber mais

O Cursive possui dezenas de atalhos que nos ajudam no dia a dia. O guia de usuário deles possui diversas páginas que sugiro ler de vez em quando. Não precisa ler tudo de uma vez só. Uma vez por semana olha uma página nova de atalhos para você tentar incorporar ao seu dia a dia. Lembre-se que no dia a dia gastamos mais tempo pensando e elaborando o que fazer do que digitando, não estamos numa competição de digitação, mas provavelmente queremos produzir código bom que seja de manutenção boa a médio prazo. https://cursive-ide.com/userguide
Lembre-se que atalhos fundamentais serão o de reload: Command Shift L e de execução da linha base em que você está trabalhando Command Shift P.

https://cursive-ide.com/userguide

@@07
Para saber mais

Para explorar alguma coisa parece ser útil escrever código dentro de seu core, mas na prática como o comando lein run carrega e roda o seu código do core, não é interessante deixar código de teste ou exploração voando. Nossos cursos de testes ensinam onde colocamos testes automatizados e é comum explorarmos através do REPL. Pessoalmente, por vezes, gosto de explorar dentro de um arquivo qualquer onde escrevo código temporário, mas sempre antes de gerar uma aplicação final esse código de exploração é jogado fora.

@@08
Para saber mais

Funções são "coisas" é a maneira informal de dizer que aqui em Clojure funções são tratadas como algo muito importante, tão importantes quanto dados. Isto é, você pode trabalhar com símbolos que referenciam funções. Funções são "first class citizens". Inclusive você pode passá-las como argumento para outras funções, ou recebê-las como retorno de funções. Funções que recebem ou retornam funções são chamadas de "higher order functions".

@@09
Predicados

Qual função verifica se o valor é estritamente positivo?

(defn estritamente-positivo? [x] (> x 0))
 
Essa função recebe x e verifica se ele é estritamente positivo
Alternativa correta
(defn estritamente-positivo? % > 0)
 
Alternativa correta
(defn estritamente-positivo? [x] (x > 0))
 
Alternativa correta
(defn estritamente-positivo? [x] > 0)

@@10
O que aprendemos?

O que aprendemos nesta aula:
Utilizar o plugin Cursive;
O que é o namespace;
Atalhos do Intellij;
Utilizar o ; para comentar a linha;
O que são predicates;
Fazer uma função chamar a outra;
Criar uma função anônima;
Utilizar % para fazer um função lambda.
