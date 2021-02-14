package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

abstract class Piece {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    }

    public boolean isTaken() {
        // if piece is taken by opponent
    }

    public boolean canMove(char next) {
        // if piece can move to next spot
        // check for water spots here
        // can extend within individual piece class
    }

}
