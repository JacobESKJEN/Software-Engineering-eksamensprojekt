package dtu.projectapp.ui;

import java.util.Optional;

import javafx.scene.control.TextInputDialog;

public abstract class InputDialog {
    protected TextInputDialog dialog;

    public void resetDialog() {

    }

    public String getResult() {
        resetDialog();
        Optional<String> result = dialog.showAndWait();

        String entered = "";

        if (result.isPresent()) {
            entered = result.get();
        }

        return entered;
    }
}
