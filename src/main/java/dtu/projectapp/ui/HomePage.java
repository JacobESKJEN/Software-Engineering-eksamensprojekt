package dtu.projectapp.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import dtu.projectapp.model.Project;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.stream.Collectors;
import java.beans.PropertyChangeListener;
import java.util.List;

public class HomePage implements Page {
    private Scene scene;
    private BorderPane root;
    private ListView projectListView;
    private Button addProjectButton;

    private HomePageObserver observer = new HomePageObserver(this);

    public HomePage() {
        root = new BorderPane();
        scene = new Scene(root);

        projectListView = new ListView<String>();
        root.setRight(projectListView);

        addProjectButton = new Button("Add project");
        root.setCenter(addProjectButton);
    }

    public ListView getProjectListView() {
        return projectListView;
    }

    public Button getAddProjectButton() {
        return addProjectButton;
    }

    public PropertyChangeListener getObserver() {
        return observer;
    }

    public void updateProjects(List<Project> list) {
        ObservableList<String> projects = FXCollections
                .observableArrayList(list.stream().map(Project::getName).collect(Collectors.toList()));

        projectListView.setItems(projects);
    }

    public Scene getScene() {
        return scene;
    }
}
