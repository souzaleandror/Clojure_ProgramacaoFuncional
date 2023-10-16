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