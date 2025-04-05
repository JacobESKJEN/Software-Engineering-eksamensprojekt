module hellofx {
    requires transitive javafx.controls;
    requires javafx.fxml;

    opens dtu.projectapp.ui to javafx.fxml; // Gives access to fxml files

    exports dtu.projectapp.ui; // Exports the class inheriting from javafx.application.Application
    exports dtu.projectapp.model;
}