package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class mediaNotaController {

    @FXML
    private Button btnCalcularMedia;

    @FXML
    private Label lblResultado;

    @FXML
    private TextField txtNota1;

    @FXML
    private TextField txtNota2;

    @FXML
    private TextField txtNota3;

    @FXML
    private TextField txtNota4;

    @FXML
    public void initialize() {
        
        btnCalcularMedia.setOnAction(e -> CalcularMedia());
    }

    private void CalcularMedia() {
        try {
            
            double nota1 = Double.parseDouble(txtNota1.getText());
            double nota2 = Double.parseDouble(txtNota2.getText());
            double nota3 = Double.parseDouble(txtNota3.getText());
            double nota4 = Double.parseDouble(txtNota4.getText());

           
            double media = (nota1 + nota2 + nota3 + nota4) / 4;

            
            String situacao;
            if (media >= 7) {
                situacao = "Aprovado";
            } else if (media >= 5) {
                situacao = "Recuperação";
            } else {
                situacao = "Reprovado";
            }

                        lblResultado.setText(String.format("Média: %.2f - %s", media, situacao));

        } catch (NumberFormatException ex) {
            
            lblResultado.setText("Digite apenas números válidos em todas as notas!");
        }
    }
}
