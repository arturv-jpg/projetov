package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import application.view.calculadoraController;

public class calcularIMCController {

    @FXML private Button btnCalcularIMC;
    @FXML private Label lblResultado;
    @FXML private TextField txtAltura;
    @FXML private TextField txtNome;
    @FXML private TextField txtPeso;

    public void calcularIMC () {
    String nome=txtNome.getText();
    double peso=Double.valueOf(txtPeso.getText());
    double altura=calculadoraController.StrToDbl(txtAltura.getText());	
    double imc=peso / (altura*altura);
    
    String classificacao;
    if (imc < 18.5) {
        classificacao = "Abaixo do peso";
    } else if (imc < 25) {
        classificacao = "Peso normal";
    } else if (imc < 30) {
        classificacao = "Sobrepeso";
    } else if (imc < 35) {
        classificacao = "Obesidade grau I";
    } else if (imc < 40) {
        classificacao = "Obesidade grau II (severa)";
    } else {
        classificacao = "Obesidade grau III (mórbida)";
    }

    lblResultado.setText(
        nome + ", o seu IMC é: " + String.format("%.2f", imc) +
        " - Classificação: " + classificacao
    );
    } 
   } 


