package flappybird;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

    public GameFrame(String username) {

        setTitle("Flappy Bird - " + username);

        setSize(600, 800);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        GamePanel panel =
                new GamePanel(username);

        add(panel);

        setVisible(true);

        panel.requestFocusInWindow();
    }
}
