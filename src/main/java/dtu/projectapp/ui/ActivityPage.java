package dtu.projectapp.ui;

import java.beans.PropertyChangeListener;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class ActivityPage implements Page {
    private Scene scene;
    private BorderPane root;
    private ListView projectListView;
    private Button addProjectButton;
    
    private ActivityPageObserver observer = new ActivityPageObserver(this);

    public ActivityPage() {
        root = new BorderPane();
        scene = new Scene(root);

        addProjectButton = new Button("Add project");
        root.setCenter(addProjectButton);
    }

    

    @Override
    public Scene getScene() {
        return scene;
    }



    @Override
    public PropertyChangeListener getObserver() {
        return observer;
    }
}
