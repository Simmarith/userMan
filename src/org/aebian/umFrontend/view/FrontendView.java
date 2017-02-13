/* FrontendView Class by @Aebian */
package org.aebian.umFrontend.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;


public class FrontendView extends Application {

    protected static Button btnLogin, btnSave, btnDiscard, btnRefresh, btnEdit;
    private Stage primaryStage;
    private VBox vLogin, vUmgmt, vUedit;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {

        GridPane root = new GridPane();
        root.setStyle("-fx-background-color: #4580BA;");
        primaryStage.setMinWidth(800);

        primaryStage.setMinHeight(500);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("file:res/images/uMgmt.png"));

        primaryStage.setTitle("User Management");
        Scene scene = new Scene(root, 800, 500);
        primaryStage.setScene(scene);

        //primaryStage.show();
        this.primaryStage = primaryStage;
        root.setAlignment( Pos.CENTER );
        initRootLayout();

        showLogin();

    }

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FrontendView.class.getResource("UI/umFrontendRoot.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showLogin() {
        try {
            // Load the login page.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FrontendView.class.getResource("UI/umFrontendLogin.fxml"));
            VBox showLogin = (VBox) loader.load();

            // Set login to center of root layout.
            rootLayout.setCenter(showLogin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }






}
