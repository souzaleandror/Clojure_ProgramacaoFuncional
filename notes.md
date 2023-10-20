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

#### 18/10/2023

@04-Primeiros passos com colecoes

@@01
Projeto da aula anterior

Caso queira, você pode baixar o projeto do curso no ponto em que paramos na aula anterior.

https://github.com/alura-cursos/clojure-introducao/archive/aula3.zip

@@02
Vetores, get e updates

Continuando com o nosso recurso, repare que o IntelliJ, em algum momento, pode exibir notificações de eventos, como ter encontrado um repositório que ainda não foi indexado. Nesses casos, podemos simplesmente esconder os eventos. Para prosseguirmos, criaremos um novo arquivo aula4.clj no qual definiremos o namespace curso.aula4. Lembrando que se quisermos utilizar esse arquivo no REPL, precisamos chamar (use 'curso.aula4) para fazermos com que ele seja capaz de encontrar símbolos nesse namespace.
Nessa aula voltaremos a trabalhar com vetores. Começaremos definindo os precos de três produtos que possuímos.

(ns curso.aula4)

(def precos [30 700 1000])COPIAR CÓDIGO
Pressionando "Command + Shift + L" ("Alt + Shift + L" no Windows) nós carregaremos esse arquivo no REPL. Como utilizamos o namespace desse arquivo, conseguiremos imprimir o vetor precos corretamente. Repare que sempre preferiremos criar nossos códigos dentro do arquivo, ao invés de no REPL, para mantermos um histórico.

Talvez você se lembre de que podemos utilizar um vetor como se fosse uma função. Dessa forma, se executarmos (println (precos 0)), conseguiremos o valor da primeira posição nesse vetor - no caso, 30.

(def precos [30 700 1000])

(println (precos 0))COPIAR CÓDIGO
Também existe uma função chamada (get) que recebe uma coleção, nesse caso um vetor precos, e o índice que queremos buscar.

(def precos [30 700 1000])

(println (precos 0))
(println (get precos 0))COPIAR CÓDIGO
Assim como na execução anterior, conseguiremos como retorno o 30. Se pedirmos o índice 3, o retorno será 1000. Já se tentarmos um índice ao qual não temos excesso, por exemplo 17, utilizando o vetor como uma função, receberemos um erro padrão IndexOutOfBoundsException do Java. Porém, o (get) nos retornará nil.

Isso acontece pois o retorno vai depender da função que estivermos utilizando, ou seja, uma coisa é utilizarmos o vetor como função para acessar diretamente a posição 17 e retornar um valor, e outra é utilizarmos a função (get), que já possui um tratamento para valores que estão fora daquele escopo.

A função (get) também nos permite definir um valor padrão para ser devolvido caso aquele índice não exista, por exemplo 0:

(def precos [30 700 1000])

(println (precos 0))
(println (get precos 0))
(println (get precos 2))
; (println (precos 17))
(println "valor padrão nil" (get precos 17))
(println "valor padrão 0" (get precos 17 0))COPIAR CÓDIGO
Ainda que tenhamos um valor padrão, se passarmos um índice existente, o retorno será o valor atribuído a aquele índice. Assim, aprendemos que é possível trabalhar com vetores de maneiras diferentes. Quando queremos evitar exceptions, por exemplo, podemos utilizar o get().

Anteriormente, também aprendemos a utilizar o (conj), que adiciona um novo elemento ao final do vetor sem realmente atualizá-lo, afinal o vetor é imutável.

(print (conj precos 5))
(print precos)COPIAR CÓDIGO
[30 700 1000 5]
[30 700 1000]

Ao utilizarmos o (conj), devemos tomar cuidado com a ordem dos parâmetros: o vetor (ou coleção) deverá vir primeiro, do contrário teremos um erro. Observe:

(print (conj 5 precos))COPIAR CÓDIGO
Syntax error (ClassCastException) compiling at (aula4.clj:15:1). java.base/java.lang.Long cannot be cast to clojure.lang.IPersistentCollection
Repare que a interface de coleção do Clojure é chamada de "Persistent", no sentido de que ela persiste da maneira que está - ou seja, se refere à imutabilidade que já comentamos. Antes de continuarmos, podemos comentar essa última linha de código com o atalho "Command + /" ("Ctrl + /").

Que outros tipos de operações podemos fazer com conjuntos? Podemos, por exemplo, atualizar um elemento dentro do vetor. Imagine que queremos realizar uma soma. Para somarmos 1, por exemplo, podemos utilizar (+ 5 1), correto? Mas existe também outra forma de somarmos 1, ou seja, incrementarmos um valor, que é utilizando a função (inc).

(println (+ 5 1))
(print (inc 5))COPIAR CÓDIGO
6
6

Como visto, a função (inc) recebe um argumento e soma 1. Agora queremos atualizar o vetor precos chamando a função (inc) para a sua posição 0. Isso pode ser feito utilizando a função (update), que aplica uma função a um vetor no índice passado como parâmetro.

(println (update precos 0 inc))COPIAR CÓDIGO
Executando esse código, nosso retorno será o novo vetor atualizado:

[31 700 1000]
Porém, devemos nos lembrar de que nada altera o vetor original, pois nossas coleções são persistentes/imutáveis. Podemos confirmar isso chamando também (update precos 0 dec), que chamará a função (dec) para decrementar em 1 o valor original.

(println (update precos 0 inc))
(println (update precos 1 dec))COPIAR CÓDIGO
[31 700 1000]
[30 699 1000]

Como visto, as duas funções funções são executadas sobre o vetor precos original, de modo que o valor do índice 0 volta a ser 30 no retorno da segunda chamada.

Para vermos essa execução acontecendo na prática, usaremos o (defn) para criarmos a nossa própria função soma-1. Ela receberá como parâmetro um valor e executará (+ valor 1), além de fazer um (println) da mensagem "estou somando um em" seguida do valor.

Em seguida, chamaremos a função (update) passando a posição 0 do nosso vetor precos e a função soma-1 que acabamos de criar.

(defn soma-1
  [valor]
  (println "estou somando um em" valor)
  (+ valor 1))

(println (update precos 0 soma-1))COPIAR CÓDIGO
estou somando um em 30
[31 700 1000]

Como é possível notar, a função foi aplicada corretamente sobre o valor 30 e retornou um conjunto atualizado contendo [31 700 100]. Se quisermos, podemos repetir o processo com qualquer valor, por exemplo 3:

(defn soma-3
  [valor]
  (println "estou somando 3 em" valor)
  (+ valor 3))

(println (update precos 0 soma-3))COPIAR CÓDIGO
estou somando 3 em 30
[33 700 1000]

No próximo vídeo aplicaremos os conteúdos que vimos na aula 3 ao nosso conjunto de preços.

@@03
Map e filter

Para continuarmos nossos estudos, vamos pegar as definições de (aplica-desconto) e (valor-descontado) que fizemos anteriormente - nesse caso, versões mais antigas e mais simples, nas quais não estamos utilizando funções como argumentos.
; CODIGO DA AULA ANTERIOR

(defn aplica-desconto?
  [valor-bruto]
  (> valor-bruto 100))

(defn valor-descontado
  "Retorna o valor com desconto de 10% se o valor bruto for estritamente maior que 100."
  [valor-bruto]
  (if (aplica-desconto? valor-bruto)
    (let [taxa-de-desconto (/ 10 100)
          desconto         (* valor-bruto taxa-de-desconto)]
      (- valor-bruto desconto))
    valor-bruto))COPIAR CÓDIGO
Agora queremos aplicar a função (valor-descontado) para todos os preços. Para isso, em linguagens imperativas, costumamos criar um laço for de maneira bastante explícita, iterando pelos valores do conjunto, como em (for preco in precos). Esse é um ponto importante: pessoalmente, ao invés de discutir a diferença entre uma linguagem imperativa e declarativa, considero mais propício discutir a diferença entre a maneira implícita e a explícita. Isso porque a complexidade de um laço continua no código, e precisamos compreendê-la.

A maneira mais comum de aplicarmos uma função a todos os itens de um conjunto é utilizando a função (map). Nesse caso, iremos mapear a função (valor-descontado) para cada um dos precos.

(println "map" (map valor-descontado precos))COPIAR CÓDIGO
Por trás dos panos, de alguma forma - que por enquanto não importa -, iremos passar por todos os elementos de precos chamando o (valor-descontado). Como resultado, teremos:

(30 630N 900N)
Em 30 o desconto não foi aplicado, pois esse valor é menor do que 100. Já em 630N e 900N, o desconto foi aplicado corretamente. Assim, percebemos que a função (map) é bastante prática, executando valor-descontado (preco) para cada um dos elementos, de maneira análoga a uma função matemática (f (x)).

O (map) é bastante útil e muito utilizado, em geral em momentos nos quais queremos fazer um laço passando por todos os elementos e aplicando uma função. Claro, nem sempre o objetivo será executar somente o (map). E se quisermos, por exemplo, saber quais são os valores do vetor original precos aos quais devemos aplicar o desconto, filtrando-os?

Para pensarmos nesse problema, vamos executar a função (range) que devolve, de alguma forma, os valores de 0 até o valor passado como parâmetro, excluindo-o. Ou seja, (range 10) retornará os valores de 0 até 9.

Já para filtrarmos valores, podemos utilizar a função (filter), que, assim como (map), recebe como segundo parâmetro o vetor ou a sequência de valores em que será aplicada. Já o primeiro parâmetro é a função de filtragem a ser utilizada. Nesse exemplo, usaremos a função even? ("par?"), que recebe um parâmetro "n" e retorna os valores pares.

(println (range 10))
(println (filter even? (range 10)))COPIAR CÓDIGO
Como retorno desse código, teremos:

(0 1 2 3 4 5 6 7 8 9)
(0 2 4 6 8)

Ou seja, ao aplicarmos o filtro, passamos por cada elemento do conjunto (range 10) chamando a função even?. Se o valor devolvido por essa função é verdadeiro, ele é mantido; do contrário (se for nulo ou falso), ele é descartado. É isso que queremos fazer, filtrando os precos por meio de uma função que devolverá verdadeiro caso o desconto deva ser aplicado, e algo equivalente a falso caso não deva. Na verdade já temos essa função, que é (aplica-desconto?). Para testarmos, imprimiremos novamente nosso vetor original antes da execução do filtro.

(println "vetor" precos)
(println "filter" (filter aplica-desconto? precos))COPIAR CÓDIGO
Como retorno, teremos:

vetor [30 700 1000]
filter (700 1000)

Dessa forma, após a aplicação do filtro, o valor 30 foi descartado, afinal o retorno da função (aplica-desconto?) foi false. Poderíamos ainda aplicar o mapa após esse filtro, mostrando somente os valores com desconto dos produtos em que tal desconto foi aplicado.

(println "map após o filter" (map valor-descontado (filter aplica-desconto? precos)))COPIAR CÓDIGO
map após o filter (630N 900N)
Assim, estamos compondo por meio das funções (filter) e (map) uma lógica parecida com a que faríamos usando um laço for, algo muito comum no dia-a-dia.

@@04
Reduce e variações

Por fim, gostaríamos de apresentar uma terceira função que, junto com a (map) e a (filter), também é muito utilizada no dia-a-dia. Dado que o (map) pega uma sequência de elementos, por exemplo um vetor, e aplica uma função para cada um desses elementos, é possível transformar uma sequência em outra sequência. O (filter) pega uma sequência de elementos e aplica uma função para cada um deles, diminuindo o tamanho da sequência. Porém, no (filter) e no (map) não é possível fazer operações com mais de um elemento ao mesmo tempo (talvez por meio de alguma "gambiarra", mas é difícil).
Por exemplo, o (filter) consegue aplicar uma função para o primeiro, o segundo e o terceiro valor do conjunto precos, mas não consegue somar esses valores entre si. Mas e se quisermos fazer isso, obtendo o total dos nossos precos, ou mesmo a média entre eles?

A ideia é pegarmos uma sequência de elementos e reduzirmos o seu tamanho para um determinado número, como 1. A função que nos permite fazer isso se chama (reduce). Como de costume, ela recebe como segundo parâmetro a sequência (ou os valores) que queremos reduzir, e como primeiro parâmetro uma função que desejamos aplicar. Em nosso teste, usaremos a função de soma, +.

(println "vetor" precos)
(println (reduce + precos))COPIAR CÓDIGO
vetor [30 700 1000]
1730

Assim, conseguimos obter a soma total de todos os valores desse conjunto. Para isso, a função (reduce), de alguma maneira, aplica a função + aos elementos do vetor. Mas como? Vamos testar isso criando nossa própria função de soma, chamada (minha-soma). Essa função receberá como parâmetros dois valores, valor-1 e valor-2, imprimirá na tela a mensagem "somando" seguida desses valores e, por fim, executará a soma propriamente dita ((+ valor-1 valor-2)).

Em seguida, chamaremos a função (reduce) passando como parâmetros nosso conjunto precos e nossa função (minha-soma).

(defn minha-soma
  [valor-1 valor-2]
  (println "somando" valor-1 valor-2)
  (+ valor-1 valor-2))

(println (reduce minha-soma precos))COPIAR CÓDIGO
Executando esse código, as últimas saídas no console serão:

somando 30 700
somando 730 1000

1730

Ou seja, o (reduce) pegou os dois primeiros valores do vetor precos e chamou a função (minha-soma). Em seguida, pegou o resultado dessa função e chamo novamente minha-soma com o próximo valor. Se tivéssemos um vetor com mais elementos, esse procedimento continuaria a ser repetido. Para testarmos, chamaremos novamente o (reduce), dessa vez utilizando como conjunto o (range 10).

(println (reduce minha-soma (range 10)))COPIAR CÓDIGO
somando 0 1
somando 1 2

somando 3 3

somando 6 4

somando 10 5

somando 15 6

somando 21 7

somando 28 8

somando 36 9

45

E se passarmos um vetor contendo um único elemento? Vamos testar:

(println (reduce minha-soma [15]))COPIAR CÓDIGO
15
Nesse caso, a devolução é somente 15, sem que a função minha-soma sequer seja chamada. A função (reduce) também pode ser chamada com um parâmetro extra, que é o valor inicial. Como exemplo, passaremos o valor 0:

(println (reduce minha-soma 0 precos))COPIAR CÓDIGO
somando 0 30
somando 30 700

somando 730 1000

30

Repare que, dessa vez, o valor que passamos como parâmetro foi utilizado na primeira chamada de minha-soma, e a execução então seguiu com os outros elementos do conjunto. A mesma coisa acontece se chamarmos (println (reduce minha-soma 0 (range 10))):

somando 0 0
somando 0 1

somando 1 2

somando 3 3

somando 6 4

somando 10 5

somando 15 6

somando 21 7

somando 28 8

somando 36 9

45

E também se chamarmos (println (reduce minha-soma 0 [15])):

somando 0 15 15
E se passarmos um vetor sem nenhum elemento?

(println (reduce minha-soma 0 []))COPIAR CÓDIGO
Nesse caso, a devolução é somente o elemento inicial:

0
Já se tivermos uma sequência vazia e nenhum elemento inicial, teremos uma ArityException, já que não teremos passado o número adequado de parâmetros para a função.

(println (reduce minha-soma []))COPIAR CÓDIGO
Syntax error (ArityException) compiling at (aula4.clj:81:1). Wrong number of args (0) passed to: curso.aula4/minha-soma
O mesmo erro acontece se tentarmos passar como parâmetro um vetor que definimos como vazio:

(def vazio [])
(println (reduce minha-soma vazio))COPIAR CÓDIGO
Nesse caso, poderia ter sido definido que a sintaxe deveria ser suportada, devolvendo nulo, mas não é isso que acontece. Ou seja, é necessário passar pelo menos um valor para que o (reduce) funcione.

Com isso, aprendemos uma trinca de funções bastantes úteis para trabalharmos com coleções no nosso dia-a-dia: (map), (filter) e (reduce). Na documentação do Clojure é possível encontrar diversas outras funções e suas respectivas definições. Também podemos encontrar tais definições diretamente no código fonte em nossa IDE, segurando "Command" ou "Ctrl" e clicando na função para acessá-lo.

https://clojure.github.io/clojure/clojure.core-api.html

@@05
Faça como eu fiz na aula

Chegou a hora de você seguir todos os passos realizados por mim durantes esta aula. Caso já tenha feito, excelente. Se ainda não, é importante que você implemente o que foi visto no vídeo para poder continuar com a próxima aula, que tem como pré-requisito todo o código aqui escrito. Se por acaso você já domina essa parte, em cada capítulo, você poderá baixar o projeto feito até aquele ponto.


O gabarito deste exercício é o passo a passo demonstrado no vídeo. Tenha certeza de que tudo está certo antes de continuar. Ficou com dúvida? Podemos te ajudar pelo nosso fórum.

@@06
Para saber mais

É comum ver a apresentação de linguagens funcionais através das analogias e das ideias de funções matemáticas, com exemplos matemáticos. Nossa abordagem não é essa pois a maior parte das aplicações que você vai criar em Clojure serão com domínios do dia a dia, hospitais, bancos, ecommerces, lojas etc. Claro, a ideia matemática de uma função pura é muito importante e será tema recorrente dos cursos, cada vez nos mostrando um pouco mais dos ganhos que temos uma vez que as definições de símbolos serem a valores imutáveis em seus contextos, e funções que não causam efeitos colaterais em todo nosso sistema.

@@07
Erros

Como em qualquer linguagem de programação, um erro comum é como lidamos com... erros. Qual a forma mais segura de acessar a quinta posição de um vetor em Clojure?

(defn quinto-elemento [v] (v 5))
 
Alternativa correta
(defn quinto-elemento [v] (v 4))
 
Alternativa correta
(defn quinto-elemento [v] (get v 5))
 
Alternativa correta
(defn quinto-elemento [v] (get v 4))
 
Se v for nulo, teremos nulo. Se não existir tal elemento, teremos nulo. Caso contrário teremos o quinto elemento.

@@08
O que aprendemos?

O que aprendemos nesta aula:
Utilizar o get para evitar exceções;
Definir o valor padrão de retorno do get;
Utilizar a função inc para somar o número atual mais um;
Utilizar a função update para apenas retornar um vetor com um valor alterado;
Utilizar a função map para passar por todos os elementos;
Utilizar a função filter para fazer a filtragem de elementos;
Utilizar a função reduce para reduzir valores.

#### 19/10/2023

@05-Conhecendo mapas e threading

@@01
Projeto da aula anterior

Caso queira, você pode baixar o projeto do curso no ponto em que paramos na aula anterior.

https://github.com/alura-cursos/clojure-introducao/archive/aula4.zip

@@02
Mapas, vals e keys

Já aprendemos a trabalhar com os tipos escalares, como inteiros, strings e doubles, além de BigInts e BigDecimals, e vimos que, na documentação do Clojure, existem várias funções que nos permitem trabalhar com coleções/vetores. Também aprendemos que as sequências que estamos passando entre parênteses são, na verdade, listas, um outro tipo de coleção que pode ser trabalhada em Clojure.
Além dos vetores, existe um outro tipo de coleção, na qual associamos um valor a outro. Por exemplo, imagine que queremos comprar 10 mochilas e 5 camisetas. Dessa forma, queremos conectar os valores 10 e mochilas, e 5 e camisetas, em uma espécie de coleção associativa cuja implementação comum costuma ser um hashmap.

Para trabalharmos com esse tipo de mapa, criaremos um novo arquivo aula5.clj com o namespace curso.aula5. Nele, definiremos um estoque que consistirá em um mapa, cuja implementação no Clojure é feita com {}. Nesse mapa teremos os elementos "Mochila" 10 e "Camiseta" 5. Por fim, faremos um (println) do nosso estoque.

(ns curso.aula5)

(def estoque {"Mochila" 10 "Camiseta" 5})
(println estoque)COPIAR CÓDIGO
Executando esse códig,o teremos como saída:

{Mochila 10, Camiseta 5}
Repare que essa sintaxe é muito parecida com a de um vetor, com a diferença de que o primeiro valor está atrelado ao segundo, o terceiro ao quarto e assim por diante. Com quatro elementos é fácil entendermos o que está acontecendo no código, mas imagine que tenhamos uma lista com dezenas de valores? Nesse sentido, é uma questão de boa prática adicionarmos vírgulas entre os pares, ou mesmo pularmos linhas.

(def estoque {"Mochila" 10 "Camiseta" 5})

(println estoque)

(def estoque {"Mochila" 10, "Camiseta" 5})
(def estoque {"Mochila" 10
              "Camiseta" 5})

(println estoque)COPIAR CÓDIGO
Com qual uma dessas formas, o resultado impresso será sempre o mesmo. Assim como com outras coleções, existem funções que nos permitem trabalhar com esses valores. Por exemplo, podemos utilizar o (count) para recuperarmos o número de elementos no dicionário (outro nome que utilizamos para um mapa) estoque.

(def estoque {"Mochila" 10
              "Camiseta" 5})

(println estoque)

(println "Temos" (count estoque) "elementos")COPIAR CÓDIGO
Temos 2 elementos
Em questões de otimização, dependendo da coleção, a quantidade total de elementos poderá estar separada em outro lugar para não precisar ser calculada toda vez. Também podemos utilizar outras funções, como a (keys), que nos devolve as chaves que a coleção possui.

(println "Chaves são:" (keys estoque))COPIAR CÓDIGO
Chaves são: (Mochila Camiseta)
Outra função útil é a (vals), que nos retorna somente os valores da coleção.

(println "Valores são:" (vals estoque))COPIAR CÓDIGO
Valores são: (10 5)
Por padrão, não existe garantia da ordem que receberemos esses elementos, e eles estarem na mesma sequência que definimos é apenas coincidência. Essas e outras funções específicas podem ser consultadas na documentação do Clojure, na internet ou mesmo no código fonte.

Por enquanto, estamos trabalhando com strings como chaves de um mapa, o que, ainda que seja possível, não é comum. No dia-a-dia, costumamos trabalhar com :mochila, por exemplo, onde o símbolo : (dois pontos) é utilizado para definir uma palavra-chave (ou "keyword").

(def estoque {:mochila 10
              :camiseta 5})COPIAR CÓDIGO
Da mesma maneira que trabalhamos como vetores, é possível manipularmos esses mapas, adicionando, removendo, buscando ou alterando os elementos dentro deles. Não iremos passar por todas essas operações, mas aprenderemos a lidar com as principais, de modo que você estará preparado para explorar a documentação e criar seus próprios códigos em Clojure.

Primeiro, vamos adicionar um valor ao mapa estoque. Para isso, utilizaremos a função (assoc), de "associar", passando como argumento a chave :cadeira e o valor 3.

(println (assoc estoque :cadeira 3))COPIAR CÓDIGO
Como as coleções são persistentes por padrão, ao imprimirmos o retorno dessa função na tela receberemos um novo mapa contendo, agora, três elementos.

{:mochila 10, :camiseta 5, :cadeira 3}
Já se imprimirmos novamente o símbolo estoque, receberemos:

{:mochila 10, :camiseta 5}
Ou seja, ele continua referenciando o mapa original, devido á imutabilidade que já comentamos antes. Poderíamos também sobrescrever um valor pedindo a associação, no estoque, da chave :mochila com o novo valor 1.

(println (assoc estoque :mochila 1))COPIAR CÓDIGO
{:mochila 1, :camiseta 5}
A função (assoc) também pode ser utilizada com vetores ou outras coleções, mas devemos nos lembrar de consultar a documentação para entendermos as implicações em cada uma delas. A função (update), que já conhecemos anteriormente quando trabalhamos com vetores, também pode ser utilizada com esse tipo de dicionário. No caso, ela receberá como parâmetros a coleção a ser alterada, o índice que queremos atualizar e a função a ser aplicada, por exemplo o inc (de "increase").

(println (update estoque :mochila inc))COPIAR CÓDIGO
Com isso, aplicaremos a função inc ao valor que está dentro da chave :mochila, resultando em:

{:mochila 11, :camiseta 5}
Podemos analisar melhor a execução criando uma função (tira-um) que recebe um valor, imprime na tela a mensagem "tirando um de" seguida do valor recebido e, por fim, executa a subtração de valor por 1. Em seguida, executaremos novamente a função (update), dessa vez passando a recém-criada tira-um como parâmetro.

(defn tira-um
  [valor]
  (println "tirando um de" valor)
  (- valor 1))

(println (update estoque :mochila tira-um))COPIAR CÓDIGO
tirando um de 10
{:mochila 9, :camiseta 5}

Podemos inclusive executar uma função lambda que subtrai 3 do valor passado na chave (%).

(println (update estoque :mochila #(- % 3)))COPIAR CÓDIGO
{:mochila 7, :camiseta 5}
Por último, também poderíamos remover elementos utilizando a função (dissoc), que irá desassociar do estoque uma chave. Nesse caso, passaremos como parâmetro a chave :mochila, de modo que nosso retorno será somente a chave :camiseta.

(println (dissoc estoque :mochila))COPIAR CÓDIGO
{:camiseta 5}

@@03
Mapas aninhados update-in e threading first

Já aprendemos a trabalhar com um mapa simples e direto, que podemos chamar de "profundidade 1", no qual as chaves e os valores estão relacionados de maneira linear. Nesse caso, criaremos um mapa pedido contendo dois elementos, :mochila e :camiseta. Cada um desse elementos será também um mapa contendo as chaves :quantidade e :preco, por exemplo :quantidade 2, :preco 80 (duas mochilas, 80 reais cada uma).
Por fim, faremos a impressão desse pedido na tela. Para visualizarmos mais facilmente nossos dados, incluiremos um (println "\n\n\n\n"), adicionando algumas linhas em branco no console.

(def pedido {:mochila { :quantidade 2, :preco 80}
             :camiseta {:quantidade 3, :preco 40}})
(println "\n\n\n\n")
(println pedido)COPIAR CÓDIGO
{:mochila {:quantidade 2, :preco 80}, :camiseta {:quantidade 3, :preco 40}}
Com a função (assoc), podemos adicionar uma nova chave :chaveiro ao nosso pedido, passando também a chave :quantidade com o valor 1 e preco com o valor 10. Dessa vez, ao invés de simplesmente imprimirmos o retorno na tela, utilizaremos o (def) para redefinirmos o símbolo pedido com os novos valores.

(def pedido (assoc pedido :chaveiro {:quantidade 1, :preco 10}))
(println pedido)COPIAR CÓDIGO
{:mochila {:quantidade 2, :preco 80}, :camiseta {:quantidade 3, :preco 40}, :chaveiro {:quantidade 1, :preco 10}}
Quando estávamos trabalhando com vetores, vimos que era possível acessar valores utilizando esses vetores como se fossem funções. Da mesma forma, podemos utilizar um mapa como função, pedindo um valor em seguida.

(println (pedido :mochila))COPIAR CÓDIGO
Como retorno, teremos o conteúdo da chave passada por parâmetro.

{:quantidade 2, :preco 80}
Outra possibilidade para isso é utilizarmos a função (get).

(println (pedido :mochila))
(println (get pedido :mochila))COPIAR CÓDIGO
Em ambos os casos, o retorno será o mesmo:

{:quantidade 2, :preco 80}
{:quantidade 2, :preco 80}

Utilizando a função (get), é possível estabelecermos um valor padrão, como 0 ou um mapa vazio ({}). Se não passarmos um valor padrão, o retorno da chamada para a qual passamos uma chave inexistente será nulo (nil).

(println (get pedido :cadeira))
(println (get pedido :cadeira 0))
(println (get pedido :cadeira {}))COPIAR CÓDIGO
nil
0

{}

Uma terceira maneira de acessarmos uma chave dentro de um mapa é utilizando a própria chave como função. Isso é possível pois as keywords também implementam a interface IFN.

(println (:mochila pedido))COPIAR CÓDIGO
{:quantidade 2, :preco 80}
Se tentarmos chamar como função uma chave que não existe naquele mapa, o retorno será nulo. Da mesma forma que fizemos anteriormente, podemos passar um valor padrão para ser retornado ao invés desse nulo.

(println (:cadeira pedido))
(println (:cadeira pedido {}))COPIAR CÓDIGO
nil
{}

Dentre essas formas, a sintaxe (:mochila pedido), na qual utilizamos a chave como função, é a mais comum. Às vezes o (get) é utilizado para deixar explícito o que está acontecendo no código. Já a sintaxe (pedido :mochila) é bastante rara, já que receberemos uma NullPointerException caso o mapa seja nulo.

Se quisermos recuperar a :quantidade de :mochila no pedido, podemos utilizar a seguinte sintaxe:

(println (:quantidade (:mochila pedido)))COPIAR CÓDIGO
Dessa forma, invocaremos primeiro (:mochila pedido), recuperando as chaves e valores nesse mapa, e então (:quantidade) para recuperarmos o valor atribuído a essa chave, nesse caso 2.

Até o momento estávamos utilizando o (update) para alterarmos um valor no mapa. Porém, essa função recebia como parâmetros o mapa, a chave e a função a ser aplicada. Dessa forma, o que acontece se fizermos (update pedido :mochila inc)?

(println (update pedido :mochila inc))COPIAR CÓDIGO
Essa construção não faz sentido, afinal não é possível acrescentarmos 1 à :mochila, que possui :quantidade e :preco. Tanto é que, se executarmos esse código, receremos uma ClassCastException informando que a sintaxe não está adequada. Se quisermos acrescentar 1 à :quantidade, por exemplo, deveremos acessar essa chave dentro da chave :mochila, o que pode ser feito com a função (update-in).

(println (update-in pedido [:mochila :quantidade] inc))COPIAR CÓDIGO
{:mochila {:quantidade 3, :preco 80}, :camiseta {:quantidade 3, :preco 40}, :chaveiro {:quantidade 1, :preco 10}}
Além da (update-in), outras funções, como (get-in), nos permitem acessar níveis mais profundos dos nossos mapas.

Existe também outra maneira de navegarmos para dentro de um mapa. Repare nesse encadeamento:

(println (:quantidade (:mochila pedido)))COPIAR CÓDIGO
Aqui, é como se estivéssemos passando pelo pedido para conseguirmos a :mochila, e então passando pela :mochila para pegarmos a :quantidade. Isto é, estamos executando uma função com o valor de retorno de outra função, e assim sucessivamente. Esse tipo de encadeamento é chamado de "threading", em uma analogia com o passar de um feio pelo tecido utilizando uma agulha.

Para iniciarmos o threading, utilizamos o operador -> passando o primeiro elemento sobre o qual gostaríamos de executar uma função, no caso pedido. Em seguida, passamos a função a ser executada, que será :mochila, e a função a ser executada sobre o resultado dela, ou seja, :quantidade.

(println (-> pedido
               :mochila
               :quantidade))COPIAR CÓDIGO
2
Pode parecer estranho, mas essa é a maneira tradicional de invocarmos funções em todas as linguagens. Em orientação a objetos, por exemplo, pegaríamos a mochila a partir do pedido e, a partir do resultado dessa função, pediríamos a quantidade.

pedido.get(mochila).get(quantidade)COPIAR CÓDIGO
Existe um argumento de que a legibilidade no funcional acaba sendo maior. Porém, se a legibilidade do prefix ((:quantidade (:mochila pedido))) fosse maior, não teríamos suporte a à outra forma ((-> pedido :mochila :quantidade)) no Clojure, nem mesmo ela seria a mais utilizada. Isso acontece porque a forma prefix parece ter uma dificuldade inerente de leitura quando trabalhamos com encadeamentos maiores de funções, atrapalhando a manutenção do código a longo prazo.

Como curiosidade, se quiséssemos, também poderíamos encadear a nossa chamada da seguinte forma, executando o (println) ao final:

(-> pedido 
      :mochila 
      :quantidade 
      println)COPIAR CÓDIGO
O retorno, como esperado, será 2, afinal estamos chamando cada um desses símbolos como função, passando o seu resultado adiante e, por fim, imprimindo-o na tela.

@@04
Faça como eu fiz na aula

Chegou a hora de você seguir todos os passos realizados por mim durantes esta aula. Caso já tenha feito, excelente. Se ainda não, é importante que você implemente o que foi visto no vídeo para poder continuar com a próxima aula, que tem como pré-requisito todo o código aqui escrito. Se por acaso você já domina essa parte, em cada capítulo, você poderá baixar o projeto feito até aquele ponto.

O gabarito deste exercício é o passo a passo demonstrado no vídeo. Tenha certeza de que tudo está certo antes de continuar. Ficou com dúvida? Podemos te ajudar pelo nosso fórum.

@@05
Threading simples

Dado o mapa a seguir:
(def 
  clientes {
    :15 {
      :nome "Guilherme"
      :certificados ["Clojure" "Java" "Machine Learning"] } })COPIAR CÓDIGO
Como extrair o total de certificados que o Guilherme tem?

(clientes -> :15 :certificados count)
 
Alternativa correta
(-> clientes 15 :certificados count)
 
15 é uma keyword, não é um número
Alternativa correta
(-> clientes :15 :certificados count)
 
Essa forma busca corretamente o id :15, a chave dos certificados e aplica a função count.
Alternativa correta
(clientes -> 15 :certificados count)

@@06
O que aprendemos?

O que aprendemos nesta aula:
Utilizar um Map(HashMap);
Utilizar a função count;
Utilizar a função keys para devolver as chaves que o map possui;
Utilizar a função assoc para associar um valor ao map;
O que é threading.

#### 21/10/2023

@06-Map, Reduce e Filters

@@01
Projeto da aula anterior

Caso queira, você pode baixar o projeto do curso no ponto em que paramos na aula anterior.

https://github.com/alura-cursos/clojure-introducao/archive/aula5.zip

@@02
Destruct de sequencia, map, reduce em mapas e thread last

Para continuarmos trabalhando com mapas, uma estrutura muito utilizada em Clojure, criaremos um novo arquivo aula6.clj com o namespace curso.aula6. Anteriormente, quando estudamos os vetores, nós utilizamos as funções (map), (filter) e (reduce), que trabalham com coleções. Um mapa, ou hashmap, também é uma coleção, então também podemos aplicar essas funções a ele.
Começaremos nossos testes criando um novo pedido com o mesmo conteúdo do que fizemos na aula anterior. A ideia agora é fazermos um (map) sobre os elementos desse pedido. Quando estamos explorando o mecanismo por trás das funções, é comum definirmos nós mesmos uma função para entendermos como ocorre a iteração entre os valores da nossa coleção.

Sendo assim, definiremos uma função (imprime-e-15) que recebe como parâmetro um valor, imprime na tela a mensagem "valor" seguida do valor recebido como parâmetro e, por fim, retorna o número 15. Em seguida, aplicaremos a função (map imprime-e-15 pedido) e imprimiremos o seu retorno na tela.

(def pedido {:mochila { :quantidade 2, :preco 80}
             :camiseta {:quantidade 3, :preco 40}})

(defn imprime-e-15 [valor]
  (println "valor" valor)
  15)

(println (map imprime-e-15 pedido))COPIAR CÓDIGO
(valor [:mochila {:quantidade 2, :preco 80}]
valor [:camiseta {:quantidade 3, :preco 40}]

15 15)

Repare que o símbolo valor está atrelado ("binded") a algo que parece um vetor, representado entre colchetes ([]). Mas será mesmo um vetor? Para verificarmos isso, imprimiremos também a classe do valor recebido (class valor).

(defn imprime-e-15 [valor]
  (println "valor" (class valor) valor)
  15)

(println (map imprime-e-15 pedido))COPIAR CÓDIGO
(valor clojure.lang.MapEntry [:mochila {:quantidade 2, :preco 80}]
valor clojure.lang.MapEntry [:camiseta {:quantidade 3, :preco 40}]

15 15)

Assim, descobrimos que o valor é do tipo MapEntry, ou uma "entrada do mapa", que possui uma chave e um valor. Mas por que ele é representado por colchetes? De alguma maneira, o nosso valor é composto de dois valores. Sendo assim, poderíamos chamá-lo de chave valor, ou seja, de dois parâmetros.

Sendo assim, vamos redefinir a função (imprime-e-15) passando com parâmtro chave valor. No nosso (println), passaremos a imprimir chave "e" valor. Assim, a ideia é desestruturarmos (destruct) o "vetor" recebido em dois parâmetros.

(defn imprime-e-15 [valor]
  (println "valor" (class valor) valor)
  15)

(println (map imprime-e-15 pedido))

(defn imprime-e-15 [chave valor]
  (println chave "e" valor)
  15)

(println (map imprime-e-15 pedido))COPIAR CÓDIGO
Ao executarmos o código, nossa segunda chamada, na qual esperamos receber dois parâmetros, exibirá um erro indicando que somente um argumento foi passado em (imprime-e-15), quando a sua aridade é 2.

(valor clojure.lang.MapEntry [:mochila {:quantidade 2, :preco 80}]
valor clojure.lang.MapEntry [:camiseta {:quantidade 3, :preco 40}]

15 15)

Syntax error (ArityException) compiling at (aula6.clj:16:1).

Wrong number of args (1) passed to: curso.aula6/imprime-e-15

(

Isso acontece pois, como podemos perceber, só temos um parâmetro. Para conseguirmos desestruturar esse parâmetro em dois, como um vetor, precisaremos passá-lo entre colchetes, da seguinte forma:

(defn imprime-e-15 [[chave valor]]
  (println chave "e" valor)
  15)

(println (map imprime-e-15 pedido))COPIAR CÓDIGO
Com isso, receberemos com sucesso as chaves e os valores separados:

(:mochila e {:quantidade 2, :preco 80}
:camiseta e {:quantidade 3, :preco 40}

15 15)

Assim aprendemos que, se for de nosso interesse, é possível desestruturarmos vetores. Para continuarmos nossos testes, ao invés de mapearmos para o número 15, faremos isso para o próprio valor.

(defn imprime-e-15 [[chave valor]]
  (println chave "<e>" valor)
  valor)

(println (map imprime-e-15 pedido))COPIAR CÓDIGO
Dessa forma, teremos como retorno:

(:mochila e {:quantidade 2, :preco 80}
:camiseta e {:quantidade 3, :preco 40}

{:quantidade 2, :preco 80} {:quantidade 3, :preco 40})

Na prática, nossa devolução foi a mesma da execução de um (vals). Repare que um (map) de um dicionário/hashmap chama a função com cada entrada, incluindo chave e valor, interpretando-a como um único parâmetro.

Agora queremos calcular o custo de cada um dos nossos produtos. Por exemplo, sabemos que a :mochila custará 160 reais, que é a multiplicação de 80 por 2. Da mesma forma, a :camiseta custará 120 reais. Criaremos então uma função (preco-dos-produtos) recebendo [chave valor]. Para extrairmos as quantidades e preços desse hashmap, usaremos (:quantidade valor) e (:preco valor), multiplicando-os em seguida.

Por fim, chamaremos um (map) passando como parâmetros a nossa função (preco-dos-produtos) e o pedido.

(defn preco-dos-produtos [[chave valor]]
  (* (:quantidade valor) (:preco valor)))

(println (map preco-dos-produtos pedido))COPIAR CÓDIGO
Ao executarmos esse código, teremos como retorno:

(160, 120)
Ou seja, conseguimos encontrar os valores desejados com sucesso. Se quisermos, ainda podemos somar esses valores para obtermos o custo total dos produtos. Para isso, utilizaremos (reduce +).

(defn preco-dos-produtos [[chave valor]]
  (* (:quantidade valor) (:preco valor)))

(println (map preco-dos-produtos pedido))
(println (reduce + (map preco-dos-produtos pedido)))COPIAR CÓDIGO
280
Repare que, nesse momento, não utilizamos o parâmetro chave. Em situações como essa, nas quais queremos ignorar um dos parâmetros resultantes da desestruturação, podemos utilizar um underline (_).

(defn preco-dos-produtos [[_ valor]]
  (* (:quantidade valor) (:preco valor)))COPIAR CÓDIGO
Ao invés de fazermos o encadeamento de funções acima, vamos extrair uma nova função (total-do-pedido) recebendo como parâmetro um pedido. Essa função se encarregará de fazer o (reduce +) de (map preco-dos-produtos pedido). Por fim, calcularemos o (total-do-pedido) e o imprimiremos na tela.

(defn total-do-pedido [pedido]
  (reduce + (map preco-dos-produtos pedido)))
(println (total-do-pedido pedido))COPIAR CÓDIGO
Isso funciona como deveria, mas já aprendemos que o encadeamento de funções não é tão natural. Muitas vezes, é comum extrairmos os valores e trabalharmos com eles de maneira mais simples.

Pensando nisso, na função (total-do-pedido), faremos um threading a partir do pedido recebido por parâmetro e executaremos um (map), passando como parâmetro a função (preco-dos-produtos), e um (reduce +). Como dessa vez estamos trabalhando com parâmetros no threading, e não somente com funções sendo executadas em sequência, precisaremos passá-las entre parênteses, como no seguinte exemplo:

(defn total-do-pedido [pedido]
  (-> pedido
      (map preco-dos-produtos)
      (reduce +))
  (reduce + (map preco-dos-produtos pedido)))

(println (total-do-pedido pedido))
COPIAR CÓDIGO
Ao rodarmos esse código, receberemos um IllegalArgumentException indicando que existe algo errado na nossa sintaxe. Isso está acontecendo pois, quando utilizamos o operador ->, estamos executando o chamado thread first, no qual o parâmetro é lido antes da função a ser executada. Entretanto, basicamente todas as funções que trabalham com coleções usam o padrão função coleção, ou seja, com o parâmetro sendo passado no final. Para solucionarmos isso, usaremos o operador ->> para executarmos o chamado thread last,.

(defn total-do-pedido [pedido]
  (->> pedido
      (map preco-dos-produtos)
      (reduce +))
  (reduce + (map preco-dos-produtos pedido)))

(println (total-do-pedido pedido))COPIAR CÓDIGO
Dessa vez o nosso código será executado corretamente e receberemos 280 como retorno no console. Por mais que o destruct do nosso mapa esteja funcionando, em algumas situações é uma coisa estranha de se fazer. Afinal, no nosso exemplo (preco-dos-produtos), somente as informações do produto são utilizadas. Sendo assim, parece fazer mais sentido que essa função receba como parâmetro um produto, e que ela se chame (preco-total-do-produto).

(defn preco-total-do-produto [produto]
  (* (:quantidade produto) (:preco produto)))COPIAR CÓDIGO
Entretanto, teremos um problema, já que somente conseguiremos rodar esse código nas chaves do nosso pedido. Por isso, teremos que fazer uma pequena alteração na função (total-do-pedido), na qual chamaremos um vals para retornarmos os valores associados às nossas chaves, de modo a aplicarmos corretamente a função (preco-total-do-produto).

(defn preco-total-do-produto [produto]
  (* (:quantidade produto) (:preco produto)))

(defn total-do-pedido
  [pedido]
  (->> pedido
       vals
       (map preco-total-do-produto)
       (reduce +))
  (reduce + (map preco-dos-produtos pedido)))

(println (total-do-pedido pedido))COPIAR CÓDIGO
Executando esse código, teremos como retorno 280, o nosso total do pedido, com uma construção mais natural e legível.

@@03
Filtrando mapas e criando composições com comp

Já aprendemos a utilizar o (map) e o (reduce) com um mapa/hashmap. Agora vamos trabalhar com o (filter). Para começarmos, vamos redefinir o nosso pedido, incluindo agora um :chaveiro cuja :quantidade é 1 e sem nenhum :preco, ou seja, ele é gratuito.
(def pedido {:mochila  {:quantidade 2, :preco 80}
             :camiseta {:quantidade 3, :preco 40}
             :chaveiro {:quantidade 1}})COPIAR CÓDIGO
A ideia, então, é utilizarmos o (filter) para descobrirmos quais itens são gratuitos no nosso pedido. O (filter), se você se lembrar, recebe um predicado, isto é, uma função que retorna verdadeiro ou falso (ou algo equivalente a falso). Sendo assim, teremos uma função gratuito?.

(filter gratuito? pedido)COPIAR CÓDIGO
Definiremos então a função gratuito?, que receberá por parâmetro um item e, utilizando a função (get), retornará o :preco desse item. Caso esse valor não exista, o retorno será 0, que definiremos como padrão. A partir disso, verificaremos se o :preco é menor ou igual (<=) a 0.

(defn gratuito?
  [item]
  (<= (get item :preco 0) 0))

(filter gratuito? pedido)COPIAR CÓDIGO
Para acompanharmos a execução, faremos um (println) da mensagem "Filtrando gratuitos" e então imprimiremos na tela o retorno do nosso filtro.

(defn gratuito?
  [item]
  (<= (get item :preco 0) 0))

(println "Filtrando gratuitos")
(println (filter gratuito? pedido))COPIAR CÓDIGO
Como retorno, teremos:

([:mochila {:quantidade 2, :preco 80}] [:camiseta {:quantidade 3, :preco 40}] [:chaveiro {:quantidade 1}])COPIAR CÓDIGO
Isso significa que temos algo errado, afinal tivemos como retorno todos os produtos, mas nem todos eles são gratuitos. Isso aconteceu pois, assim como quando utilizamos o (map), tanto as chaves quanto os valores estão sendo passados. Para resolvermos esse problema, na execução do nosso filtro, faremos (vals pedido) para obtermos somente os valores desejados.

(defn gratuito?
  [item]
  (<= (get item :preco 0) 0))
(println "Filtrando gratuitos")
(println (filter gratuito? (vals pedido)))
COPIAR CÓDIGO
Com isso, conseguiremos obter o nosso único item gratuito:

({:quantidade 1})
Ao invés de utilizarmos o (vals), poderíamos criar uma variação da função gratuito? na qual fazemos o destruct do parâmetro recebido em [chave item] e executamos o filtro diretamente.

(defn gratuito?
  [[chave item]]
  (<= (get item :preco 0) 0))
(println "Filtrando gratuitos")
(println (filter gratuito? pedido))COPIAR CÓDIGO
Assim conseguiremos obter nosso item com sucesso:

([:chaveiro {:quantidade 1}])
Uma terceira variação seria recebermos um item normalmente e, na execução do nosso filtro, criarmos uma função lambida que recebe [chave item] e delega esses parâmetros para a função gratuito?.

(defn gratuito?
  [item]
  (<= (get item :preco 0) 0))
(println "Filtrando gratuitos")
(println (filter (fn [[chave item]] (gratuito? item)) pedido))COPIAR CÓDIGO
Da mesma forma que nas variações anteriores, a execução retornará o nosso item.

([:chaveiro {:quantidade 1}])
Também poderíamos criar uma função anônima que verifica se o segundo elemento do item recebido como parâmetro é gratuito?, o que pode ser feito com (second %), uma função que nos retorna o segundo elemento da coleção passada para ela.

(defn gratuito?
  [item]
  (<= (get item :preco 0) 0))
(println "Filtrando gratuitos")
(println (filter #(gratuito? (second %)) pedido))COPIAR CÓDIGO
Não é o caminho mais ideal, porém parece mais simples que a variação anterior. Prosseguindo, da mesma forma que temos uma função gratuito?, poderíamos ter uma função pago? que verifica os itens cujo :preco é maior do que 0. Para isso, tal função receberia um item e executaria a função gratuito? sobre ele, passando em seguida por um (not), que irá invertê-la. De modo a testarmos esse código rapidamente, passaremos diretamente um item com :preco 50 e outro com :preco 0, e imprimiremos o resultado das execuções na tela.

(defn pago?
  [item]
  (not (gratuito? item)))

(println (pago? {:preco 50}))
(println (pago? {:preco 0}))COPIAR CÓDIGO
Como retorno, teremos:

true
false

Repare então que o pago? é uma composição de duas funções, (not) e gratuito?. Sendo assim, poderíamos defini-la da forma (comp not gratuito?) e aplicá-la a um item.

(println ((comp not gratuito?) {:preco 50}))COPIAR CÓDIGO
true
Assim, definimos uma função e a invocamos na mesma linha, o que não é o ideal. O mais interessante seria definirmos um símbolo pago? como a função retornada pelo código (comp not gratuito?).

(def pago? (comp not gratuito?))
(println (pago? {:preco 50}))
(println (pago? {:preco 0}))COPIAR CÓDIGO
Repare que, como dissemos anteriormente, pago? não é uma função, mas sim um símbolo que referencia a função retornada pelo (comp), muito utilizado no dia-a-dia para compor o comportamento de funções sem aplicá-los imediatamente.

@@04
Map reduce

Dado o vetor a seguir:
(def clientes [
  { :nome "Guilherme"
    :certificados ["Clojure" "Java" "Machine Learning"] }
    { :nome "Paulo"
      :certificados ["Java" "Ciência da Computação"] }
    { :nome "Daniela"
      :certificados ["Arquitetura" "Gastronomia"] }])COPIAR CÓDIGO
Como calcular o total de certificados de todos os clientes?

(-> clientes (map :certificados) (map count) (reduce +))
 
Alternativa correta
(->> clientes (map :certificados) (map count) (reduce +))
 
Extraimos os certificados, contamos e depois somamos.
Alternativa correta
(-> clientes (map :certificados) count (reduce +))
 
O thread first não é a maneira padrão das APIs de mapa, reduce etc.
Alternativa correta
(->> clientes (map :certificados) count (reduce +))
 
O count deve ser aplicado a cada um dos vetores, portanto teremos que "mapeá-lo"

@@05
Faça como eu fiz na aula

Chegou a hora de você seguir todos os passos realizados por mim durantes esta aula. Caso já tenha feito, excelente. Se ainda não, é importante que você implemente o que foi visto no vídeo para poder continuar com a próxima aula, que tem como pré-requisito todo o código aqui escrito. Se por acaso você já domina essa parte, em cada capítulo, você poderá baixar o projeto feito até aquele ponto.

O gabarito deste exercício é o passo a passo demonstrado no vídeo. Tenha certeza de que tudo está certo antes de continuar. Ficou com dúvida? Podemos te ajudar pelo nosso fórum.

@@06
Conclusão

Parabéns, você concluiu o primeiro curso de introdução à linguagem Clojure! Vamos recapitular os assuntos que vimos ao longo desse treinamento?
Começamos falando sobre a sintaxe da linguagem, invocando funções e aprendendo que isso ocorre de maneira um pouco diferente do que estamos acostumados em outras linguagens. Nesse ponto, aprendemos que é possível criar símbolos e atribuir valores a eles, sejam eles números escalares, strings ou mesmo funções - inclusive, é possível passar uma função como parâmetro de outra função, o que chamamos de high order functions.

Prosseguindo, discutimos como definir funções e receber parâmetros, e passamos a utilizar o IntelliJ, que facilitou bastante a construção dos nossos códigos. Além disso, ele nos trouxe o REPL, que, utilizando um projeto criado pelo Leiningen, nos permitiu executar os nossos testes.

Aprendemos também a criar símbolos locais, trabalhar com condicionais, isolar comportamentos e compor funções por meio de outras funções. Ao adentrarmos o mundo das coleções, mais especificamente vetores e mapas, conhecemos a tríade de funções mais importantes para o trabalho com esse tipo de objeto: (map), (reduce) e (filter). Também descobrimos como adicionar, remover ou alterar valores nas nossas coleções, sempre pensando em imutabilidade - ou seja, as funções não alteram os valores referenciados por um símbolo, apenas retorna um novo mapa ou vetor com as mudanças atribuídas.

Mais adiante, trabalhamos não só com mapas simples, de "uma dimensão", como também com mapas mais complexos, cujos valores são outros mapas. Ao longo desse processo, questões de boas práticas, legibilidade e como trabalhar com funções foram aparecendo e sendo discutidas.

Agora que temos uma noção melhor da linguagem, conseguiremos conectar esses aprendizados com os nossos conhecimentos de estrutura de dados, algoritmos e outros conteúdos, de modo a utilizarmos as estruturas corretas no momento adequado para trazer os benefícios desejados. Nos cursos futuros, conheceremos ainda outros benefícios de trabalhar com o Clojure.

Bons estudos e até a próxima!

@@07
Projeto do curso

Caso queira, você pode baixar aqui o projeto do curso no ponto em que paramos na aula anterior.

https://github.com/alura-cursos/clojure-introducao/archive/aula6.zip

@@08
O que aprendemos?

O que aprendemos nesta aula:
Utilizar o destruct para um dicionário;
Utilizar o Thread last;
Utilizar o map, reduce e filter em um mapa;
Criar composição com o comp;