# RESTFUL-Api

![GitHub repo size](https://img.shields.io/github/repo-size/Arthur-MARKOWICZ/RESTFUL-Api?style=for-the-badge) 
![GitHub language count](https://img.shields.io/github/languages/count/Arthur-MARKOWICZ/RESTFUL-Api?style=for-the-badge) 
![GitHub top language](https://img.shields.io/github/languages/top/Arthur-MARKOWICZ/RESTFUL-Api?style=for-the-badge) 
![GitHub last commit](https://img.shields.io/github/last-commit/Arthur-MARKOWICZ/RESTFUL-Api?style=for-the-badge)
![GitHub issues](https://img.shields.io/github/issues/Arthur-MARKOWICZ/RESTFUL-Api?style=for-the-badge)
![GitHub forks](https://img.shields.io/github/forks/Arthur-MARKOWICZ/RESTFUL-Api?style=for-the-badge)

**API RESTful em Java 17 e Spring Boot 3.4.4**, desenvolvida para colocar em prática conceitos de **serviços REST, autenticação**, integrada a um banco de dados **PostgreSQL**.

---

## 🎯 Objetivo do Projeto

O **RESTFUL-Api** foi criado com o propósito de **praticar e consolidar conhecimentos em desenvolvimento de APIs em Java**, permitindo:

- Aplicar conceitos de **arquitetura RESTful** e boas práticas de desenvolvimento.
- Implementar **autenticação e segurança** de endpoints.
- Validar e tratar erros de forma consistente.
- Integrar a aplicação com **banco de dados PostgreSQL**.
- Documentar a API com **Swagger/OpenAPI**, facilitando testes e integração com front-end.

O foco do projeto é **aprender na prática como criar APIs robustas e escaláveis em Java e Spring Boot**.

---

## 🛠 Tecnologias e Motivos

- **Java 17**: linguagem moderna, robusta e amplamente usada em back-end corporativo.
- **Spring Boot 3.4.4**: framework que simplifica a criação de APIs RESTful.
- **PostgreSQL**: banco de dados relacional confiável e escalável.
- **Maven**: gerenciamento de dependências e build da aplicação.
- **Swagger/OpenAPI**: documentação interativa dos endpoints.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)

---

## 🛠 Funcionalidades

- CRUD completo de entidades (ex.: Usuários, Produtos, etc.)  
- Autenticação e login de usuários  
- Validação de dados e tratamento de erros  
- Documentação interativa via Swagger  

---

## 📈 Skills Demonstradas

- Back-End: Java 17, Spring Boot, RESTful APIs  
- Banco de Dados: PostgreSQL, modelagem de dados, queries  
- Boas práticas: arquitetura RESTful, organização de projeto, versionamento  
- Documentação profissional da API com Swagger  

---


## ⚙️ Como Executar

1. **Clone** este repositório  
   ```bash
   git clone https://github.com/Arthur-MARKOWICZ/RESTFUL-Api.git
   cd RESTFUL-Api
   ```

2. **Crie** um banco de dados PostgreSQL (ex.: `restful_api_db`)  
   ```sql
   CREATE DATABASE restful_api_db;
   ``` 

3. **Configure** as credenciais em `src/main/resources/application.properties`:  
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/restful_api_db
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   spring.jpa.hibernate.ddl-auto=update
   ``` 

4. **Compile e execute** a aplicação  
   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

5. **Acesse** a documentação interativa  
http://localhost:8080/swagger-ui/index.html
