package dtu.projectapp.ui.Dialogs;

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

public class AvailableEmployeesDialog extends ListDialog { // Jacob
    public AvailableEmployeesDialog() {
        super();
    }

    @Override
    public String getCustomTitle() {
        return "Available employees";
    }

    public void updateList(List<Employee> availableEmployees) {
        resetDialog();
        List<String> employeeIds = availableEmployees.stream().map(Employee::getId).collect(Collectors.toList());
        ObservableList<String> observableEmployeeIds = FXCollections.observableArrayList(employeeIds);
        super.listView.setItems(observableEmployeeIds);
    }
}
