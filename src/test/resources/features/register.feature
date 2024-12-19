Feature: Cadastro de novo candidato

  Scenario: Login e cadastro de um novo candidato
    Given Eu visito o site
    When Eu cadastro um novo canditato
    Then Eu devo ver uma mensagem de sucesso

