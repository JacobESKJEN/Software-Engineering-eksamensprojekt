package dtu.projectapp.ui;

import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.stream.Collectors;

import dtu.projectapp.model.Activity;
import dtu.projectapp.model.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private CreateActivityDialog addEmployeeToActivityDialog;
    private ListView ActivityListView;
    private Activity activity;
    private Label projectLeaderLabel;

    private Button setProjectLeaderButton;
    private Button projectStatusButton;
    private Button empStatusButton;
    private Button ETAReportButton;
    private Button addActivityButton;
    ProjectPageObserver observer = new ProjectPageObserver(this);

    public ProjectPage(Project project) {
        root = new BorderPane();
        scene = new Scene(root);
        
        ActivityListView = new ListView<Activity>(); //List for activities
        root.setRight(ActivityListView);
        
        project.createActivity("ActivityName", "2005-09-12", "2005-10-12", 1);

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

        addActivityButton = new Button("Add Activity");
        projectStatusButton = new Button("Get Full Report");
        ETAReportButton = new Button("Get Project ETA");
        empStatusButton = new Button("Get Employee Status");

        HBox hboxReports = new HBox();
        hboxReports.setAlignment(Pos.CENTER);
        hboxReports.getChildren().addAll(empStatusButton, ETAReportButton, projectStatusButton,addActivityButton);

        vbox.getChildren().add(hboxReports);

        root.setCenter(vbox);
    }

    public ListView getActivityListView() {
        return ActivityListView;
    }

    public void updateProjectLeader(String projectLeaderId) {
        System.out.println("Updated project leader");
        projectLeaderLabel.setText("Project leader: " + projectLeaderId);
    }

    // public void updateActivitys(List<Activity> list) {
    //     ObservableList<String> Activitys = FXCollections
    //             .observableArrayList(list.stream().map(Activity::getName).collect(Collectors.toList()));

    //     ActivityListView.setItems(Activitys);
    // }
    public void updateActivitys(List<Activity> activities) {
        List<String> activityNames = activities.stream()
                .map(Activity::getName)
                .collect(Collectors.toList());
        ObservableList<String> observableActivityNames = FXCollections.observableArrayList(activityNames);
        ActivityListView.setItems(observableActivityNames);
        
    }

    public Button getSetProjectLeaderButton() {
        return setProjectLeaderButton;
    }

    public Button getAddActivityButton() {
        return addActivityButton;
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
