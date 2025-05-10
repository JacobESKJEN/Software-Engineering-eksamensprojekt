package dtu.projectapp.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonBar.ButtonData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dtu.projectapp.model.Employee;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class AvailableEmployeesDialog extends Dialog {
    public AvailableEmployeesDialog() {
        super();

        resetDialog(new ArrayList<>());
    }

    public void resetDialog(List<Employee> availableEmployees) {
        super.getDialogPane().getButtonTypes().clear();
        super.setTitle("Available employees");

        VBox vbox = new VBox();
        super.getDialogPane().setContent(vbox);

        ButtonType closeButton = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
        super.getDialogPane().getButtonTypes().add(closeButton);

        ListView<String> availableEmployeesListView = new ListView<>();
        List<String> employeeIds = availableEmployees.stream().map(Employee::getId).collect(Collectors.toList());
        ObservableList<String> observableEmployeeIds = FXCollections.observableArrayList(employeeIds);
        availableEmployeesListView.setItems(observableEmployeeIds);
        vbox.getChildren().add(availableEmployeesListView);
    }

    public void showDialog() {
        super.showAndWait();
    }
}
