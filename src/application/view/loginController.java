package application.view;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class loginController {
	@FXML
    private PasswordField txtSenha;

    @FXML
    private TextField txtUsuario;
    
    @FXML
    private Label lblNovoUsuario;
	
	public void sair() {
		System.exit(0);
	}
	
	public void entrar() {
		try {
		String usuario=txtUsuario.getText();
		String senha=txtSenha.getText();
		
		if (usuario.equals("admin") 
			&& senha.equals("admins")) {
			Alert aviso;
			aviso=new Alert(Alert.AlertType.CONFIRMATION);
			aviso.setTitle("Confirmação");
			aviso.setContentText("Bem vindo ao Sistema"+usuario);
			aviso.showAndWait();
		
			//FECHAR TELA DE LOGIN
			txtUsuario.getScene().getWindow().hide();
			
			//ABRE A TELA PRINCIPAL
			Parent root = FXMLLoader.load(getClass().getResource("aplicativo.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} else {
			Alert aviso;
			aviso=new Alert(Alert.AlertType.ERROR);
			aviso.setTitle("Erro");
			aviso.setContentText("Usuario ou senha incorretos");
			aviso.showAndWait();
		}
		} catch(Exception e) {
			e.printStackTrace();
			}
		}
	@FXML
	/* @FXML OU @override -> indica que o codigo sera executado assim que carregar a pagina*/
	
	private void initialize() {
		/* QUANDO PRESSIONAR ENTER NO CAMPO USUARIO FOCA A EDIÇÃO NO CAMPO DE SENHA
		 * */
		
		 txtUsuario.setOnAction(e->{txtSenha.requestFocus();});
		 /* QUANDO PRESSIONAR ENTER NO CAMPO SENHA ACIONA O METODO DE ENTRAR*/
		 txtSenha.setOnAction(e->{entrar();});
	

      lblNovoUsuario.setOnMouseClicked(event->{
	  try {
//abre a tela cadastrado usuario
		Parent root = FXMLLoader.load(getClass().getResource("usuario.fxml"));
				Stage stage = new Stage();
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
	}catch (Exception e) {
		e.printStackTrace();
	}
});
}
}

	
		
