package sample.Lib;
/**
 white king
 white queen
 white rook
 white bishop
 white knight
 white pawn
 black king
 black queen
 black rook
 black bishop
 black knight
 black pawn
 */
public abstract class Component {
  private String imagePath; //the path to the piece image ie. "sample.lin.images.(place image here)"
  private final int NEWRULE_BOARD_LENGTH = 25; //size of the new board's length
  private final int REGULAR_BOARD_LENGTH = 8; //size of standard board length
  private int row; //the row of the component's tile (the y coordinate)
  private int col; //the column of the components's tile (the c coordinate)
  Color color; //color of the piece

  public abstract boolean canMoveTo(int col, int row); //can the piece move to that col and row?

  public abstract void render(); //puts the image render of the piece on the board

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

  // bishop can only go diagonal (forward or backward), cannot jump over other pieces, has access to only half the squares on the board
  // kings bishop, queen’s bishop
  // Due to diagonal movement, each bishop remains on either the white or black squares
  class Bishop extends Component {
    @Override
    public boolean canMoveTo(int col, int row) {

      // checks that coordinal movement always has slope of +/- 1
      if (((this.getCol() - col) / (this.getRow() - row)) == 1 || (((this.getCol() - col) / (this.getRow() - row)) == -1)) {
        return true;
      } else if ((this.getCol() == col) && (this.getRow() == row)) {
        return false;
      }
      return false;
    }

    @Override
    public void render() {

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
    public void render() {

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
      public void render() {

      }
}