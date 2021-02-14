package sample.Lib;

import javafx.scene.layout.GridPane;
import static sample.Lib.Component.Color.WHITE;

/**
  Name: WarChess game
  How to define: use- new WarChessGame(int, GridPane);
  where the int is the size of 1 of the board's length
  ie. if the board was 8 x 8 the int would be 8
  the GridPane should be the rendering grid used to render
  the checkerboard for the javafx components
*/

public class WarChessGame  {
  private GridPane board; //the actual grid object itself that renders the pieces
  BoardTile[][] pieces; //the board representation of the game
  private final int NEWRULE_BOARD_LENGTH = 25;
  private final int REGULAR_BOARD_LENGTH = 8;
  private int currentPlayer = 1;
  //current pieces clicked indexes
  //BTW, x = col, y = row
  private int col1 = 0; //x coordinate of the first clicked tile
  private int col2 = 0; //x coordinate of the second clicked tile
  private int row1 = 0; //y coordinate of the first clicked tile
  private int row2 = 0; //y coordinate of the second clicked tile
  
  private int boardClickedCount = 0; //amount of times board clicked
  /**

   **/
  public WarChessGame(int squareLengthCells, GridPane pane) {
    this.board = pane;
    this.pieces = new BoardTile[squareLengthCells][squareLengthCells];

    //seed chess board with tiles
    for(int i = 0; i < this.pieces.length; i++) {
      for(int j = 0; j< this.pieces[i].length; j++) {
        //create new tile
        this.pieces[i][j] = new BoardTile(20, 20, i, j);
        //assign click reaction
        this.pieces[i][j].setOnMouseClicked(t -> {
          //get object clicked
          BoardTile tile = (BoardTile)(t.getSource());
          //change object state
          handleInput(tile.getRow(), tile.getCol());
          if(this.boardClickedCount == 0) {

          }
        });
        //add piece to grid
        board.add(this.pieces[i][j], i, j);
      }
    }
  }


  public void handleInput(int row, int col) {
    this.boardClickedCount++;
    if(boardClickedCount == 2) {
      this.row2 = row;
      this.col2 = col;
      move();
      this.boardClickedCount = 0;
      this.col1 = 0;
      this.col2 = 0;
      this.row1 = 0;
      this.row2 = 0;
      this.pieces[row][col].resetColor();
    } else {
      this.row1 = row;
      this.col1 = cal;
      this.pieces[row][col].setToClicked();
    }
  }

  /**
    moves the piece to the new tile if it's a valid selection by the user
  **/
  public void move() {
    Component oldPiece; //the old chess piece

    //if the old tile doesn't have a piece, if the new tile has a piece
    //or if the color of the piece selected is different than the
    //player's given piece color or if both tiles are empty
    //or if the piece clicked again is the same
    if (!this.pieces[this.col1][this.row1].hasPiece()
      || this.pieces[this.col2][this.row2].hasPiece()
      || (playerColor(this.currentPlayer) != this.pieces[this.col1][this.row1].getColor())
      ||(row1 == row2) ||(col1 == col2)) {
      //remove pieces
      oldPiece = this.pieces[col1][row1].removePiece();
      this.pieces[col2][row2].movePiece(oldPiece);
      incrementTurn();
    }
  }

  //moves to next player turn
  private void incrementTurn() {
    this.currentPlayer++;
    if(this.currentPlayer > 2) {
      this.currentPlayer = 1;
    }
  }

  //gets the current player num's turn
  public int getCurrentPlayer() {
    return this.currentPlayer;
  }

  //check if there is a checkmate
  public boolean gameOver() {
    return false;
  }

  //gets the color that the current player num can move
  private Component.Color playerColor(int x) {
    if(x == 1) {
      return Component.Color.WHITE;
    } else {
      return Component.Color.BLACK;
    }
  }
}
