package sample.Lib;
import javafx.scene.image.ImageView;

public abstract class ChessPieces {
  private abstract String imagePath; //the path to the piece image ie. "sample.lin.images.(place image here)"
  private final int NEWRULE_BOARD_LENGTH = 25; //size of the new board's length
  private final int REGULAR_BOARD_LENGTH = 8; //size of standard board length
  private int row; //the row of the component's tile (the y coordinate)
  private int col; //the column of the components's tile (the c coordinate)
  Color color; //color of the piece
  
  public abstract boolean canMoveTo(int col, int row); //can the piece move to that col and row?
  public abstract void render(); //puts the image render of the piece on the board
  
  //moves the piece 
  public void moveTo(int col, int row){
    this.col = col;
    this.row = row;
  }

  
  enum Color {
    BLACK, WHITE;
  }
  
  
}
