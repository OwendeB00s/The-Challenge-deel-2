
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        root.setBottom(navBar);


        homeBtn.setOnAction(e -> updateView("Welkom!", homeBtn));
        historyBtn.setOnAction(e -> updateView("Geschiedenis (inhoud komt nog)", historyBtn));

        settingsBtn.setOnAction(e -> {

            VBox settingsView = new VBox(20);
            settingsView.setAlignment(Pos.CENTER);
            Text settingsText = new Text("Instellingen (inhoud komt nog)");
            settingsView.getChildren().addAll(settingsText, accountBtn);
            root.setCenter(settingsView);


            homeBtn.setDisable(false);
            historyBtn.setDisable(false);
            settingsBtn.setDisable(true);
            accountBtn.setDisable(false);
        });

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
}
