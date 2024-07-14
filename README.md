![Imgur](https://i.imgur.com/zKVXzjq.png)

<h1 align = "center">
  Voll.med
</h1>
<p align="center">
  <a href="#descrição">Descrição</a> •
  <a href="#funcionalidades">Funcionalidades</a> •
  <a href="#tecnologias-utilizadas">Tecnologias</a> •
  <a href="#como-executar-o-projeto">Como Executar</a> •
  <a href="#screenshots">Screenshots</a> •
  <a href="#observações">Observações</a> •
  <a href="#contribuição">Contribuição</a> •
  <a href="#contato">Contato</a>
</p>

## Descrição
A aplicação Voll.med é uma API RESTful que permite o agendamento de consultas médicas, visando conectar pacientes e médicos, facilitando o acesso à saúde.

## Funcionalidades

1. **Cadastro de Pacientes:** Permite cadastrar novos pacientes com informações como nome, email, telefone, CPF e endereço.
2. **Cadastro de Médicos:** Permite cadastrar novos médicos com informações como nome, email, telefone, CRM, especialidade e endereço.
3. **Agendamento de Consultas:** Permite agendar consultas com médicos cadastrados, definindo data, hora e o médico que realizará a consulta. Caso o médico não seja informado, é obrigatório informar a especialidade para que o sistema escolha um médico disponível aleatório.
4. **Cancelamento de Consultas:** Permite deletar logicamente uma consulta do banco de dados, informando o id da consulta e o motivo do cancelamento 
5. **Autenticação e Autorização:** Implementa um sistema de autenticação e autorização para garantir a segurança da API.

## Tecnologias Utilizadas

- **Spring Boot:** Framework Java para desenvolvimento de aplicações web.
- **Java:** Linguagem de programação utilizada para desenvolver a aplicação.
- **Spring Data JPA:** Framework para acesso a dados com JPA.
- **MySQL:** Banco de dados relacional utilizado para armazenar os dados da aplicação.
- **Flyway:** Ferramenta para gerenciamento de migrações de banco de dados.
- **Spring Security:** Framework para segurança de aplicações web.
- **SpringDoc:** Ferramenta para gerar documentação da API com Swagger.
- **JUnit:** Framework para testes unitários.
- **Mockito:** Framework para mocks em testes unitários.
- **Maven:** Ferramenta para gerenciamento de dependências e construção do projeto.

## Como Executar o Projeto:

1. Clone o repositório:
    ```bash
    git clone https://github.com/KarlaSilvaDev/voll.med
    ```
    
2. Entre no diretório do projeto
    ```bash
    cd voll.med
    ```
    
3. Banco de dados:
    - Crie um banco de dados chamado **vollmed_api**
    - Configure o banco de dados no arquivo `application.properties` ou cria variáveis de ambiente na sua máquina
    - Importe as migrações do Flyway para o banco de dados
    - Para testes, crie o banco de dados **vollmed_api_test**

5. Execute a aplicação:

    Execute o comando `mvn springboot:run` na raiz do projeto.
   
6. Acesse a documentação da API:

    Acesse a URL `http://localhost:8080/swagger-ui/index.html` para visualizar a documentação da API.

## Screenshots

//Pendente//

## Observações:

- As credenciais de acesso ao banco de dados estão definidas no arquivo application.properties.
- A aplicação utiliza o Spring Security para autenticação e autorização.
- A documentação da API é gerada automaticamente pelo SpringDoc e pode ser acessada através do Swagger.

## Contribuição

Se deseja contribuir para o projeto, siga os passos abaixo:

1. Faça um fork deste repositório
   
2. Crie uma branch para sua feature:
    ```bash
     git checkout -b feature/nova-feature
    ```
    
3. Faça commit das suas alterações:
    ```bash
    git commit -m "Adiciona nova feature"
    ```
    
4. Envie as alterações para o seu fork:
    ```bash
    git push origin feature/nova-feature
    ```
    
5. Abra um pull request neste repositório

## Contato

Caso tenha alguma dúvida ou sugestão, entre em contato pelo email `karlasilvaeng@gmail.com`.

## Licença:

Esta aplicação é licenciada sob a licença `Apache 2.0`.
