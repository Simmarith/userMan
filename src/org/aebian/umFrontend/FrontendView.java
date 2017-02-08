package org.aebian.umFrontend;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FrontendView extends Application {

    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane();

        Scene scene = new Scene(root, 600, 500);

        primaryStage.getIcons().add(new Image("/org/aebian/umFrontend/icons/uMgmt.png"));
        primaryStage.setTitle("User Management");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
