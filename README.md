# Projeto Biblioteca de Jogos com Testes Unitários

## Objetivo

- No Bootcamp everis New Talents - Java foram desenvolvidos Testes unitários em um sistema de gerenciamento de estoques de cerveja utilizando API REST.
    - O mesmo pode ser encontrado no link: [rpeleias/beer_api_digital_innovation_one: Digital Innovation: Expert class - Desenvolvimento de testes unitários para validar uma API REST de gerenciamento de estoques de cerveja.(github.com)](https://github.com/rpeleias/beer_api_digital_innovation_one)

- Visando utilizar o mesmo conceito visto no curso, mas criando alterações, optei por criar um sistema API REST para gerenciar uma biblioteca de jogos, sendo desenvolvido a partir do mesmo, testes unitários para verificar o devido funcionamento do código.

- Assim, foi criado um **API REST** para _cadastrar_, _listar_, _recuperar_ e _deletar_ um jogo.

- Iremos criar nossa API utilizando o sistema de banco de dados **H2**.

- Os testes unitarios foram criados utilizando os frameworks **MOCKITO** e **Junit**

- O jogo, conta com as seguintes informações:
    - Nome
    - Ano de lançamento
    - Nome da desenvolvedora
    - Tipo de jogo
### Sobre as Camadas

#### Entity

- Aqui iniciamos com a criação das entidades que existem no nosso banco de dados, ela representa exatamente nosso banco
- Neste caso teremos apenas uma única entidade **Game(Jogos)**
#### Controller

- Camada responsável pelo mapeamento dos métodos HTTP. Essa é a camada que estará visível em nossa interface, para apartir do **path** definido enviarmos as requisições e informações para a camada Service.
- Nessa camada não vamos interagir com as entidades, a fim de seguir o padrão RESTful usaremos uma camada DTO, onde podemos definir regras para nosso atributo e controlar o que realmente vai para nosso banco de Dados.

#### Service

- Camada responsável pelas regras de negócio. As requisições provenientes da camada controller é recebida e executada, seguindo as regras definidas.
- Nessa camada iremos interagir com o Banco de Dados, a partir da camada Repository.

#### Repository

- Camada na qual fazemos a interação com nosso banco de dados, salvando, alternado, recuperando e excluindo dados diretamente do banco.

### Iniciando o projeto

- Para executar o projeto no terminal, digite o seguinte comando:

```shell script
mvn spring-boot:run 
```
### Como inserir dados

- A inserção de dados é feita a partir de um JSON. Um exemplo pode ser visto abaixo:

``` {
{
    "name" : "Skyrim",
    "launchAge" : "2011",
    "companyName" : "Bethesda",
    "type" : "RPG"
}

```

### Sobre as Camadas de Teste

#### Builder

- Camada responsável por criar um objeto padrão para os testes.

#### Controller(Test)

-Camada responsável por testar o funcionamento das funções presentes na camada de controle do código.

#### Service(Test)

-Camada responsável por testar o funcionamento das funções presentes na camada de serviços do código.

### Iniciando os Testes

- Para iniciar a suite de testes, basta executar o seguinte comando:

```shell script
mvn clean test
```