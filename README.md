# RESTFUL-Api



API RESTful em **Java 17** e **Spring Boot 3.4.4** para colocar em prática conceitos de serviços REST, autenticação, validação de dados e tratamento de erros, integrada a um banco de dados **PostgreSQL**.
 
## 🚀 Tecnologias

<p align="left">
  <img alt="Java" src="https://img.shields.io/badge/Java-ED8B00?logo=java&logoColor=white" />
  <img alt="Spring Boot" src="https://img.shields.io/badge/Spring%20Boot-6DB33F?logo=springboot&logoColor=white" />
  <img alt="Maven" src="https://img.shields.io/badge/Maven-C71A36?logo=apachemaven&logoColor=white" />
  <img alt="PostgreSQL" src="https://img.shields.io/badge/PostgreSQL-316192?logo=postgresql&logoColor=white" /> 
  <img alt="Swagger" src="https://img.shields.io/badge/Swagger-85EA2D?logo=swagger&logoColor=black" />
</p>

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
