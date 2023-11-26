# Quarkus Rede Social
Projeto de API Rest para uma simples rede social com usuários, posts e seguidores.

## Tecnologias:
* Java 17
* Quarkus
* JWT
* Postgres
* FlyWay
* Maven
* Swagger
* Docker

Para gerar um token acessar http://localhost:8080/token e para cada requisição passar o Bearer {TOKEN} no header.

Para acessar o swagger http://localhost:8080/q/swagger-ui

Para gerar a imagem do projeto no Docker docker build -f src\main\docker\Dockerfile.jvm -t quarkus-rede-social:1.0 .

Fique à vontade para contribuir com este projeto.
