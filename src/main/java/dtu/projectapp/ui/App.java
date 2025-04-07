package dtu.projectapp.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import dtu.projectapp.model.ProjectApp;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static ProjectApp projectApp;

    @Override
    public void start(Stage stage) throws IOException {
        // scene = new Scene(loadFXML("primary"), 640, 480);
        Page page = new HomePage();
        scene = page.getScene();

        projectApp = new ProjectApp();
        projectApp.addObserver(page.getObserver());
        page.addObserver(projectApp);

        stage.setTitle("Project management software");
        stage.setScene(scene);
        stage.show();

    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}