package dtu.projectapp.ui;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
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


        VBox vbox = new VBox();
        super.getDialogPane().setContent(vbox);


        ButtonType closeButton = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
        super.getDialogPane().getButtonTypes().add(closeButton);

        TextArea reportBox = new TextArea(report);
        reportBox.setEditable(false);
        reportBox.setWrapText(true);
        reportBox.setMaxWidth(Double.MAX_VALUE);
        vbox.getChildren().add(reportBox);
    }

    public void showDialog() {
        super.showAndWait();
    }
}