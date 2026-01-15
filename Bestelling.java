import java.time.LocalDate;

public class Bestelling {

    private String bestelCode;
    private String hoeveelheid;
    private String prijs;
    private LocalDate datum;
    private String knr;

    public Bestelling(String bestelCode, String hoeveelheid, String prijs, LocalDate datum, String knr) {
        this.bestelCode = bestelCode;
        this.hoeveelheid = hoeveelheid;
        this.prijs = prijs;
        this.datum = datum;
        this.knr = knr;
    }

    public String getBestelCode() { return bestelCode; }
    public String getHoeveelheid() { return hoeveelheid; }
    public String getPrijs() { return prijs; }
    public LocalDate getDatum() { return datum; }
    public String getKnr() { return knr; }
}
