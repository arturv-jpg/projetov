package application.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class aplicativoController {

    @FXML
    private AnchorPane conteudoPane;

    
    private void carregarTela(String fxmlfile) {
    	try {
    		Parent fxml = FXMLLoader.load(getClass().getResource("/application/view/" + fxmlfile));
    		conteudoPane.getChildren().clear();
    		conteudoPane.getChildren().add(fxml);
    		
    		conteudoPane.setTopAnchor(fxml,0.0);
    		conteudoPane.setBottomAnchor(fxml,0.0);
    		conteudoPane.setLeftAnchor(fxml,0.0);
    		conteudoPane.setRightAnchor(fxml,0.0);
    		 Scene cena = conteudoPane.getScene();
    		
    		if (cena!=null) {
    			Stage stage = (Stage) cena.getWindow();
    		}
    		
    	} catch(Exception e) {
    	e.printStackTrace();
    	}
    }
    @FXML
    private void abrirPaginaInicial() {
    	carregarTela("Principal.fxml");
    }
    @FXML
    private void abrirCalculadora() {
    	carregarTela("calculadora.fxml");
    }
    @FXML
    private void abrircalcularIMC() {
    	carregarTela("calcularIMC.fxml");
    }
    @FXML
    private void abrirjogo() {
    	carregarTela("jogo.fxml");
    }
    @FXML
    private void abrirjogoTiro() {
    	carregarTela("jogoTiro.fxml");
    }
    @FXML
    private void abrirmediaNota() {
    	carregarTela("mediaNota.fxml");
    }
    @FXML
    private void abriridade() {
    	carregarTela("idade.fxml");
    }
    @FXML
    private void abrirNovoUsuario() {
        carregarTela("Usuario.fxml");
}
    @FXML
    private void abrirCarroController() {
        carregarTela("carro.fxml");
}
    @FXML
    private void initialize() {
    	//abrirPaginaInicial();
    }
}

