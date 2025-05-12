package dtu.projectapp.ui.Dialogs;

import java.util.List;
import java.util.stream.Collectors;

import dtu.projectapp.model.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ViewAssignedEmployeesDialog extends ListDialog { // Jacob
    public ViewAssignedEmployeesDialog() {
        super();
    }

    @Override
    public String getCustomTitle() {
        return "Assigned employees";
    }

    public void updateList(List<Employee> employees) {
        resetDialog();
        List<String> employeeIds = employees.stream().map(Employee::getId).collect(Collectors.toList());
        ObservableList<String> observableEmployeeIds = FXCollections.observableArrayList(employeeIds);
        super.listView.setItems(observableEmployeeIds);
    }
}
