package sample.UIControllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import sample.Lib.WarChessGane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainUIController implements Initializable {
  @FXML GridPane grid;
  @FXML Label nextTurn;
  WarChessGane state;
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    state = new WarChessGane(8, grid);
    grid.setOnMouseClicked(t ->{
      ObservableList<Node> children= grid.getChildren();
      for(Node node: children){
        if (node.mousePres) {

        }
      }
      System.out.println("clicked!");
    });
  }
}
