package dtu.projectapp.ui;

import java.beans.PropertyChangeListener;

import dtu.projectapp.model.Employee;
import dtu.projectapp.model.ProjectApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class HomePageController implements PageController {
    private HomePage homePage;
    private ProjectApp projectApp;
    private App app;
    private CreateProjectDialog createProjectDialog;
    private AddEmployeeDialog addEmployeeDialog;

    public HomePageController(ProjectApp projectApp, App app) {
        homePage = new HomePage();
        this.projectApp = projectApp;
        this.app = app;

        createProjectDialog = new CreateProjectDialog();
        addEmployeeDialog = new AddEmployeeDialog();

        homePage.updateProjects(projectApp.getProjects());

        homePage.getProjectListView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent evt) {
                Object selectedItem = homePage.getProjectListView().getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    ProjectPageController projPage = new ProjectPageController(projectApp, app,
                            projectApp.findProject(selectedItem.toString()));
                    app.newPage(projPage);
                }
            }
        });

        homePage.getAddProjectButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String projectName = createProjectDialog.getResult();
                if (!projectName.equals("")) {
                    try {
                        projectApp.createProject(projectName);
                    } catch (Exception e) {
                        ErrorDialog.showExceptionDialog(e);
                    }
                }
            }
        });

        homePage.getLogOutButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    projectApp.logout();
                    app.newPage(new LogInPageController(projectApp, app));
                } catch (Exception e) {
                    ErrorDialog.showExceptionDialog(e);
                }
            }
        });

        homePage.getAddEmployeeButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent evt) {
                String employeeId = addEmployeeDialog.getResult();
                try {
                    projectApp.addEmployee(employeeId);
                } catch (Exception e) {
                    ErrorDialog.showExceptionDialog(e);
                }
            }
        });
    }

    public Scene getScene() {
        return homePage.getScene();
    }

    public PropertyChangeListener getObserver() {
        return homePage.getObserver();
    }
}
