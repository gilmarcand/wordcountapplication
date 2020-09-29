#WordCountApplication

## Test
Para executar o teste automatizado da aplicação:
>$ sbt clean test

## Build
Para fazer o build da aplicação executar comando:
>$ sbt clean assembly

Essa instrução vai gerar o arquivo WordCountApplication.jar

## Run
Para executar a aplicação
>$ java -jar -DinputFile=input.txt -Dspark.master=local target/scala-2.12/WordCountApplication.jar

 1. Para informar o arquivo a ser processado, passar o parâmetro parâmetro *-DinputFile*. Se omitido a aplicação irá buscar o arquivo *input.txt* no diretório atual
 2. Para informar a URL do Spark Master passar o parâmetro *-Dspark.master*. Se omitido, a aplicação tentará conectar em um spark local.