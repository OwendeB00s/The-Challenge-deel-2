
import java.time.LocalDate;
import java.util.UUID;

public class Meting {
    private final String metingId;
    private final String co2Gehalte;
    private final LocalDate datum;
    private final String tijd;
    private final String product;
    private final String bestelling;

    public Meting(String co2Gehalte, LocalDate datum, String tijd, String product, String bestelling) {
        this.metingId = UUID.randomUUID().toString();
        this.co2Gehalte = co2Gehalte;
        this.datum = datum;
        this.tijd = tijd;
        this.product = product;
        this.bestelling = bestelling;
    }

    public Meting(String metingId, String co2Gehalte, LocalDate datum, String tijd, String product, String bestelling) {
        this.metingId = metingId;
        this.co2Gehalte = co2Gehalte;
        this.datum = datum;
        this.tijd = tijd;
        this.product = product;
        this.bestelling = bestelling;
    }

    public String getMetingId() { return metingId; }
    public String getCo2Gehalte() { return co2Gehalte; }
    public LocalDate getDatum() { return datum; }
    public String getTijd() { return tijd; }
    public String getProduct() { return product; }
    public String getBestelling() { return bestelling; }
}
``
