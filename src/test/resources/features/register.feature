Feature: Cadastro de novo usuário

  Scenario: Login e cadastro de um novo funcionário
    Given Eu visito o site
    When Eu cadastro um novo canditato
    Then Eu devo ver uma mensagem de sucesso

