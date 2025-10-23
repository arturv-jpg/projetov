package application.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CarroController {

    @FXML
    private ImageView imgCorolla, imgCivic, imgOnix, imgMustang, imgOmega;

    @FXML
    private void abrirCorollaDetalhe() throws IOException {
        abrirTelaDetalhe(new Carro("Toyota", "Corolla", "2.0 Flex", "Prata", 2022, "Sedan"));
    }

    @FXML
    private void abrirCivicDetalhe() throws IOException {
        abrirTelaDetalhe(new Carro("Honda", "Civic", "2.0 Flex", "Branco", 2021, "Sedan"));
    }

    @FXML
    private void abrirOnixDetalhe() throws IOException {
        abrirTelaDetalhe(new Carro("Chevrolet", "Onix", "1.0 Turbo", "Vermelho", 2023, "Hatch"));
    }

    @FXML
    private void abrirMustangDetalhe() throws IOException {
        abrirTelaDetalhe(new Carro("Ford", "Mustang", "5.0 V8", "Amarelo", 2020, "Esportivo"));
    }

    @FXML
    private void abrirOmegaDetalhe() throws IOException {
        abrirTelaDetalhe(new Carro("Chevrolet", "Omega", "3.6 V6", "Preto", 2011, "Sedan Executivo"));
    }

    private void abrirTelaDetalhe(Carro carro) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CarroDetalhe.fxml"));
        Parent root = loader.load();

        CarroDetalheController controller = loader.getController();
        controller.setCarro(carro); // passa o carro selecionado

        Stage stage = new Stage();
        stage.setTitle(carro.getNome());
        stage.setScene(new Scene(root));
        stage.show();
    }
}

