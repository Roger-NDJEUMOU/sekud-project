package main;

import java.io.File;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.Login;

/**
 *
 * @author Roger NDJEUMOU
 */
public class Main extends Application {

    public static final String APP_NAME = "Sek\u00F9d";

    private static Stage stage;
    public Image appLogo = new Image(Main.class
            .getResourceAsStream("../images/sekud-logo.jpg"));

    HostServices hostServices = getHostServices();

    public static Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;
        stage.getIcons().add(appLogo);
        setStage(APP_NAME + " - Login", "../views/LoginView.fxml");
    }

    public static void loadLoginView(ActionEvent event) throws Exception {
        closeView(event);
        setStage(APP_NAME + " - Login", "../views/LoginView.fxml");
    }

    public static void loadHomeView(ActionEvent event, Login loginInfo)
            throws Exception {
        // Close the log in windows
        closeView(event);

        stage.setUserData(loginInfo); // Set data for home view
        // Launch home view
        setStage(APP_NAME + " - Home", "../views/homeView.fxml");
    }

    private static void closeView(ActionEvent event) {
        Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    // Method to open new scene whose title is "title" and 
    // whose location is "path"
    private static void setStage(String title, String path)
            throws Exception {
        Scene scene = new Scene(loadFXML(path));
        stage.setTitle(title);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    private static Parent loadFXML(String path)
            throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(path));
        return fxmlLoader.load();
    }

    public void viewPDF(File file) {
        hostServices.showDocument(file.getAbsolutePath());
    }

    public void openBrowser(String url) {
        hostServices.showDocument(url);
    }

    public void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.getIcons().add(appLogo);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public Main() {

    }
}
