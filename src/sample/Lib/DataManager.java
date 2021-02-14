package sample.Lib;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import sample.UIControllers.MainUIController;

public class DataManager implements Runnable{
  private GridPane grid;
  private Label gameStateMessenger;
  private MainUIController controller;

  @Override
  public void run() {

  }


  private void interrupt() throws InterruptedException {
    this.interrupt();
  }
  public void clean() {
    grid = null;
    gameStateMessenger = null;
  }

  public void assignAndTrack(GridPane grid, Label messenger, MainUIController controller) {
    this.grid = grid;
    this.gameStateMessenger = messenger;
    this.controller = controller;
  }

  public DataManager(GridPane pane, Label label) {
    this.grid = pane;
    this.gameStateMessenger = label;
  }
}
