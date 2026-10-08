package flappybird;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel
        implements ActionListener, KeyListener {

    private Bird bird;

    private ArrayList<Pipe> pipes;

    private Timer timer;

    private Random random;

    private String username;

    private int score = 0;

    private int frameCount = 0;

    private boolean gameOver = false;

    private int highScore;

    public GamePanel(String username) {

        this.username = username;

        highScore =
                Database.getHighScore(username);

        bird = new Bird();

        pipes = new ArrayList<>();

        random = new Random();

        setFocusable(true);

        addKeyListener(this);

        timer = new Timer(
                30,
                this
        );

        timer.start();

        requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        drawBackground(g);

        bird.draw(g);

        for (Pipe pipe : pipes) {

            pipe.draw(g);
        }

        drawScore(g);

        if (gameOver) {

            drawGameOver(g);
        }
    }

    private void drawBackground(Graphics g) {

        // Sky
        g.setColor(
                new Color(100, 200, 255)
        );

        g.fillRect(
                0,
                0,
                getWidth(),
                getHeight()
        );

        // Clouds
        g.setColor(Color.WHITE);

        g.fillOval(50, 100, 80, 40);

        g.fillOval(100, 80, 100, 60);

        g.fillOval(400, 150, 100, 50);

        // Ground
        g.setColor(
                new Color(80, 180, 50)
        );

        g.fillRect(
                0,
                getHeight() - 100,
                getWidth(),
                100
        );

        // Ground line
        g.setColor(
                new Color(180, 120, 50)
        );

        g.fillRect(
                0,
                getHeight() - 30,
                getWidth(),
                30
        );
    }

    private void drawScore(Graphics g) {

        g.setColor(Color.WHITE);

        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        g.drawString(
                "Score: " + score,
                20,
                40
        );

        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        g.drawString(
                "Best: " + highScore,
                20,
                70
        );
    }

    private void drawGameOver(Graphics g) {

        g.setColor(
                new Color(0, 0, 0, 170)
        );

        g.fillRect(
                0,
                0,
                getWidth(),
                getHeight()
        );

        g.setColor(Color.WHITE);

        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        45
                )
        );

        g.drawString(
                "GAME OVER",
                160,
                350
        );

        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        25
                )
        );

        g.drawString(
                "Score: " + score,
                235,
                400
        );

        g.drawString(
                "Press R to Restart",
                185,
                450
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (gameOver) {

            repaint();

            return;
        }

        frameCount++;

        bird.update();

        // Create pipes
        if (frameCount % 90 == 0) {

            int topHeight =
                    100 + random.nextInt(300);

            pipes.add(
                    new Pipe(
                            getWidth(),
                            topHeight
                    )
            );
        }

        // Move pipes
        for (Pipe pipe : pipes) {

            pipe.update();

            // Collision
            if (
                    bird.getBounds()
                            .intersects(
                                    pipe.getTopBounds()
                            )
                    ||
                    bird.getBounds()
                            .intersects(
                                    pipe.getBottomBounds()
                            )
            ) {

                endGame();

                return;
            }

            // Score
            if (
                    pipe.x + pipe.width == bird.x
            ) {

                score++;
            }
        }

        // Remove pipes
        for (int i = pipes.size() - 1;
             i >= 0;
             i--) {

            if (pipes.get(i).isOffScreen()) {

                pipes.remove(i);
            }
        }

        // Ground collision
        if (
                bird.y + bird.height
                        >= getHeight() - 30
        ) {

            endGame();

            return;
        }

        // Ceiling collision
        if (bird.y <= 0) {

            bird.y = 0;

            bird.velocity = 0;
        }

        repaint();
    }

    private void endGame() {

        gameOver = true;

        timer.stop();

        Database.saveScore(
                username,
                score
        );

        if (score > highScore) {

            highScore = score;
        }

        repaint();
    }

    private void restartGame() {

        bird = new Bird();

        pipes.clear();

        score = 0;

        frameCount = 0;

        gameOver = false;

        timer.start();

        requestFocusInWindow();
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE ||
                key == KeyEvent.VK_UP) {

            if (!gameOver) {

                bird.jump();
            }
        }

        if (key == KeyEvent.VK_R) {

            if (gameOver) {

                restartGame();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}