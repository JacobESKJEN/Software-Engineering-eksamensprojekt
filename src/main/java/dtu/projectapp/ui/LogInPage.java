package dtu.projectapp.ui;

import java.beans.PropertyChangeListener;

import dtu.projectapp.model.Project;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class LogInPage implements Page {
    private Scene scene;
    private VBox root;
    private TextField idField;
    private Button loginButton;
    private PasswordField passwordField;

    private LogInPageObserver observer = new LogInPageObserver(this);

    public LogInPage() {
        root = new VBox();
        root.setAlignment(Pos.CENTER);
        scene = new Scene(root);

        Label label = new Label("Log in to employee account");
        root.getChildren().add(label);

        idField = new TextField();
        idField.setMaxWidth(280);
        idField.setPromptText("Enter your employee id");
        root.getChildren().add(idField);

        passwordField = new PasswordField();
        passwordField.setMaxWidth(280);
        passwordField.setPromptText("Enter your password");
        root.getChildren().add(passwordField);

        loginButton = new Button("Log in");
        loginButton.setDefaultButton(true);
        root.getChildren().add(loginButton);
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public TextField getIdInputField() {
        return idField;
    }

    public PasswordField getPasswordInputField() {
        return passwordField;
    }

    public PropertyChangeListener getObserver() {
        return observer;
    }

    public Scene getScene() {
        return scene;
    }
}
