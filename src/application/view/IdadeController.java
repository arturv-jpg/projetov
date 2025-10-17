package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.time.LocalDate;
import java.time.Period;

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
            Period idade = Period.between(dataNascimento, hoje);
            
            long diasVividos = 
            		java.time.temporal.ChronoUnit.DAYS.between(
            		dataNascimento, hoje);

            String diaSemanaNascimento = 
            	dataNascimento.getDayOfWeek().getDisplayName(
            		java.time.format.TextStyle.FULL, 
            		java.util.Locale.getDefault()
            		);
            
            lblResultado.setText(nome + ", sua idade é: " + idade.getYears() + " anos.");
            
        } else {
        		lblResultado.setText("Por favor, preencha todos os campos.");
        }
    }
}
//int hoje2 = LocalDate.now().getYear();
//int mesAtual = hoje2.getMonthValue(); // 1 a 12
//int diaAtual = hoje2.getDayOfMonth(); // 1 a 31
/*
LocalDate hoje = LocalDate.now();
long idadeAnos = ChronoUnit.YEARS.between(dataNascimento, hoje);
long diasVividos = Duration.between(dataNascimento.atStartOfDay(), hoje.atStartOfDay()).toDays();
String diaSemanaNascimento = dataNascimento.format(DateTimeFormatter.ofPattern("EEEE", Locale.getDefault()));

lblResultado.setText(
                    nome + ", sua idade é: " + idade.getYears() + " anos.\n" +
                    "Você já viveu " + diasVividos + " dias.\n" +
                    "Você nasceu em uma " + diaSemanaNascimento + "."
                );
* */