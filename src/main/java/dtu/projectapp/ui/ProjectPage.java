package dtu.projectapp.ui;

import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.stream.Collectors;

import dtu.projectapp.model.Activity;
import dtu.projectapp.model.Employee;
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

    private ListView ActivityListView;
    private Activity activity;
    private Employee employee;
    private Label projectLeaderLabel;

    private Button homePageButton;
    // private Button addEmployeeButton;
    // private Button removeEmployeeButton;
    private Button setProjectLeaderButton;
    private Button removeActivityButton;
    private Button projectStatusButton;
    private Button empStatusButton;
    private Button ETAReportButton;
    private Button addActivityButton;
    private Button addSpecialActivityButton;

    private ProjectPageObserver observer = new ProjectPageObserver(this);

    public ProjectPage(Project project) {
        root = new BorderPane();
        scene = new Scene(root);

        ActivityListView = new ListView<Activity>(); // List for activities
        root.setRight(ActivityListView);

        HBox topHbox = new HBox();
        homePageButton = new Button("Return to homepage");
        topHbox.getChildren().add(homePageButton);
        root.setLeft(topHbox);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);

        Label projectNameLabel = new Label(project.getName());
        Label projectIdLabel = new Label("Project id: " + project.getId());

        projectLeaderLabel = new Label(
                project.getProjectLeader() != null ? "Project leader: " + project.getProjectLeader().getId()
                        : "No project leader");

        vbox.getChildren().addAll(projectNameLabel, projectIdLabel, projectLeaderLabel);

        assignProjectLeaderDialog = new AssignProjectLeaderDialog();
        setProjectLeaderButton = new Button("Set project leader");
        vbox.getChildren().add(setProjectLeaderButton);

        addActivityButton = new Button("Add Activity");
        addSpecialActivityButton = new Button("Add Special Activity");
        removeActivityButton = new Button("Remove Activity");
        projectStatusButton = new Button("Get Full Report");
        ETAReportButton = new Button("Get Project ETA");
        empStatusButton = new Button("Get Employee Status");

        HBox hboxReports = new HBox();
        hboxReports.setAlignment(Pos.CENTER);
        hboxReports.getChildren().addAll(empStatusButton, ETAReportButton, projectStatusButton);

        HBox hboxActivity = new HBox();
        hboxActivity.setAlignment(Pos.BASELINE_CENTER);
        hboxActivity.getChildren().addAll(addActivityButton, removeActivityButton, addSpecialActivityButton);
        vbox.getChildren().addAll(hboxReports, hboxActivity);

        root.setCenter(vbox);
    }

    public ListView getActivityListView() {    //Noah
        return ActivityListView;
    }

    public void updateProjectLeader(String projectLeaderId) { // Jacob
        System.out.println("Updated project leader");
        projectLeaderLabel.setText("Project leader: " + projectLeaderId);
    }

    public void updateActivitys(List<Activity> activities) {    //Noah
        List<String> activityNames = activities.stream()
                .map(Activity::getName)
                .collect(Collectors.toList());
        ObservableList<String> observableActivityNames = FXCollections.observableArrayList(activityNames);
        ActivityListView.setItems(observableActivityNames);

    }

    public Button getHomePageButton() { // Jacob
        return homePageButton;
    }

    public Button getSetProjectLeaderButton() { // Jacob
        return setProjectLeaderButton;
    }

    public Button getAddActivityButton() {    //Noah
        return addActivityButton;
    }

    public Button getAddSpecialActivityButton() {    //Noah
        return addSpecialActivityButton;
    }

    public Button getProjectStatusButton() { // Oliver
        return projectStatusButton;
    }

    public Button getEmpStatusButton() { // Oliver
        return empStatusButton;
    }

    public Button getETAReportButton() { // Oliver
        return ETAReportButton;
    }

    public Button getRemoveActivityButton() {    //Noah
        return removeActivityButton;
    }

    public PropertyChangeListener getObserver() { 
        return observer;
    }

    public Scene getScene() { 
        return scene;
    }

}
