# Simi - Sistema de Gestão de Plantões

## Descrição
Simi é um sistema para gerenciar plantões, estudantes, preceptores, semestres, especialidades e localizações.  
O backend é desenvolvido em **Java** usando **Spring Boot**, com **JPA/Hibernate** para persistência e **Springdoc OpenAPI** para documentação da API.

Ele permite:

- Criar e listar usuários
- Criar e listar estudantes
- Criar e listar plantões
- Alocar estudantes em plantões
- Criar e listar especialidades, semestres, preceptores e localizações

---

## Tecnologias

- Java 21
- Spring Boot 3.5
- Spring Data JPA
- Hibernate
- PostgreSQL
- Lombok
- Springdoc OpenAPI
- Jakarta Validation

---

## Instalação

### Pré-requisitos

- Java 21 ou superior
- PostgreSQL
- Maven

## Rodando o projeto

### 1. Subindo o banco de dados

O projeto já possui um **docker-compose.yml** para rodar o PostgreSQL

Para subir o banco:
```yaml
docker-compose up -d
```

Para parar e remover o container (e dados):
```yaml
docker-compose down -v
```

### 2. Rodar a API
```yaml
mvn clean spring-boot:run
```
A aplicação irá iniciar na porta 8080.

### 3. Acessar a documentação do Swagger

Use para testar as rotas e conhecer a documentação:
```yaml
http://localhost:8080/api/docs
```

## Endpoints Principais

| Recurso        | Endpoint                  | Método   |
| -------------- | ------------------------- | -------- |
| Usuários       | `/api/users`              | GET/POST |
| Estudantes     | `/api/students`           | GET/POST |
| Plantões       | `/api/shifts`             | GET/POST |
| Alocações      | `/api/shifts/allocations` | GET/POST |
| Especialidades | `/api/specialties`        | GET/POST |
| Semestres      | `/api/semesters`          | GET/POST |
| Preceptores    | `/api/preceptors`         | GET/POST |
| Localizações   | `/api/locations`          | GET/POST |
