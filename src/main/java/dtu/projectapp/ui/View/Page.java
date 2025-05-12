package dtu.projectapp.ui.View;

import java.beans.PropertyChangeListener;

import javafx.scene.Scene;

public interface Page { // Jacob
    public Scene getScene();

    public PropertyChangeListener getObserver();
}
