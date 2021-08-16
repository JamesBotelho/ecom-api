# E-commerce Rest API

Esta api tem como objetivo replicar as principais funcionalidades de um e-commerce.

## Funcionalidades

* Cadastro de clientes;
* Autenticação por e-mail e senha;
* Listagem de produtos por id ou categoria utilizando paginação;
* Listagem de categorias;
* Listar pedidos de um cliente;
* Salvar, recuperar e deletar carrinho do cliente;
* Inserir um novo pedido;

## Dependências necessárias

O projeto está configurado para no profile default, utilizar o banco de dados mysql com a database db_ecom criada e o mongodb para persistência do carrinho. O mongodb também é necessário para os ambientes de test e dev.

Para configurar as conexões, pode-se utilizar as seguintes variáveis de ambiente:

* MYSQL_HOST: host do banco mysql
* MYSQL_PORT: porta do banco mysql
* USER_DATABASE: usuário do banco mysql
* DB_PASSWORD: senha do usuário do banco mysql
* MONGO_HOST: host do banco mongodb
* MONGO_PORT: porta do banco mondodb
* MONGO_DATABASE: database do mongodb
* TOKEN_ISSUER: Nome do proprietário que assina a chave JWT para autenticação
* TOKEN_EXPIRATION: Tempo em millisegundos para expiração do token JWT
* TOKEN_SECRET: chave secreta que será utilizada para codificar/decodificar o token JWT

## Imagem docker

O projeto possui uma imagem docker no dockerhub acessado através deste link: https://hub.docker.com/repository/docker/jamesbotelho/ecom-api

## Documentação dos endpoints

A aplicação possui o swagger configurado utilizando o SpringFox, que pode ser acessado através do endereço: http://host-da-aplicacao/swagger-ui/

## Autor: James Botelho
* [LinkedIn](https://br.linkedin.com/in/james-de-oliveira-botelho-67344914b)
* [Gmail]<jms.devel@gmail.com>
