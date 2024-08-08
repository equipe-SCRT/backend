# SCRT - Tech For Good

Bem-vindo ao repositório de backend do projeto **Tech For Good** do **Grupo 5**. Este projeto é nomeado **SCRT**.

## 📋 Estrutura do Projeto

Para a estruturação do projeto, utilizamos os padrões **MVC (Model-View-Controller)**, **Adapter** e **SOLID**, que facilitam a escalabilidade e manutenção do código, tornando-o mais fácil de estender, modificar e manter conforme o sistema cresce e evolui.

### 📌 MVC (Model-View-Controller)

**MVC** é uma arquitetura de software que separa a lógica de negócios (Model), a apresentação (View) e a interação do usuário (Controller). Essa separação promove uma organização clara e modular do projeto.

- **Model**: Lida com a lógica de negócios e manipulação de dados.
- **View**: Responsável pela apresentação das informações ao usuário.
- **Controller**: Gerencia a comunicação entre Model e View, lidando com as entradas do usuário.

### 📌 Adapter

O padrão **Adapter** é utilizado para permitir a colaboração entre objetos com interfaces incompatíveis, atuando como um intermediário que traduz as chamadas de um formato para outro. Isso facilita a integração de componentes ou sistemas distintos.

### 📌 SOLID

**SOLID** é um conjunto de cinco princípios de design de software que visam tornar o código mais modular, flexível e fácil de manter. Eles promovem coesão, baixo acoplamento e abstração.

- **S**ingle Responsibility Principle: Uma classe deve ter uma, e apenas uma, razão para mudar.
- **O**pen/Closed Principle: Entidades de software devem estar abertas para extensão, mas fechadas para modificação.
- **L**iskov Substitution Principle: Objetos de uma classe base devem poder ser substituídos por objetos de uma classe derivada sem alterar o funcionamento do programa.
- **I**nterface Segregation Principle: Muitas interfaces específicas são melhores do que uma interface única e geral.
- **D**ependency Inversion Principle: Dependa de abstrações, não de implementações concretas.

## 💻 Tecnologias Utilizadas

- **Spring Boot**: Framework para criação de aplicações Java que simplifica o desenvolvimento com configuração mínima.
- **Restful**: Estilo de arquitetura para sistemas distribuídos baseados em HTTP.
- **Java**: Linguagem de programação utilizada para o desenvolvimento do backend.
- **SQL Server e MySQL**: Sistemas de gerenciamento de banco de dados utilizados para armazenar e gerenciar os dados da aplicação.

## ✔ Versão e dependências

Versão do Java (Maven) → 17 <br>

dependências: <br>

Spring Boot Framework -> 3.2.4 <br>
Spring Security Test -> 3.2.4 <br>
Spring Boot Starter Security -> 3.2.4 <br>
JJWT :: API -> 0.11.5 <br>
JJWT :: Impl -> 0.11.2 <br>
JJWT :: Extensions :: Jackson -> 0.11.1 <br>
Spring Boot Starter Actuator -> 3.0.5 <br>
Spring Boot Starter Validation -> 3.2.4 <br>
Spring Boot Starter Data JPA -> 3.2.4 <br>
Spring Boot Starter Web -> 3.2.4 <br>
Spring Boot Starter Test -> 3.2.4 <br>
Spring Boot Maven Plugin -> 3.2.4 <br>
MySQL Connector/J -> 8.3.0 <br>
SpringDoc OpenAPI Starter WebMVC UI -> 2.4.0 <br>
Project Lombok -> 1.18.30 <br>
MapStruct Core -> 1.6.0.Beta2 <br>
MapStruct Processor -> 1.6.0.Beta2 <br>
Apache Commons CSV -> 1.8 <br>
