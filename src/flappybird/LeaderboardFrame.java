package flappybird;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LeaderboardFrame extends JFrame {

    private JTextArea area;

    public LeaderboardFrame() {

        setTitle("Flappy Bird - Leaderboard");

        setSize(400, 500);

        setLocationRelativeTo(null);

        area = new JTextArea();

        area.setEditable(false);

        area.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        16
                )
        );

        add(
                new JScrollPane(area)
        );

        loadScores();

        setVisible(true);
    }

    private void loadScores() {

        String sql =
                "SELECT username, MAX(score) AS best_score " +
                "FROM scores " +
                "GROUP BY username " +
                "ORDER BY best_score DESC " +
                "LIMIT 10";

        try (
                Connection con =
                        Database.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            area.append(
                    "       LEADERBOARD\n\n"
            );

            area.append(
                    "USERNAME          SCORE\n"
            );

            area.append(
                    "------------------------\n"
            );

            while (rs.next()) {

                String username =
                        rs.getString("username");

                int score =
                        rs.getInt("best_score");

                area.append(
                        String.format(
                                "%-18s %d%n",
                                username,
                                score
                        )
                );
            }

        } catch (SQLException e) {

            area.setText(
                    "Database error:\n"
                    + e.getMessage()
            );
        }
    }
}