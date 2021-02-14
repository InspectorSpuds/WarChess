package sample.Lib;


import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.awt.*;

public abstract class Component {
  private String imagePath; //the path to the piece image ie. "sample.lin.images.(place image here)"
  private final int NEWRULE_BOARD_LENGTH = 25; //size of the new board's length
  private final int REGULAR_BOARD_LENGTH = 8; //size of standard board length
  private int row; //the row of the component's tile (the y coordinate)
  private int col; //the column of the components's tile (the c coordinate)
  public Color color; //color of the piece

  public abstract boolean canMoveTo(int col, int row); //can the piece move to that col and row?

  public abstract ImagePattern render(); //puts the image render of the piece on the board

  public abstract boolean isKing();

  //moves the piece
  public void moveTo(int col, int row) {
    this.col = col;
    this.row = row;
    render();
  }

  enum Color {
    BLACK, WHITE;
  }

  /*
  accessor methods for row and col since they're private variables. If variables changed to protected, then col and row can be
  accessed within the subclasses. OR I could be wrong since this class is part of the sample lib package.
  */
  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public void setColumn(int col) {
    this.col = col;
  }
}

class Pawn extends Component {

  @Override
  public boolean canMoveTo(int col, int row) {
    if(col <= getCol() + 1 && col >= getCol() - 1 && row <= getRow() + 1 && row >= getRow() - 1) {
      return true;
    }
    return false;
  }

  @Override
  public ImagePattern render() {
    if(this.color == Color.WHITE) {
      return new ImagePattern(new Image("sample/Lib/assets/pawn_white.png"));
    } else {
      return new ImagePattern(new Image("sample/Lib/assets/pawn_black.png"));
    }
  }

  @Override
  public boolean isKing() {
    return false;
  }

  public Pawn(Color color) {
    this.color = color;
  }
}

class King extends Component {

  @Override
  public boolean canMoveTo(int col, int row) {
    if(col <= getCol() + 1 && col >= getCol() - 1 && row <= getRow() + 1 && row >= getRow() - 1) {
      return true;
    }
    return false;
  }

  @Override
  public ImagePattern render() {
    if(this.color == Color.WHITE) {
      return new ImagePattern(new Image("sample/Lib/assets/king_white.png"));
    } else {
      return new ImagePattern(new Image("sample/Lib/assets/king_black.png"));
    }
  }

  @Override
  public boolean isKing() {
    return true;
  }
}

class Knight extends Component {

  @Override
  public boolean canMoveTo(int col, int row) {
    return false;
  }

  @Override
  public ImagePattern render() {
    if(this.color == Color.WHITE) {
      return new ImagePattern(new Image("sample/Lib/assets/knight_white.png"));
    } else {
      return new ImagePattern(new Image("sample/Lib/assets/knight_black.png"));
    }
  }

  @Override
  public boolean isKing() {
    return false;
  }
}

class Bishop extends Component {
  @Override
  public boolean canMoveTo(int col, int row) {
    // checks that coordinal movement always has slope of +/- 1
    if (((this.getCol() - col) / (this.getRow() - row)) == 1 || (((this.getCol() - col) / (this.getRow() - row)) == -1)) {
      return true;
    }
    return false;
  }

  @Override
  public ImagePattern render() {
    if(this.color == Color.WHITE) {
      return new ImagePattern(new Image("sample/Lib/assets/bishop_white.png"));
    } else {
      return new ImagePattern(new Image("sample/Lib/assets/bishop_black.png"));
    }
  }

  @Override
  public boolean isKing() {
    return false;
  }
}

class Queen extends Component {
  @Override
  public boolean canMoveTo(int col, int row) {
    // checks boundaries if necessary?
    if (this.getCol() > 24 || this.getCol() < 0 || this.getRow() > 24 || this.getRow() < 0) {
      return false;
    } else if ((this.getCol() == col) && (this.getRow() == row)) {
      return false;
    } else if (((this.getCol() - col) / (this.getRow() - row)) == 1 || (((this.getCol() - col) / (this.getRow() - row)) == -1) && (this.getCol() == col) || (this.getRow() == row)) {
      return true;
    }
      return false;
  }

  @Override
  public ImagePattern render() {
    if(this.color == Color.WHITE) {
      return new ImagePattern(new Image("sample/Lib/assets/queen_white.png"));
    } else {
      return new ImagePattern(new Image("sample/Lib/assets/queen_black.png"));
    }
  }

  @Override
  public boolean isKing() {
    return false;
  }
}

class Rook extends Component {
  @Override
  public boolean canMoveTo(int col, int row) {
    // checks boundaries if necessary?
    // hasn't moved
    if ((!(this.getCol() == col) && (this.getRow() == row)) || ((this.getCol() == col) && !(this.getRow() == row))) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public ImagePattern render() {
    if(this.color == Color.WHITE) {
      return new ImagePattern(new Image("assets/rook_white.png"));
    } else {
      return new ImagePattern(new Image("assets/rook_black.png"));
    }
  }

  @Override
  public boolean isKing() {
    return false;
  }
}