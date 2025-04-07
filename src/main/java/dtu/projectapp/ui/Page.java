package dtu.projectapp.ui;

import java.beans.PropertyChangeListener;

import javafx.scene.Scene;

public interface Page {
    public Scene getScene();

    public PropertyChangeListener getObserver();

    public void addObserver(PropertyChangeListener observer);
}
