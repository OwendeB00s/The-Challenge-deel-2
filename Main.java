import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    private BorderPane root;
    private Button homeBtn, historyBtn, settingsBtn, accountBtn;
    private HBox navBar;

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();

       
        homeBtn = new Button("Home");
        historyBtn = new Button("Geschiedenis");
        settingsBtn = new Button("Instellingen");
        accountBtn = new Button("Account");


        updateView("Welkom!", homeBtn);

        
        navBar = new HBox(10, historyBtn, homeBtn, settingsBtn);
        navBar.setAlignment(Pos.CENTER);
        navBar.setPadding(new Insets(10));
        root.setBottom(navBar);


        homeBtn.setOnAction(e -> updateView("Welkom!", homeBtn));
        historyBtn.setOnAction(e -> updateView("Geschiedenis (inhoud komt nog)", historyBtn));
        settingsBtn.setOnAction(e -> openSettings());
        accountBtn.setOnAction(e -> updateView("Account", accountBtn));


        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("AirAware");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateView(String text, Button activeBtn) {
        root.setCenter(new Text(text));


        homeBtn.setDisable(false);
        historyBtn.setDisable(false);
        settingsBtn.setDisable(false);
        accountBtn.setDisable(false);


        if (activeBtn != null) {
            activeBtn.setDisable(true);
        }
    }

    private void openSettings() {
        VBox settingsBox = new VBox(20);
        settingsBox.setPadding(new Insets(20));
        settingsBox.setAlignment(Pos.CENTER);

        Button personalDataBtn = new Button("Personal Data");
        Button connectDeviceBtn = new Button("Connect Device");
        Button logoutBtn = new Button("Logout");


        personalDataBtn.setMinWidth(200);
        connectDeviceBtn.setMinWidth(200);
        logoutBtn.setMinWidth(200);
        accountBtn.setMinWidth(200);

        settingsBox.getChildren().addAll(
                personalDataBtn,
                connectDeviceBtn,
                logoutBtn,
                accountBtn
        );

        root.setCenter(settingsBox);

       
        homeBtn.setDisable(false);
        historyBtn.setDisable(false);
        settingsBtn.setDisable(true);
        accountBtn.setDisable(false);


        personalDataBtn.setOnAction(ev ->
                updateView("No data found", personalDataBtn)
        );

        connectDeviceBtn.setOnAction(ev -> {
            TextInputDialog codeDialog = new TextInputDialog();
            codeDialog.setTitle("Connect Device");
            codeDialog.setHeaderText("Voer de verbindingscode in:");
            codeDialog.setContentText("Code:");
            codeDialog.showAndWait();
        });

        logoutBtn.setOnAction(ev -> {
            Alert logoutAlert = new Alert(Alert.AlertType.CONFIRMATION);
            logoutAlert.setTitle("Logout");
            logoutAlert.setHeaderText("Are you sure you want to log out?");
            logoutAlert.setContentText("Klik Ja of Nee");

            ButtonType yesBtn = new ButtonType("Ja");
            ButtonType noBtn = new ButtonType("Nee");

            logoutAlert.getButtonTypes().setAll(yesBtn, noBtn);
            logoutAlert.showAndWait();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
