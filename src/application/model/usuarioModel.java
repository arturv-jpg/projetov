package application.model;

public class usuarioModel {

	private int id;
	private String nomeCompleto;
	private String login;
	private String senha;
	
	public usuarioModel(int id,String nomeCompleto,
		    String login,String senha) {
			this.id=id;
			this.nomeCompleto=nomeCompleto;
			this.login=login;
			this.senha=senha;
	}
	
	//getters e setters
		public int getID() {return id;}
		public void setID(int id) {this.id=id;}
		
		public String getNomeCompleto() {return nomeCompleto;}
		public void setNomeCompleto(String nomeCompleto) {this.nomeCompleto=nomeCompleto;}
 		
		public String getLogin() {return login;}
		public void setlogin(String login) {this.login=login;}
		
		public String getSenha() {return senha;}
		public void setsenha(String senha) {this.senha=senha;}
	
	}


