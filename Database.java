import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static final String URL = "jdbc:mysql://localhost:3306/databse%20challenge";
    private static final String USER = "root";
    private static final String PASSWORD = "OwendeB00s";

    public static void insertBestelling(String code, String hoeveelheid, String prijs, String datum, String knr) {
        String sql = "INSERT INTO bestelling (Bestel_code, Hoeveelheid, Prijs, Datum, Knr) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

             PreparedStatement stmt = conn.prepareStatement(sql)) { stmt.setString(1, code);
            stmt.setString(2, hoeveelheid);
            stmt.setString(3, prijs);
            stmt.setString(4, datum);
            stmt.setString(5, knr);
            stmt.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static List<Bestelling> getBestellingen() {
        List<Bestelling> lijst = new ArrayList<>();
        String sql = "SELECT * FROM bestelling";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lijst.add(new Bestelling(
                        rs.getString("Bestel_code"),
                        rs.getString("Hoeveelheid"),
                        rs.getString("Prijs"),
                        rs.getDate("Datum").toLocalDate(),
                        rs.getString("Knr") ));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return lijst;
    }
}