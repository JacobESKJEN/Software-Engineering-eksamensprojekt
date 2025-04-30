package dtu.projectapp.ui;

import java.util.Optional;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.VBox;

public class ProjectStatusDialog extends Dialog {
    public ProjectStatusDialog() {
        super();

        resetDialog("");
    }

    public void resetDialog(String report) {
        super.getDialogPane().getButtonTypes().clear();
        super.setTitle("Project Status");
        //Label headlineLabel = new Label("HEAD LINEEE");
        VBox vbox = new VBox();
        super.getDialogPane().setContent(vbox);
        //vbox.getChildren().add(headlineLabel);
        ButtonType closeButton = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
        super.getDialogPane().getButtonTypes().add(closeButton);

        Label textArea = new Label(report);
        vbox.getChildren().add(textArea);
    }

    public void showDialog() {
        super.showAndWait();
    }
}