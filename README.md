# Worst Movies API

API RESTful para leitura da lista de indicados e vencedores da categoria **Pior Filme** do Golden Raspberry Awards.

---

## 📝 **Sobre o projeto**

Esta API permite consultar os **produtores com maior e menor intervalo de tempo entre vitórias consecutivas** na categoria Pior Filme, de acordo com os dados fornecidos no arquivo CSV.

A aplicação:

- Lê o arquivo CSV ao iniciar e insere os dados no banco H2 em memória.
- Disponibiliza um endpoint REST para consulta dos intervalos.

---

## 🚀 **Como rodar o projeto**

### Pré-requisitos:

- Java 17+
- Maven 3.8+

### Passos:

1. Clone o repositório:

   ```bash
   git clone https://github.com/Augustocct/WorstMovies.git
   cd WorstMovies

   ```

2. Compile e execute a aplicação:
   mvn spring-boot:run

3. Acesse a API no navegador ou via Postman:
   GET http://localhost:8080/api/awards/intervals

### 🗃️ Acessar o banco de dados H2:

    http://localhost:8080/h2-console

    Configuração de conexão:

    JDBC URL: jdbc:h2:mem:testdb

    User: sa

    Password: (em branco)

### 🧪 Como rodar os testes de integração:

    mvn test

### 📄 Especificações técnicas

   Java 17

   Spring Boot

   Spring Web

   Spring Data JPA

   Banco H2 (em memória)

   Lombok

   Testes com JUnit + MockMvc
