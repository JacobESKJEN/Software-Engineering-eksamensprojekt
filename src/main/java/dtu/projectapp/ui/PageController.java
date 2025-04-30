package dtu.projectapp.ui;

import java.beans.PropertyChangeListener;

import javafx.scene.Scene;

public interface PageController {
    public Scene getScene();

    public PropertyChangeListener getObserver();
}
