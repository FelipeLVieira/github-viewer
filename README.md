![Logo of the project](https://lh3.googleusercontent.com/wsN2aLNkRpe0f9jLH5iZx97EkyNFPNnxoXyOV07papfWN9CDLQ_7FSWIHyXy-m9f7iNP=w300)

# github-viewer
> Visualizador de repositórios e pull requests utilizando a API do Github

Este repositório contém um aplicativo em Android nativo (Java) que lista os 
repositórios do github (linguagem default: Java), bem como é possível navegar
pelos pull requests dos repositórios listados e por fim acessar o endereço web
do pull request selecionado.

## Começando a usar

Para utilizar o projeto

```shell
$ git clone https://github.com/FelipeLVieira/github-viewer
```

Este comando cria uma cópia do projeto no diretório atual.
É necessário instalar o Android Studio para gerar o arquivo .apk.
Para habilitar a instalação do apk no dispositivo, caminho 
"Configurações -> Segurança" do dispositivo, desativar a chave 
ao lado de "Fontes desconhecidas" para que o aplicativo possa 
ser instalado.
[](https://s2.glbimg.com/dnFSuDxeynpFd6fq-VKwlwANTBs=/0x0:1448x1280/1000x0/smart/filters:strip_icc()/i.s3.glbimg.com/v1/AUTH_08fbf48bc0524877943fe86e43087e7a/internal_photos/bs/2018/o/R/eeE3uBTmAii94QzeFwVw/b8.png)

Após desativada a chave, será possível copiar e executar o arquivo
.apk para efetuar a instalação.

## Estrutura utilizada

O projeto foi desenvolvido com a biblioteca de retrocomatibilidade AppCompat.
A versão mínima recomendada é a API 23 (Marshmallow) e a SDK alvo é a API 27 (Oreo)
O projeto utiliza as bibliotecas Gson (2.8.2), Retrofit (2.4.0) e Glide (4.6.1)

## Features

* Scroll infinito
* Material design

## Configuração

Nenhuma configuração adicional é necessária.

## Contributing

Se você gostaria de contribuir, basta fazer um fork do repositório e usar
um feature branch. Pull requests são sempre bem-vindas.


## Licensing

The code in this project is licensed under MIT license.
