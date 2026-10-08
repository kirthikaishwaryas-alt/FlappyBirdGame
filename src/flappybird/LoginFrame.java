package flappybird;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {

        setTitle("Flappy Bird - Login");

        setSize(400, 300);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(6, 1, 10, 10));

        JLabel title = new JLabel(
                "FLAPPY BIRD",
                JLabel.CENTER
        );

        title.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        usernameField = new JTextField();

        passwordField = new JPasswordField();

        usernameField.setBorder(
                BorderFactory.createTitledBorder("Username")
        );

        passwordField.setBorder(
                BorderFactory.createTitledBorder("Password")
        );

        JButton loginButton =
                new JButton("LOGIN");

        JButton registerButton =
                new JButton("REGISTER");

        panel.add(title);
        panel.add(usernameField);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);

        add(panel);

        loginButton.addActionListener(e -> login());

        registerButton.addActionListener(e -> register());

        setVisible(true);
    }

    private void login() {

        String username =
                usernameField.getText().trim();

        String password =
                new String(passwordField.getPassword());

        if (username.isEmpty() ||
                password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter username and password"
            );

            return;
        }

        if (Database.loginUser(username, password)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Login successful!"
            );

            dispose();

            new GameFrame(username);

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid username or password"
            );
        }
    }

    private void register() {

        String username =
                usernameField.getText().trim();

        String password =
                new String(passwordField.getPassword());

        if (username.isEmpty() ||
                password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter username and password"
            );

            return;
        }

        if (Database.registerUser(username, password)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Registration successful!"
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Username already exists!"
            );
        }
    }
}