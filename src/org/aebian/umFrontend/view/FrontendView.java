/* FrontendView Class by @Aebian */
package org.aebian.umFrontend.view;

import javafx.application.Application;
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


public class FrontendView extends Application {

    protected static Button btnLogin, btnSave, btnDiscard, btnRefresh, btnEdit;
    private Stage frame;
    private VBox vLogin, vUmgmt, vUedit;

    @Override
    public void start(Stage primaryStage) {

        //HBox root = new HBox(); // StackPane puts Nodes on top of each other so I use HBox instead
        GridPane root = new GridPane();
        //root.setSpacing(8);
        root.setStyle("-fx-background-color: #4580BA;");

        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(500);
        primaryStage.setResizable(false);

        primaryStage.getIcons().add(new Image("file:res/images/uMgmt.png"));
        primaryStage.setTitle("User Management");
        Scene scene = new Scene(root, 800, 500);

        primaryStage.setScene(scene);
        primaryStage.show();
        root.setAlignment( Pos.CENTER );

    }
}
