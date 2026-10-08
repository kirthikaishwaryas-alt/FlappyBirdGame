package flappybird;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

    private static final String URL =
            "jdbc:mysql://localhost:3306/flappy_bird";

    private static final String USER = "root";

    // CHANGE THIS TO YOUR MYSQL PASSWORD
    private static final String PASSWORD = "Ishu@1428";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static boolean registerUser(String username, String password) {

        String sql =
                "INSERT INTO users(username, password) VALUES (?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println("Registration error: "
                    + e.getMessage());

            return false;
        }
    }

    public static boolean loginUser(String username, String password) {

        String sql =
                "SELECT * FROM users WHERE username=? AND password=?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {

            System.out.println("Login error: "
                    + e.getMessage());

            return false;
        }
    }

    public static void saveScore(String username, int score) {

        String sql =
                "INSERT INTO scores(username, score) VALUES (?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setInt(2, score);

            ps.executeUpdate();

        } catch (SQLException e) {

            System.out.println("Score save error: "
                    + e.getMessage());
        }
    }

    public static int getHighScore(String username) {

        String sql =
                "SELECT MAX(score) FROM scores WHERE username=?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {

            System.out.println("High score error: "
                    + e.getMessage());
        }

        return 0;
    }
}