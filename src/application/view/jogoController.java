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

    private MediaPlayer mediaPlayer; // 🔊 variável global para não encerrar o som

    private double playerX = 200;
    private final double playerY = 500;
    private final double raio = 16;
    private final double larguraTela = 360;
    private final double alturaTela = 600;
    private int pontuacao = 0;
    private ArrayList<Obstaculo> obstaculos = new ArrayList<>();
    private Random random = new Random();
    private boolean esquerda, direita, nitroAtivo;

    private Image imagemPlayer;
    private Image imagemObstaculo;

    // 🔥 Nitro
    private double velocidadeBase = 4;
    private double velocidadeNitro = 8;
    private boolean podeUsarNitro = true;
    private long tempoNitro = 0;
    private long duracaoNitro = 2_000_000_000L; // 2 segundos
    private long recargaNitro = 4_000_000_000L; // 4 segundos
    private long tempoUltimoNitro = 0;

    @FXML
    public void initialize() {
        // 🎵 Música de fundo (loop infinito)
        String caminhoMusica = getClass().getResource("DeathNote.mp3").toExternalForm();
        Media musica = new Media(caminhoMusica);
        mediaPlayer = new MediaPlayer(musica);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // repete para sempre
        mediaPlayer.setVolume(0.3);
        mediaPlayer.play();

        // 🚗 Imagens
        InputStream playerStream = getClass().getResourceAsStream("f1_azul.png");
        InputStream obstaculoStream = getClass().getResourceAsStream("f1_vermelho.png");
        imagemPlayer = new Image(playerStream);
        imagemObstaculo = new Image(obstaculoStream);

        // Foco no canvas
        canva.setFocusTraversable(true);
        canva.requestFocus();

        GraphicsContext gc = canva.getGraphicsContext2D();

        // 🎮 Controles
        canva.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) esquerda = true;
            if (e.getCode() == KeyCode.RIGHT) direita = true;
            if (e.getCode() == KeyCode.SPACE && podeUsarNitro) ativarNitro();
        });

        canva.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.LEFT) esquerda = false;
            if (e.getCode() == KeyCode.RIGHT) direita = false;
        });

        // 🕹️ Game Loop
        AnimationTimer timer = new AnimationTimer() {
            long ultimoSpaw = 0;

            @Override
            public void handle(long now) {
                atualizar(now);
                desenhar(gc, now);

                // Criação dos obstáculos
                if (now - ultimoSpaw > 1_000_000_000) {
                    obstaculos.add(new Obstaculo(random.nextInt((int) larguraTela - 40), -40));
                    ultimoSpaw = now;
                }
            }
        };
        timer.start();
    }

    private void desenhar(GraphicsContext gc, long now) {
        // Fundo (muda com nitro)
        gc.setFill(nitroAtivo ? Color.DARKBLUE : Color.GRAY);
        gc.fillRect(0, 0, larguraTela, alturaTela);

        // Player
        gc.drawImage(imagemPlayer, playerX - raio, playerY - raio, raio * 4, raio * 4);

        // Obstáculos
        for (Obstaculo obs : obstaculos) {
            gc.drawImage(imagemObstaculo, obs.x, obs.y, obs.largura, obs.altura);
        }

        // HUD
        gc.setFill(Color.BLACK);
        gc.setFont(javafx.scene.text.Font.font(18));
        gc.fillText("Pontuação: " + pontuacao, 10, 20);

        // Nitro status
        gc.setFill(podeUsarNitro ? Color.LIME : Color.RED);
        gc.fillText("Nitro: " + (podeUsarNitro ? "Disponível" : "Recarregando"), 10, 40);
    }

    private void atualizar(long now) {
        // Movimento lateral
        if (esquerda && playerX - raio > 0) playerX -= 5;
        if (direita && playerX + raio < larguraTela) playerX += 5;

        double velocidade = nitroAtivo ? velocidadeNitro : velocidadeBase;

        // Atualiza obstáculos
        Iterator<Obstaculo> it = obstaculos.iterator();
        while (it.hasNext()) {
            Obstaculo obs = it.next();
            obs.y += velocidade;

            double centroPlayerX = playerX + raio;
            double centroPlayerY = playerY + raio;
            double obsTopo = obs.y;
            double obsBase = obs.y + obs.altura;
            double obsEsquerda = obs.x;
            double obsDireita = obs.x + obs.largura;

            boolean colidiu = centroPlayerX >= obsEsquerda &&
                    centroPlayerX <= obsDireita &&
                    centroPlayerY >= obsTopo &&
                    centroPlayerY <= obsBase;

            if (colidiu) {
                pontuacao = 0;
                it.remove();
            } else if (obs.y > alturaTela) {
                pontuacao++;
                it.remove();
            }
        }

        // ⏱️ Tempo do nitro
        if (nitroAtivo && now - tempoNitro > duracaoNitro) {
            desativarNitro(now);
        }
        if (!podeUsarNitro && now - tempoUltimoNitro > recargaNitro) {
            podeUsarNitro = true;
        }
    }

    private void ativarNitro() {
        nitroAtivo = true;
        tempoNitro = System.nanoTime();
        podeUsarNitro = false;
    }

    private void desativarNitro(long now) {
        nitroAtivo = false;
        tempoUltimoNitro = now;
    }

    // 🚧 Classe obstáculo
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
