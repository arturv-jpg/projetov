package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CarroDetalheController {

    @FXML
    private Label lblInfo;

    @FXML
    private Button btnLigar, btnDesligar;

    // Apenas uma declaração do atributo carro
    private Carro carro;

    // Recebe o carro selecionado
    public void setCarro(Carro carro) {
        this.carro = carro;
        atualizarInfo();
    }

    @FXML
    private void ligarCarro() {
        if (carro != null) {
            carro.ligarCarro();
            atualizarInfo();
        }
    }

    @FXML
    private void desligarCarro() {
        if (carro != null) {
            carro.desligarCarro();
            atualizarInfo();
        }
    }

    // Atualiza as informações do carro na tela
    private void atualizarInfo() {
        if (carro != null) {
            lblInfo.setText(carro.exibirInformacoes());
        }
    }
}
