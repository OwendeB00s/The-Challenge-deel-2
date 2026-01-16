
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Database {

    private static final String URL = "jdbc:mysql://localhost:3306/databasechallenge";
    private static final String USER = "root";
    private static final String PASSWORD = "OwendeB00s";

    public static void insertMeting(String metingId, String co2Gehalte, LocalDate datum,
                                    String tijd, String product, String bestelling) {

       
        String sql =
                "INSERT INTO meting (metingId, CO2_gehalte, Datum, Tijd, Product, Bestelling) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, metingId);
            stmt.setString(2, co2Gehalte);
            stmt.setDate(3, java.sql.Date.valueOf(datum));
            stmt.setString(4, tijd);
            stmt.setString(5, product);
            stmt.setString(6, bestelling);

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Meting> getMetingen() {
        List<Meting> lijst = new ArrayList<>();
        String sql = "SELECT * FROM meting";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lijst.add(new Meting(
                        rs.getString("metingId"),       // i.p.v. Meting_id
                        rs.getString("CO2_gehalte"),
                        rs.getDate("Datum").toLocalDate(),
                        rs.getString("Tijd"),
                        rs.getString("Product"),
                        rs.getString("Bestelling")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lijst;
    }
}
