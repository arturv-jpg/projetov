package application.view;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class jogoController {

    @FXML
    private Canvas canva;

    private MediaPlayer mediaPlayer;

    // Player 1
    private double player1X = 120;
    private final double player1Y = 500;
    private boolean esquerda1, direita1, nitro1Ativo;
    private boolean podeUsarNitro1 = true;
    private long tempoNitro1 = 0;
    private long tempoUltimoNitro1 = 0;
    private int pontuacao1 = 0;

    // Player 2
    private double player2X = 220;
    private final double player2Y = 500;
    private boolean esquerda2, direita2, nitro2Ativo;
    private boolean podeUsarNitro2 = true;
    private long tempoNitro2 = 0;
    private long tempoUltimoNitro2 = 0;
    private int pontuacao2 = 0;

    // Configurações gerais
    private final double raio = 16;
    private final double larguraTela = 360;
    private final double alturaTela = 600;
    private double velocidadeBase = 4;
    private double velocidadeNitro = 8;
    private long duracaoNitro = 2_000_000_000L;
    private long recargaNitro = 4_000_000_000L;

    private ArrayList<Obstaculo> obstaculos = new ArrayList<>();
    private Random random = new Random();

    private Image imagemPlayer1;
    private Image imagemPlayer2;
    private Image imagemObstaculo;

    @FXML
    public void initialize() {
        // 🎵 Música de fundo
        String caminhoMusica = getClass().getResource("DeathNote.mp3").toExternalForm();
        Media musica = new Media(caminhoMusica);
        mediaPlayer = new MediaPlayer(musica);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.3);
        mediaPlayer.play();

        // 🚗 Imagens
        InputStream player1Stream = getClass().getResourceAsStream("f1_azul.png");
        InputStream player2Stream = getClass().getResourceAsStream("f1_verde.png");
        InputStream obstaculoStream = getClass().getResourceAsStream("f1_vermelho.png");
        imagemPlayer1 = new Image(player1Stream);
        imagemPlayer2 = new Image(player2Stream);
        imagemObstaculo = new Image(obstaculoStream);

        // 🎮 Controles
        canva.setFocusTraversable(true);
        canva.requestFocus();

        canva.setOnKeyPressed(e -> {
            // Player 1
            if (e.getCode() == KeyCode.LEFT) esquerda1 = true;
            if (e.getCode() == KeyCode.RIGHT) direita1 = true;
            if (e.getCode() == KeyCode.SPACE && podeUsarNitro1) ativarNitro1();

            // Player 2
            if (e.getCode() == KeyCode.A) esquerda2 = true;
            if (e.getCode() == KeyCode.D) direita2 = true;
            if (e.getCode() == KeyCode.SHIFT && podeUsarNitro2) ativarNitro2();
        });

        canva.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.LEFT) esquerda1 = false;
            if (e.getCode() == KeyCode.RIGHT) direita1 = false;
            if (e.getCode() == KeyCode.A) esquerda2 = false;
            if (e.getCode() == KeyCode.D) direita2 = false;
        });

        GraphicsContext gc = canva.getGraphicsContext2D();

        // 🕹️ Game loop
        AnimationTimer timer = new AnimationTimer() {
            long ultimoSpaw = 0;

            @Override
            public void handle(long now) {
                atualizar(now);
                desenhar(gc, now);

                if (now - ultimoSpaw > 1_000_000_000) {
                    obstaculos.add(new Obstaculo(random.nextInt((int) larguraTela - 40), -40));
                    ultimoSpaw = now;
                }
            }
        };
        timer.start();
    }

    private void desenhar(GraphicsContext gc, long now) {
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, larguraTela, alturaTela);

        // Players
        gc.drawImage(imagemPlayer1, player1X - raio, player1Y - raio, raio * 4, raio * 4);
        gc.drawImage(imagemPlayer2, player2X - raio, player2Y - raio, raio * 4, raio * 4);

        // Obstáculos
        for (Obstaculo obs : obstaculos) {
            gc.drawImage(imagemObstaculo, obs.x, obs.y, obs.largura, obs.altura);
        }

        // HUD
        gc.setFill(Color.BLACK);
        gc.setFont(javafx.scene.text.Font.font(16));
        gc.fillText("P1: " + pontuacao1, 10, 20);
        gc.fillText("P2: " + pontuacao2, 290, 20);

        // Nitro status
        gc.setFill(podeUsarNitro1 ? Color.LIME : Color.RED);
        gc.fillText("N1", 10, 40);
        gc.setFill(podeUsarNitro2 ? Color.LIME : Color.RED);
        gc.fillText("N2", 330, 40);
    }

    private void atualizar(long now) {
        // Movimento lateral P1
        if (esquerda1 && player1X - raio > 0) player1X -= 5;
        if (direita1 && player1X + raio < larguraTela / 2 - 10) player1X += 5;

        // Movimento lateral P2
        if (esquerda2 && player2X - raio > larguraTela / 2) player2X -= 5;
        if (direita2 && player2X + raio < larguraTela) player2X += 5;

        // Velocidades
        double vel1 = nitro1Ativo ? velocidadeNitro : velocidadeBase;
        double vel2 = nitro2Ativo ? velocidadeNitro : velocidadeBase;

        Iterator<Obstaculo> it = obstaculos.iterator();
        while (it.hasNext()) {
            Obstaculo obs = it.next();
            obs.y += (vel1 + vel2) / 2; // avança com média das velocidades

            // Colisões
            if (colisao(player1X, player1Y, obs)) {
                pontuacao1 = 0;
                it.remove();
            } else if (colisao(player2X, player2Y, obs)) {
                pontuacao2 = 0;
                it.remove();
            } else if (obs.y > alturaTela) {
                pontuacao1++;
                pontuacao2++;
                it.remove();
            }
        }

        // Nitro timers
        if (nitro1Ativo && now - tempoNitro1 > duracaoNitro) desativarNitro1(now);
        if (!podeUsarNitro1 && now - tempoUltimoNitro1 > recargaNitro) podeUsarNitro1 = true;

        if (nitro2Ativo && now - tempoNitro2 > duracaoNitro) desativarNitro2(now);
        if (!podeUsarNitro2 && now - tempoUltimoNitro2 > recargaNitro) podeUsarNitro2 = true;
    }

    private boolean colisao(double x, double y, Obstaculo obs) {
        double centroX = x + raio;
        double centroY = y + raio;
        return centroX >= obs.x && centroX <= obs.x + obs.largura &&
               centroY >= obs.y && centroY <= obs.y + obs.altura;
    }

    // Nitro player 1
    private void ativarNitro1() {
        nitro1Ativo = true;
        tempoNitro1 = System.nanoTime();
        podeUsarNitro1 = false;
    }

    private void desativarNitro1(long now) {
        nitro1Ativo = false;
        tempoUltimoNitro1 = now;
    }

    // Nitro player 2
    private void ativarNitro2() {
        nitro2Ativo = true;
        tempoNitro2 = System.nanoTime();
        podeUsarNitro2 = false;
    }

    private void desativarNitro2(long now) {
        nitro2Ativo = false;
        tempoUltimoNitro2 = now;
    }

    // Obstáculo
    class Obstaculo {
        double x, y;
        final double largura = 70;
        final double altura = 60;

        Obstaculo(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}

