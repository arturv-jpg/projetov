package application.view;

public class Carro {
    private String marca;
    private String nome;
    private String motor;
    private String cor;
    private int ano;
    private String modelo;
    private boolean ligado;
    public String getNome() {
        return this.modelo; 
    }


    public Carro(String marca, String nome, String motor, String cor, int ano, String modelo) {
        this.marca = marca;
        this.nome = nome;
        this.motor = motor;
        this.cor = cor;
        this.ano = ano;
        this.modelo = modelo;
        this.ligado = false;
    }

    public String exibirInformacoes() {
        return "Marca: " + marca + "\n" +
               "Nome: " + nome + "\n" +
               "Motor: " + motor + "\n" +
               "Cor: " + cor + "\n" +
               "Ano: " + ano + "\n" +
               "Modelo: " + modelo + "\n" +
               "Status: " + (ligado ? "Ligado" : "Desligado");
    }

    public void ligarCarro() {
        ligado = true;
    }

    public void desligarCarro() {
        ligado = false;
    }
}

