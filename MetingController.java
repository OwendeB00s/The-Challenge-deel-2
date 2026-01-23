
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.UUID;

public class MetingController {

    @FXML private TextField metingIdField;
    @FXML private TextField co2Field;
    @FXML private DatePicker datumPicker;
    @FXML private TextField tijdField;
    @FXML private TextField productField;
    @FXML private TextField bestellingField;

    @FXML private TableView<Meting> table;

    @FXML private TableColumn<Meting, String> colId;
    @FXML private TableColumn<Meting, String> colCo2;
    @FXML private TableColumn<Meting, String> colDatum;
    @FXML private TableColumn<Meting, String> colTijd;

    @FXML
    public void initialize() {
        // ✅ TableView kolommen koppelen aan getters
        colId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMetingId()));
        colCo2.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCo2Gehalte()));
        colDatum.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDatum().toString()));
        colTijd.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTijd()));

        // ✅ Meteen een UUID klaarzetten in het metingIdField
        genereerNieuwMetingId();

        refreshTable();
    }

    // ✅ Genereert een nieuwe UUID en toont hem in het veld
    private void genereerNieuwMetingId() {
        metingIdField.setText(UUID.randomUUID().toString());
    }

    @FXML
    public void voegToe() {
        LocalDate datum = datumPicker.getValue();

        // ✅ metingIdField niet meer checken; die wordt automatisch gevuld
        if (co2Field.getText().isBlank() || datum == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Onvolledig");
            alert.setHeaderText("Vul minimaal CO2 en Datum in.");
            alert.showAndWait();
            return;
        }

        // ✅ Insert met UUID uit het veld
        Database.insertMeting(
                metingIdField.getText(),
                co2Field.getText(),
                datum,
                tijdField.getText(),
                productField.getText(),
                bestellingField.getText()
        );

        // Velden leegmaken (behalve ID)
        co2Field.clear();
        datumPicker.setValue(null);
        tijdField.clear();
        productField.clear();
        bestellingField.clear();

        // ✅ Nieuwe UUID voor de volgende meting
        genereerNieuwMetingId();

        refreshTable();
    }

    private void refreshTable() {
        table.setItems(FXCollections.observableArrayList(Database.getMetingen()));
    }
}
