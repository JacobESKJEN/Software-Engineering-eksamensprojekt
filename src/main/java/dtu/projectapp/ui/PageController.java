package dtu.projectapp.ui;

import java.beans.PropertyChangeListener;

import javafx.scene.Scene;

public interface PageController { // Jacob
    public Scene getScene();

    public PropertyChangeListener getObserver();
}
