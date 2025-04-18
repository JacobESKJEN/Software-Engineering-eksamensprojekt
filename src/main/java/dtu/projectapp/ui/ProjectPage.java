package dtu.projectapp.ui;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.stream.Collectors;

import dtu.projectapp.model.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class ProjectPage implements Page {
    private Scene scene;
    private BorderPane root;
    PropertyChangeSupport support = new PropertyChangeSupport(this);

    ProjectPageObserver observer = new ProjectPageObserver(this);

    public ProjectPage(Project project) {
        root = new BorderPane();
        scene = new Scene(root);

        Label projectNameLabel = new Label(project.getName());

        root.setCenter(projectNameLabel);
    }

    public PropertyChangeListener getObserver() {
        return observer;
    }

    public void addObserver(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public Scene getScene() {
        return scene;
    }
}
