# language: pt
@hamburgueria
Funcionalidade: Pedidos na hamburgueria Peppa Lanches
  Para realizar pedidos corretos
  Como cliente
  Eu quero saber se o item pode ser pedido, o valor total e o tempo estimado

  Contexto:
    Dado que o cardápio contém os itens:
      | item         | preco |
      | x-bacon      | 25.00 |
      | x-salada     | 22.00 |
      | batata frita | 12.00 |

  @feliz
  Cenário: Pedido simples de item existente
    Quando eu solicitar "x-bacon" com quantidade 2
    Então o valor total deve ser 50.00

  @inexistente
  Cenário: Pedido de item inexistente
    Quando eu solicitar "suco" com quantidade 1
    Então deve ocorrer erro com a mensagem "Item indisponível no cardápio"

  @quantidade
  Cenário: Pedido com quantidade inválida
    Quando eu solicitar "x-salada" com quantidade 0
    Então deve ocorrer erro com a mensagem "Quantidade inválida"

  @desconto
  Cenário: Pedido com desconto de 10 por cento
    Quando eu solicitar "batata frita" com quantidade 3 e desconto de 10 por cento
    Então o valor total com desconto deve ser 32.40

  @sla
  Cenário: Calcular tempo estimado de preparo
    Quando eu solicitar um total de 5 itens
    Então o tempo estimado deve ser 18