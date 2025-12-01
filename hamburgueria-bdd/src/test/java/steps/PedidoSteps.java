package steps;

import io.cucumber.java.pt.*;
import org.junit.jupiter.api.Assertions;
import peppa.hamburgueria.CardapioService;
import peppa.hamburgueria.PedidoService;

public class PedidoSteps {

    private CardapioService cardapio;
    private PedidoService pedidoService;
    private double resultado;
    private Exception erroCapturado;
    private int tempoEstimado;

    public void que_o_cardapio_contem_os_itens(io.cucumber.datatable.DataTable dataTable) {
        cardapio = new CardapioService();
        dataTable.asMaps().forEach(linha -> {
            String item = linha.get("item");
            double preco = Double.parseDouble(linha.get("preco"));
            cardapio.cadastrarItem(item, preco);
        });
        pedidoService = new PedidoService(cardapio);
    }

    public void eu_solicitar_com_quantidade(String item, Integer qtd) {
        try {
            resultado = pedidoService.calcularTotal(item, qtd);
        } catch (Exception e) {
            erroCapturado = e;
        }
    }

    public void eu_solicitar_com_desconto(String item, Integer qtd, Integer desconto) {
        try {
            double total = pedidoService.calcularTotal(item, qtd);
            double valorDesconto = total * (desconto / 100.0);
            resultado = Math.round((total - valorDesconto) * 100.0) / 100.0;
        } catch (Exception e) {
            erroCapturado = e;
        }
    }

    public void eu_solicitar_um_total_de_itens(Integer qtdTotal) {
        tempoEstimado = pedidoService.calcularTempoEstimado(qtdTotal);
    }

    public void o_valor_total_deve_ser(Double esperado) {
        Assertions.assertEquals(esperado, resultado);
    }

    public void o_valor_total_com_desconto_deve_ser(Double esperado) {
        Assertions.assertEquals(esperado, resultado);
    }

    public void deve_ocorrer_erro_com_a_mensagem(String mensagem) {
        Assertions.assertNotNull(erroCapturado);
        Assertions.assertEquals(mensagem, erroCapturado.getMessage());
    }

    public void o_tempo_estimado_deve_ser(Integer esperado) {
        Assertions.assertEquals(esperado, tempoEstimado);
    }
}