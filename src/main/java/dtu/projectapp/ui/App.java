package dtu.projectapp.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dtu.projectapp.model.Project;
import dtu.projectapp.model.ProjectApp;

/**
 * JavaFX App
 */
public class App extends Application implements PropertyChangeListener {
    private Page page;
    private static Scene scene;
    private static ProjectApp projectApp;
    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.setTitle("Project management software");

        newPage(new LogInPage());
    }

    public void newPage(Page page) {
        projectApp.addObserver(page.getObserver());
        projectApp.addObserver(this);
        page.addObserver(projectApp);
        page.addObserver(this);
        scene = page.getScene();
        this.page = page;

        primaryStage.setScene(scene);
        primaryStage.setWidth(720);
        primaryStage.setHeight(480);
        primaryStage.show();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("Select project")) {
            ProjectPage projPage = new ProjectPage(projectApp.findProject(evt.getNewValue().toString()));
            newPage(projPage);
        } else if (evt.getPropertyName().equals("login")) {
            System.out.println(evt.getNewValue());
            newPage(evt.getNewValue().equals(true) ? new HomePage() : new LogInPage());
        } else if (evt.getPropertyName().equals("Exception")) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText((String) evt.getNewValue());
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        projectApp = new ProjectApp();

        launch();
    }

}