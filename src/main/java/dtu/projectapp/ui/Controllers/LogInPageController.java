package dtu.projectapp.ui.Controllers;

import java.beans.PropertyChangeListener;
import java.util.concurrent.ExecutionException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

import dtu.projectapp.model.*;
import dtu.projectapp.ui.App;
import dtu.projectapp.ui.Dialogs.ErrorDialog;
import dtu.projectapp.ui.View.LogInPage;

public class LogInPageController implements PageController { // Jacob
    private LogInPage logInPage;
    private ProjectApp projectApp;
    private App app;

    public LogInPageController(ProjectApp projectApp, App app) {
        logInPage = new LogInPage();
        this.app = app;
        this.projectApp = projectApp;

        logInPage.getLoginButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    projectApp.login(logInPage.getIdInputField().getText());
                } catch (Exception e) {
                    ErrorDialog.showExceptionDialog(e);
                }
                if (projectApp.getLoggedInEmployee() != null
                        && projectApp.getLoggedInEmployee().getId().equals(logInPage.getIdInputField().getText())) {
                    app.newPage(new HomePageController(projectApp, app));
                }
            }
        });
    }

    public Scene getScene() {
        return logInPage.getScene();
    }

    public PropertyChangeListener getObserver() {
        return logInPage.getObserver();
    }
}
