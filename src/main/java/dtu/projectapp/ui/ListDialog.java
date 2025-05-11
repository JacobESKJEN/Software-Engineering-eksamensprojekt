package dtu.projectapp.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dtu.projectapp.model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.control.Dialog;

public class ListDialog extends Dialog {
    protected ListView<String> listView = new ListView<>();

    public ListDialog() {
        super();

        resetDialog();
    }

    public void resetDialog() {
        super.getDialogPane().getButtonTypes().clear();
        super.setTitle(getCustomTitle());

        VBox vbox = new VBox();
        super.getDialogPane().setContent(vbox);

        ButtonType closeButton = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
        super.getDialogPane().getButtonTypes().add(closeButton);

        vbox.getChildren().add(listView);
    }

    public String getCustomTitle() {
        return "";
    }

    public void showDialog() {
        super.showAndWait();
    }
}
