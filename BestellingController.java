import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;

public class BestellingController {

    @FXML private TextField codeField;
    @FXML private TextField hoeveelheidField;
    @FXML private TextField prijsField;
    @FXML private TextField datumField;
    @FXML private TextField knrField;

    @FXML private TableView<Bestelling> table;
    @FXML private TableColumn<Bestelling, String> colCode;
    @FXML private TableColumn<Bestelling, String> colHoeveelheid;
    @FXML private TableColumn<Bestelling, String> colPrijs;
    @FXML private TableColumn<Bestelling, String> colKnr;
    @FXML private TableColumn<Bestelling, String> colDatum;

    @FXML
    public void initialize() {
        colCode.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getBestelCode()));
        colHoeveelheid.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getHoeveelheid()));
        colPrijs.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPrijs()));
        colDatum.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDatum().toString()));
        colKnr.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getKnr()));

        table.setItems(FXCollections.observableArrayList(Database.getBestellingen()));
    }

    @FXML
    public void voegToe() {
        Database.insertBestelling(
                codeField.getText(),
                hoeveelheidField.getText(),
                prijsField.getText(),
                datumField.getText(),
                knrField.getText()
        );

        table.setItems(FXCollections.observableArrayList(Database.getBestellingen()));
    }
}
