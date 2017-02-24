/* FrontendView Class by @Aebian */
package org.aebian.umFrontend.view;

import com.simmarith.sqlCon.Permission;
import com.simmarith.sqlCon.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import java.util.Iterator;
import java.util.Optional;


public class FrontendView extends Application {
    public static User currUser;
    @FXML
    static protected Stage primaryStage;
    @FXML
    static protected BorderPane rootLayout;
    protected static String thisUsers;
    protected static Permission currPerm;
    @FXML
    static private VBox vsAbout, vsLogin, vsAOverview, vsUOverview;
    @FXML
    private static ScrollPane cPanel;
    static private double xOffset = 0;
    static private double yOffset = 0;
    @FXML
    private Button btnLogin, btnLogout, btnAdmin, btnUser, btnSave, btnDiscard, btnUpdate, btnRefresh, btnEdit, btnchngPW, btnassPerm;
    @FXML
    private MenuItem appClose, appAbout, appMgmt;
    @FXML
    private TextField userField, firstnField, lastnField, emailField, streetField, houseField, pcodeField, cityField;
    @FXML
    private PasswordField pwField;
    @FXML
    private ListView permListView;

    public static String returnUsers() {
        ArrayList<User> users = User.getAllUsers();
        Iterator<User> usersIterator = users.iterator();
        while (usersIterator.hasNext()) {
            User thisUser = usersIterator.next();
            // cPanel.getChildrenUnmodifiable().add(thisUser);
            return thisUsers;

        }
        return null;

    }

    public static Permission returnPermissions() {
        ArrayList<Permission> perms = Permission.getAllPermissions();
        Iterator<Permission> permsIterator = perms.iterator();
        while (permsIterator.hasNext()) {
            Permission thisPerms = permsIterator.next();
            // cPanel.getChildrenUnmodifiable().add(thisUser);
            return thisPerms;

        }
        return null;

    }

    public void showRoot() { // Load root layout
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FrontendView.class.getResource("UI/umFrontendRoot.fxml"));
            rootLayout = loader.load();
            rootLayout.setId("umFrontend");
            loader.setController(rootLayout);
            loader.setRoot(rootLayout);

            //Let the root Layout be able to moved around without having the windows controls visible.
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

