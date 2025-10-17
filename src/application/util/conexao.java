package application.util;

import java.sql.Connection;
import java.sql.DriveManager;
import java.sql.SQLException;



public class conexao {
	// ENDERECO DO BANCO DE DADOS
	 private static final String URL="jbdc:mysql://localhost:3306/DB";
	 // USUARIO DE BANCO DE DADOS
     private static final String USER="root";
     //SENHA DO BANCO DE DADOS
     private static final String PASS="detonador25";
     
     public static Connection getConnection() throws SQLException{
    	 return DriverManager.getConnection(URL,USER,PASS);
     }
}
