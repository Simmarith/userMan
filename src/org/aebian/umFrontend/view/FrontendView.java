/* FrontendView Class by @Aebian */
package org.aebian.umFrontend.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.aebian.umFrontend.Authenticator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class FrontendView extends Application {
    public static List<String> userList = new ArrayList<String>();
    @FXML
    static protected Stage primaryStage;
    @FXML
    static protected BorderPane rootLayout;
    @FXML
    static private VBox vsAbout;
    @FXML
    static private VBox vsLogin;
    @FXML
    static private VBox vsAOverview;
    @FXML
    static private VBox vsUOverview;
    private static double xOffset = 0;
    private static double yOffset = 0;
    @FXML
    Button btnLogin, btnSave, btnDiscard, btnRefresh, btnEdit;
    @FXML
    PasswordField pwField;
    @FXML
    TextField userField;
    @FXML
    MenuItem appClose;
    @FXML
    MenuItem appAbout;
    int admin = 0;

    public static void showRoot() { // Load root layout
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FrontendView.class.getResource("UI/umFrontendRoot.fxml"));
            rootLayout = loader.load();
            rootLayout.setId("umFrontend");
            loader.setController(rootLayout);
            loader.setRoot(rootLayout);

            //Let the root Layout be able to moved around.
            rootLayout.getTop().setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });
            rootLayout.getTop().setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    primaryStage.setX(event.getScreenX() - xOffset);
                    primaryStage.setY(event.getScreenY() - yOffset);
                }
            });

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            String css = FrontendView.class.getResource("UI/umFrontendStyles.css").toExternalForm();
            scene.getStylesheets().clear();
            scene.getStylesheets().add(css);

            primaryStage.show();


        } catch (IOException e) {
        }
    }

    public static void showLogin() { // Load the login page.
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FrontendView.class.getResource("UI/umFrontendLogin.fxml"));
            vsLogin = loader.load();
            // Set login to center of root layout.
            FrontendView.rootLayout.setCenter(vsLogin);
        } catch (IOException e) {
        }
    }

    public static void showAbout() {  // Load the about page.
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FrontendView.class.getResource("UI/umFrontendAbout.fxml"));
            vsAbout = loader.load();
            // Set about page to center of root layout.
            FrontendView.rootLayout.setCenter(vsAbout);
        } catch (IOException e) {
        }
    }

    public static void showAdminOverview() {  // Load the admin overview / edit page.
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FrontendView.class.getResource("UI/umFrontendAdminOverview.fxml"));
            vsAOverview = loader.load();
            // Set admin overview page to center of root layout.
            FrontendView.rootLayout.setCenter(vsAOverview);
        } catch (IOException e) {
        }
    }

    public static void showUserOverview() {  // Load the user overview / edit page.
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FrontendView.class.getResource("UI/umFrontendUserOverview.fxml"));
            vsUOverview = loader.load();
            // Set admin overview page to center of root layout.
            FrontendView.rootLayout.setCenter(vsUOverview);
        } catch (IOException e) {
        }
    }


     /* Button Events  */

    public void start(Stage primaryStage) {

        GridPane root = new GridPane();
        primaryStage.setMinWidth(800);
        primaryStage.initStyle(StageStyle.UNDECORATED);

        primaryStage.setMinHeight(600);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("file:res/images/uMgmt.png"));

        primaryStage.setTitle("User Management");
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setScene(scene);
        this.primaryStage = primaryStage;
        root.setAlignment(Pos.CENTER);

        showRoot();
        showLogin();

    }


     /* Frontend Dialogs  */

    @FXML
    private void handleButtonAction(ActionEvent e) throws IOException {

        if (e.getSource() == appClose) { // Exit application when user clicks on Edit > Close
            System.exit(0);
        } else if (e.getSource() == appAbout) { // Show About page when user clicks on Help > About
            showAbout();
        } else if (e.getSource() == btnRefresh) {
            showUserOverview();
        } else if (e.getSource() == btnLogin) { // Function that gets triggered when the user presses the Login Button
            if (Authenticator.thisUser != null) {
                showUserOverview();

            } else {
                loginInvalidDialog();
            }
        }

    }

    public void loginInvalidDialog() {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Login Invalid");
        alert.setHeaderText("Your login is invalid.");
        alert.setContentText("Please check your details. Could not login!");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }


    /* AdminOverview List Function */

    public void delUserConfirmDialog() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete User Dialog");
        alert.setHeaderText("Delete User $ss");
        alert.setContentText("Are you sure you want to delete the selected action?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            showAdminOverview();
        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }

    /**
     * Getter and setter.
     *
     * @return
     */


    public BorderPane getBorderPane() {
        return rootLayout;

    }

    public BorderPane setBorderPane() {
        this.rootLayout = rootLayout;
        return rootLayout;
    }

    public Stage getPrimaryStage() {
        return primaryStage;

    }
}
