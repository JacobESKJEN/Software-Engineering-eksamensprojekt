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
import java.beans.PropertyChangeSupport;
import java.util.List;

public class HomePage implements Page {
    private Scene scene;
    private BorderPane root;
    private ListView projectListView;
    private CreateProjectDialog createProjectDialog;
    PropertyChangeSupport support = new PropertyChangeSupport(this);

    HomePageObserver observer = new HomePageObserver(this);

    public HomePage() {

        root = new BorderPane();
        scene = new Scene(root);

        createProjectDialog = new CreateProjectDialog();

        projectListView = new ListView<String>();

        projectListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent evt) {
                // System.out.println(projectListView.getSelectionModel().getSelectedItem());
                support.firePropertyChange("Select project", null,
                        projectListView.getSelectionModel().getSelectedItem());
            }
        });

        root.setRight(projectListView);

        Button addProjectButton = new Button("Add project");
        addProjectButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String projectName = createProjectDialog.getResult();
                if (!projectName.equals("")) {
                    support.firePropertyChange("project", null, projectName);
                }
            }
        });

        root.setCenter(addProjectButton);
    }

    public PropertyChangeListener getObserver() {
        return observer;
    }

    public void addObserver(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
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