    public void showLogin() { // Load the login page.
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FrontendView.class.getResource("UI/umFrontendLogin.fxml"));
            vsLogin = loader.load();
            // Set login to center of root layout.
            FrontendView.rootLayout.setCenter(vsLogin);
        } catch (IOException e) {
        }
    }

    public void showAbout() {  // Load the about page.
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FrontendView.class.getResource("UI/umFrontendAbout.fxml"));
            vsAbout = loader.load();
            // Set about page to center of root layout.
            FrontendView.rootLayout.setCenter(vsAbout);
        } catch (IOException e) {
        }
    }


    public void showUserOverview() {  // Load the user overview / edit page.
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FrontendView.class.getResource("UI/umFrontendUserOverview.fxml"));

            vsUOverview = loader.load();
            // Set admin overview page to center of root layout.
            FrontendView.rootLayout.setCenter(vsUOverview);
        } catch (IOException e) {
        }


        String firstName = currUser.getFname();
        String lastName = currUser.getLname();
        String userMail = currUser.getMail();

        firstnField.setText(firstName);
        lastnField.setText(lastName);
        emailField.setText(userMail);
    }

    public void showAdminOverview() {  // Load the admin overview / edit page.
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FrontendView.class.getResource("UI/umFrontendAdminOverview.fxml"));
            vsAOverview = loader.load();
            // Set admin overview page to center of root layout.
            FrontendView.rootLayout.setCenter(vsAOverview);
            //cPanel.setContent(returnUsers()) //:ToDo show only users where password != null
        } catch (IOException e) {
        }
    }

    public void start(Stage primaryStage) { // Very default stage that has some failsafe values and loads the root layout and login uppon first run.

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


    @FXML
    private void handleButtonAction(ActionEvent e) throws IOException { // Button Actions

        if (e.getSource() == appClose) { // Exit application when user clicks on Edit > Close
            currUser = null;
            System.exit(0);

        } else if (e.getSource() == appAbout) { // Show About page when user clicks on Help > About
            showAbout();

        } else if (e.getSource() == appMgmt) { // Show User overview when user is logged in and accesses File > Management else show login
            if (currUser != null) {
                showUserOverview();
            } else {
                showLogin();
            }
        } else if (e.getSource() == btnLogin) { // Function that gets triggered when the user presses the Login Button
            currUser = Authenticator.validateLogin(userField.getText(), pwField.getText());
            currPerm = new Permission("1");

            if (currUser != null) {
                showUserOverview();

            } else {
                loginInvalidDialog();
            }

        } else if (e.getSource() == btnLogout) {
            if (currUser != null) {
                currUser = null;
                showLogin();

            }
        } else if (e.getSource() == btnAdmin) {
            btnAdmin.setDisable(true);
            if (currUser != null) {
                showAdminOverview();
            }
        } else if (e.getSource() == btnchngPW) {
            if (currUser != null) {
                chngpwDialog();
            }
        } else if (e.getSource() == btnassPerm) {
            if (currUser != null) {
                permissionDialog();
            }
        } else if (e.getSource() == btnUpdate) {

            String firstname = firstnField.getText();
            String lastname = lastnField.getText();
            String mailfield = emailField.getText();
            String stfield = streetField.getText();
            String numberfield = houseField.getText();
            String postalfield = pcodeField.getText();
            String resfield = cityField.getText();


            if (firstname != null) { // update the First Name of the User
                currUser.setFname(firstname);
            }
            if (lastname != null) { // update the Last Name of the User
                currUser.setLname(lastname);
            }
            if (emailField != null) { // update the Mail Address of the User
                currUser.setMail(mailfield);
            }
            currUser.persist();
            updateSuccessDialog();
        }

    }


    public void updateSuccessDialog() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update Successful");
        alert.setHeaderText("User update completed successfully.");
        alert.setContentText("You can close this window, nothing needs to be done.");
        alert.initStyle(StageStyle.UTILITY);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            showUserOverview();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    public void updateFailedDialog() {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Update un-successful");
        alert.setHeaderText("User update was not completed.");
        alert.setContentText("Maybe some values were wrong. Check your entries!.");
        alert.initStyle(StageStyle.UTILITY);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            showUserOverview();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    public void chngpwDialog() {

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Change Password");
        dialog.setHeaderText("Change your password here.");
        dialog.initStyle(StageStyle.UTILITY);

// Set the button types.
        ButtonType pwSetBtn = new ButtonType("Change it", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(pwSetBtn, ButtonType.CANCEL);

// Create the password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField password = new PasswordField();
        password.setPromptText("Password");
        PasswordField password2 = new PasswordField();
        password2.setPromptText("Re-enter Password");

        grid.add(new Label("Password:"), 0, 0);
        grid.add(password, 1, 0);
        grid.add(new Label("Re-enter Password:"), 0, 1);
        grid.add(password2, 1, 1);

// Enable/Disable login button depending on whether a password was entered.
        Node pwChaneBtn = dialog.getDialogPane().lookupButton(pwSetBtn);
        pwChaneBtn.setDisable(true);

// Do some validation.
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            pwChaneBtn.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

// Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == pwSetBtn) {
                if (password.getText().equals(password2.getText())) {
                    return new String(password.getText());
                } else {
                    updateFailedDialog();
                }
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(Password -> {
            String awesomePW = result.get();
            currUser.setPassword(awesomePW);
            currUser.persist();
            updateSuccessDialog();
        });

    }

    public void permissionDialog() {
        ArrayList<Permission> permissions = Permission.getAllPermissions();
        Iterator<Permission> permissionIterator = permissions.iterator();
        while (permissionIterator.hasNext()) {
            Permission thisPermission = permissionIterator.next();
            permissions.add(thisPermission);
        }


        ChoiceDialog<String> dialog = new ChoiceDialog<>();
        dialog.setTitle("Permission Dialog");
        dialog.setHeaderText("The Permission Dialog");
        dialog.setContentText("Selelct the Permission you want to give the selected User");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            // ToDo: Do the magic
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

    public void deactivateUserConfirmDialog() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete User Dialog");
        alert.setHeaderText("Delete User $ss");
        alert.setContentText("Are you sure you want to deactiate the selected user?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            showAdminOverview();
        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }
}

