package application.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class calculadoraController {

    @FXML
    private Button btnDividir;

    @FXML
    private Button btnMultiplicar;

    @FXML
    private Button btnSoma;

    @FXML
    private Button btnSubtrair;

    @FXML
    private Label lblResultado;

    @FXML
    private TextField txtNumero1;

    @FXML
    private TextField txtNumero2;
    
    @FXML
    private Button btnReset;
    
    @FXML
    private void initialize() {
    	/*
    	iniciar programa com valores zerados */
    	txtNumero1.setText("0");
		txtNumero2.setText("0");
		
		/* O setOnAction aciona o evento do componente
    	por exemplo: o click no botão
    	ou o enter em um text field */
		
		btnSubtrair.setOnAction(e->{Subtrair();});
    	btnMultiplicar.setOnAction(e->{Multiplicar();});
    	btnDividir.setOnAction(e->{dividir();});
    	btnReset.setOnAction(e->{
    		txtNumero1.setText("0");
    		txtNumero2.setText("0");
    		lblResultado.setText("Resultado:");    		
    	});

    	/* adicionar um escutador de evento no
        text field de numero
    	ao digitar dentro do text fiel ele vai trocar a letra
    	por uma informação vazia atravez do replaceAll
    	ou seja ele impede de digitar letras
    	lblResultado.setText(String.valueOf(Resultado));*/
  
    	txtNumero1.textProperty().addListener(
    			(observable, oldValue, newValue)->{
    				txtNumero1.setText(newValue.replaceAll("[^\\d.]",""));
    			});
    txtNumero2.textProperty().addListener(
			(observable, oldValue, newValue)->{
				txtNumero2.setText(newValue.replaceAll("[^\\d.]",""));
			});
    btnReset.setOnAction(e->{
    	txtNumero1.setText("0");
    	txtNumero2.setText("0");
    	lblResultado.setText("Resultado");
    });
  
}
    public void Somar () {
    	double numero1;
    	double numero2;
    	try {
    	 numero1 = Double.valueOf(txtNumero1.getText()); //utiliza o getText para retornar
    	 
    	} catch(Exception e) {
    		numero1=0;
    		txtNumero1.setText("0");
    	}
    	try {
       	 numero2 = Double.valueOf(txtNumero2.getText()); //utiliza o getText para retornar
       	 
       	} catch(Exception e) {
       		numero2=0;
       		txtNumero2.setText("0");
       	}
    	double Resultado = numero1+numero2;
    	lblResultado.setText("Resultado: "+String.valueOf(Resultado));
    	// RETORNA O VALOR DE DOUBLE PARA string
    	// INFORMA O RESULTADO NA LABEL COM O setText
    	String ParOuImpar;
    	if (Resultado % 2 == 0) {
    		ParOuImpar=" é Par.";
    	} else {
            ParOuImpar=" é impar.";
     }
    	lblResultado.setText("Resultado: "+String.valueOf(Resultado)+ParOuImpar);
    }
    public void Subtrair () {
    	double numero1 = StrToDbl(txtNumero1.getText()); 
    	double numero2 = StrToDbl(txtNumero2.getText()); 
    	txtNumero1.setText(String.valueOf(numero1));
    	txtNumero2.setText(String.valueOf(numero2));
    	double Resultado = numero1 - numero2;
    	lblResultado.setText(String.valueOf(Resultado));
    	String ParOuImpar;
    	if (Resultado % 2 == 0) {
    		ParOuImpar=" é Par.";
    	} else {
            ParOuImpar=" é impar.";
     }
    	lblResultado.setText("Resultado: "+String.valueOf(Resultado)+ParOuImpar);
    }
    	
    public void Multiplicar () {
    	double numero1 = Double.valueOf(txtNumero1.getText()); 
    	double numero2 = Double.valueOf(txtNumero2.getText()); 
    	double Resultado = numero1 * numero2;
    	
    	lblResultado.setText(String.valueOf(Resultado));
    	String ParOuImpar;
    	if (Resultado % 2 == 0) {
    		ParOuImpar=" é Par.";
    	} else {
            ParOuImpar=" é impar.";
     }
    	lblResultado.setText("Resultado: "+String.valueOf(Resultado)+ParOuImpar);
    }
    public void dividir () {
    	double numero1 = Double.valueOf(txtNumero1.getText()); 
    	double numero2 = Double.valueOf(txtNumero2.getText()); 
    	double Resultado = numero1 / numero2;
    	
    	lblResultado.setText(String.valueOf(Resultado));
    	String ParOuImpar;
    	if (Resultado % 2 == 0) {
    		ParOuImpar=" é Par.";
    	} else {
            ParOuImpar=" é impar.";
     }
    	lblResultado.setText("Resultado: "+String.valueOf(Resultado)+ParOuImpar);
    }
    // metodo de converter string para double
    public static double StrToDbl(String numero) {
    	try {
    		return Double.valueOf(numero);
    	} catch(Exception e) {
    		return 0;
    }
}
}
