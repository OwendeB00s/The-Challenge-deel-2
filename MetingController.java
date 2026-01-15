
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class MetingController {

    @FXML private TextField metingIdField;
    @FXML private TextField co2Field;
    @FXML private DatePicker datumPicker;     // <-- DatePicker
    @FXML private TextField tijdField;
    @FXML private TextField productField;
    @FXML private TextField bestellingField;

    @FXML private TableView<Meting> table;

    @FXML private TableColumn<Meting, String> colMetingId;
    @FXML private TableColumn<Meting, String> colCo2;
    @FXML private TableColumn<Meting, String> colDatum;
    @FXML private TableColumn<Meting, String> colTijd;
    @FXML private TableColumn<Meting, String> colProduct;
    @FXML private TableColumn<Meting, String> colBestelling;

    @FXML
    public void initialize() {
        colMetingId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMetingId()));
        colCo2.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCo2Gehalte()));
        colDatum.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDatum().toString()));
        colTijd.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTijd()));
        colProduct.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getProduct()));
        colBestelling.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBestelling()));

        refreshTable();
    }

    @FXML
    public void voegToe() {
        LocalDate datum = datumPicker.getValue();

        // Mini-validatie (netter, voorkomt null/lege inserts)
        if (metingIdField.getText().isBlank() || co2Field.getText().isBlank() || datum == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Onvolledig");
            alert.setHeaderText("Vul minimaal Meting ID, CO2 en Datum in.");
            alert.showAndWait();
            return;
        }

        Database.insertMeting(
                metingIdField.getText(),
                co2Field.getText(),
                datum, // <-- LocalDate doorgeven (netter)
                tijdField.getText(),
                productField.getText(),
                bestellingField.getText()
        );

        // optioneel: velden leegmaken
        metingIdField.clear();
        co2Field.clear();
        datumPicker.setValue(null);
        tijdField.clear();
        productField.clear();
        bestellingField.clear();

        refreshTable();
    }

    private void refreshTable() {
        table.setItems(FXCollections.observableArrayList(Database.getMetingen()));
    }
}
