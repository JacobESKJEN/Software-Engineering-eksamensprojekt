package dtu.projectapp.ui;

import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.stream.Collectors;

import dtu.projectapp.model.Employee;
import dtu.projectapp.model.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ProjectPage implements Page {
    private Scene scene;
    private BorderPane root;
    private AssignProjectLeaderDialog assignProjectLeaderDialog;
    private Label projectLeaderLabel;

    private Button setProjectLeaderButton;
    private Button projectStatusButton;
    private Button empStatusButton;
    private Button ETAReportButton;

    ProjectPageObserver observer = new ProjectPageObserver(this);

    public ProjectPage(Project project) {
        root = new BorderPane();
        scene = new Scene(root);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);

        Label projectNameLabel = new Label(project.getName());
        vbox.getChildren().add(projectNameLabel);

        projectLeaderLabel = new Label(
                project.getProjectLeader() != null ? "Project leader: " + project.getProjectLeader().getId()
                        : "No project leader");
        vbox.getChildren().add(projectLeaderLabel);

        assignProjectLeaderDialog = new AssignProjectLeaderDialog();
        setProjectLeaderButton = new Button("Set project leader");
        vbox.getChildren().add(setProjectLeaderButton);

        projectStatusButton = new Button("Get Full Report");
        ETAReportButton = new Button("Get Project ETA");
        empStatusButton = new Button("Get Employee Status");

        HBox hboxReports = new HBox();
        hboxReports.setAlignment(Pos.CENTER);
        hboxReports.getChildren().addAll(empStatusButton, ETAReportButton, projectStatusButton);

        vbox.getChildren().add(hboxReports);

        root.setCenter(vbox);
    }

    public void updateProjectLeader(String projectLeaderId) {
        System.out.println("Updated project leader");
        projectLeaderLabel.setText("Project leader: " + projectLeaderId);
    }

    public Button getSetProjectLeaderButton() {
        return setProjectLeaderButton;
    }

    public Button getProjectStatusButton() {
        return projectStatusButton;
    }

    public Button getEmpStatusButton() {
        return empStatusButton;
    }

    public Button getETAReportButton() {
        return ETAReportButton;
    }

    public PropertyChangeListener getObserver() {
        return observer;
    }

    public Scene getScene() {
        return scene;
    }

}
