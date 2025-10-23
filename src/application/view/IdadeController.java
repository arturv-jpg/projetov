package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class IdadeController {

    @FXML
    private Button btnCalcular;

    @FXML
    private DatePicker dtIdade;

    @FXML
    private TextField txtNome;

    @FXML
    private Label lblResultado;

    @FXML
    private void calcularIdade() {
        String nome = txtNome.getText();
        LocalDate dataNascimento = dtIdade.getValue();

        if (dataNascimento != null && nome != null && !nome.isEmpty()) {
            LocalDate hoje = LocalDate.now();

            // Calcula a idade em anos
            Period idade = Period.between(dataNascimento, hoje);

            // Calcula os dias vividos
            long diasVividos = ChronoUnit.DAYS.between(dataNascimento, hoje);

            // Descobre o dia da semana do nascimento
            String diaSemanaNascimento = dataNascimento.format(
                DateTimeFormatter.ofPattern("EEEE", Locale.getDefault())
            );

            // Exibe o resultado formatado
            lblResultado.setText(
                nome + ", sua idade é: " + idade.getYears() + " anos.\n" +
                "Você já viveu " + diasVividos + " dias.\n" +
                "Você nasceu em uma " + diaSemanaNascimento + "."
            );

        } else {
            lblResultado.setText("Por favor, preencha todos os campos.");
        }
    }
}



