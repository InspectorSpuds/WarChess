package war.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import war.Main;

import java.io.IOException;

public class Entry {
    @FXML
    private Button btnComputer;

    @FXML
    private Button btnOnlilne;

    @FXML
    private Button btnMultiplayer;

    @FXML
    private void playComp() throws IOException {
        Main.changeScene("UI/GameScene.fxml");
    }

    @FXML
    private void playOnline() {}

    @FXML
    private void playMultiplayer() {}
}
