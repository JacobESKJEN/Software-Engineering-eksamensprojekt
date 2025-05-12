package dtu.projectapp.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ErrorDialog {
    public static void showExceptionDialog(Exception e) { // Jacob
        Alert alert = new Alert(AlertType.ERROR);
        alert.setContentText(e.getMessage());
        alert.show();
    }
}
