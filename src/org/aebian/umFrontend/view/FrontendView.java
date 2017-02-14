/* FrontendView Class by @Aebian */
package org.aebian.umFrontend.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;

import java.io.IOException;


public class FrontendView extends Application {

    protected static Button btnLogin, btnSave, btnDiscard, btnRefresh, btnEdit;
    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {

        GridPane root = new GridPane();
        primaryStage.setMinWidth(800);
        primaryStage.initStyle(StageStyle.TRANSPARENT);


        primaryStage.setMinHeight(500);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("file:res/images/uMgmt.png"));

        primaryStage.setTitle("User Management");
        Scene scene = new Scene(root, 800, 500);

        primaryStage.setScene(scene);
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
            rootLayout.setId("umFrontend");

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            String css = FrontendView.class.getResource("UI/umFrontendStyles.css").toExternalForm();
            scene.getStylesheets().clear();
            scene.getStylesheets().add(css);
            primaryStage.show();

            MenuItem exit = new MenuItem("Exit");
            exit.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent t) {
                    System.exit(0);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
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
