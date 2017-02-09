/* FrontendView Class by @Aebian */
package org.aebian.umFrontend;

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

        HBox root = new HBox(); // StackPane puts Nodes on top of each other so I use HBox instead

        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(500);
        primaryStage.getIcons().add(new Image("file:res/images/uMgmt.png"));

        primaryStage.setTitle("User Management");
        Scene scene = new Scene(root, 800, 500);
        primaryStage.setScene(scene);

        primaryStage.show();
        root.setAlignment( Pos.CENTER );
        TilePane vLogin = new TilePane(Orientation.HORIZONTAL);

        vLogin.setPadding(new Insets(440, 0, 0, 250));
        vLogin.setHgap(10.0);
        root.getChildren().add(vLogin);

        final Button btnLogin = new Button("Login");
        btnLogin.setAlignment(Pos.CENTER);
        vLogin.getChildren().addAll(btnLogin);

        // #Login Form

        Label lblName = new Label("User name:");
        TextField tfName = new TextField();
        Label lblPwd = new Label("Password:");
        PasswordField pfPwd = new PasswordField();

    }
}
