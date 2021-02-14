package sample.UIControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import sample.Lib.WarChessGame;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainUIController implements Initializable {
  @FXML GridPane grid;
  @FXML Label nextTurn;
  private WarChessGame state;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    try {
      state = new WarChessGame(25, grid, nextTurn);
      nextTurn.setText("Player " + state.getCurrentPlayer() + "'s turn");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
