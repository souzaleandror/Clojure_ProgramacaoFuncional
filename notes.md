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