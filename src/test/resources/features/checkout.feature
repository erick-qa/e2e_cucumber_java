Feature: Realização de compra

  Scenario: Login e compra de um produto
    Given Eu visito a loja
    When Eu realizo a compra de um produto
    Then Eu devo ver a mensagem de sucesso