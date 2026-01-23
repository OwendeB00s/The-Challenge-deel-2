
import java.time.LocalDate;

public class Meting {

    private String metingId;
    private String co2Gehalte;
    private LocalDate datum;
    private String tijd;
    private String product;
    private String bestelling;

    // Nodig voor JSON libs (Jackson)
    public Meting() {}

    public Meting(String metingId, String co2Gehalte, LocalDate datum, String tijd, String product, String bestelling) {
        this.metingId = metingId;
        this.co2Gehalte = co2Gehalte;
        this.datum = datum;
        this.tijd = tijd;
        this.product = product;
        this.bestelling = bestelling;
    }

    public String getMetingId() { return metingId; }
    public void setMetingId(String metingId) { this.metingId = metingId; }

    public String getCo2Gehalte() { return co2Gehalte; }
    public void setCo2Gehalte(String co2Gehalte) { this.co2Gehalte = co2Gehalte; }

    public LocalDate getDatum() { return datum; }
    public void setDatum(LocalDate datum) { this.datum = datum; }

    public String getTijd() { return tijd; }
    public void setTijd(String tijd) { this.tijd = tijd; }

    public String getProduct() { return product; }
    public void setProduct(String product) { this.product = product; }

    public String getBestelling() { return bestelling; }
    public void setBestelling(String bestelling) { this.bestelling = bestelling; }
}
