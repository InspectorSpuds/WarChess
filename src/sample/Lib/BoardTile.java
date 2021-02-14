package sample.Lib;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/*
  Class name: BoardTile
  Purpose: serves as being a rectangle representation of a chess tile
  and can handle being given a chess piece and removing one




 */
public class BoardTile extends Rectangle {
  private Component piece = null;
  private int row;
  private int col;
  BoardTile[][] pieces = null;

  public BoardTile(int length, int width, int row, int col){
    super(length, width);
    this.setFill(Color.WHITE);
    this.setStroke(Color.BLACK);
    this.row = row;
    this.col = col;
    this.resetColor();
  }

  //graphical methods:
    public void setToClicked() {
      this.setFill(Color.RED);
    }

    public void resetColor() {
      //if the tile color is black
      if((this.row+this.col) % 2 == 1) {
        this.setFill(Color.GRAY);
      //if the tie color is white
      } else {
        this.setFill(Color.WHITE);
      }
    }

  public void assignLocation(int row, int col) {
    this.row = row;
    this.col = col;
    //set the tile color appropriately
    resetColor();
  }

  public int getRow() {
    return this.row;
  }

  public int getCol() {
    return this.col;
  }

  public Component.Color getColor() {
    return this.piece.color;
  }

  public boolean canMoveTo(int col, int row)
  {
    return this.piece.canMoveTo(col, row);
  }

  public boolean hasPiece() {
    return this.piece != null;
  }

  public Component removePiece() {
    Component temp = this.piece;
    this.piece.tileHolder = null;
    this.piece = null;
    return temp;
  }

  public void movePiece(Component oldPiece) {
    this.piece = oldPiece;
    this.piece.tileHolder = this;
  }

  public boolean pieceIsKing() {
    return  false;
  }

  public Component getComponent() {
    return this.piece;
  }

  public boolean isRiverTile() {
    return false;
  }

  public void render() {}

  public void setPieces(BoardTile[][] pieces) {
    this.pieces = pieces;
  }
}

class RiverTile extends BoardTile {

  public RiverTile(int length, int width, int row, int col) {
    super(length, width, row, col);
  }

  @Override
  public boolean isRiverTile() {
    return true;
  }

  @Override
  public void resetColor() {
      this.setFill(Color.LIGHTBLUE);
  }
}
