import javafx.application.Application;
import javafx.beans.binding.When;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class Main extends Application {

    private BorderPane root;
    private Button homeBtn, historyBtn, settingsBtn;
    private HBox navBar;

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();


        homeBtn = new Button("Home");
        historyBtn = new Button("History");
        settingsBtn = new Button("Settings");

        updateView("Welcome!", homeBtn);


        navBar = new HBox(10, historyBtn, homeBtn, settingsBtn);
        navBar.setAlignment(Pos.CENTER);
        navBar.setPadding(new Insets(10));
        root.setBottom(navBar);


        homeBtn.setOnAction(e -> updateView("Welcome!", homeBtn));
        historyBtn.setOnAction(e -> openHistory());
        settingsBtn.setOnAction(e -> openSettings());


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



        if (activeBtn != null) {
            activeBtn.setDisable(true);
        }
    }

    private void openHistory() {
        VBox historyBox = new VBox(20);
        historyBox.setPadding(new Insets(20));
        historyBox.setAlignment(Pos.CENTER);

        Button todayBtn = new Button("Today");
        Button weekBtn = new Button("This Week");
        Button monthBtn = new Button("This Month");


        todayBtn.setMinWidth(200);
        weekBtn.setMinWidth(200);
        monthBtn.setMinWidth(200);

        historyBox.getChildren().addAll(todayBtn, weekBtn, monthBtn);
        root.setCenter(historyBox);


        homeBtn.setDisable(false);
        historyBtn.setDisable(true);
        settingsBtn.setDisable(false);

        todayBtn.setOnAction(e -> {
            try {
                open_today_calc();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        weekBtn.setOnAction(e -> updateView("No Data Found", weekBtn));
        monthBtn.setOnAction(e -> updateView("No Data Found", monthBtn));
    }

    private void openSettings() {
        VBox settingsBox = new VBox(20);
        settingsBox.setPadding(new Insets(20));
        settingsBox.setAlignment(Pos.CENTER);

        Button personalDataBtn = new Button("Personal Data");
        Button connectDeviceBtn = new Button("Connect Device");
        Button NotificationsBtn = new Button("Manage Notifications");
        Button logoutBtn = new Button("Logout");


        personalDataBtn.setMinWidth(200);
        connectDeviceBtn.setMinWidth(200);
        NotificationsBtn.setMinWidth(200);
        logoutBtn.setMinWidth(200);


        settingsBox.getChildren().addAll(
                personalDataBtn,
                connectDeviceBtn,
                NotificationsBtn,
                logoutBtn

        );

        root.setCenter(settingsBox);

        homeBtn.setDisable(false);
        historyBtn.setDisable(false);
        settingsBtn.setDisable(true);




        personalDataBtn.setOnAction(ev ->
                openpersonaldata()
        );


        connectDeviceBtn.setOnAction(ev -> {
            TextInputDialog codeDialog = new TextInputDialog();
            codeDialog.setTitle("Connect Device");
            codeDialog.setHeaderText("Type in the connection code:");
            codeDialog.setContentText("Code:");
            codeDialog.showAndWait();
        });
        NotificationsBtn.setOnAction(ev -> {
            openNotifications();
        });

        logoutBtn.setOnAction(ev -> {
            Alert logoutAlert = new Alert(Alert.AlertType.CONFIRMATION);
            logoutAlert.setTitle("Logout");
            logoutAlert.setHeaderText("Are you sure you want to log out?");
            logoutAlert.setContentText("Click Yes or No");

            ButtonType yesBtn = new ButtonType("Yes");
            ButtonType noBtn = new ButtonType("No");

            logoutAlert.getButtonTypes().setAll(yesBtn, noBtn);
            logoutAlert.showAndWait();
            if (logoutAlert.getResult() == yesBtn) {
                Alert loggingoutAlert = new Alert(Alert.AlertType.CONFIRMATION);
                loggingoutAlert.setTitle("Logout");
                loggingoutAlert.setHeaderText("logging out");
                loggingoutAlert.showAndWait();
            }


        });
    }
    private void openpersonaldata() {
        VBox personalDataBox = new VBox(20);
        personalDataBox.setPadding(new Insets(20));
        personalDataBox.setAlignment(Pos.CENTER);

        Button ChangeusernameBtn = new Button("Change Username");
        Button ChangeEmailBtn = new Button("Change Email");
        Button ChangePasswordBtn = new Button("Change Password");
        Button DeleteAccountBtn = new Button("Delete Account");

        ChangeusernameBtn.setMinWidth(200);
        ChangeEmailBtn.setMinWidth(200);
        ChangePasswordBtn.setMinWidth(200);
        DeleteAccountBtn.setMinWidth(200);

        personalDataBox.getChildren().addAll(ChangeusernameBtn,ChangeEmailBtn,ChangePasswordBtn,DeleteAccountBtn);
        root.setCenter(personalDataBox);

        ChangeusernameBtn.setOnAction(ev -> {
            TextInputDialog codeDialog = new TextInputDialog();
            codeDialog.setTitle("Change Username");
            codeDialog.setHeaderText("Change Username:");
            codeDialog.setContentText("Username:");
            codeDialog.showAndWait();
        });

        ChangeEmailBtn.setOnAction(ev -> {
            TextInputDialog codeDialog = new TextInputDialog();
            codeDialog.setTitle("Change Email");
            codeDialog.setHeaderText("Change Email:");
            codeDialog.setContentText("Email:");
            codeDialog.showAndWait();
        });

        ChangePasswordBtn.setOnAction(ev -> {
            TextInputDialog codeDialog = new TextInputDialog();
            codeDialog.setTitle("Change Password");
            codeDialog.setHeaderText("Change Password:");
            codeDialog.setContentText("Password:");
            codeDialog.showAndWait();
        });

        DeleteAccountBtn.setOnAction(ev -> {
            Alert deleteAccountAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAccountAlert.setTitle("Delete Account");
            deleteAccountAlert.setHeaderText("Delete Account");
            deleteAccountAlert.setHeaderText("Do you wish to delete this account?");

            ButtonType yesBtn = new ButtonType("Yes");
            ButtonType noBtn = new ButtonType("No");
            deleteAccountAlert.showAndWait();
        });

    }


    private void openNotifications() {
        VBox NotificationBox = new VBox(20);
        NotificationBox.setPadding(new Insets(20));
        NotificationBox.setAlignment(Pos.CENTER);

        Button PushNotificationsBtn = new Button("Enable/Disable push notifications");
        Button EarlywarnBTn = new Button("Enable/Disable Early Warning");

        PushNotificationsBtn.setMinWidth(200);
        EarlywarnBTn.setMinWidth(200);

        NotificationBox.getChildren().addAll(PushNotificationsBtn, EarlywarnBTn);
        root.setCenter(NotificationBox);


        homeBtn.setDisable(false);
        historyBtn.setDisable(false);
        settingsBtn.setDisable(false);

        PushNotificationsBtn.setOnAction(ev -> {
            Alert PushNotificationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            PushNotificationAlert.setTitle("Push notifications");
            PushNotificationAlert.setHeaderText("Do you want to enable or disable push notifications?");
            PushNotificationAlert.setContentText("Click Enable or Disable");

            ButtonType yesBtn = new ButtonType("Enable");
            ButtonType noBtn = new ButtonType("Disable");

            PushNotificationAlert.getButtonTypes().setAll(yesBtn, noBtn);
            PushNotificationAlert.showAndWait();
        });

        EarlywarnBTn.setOnAction(ev -> {
            Alert EarlywarnAlert = new Alert(Alert.AlertType.CONFIRMATION);
            EarlywarnAlert.setTitle("Early warning");
            EarlywarnAlert.setHeaderText("Do you want to enable or disable early warning notifications?");
            EarlywarnAlert.setContentText("Click Enable or Disable");

            ButtonType yesBtn = new ButtonType("Enable");
            ButtonType noBtn = new ButtonType("Disable");

            EarlywarnAlert.getButtonTypes().setAll(yesBtn, noBtn);
            EarlywarnAlert.showAndWait();
        });
    }

    private void open_today_calc() throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("bestelling.fxml"));

            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Bestelling System");
            stage.setScene(scene);
            stage.show();

            homeBtn.setDisable(false);
            historyBtn.setDisable(false);
            settingsBtn.setDisable(false);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
