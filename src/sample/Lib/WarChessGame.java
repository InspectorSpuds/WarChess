package sample.Lib;

import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.GridPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import sample.Main;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

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
  private Label nextTurn;
  private ArrayList<Component> whitePieces;
  private ArrayList<Component> blackPieces;

  BoardTile[][] pieces; //the board representation of the game
  private final int NEWRULE_BOARD_LENGTH = 25;
  private final int REGULAR_BOARD_LENGTH = 8;
  private int currentPlayer = 1;
  private boolean inCheck = false;
  //current pieces clicked indexes
  //BTW, x = col, y = row
  private int col1 = 0; //x coordinate of the first clicked tile
  private int col2 = 0; //x coordinate of the second clicked tile
  private int row1 = 0; //y coordinate of the first clicked tile
  private int row2 = 0; //y coordinate of the second clicked tile
  private RiverTile riverNode;
  private int boardClickedCount = 0; //amount of times board clicked
  Pawn pawn = new Pawn(WHITE);


  /**

   **/
  public WarChessGame(int squareLengthCells, GridPane pane, Label nextTurn) throws IOException {
    int randRow;
    int randCol;
    this.board = pane;
    this.pieces = new BoardTile[squareLengthCells][squareLengthCells];
    Random rand = new Random();
    this.nextTurn = nextTurn;
    riverNode = new RiverTile(40,40,rand.nextInt(18)+6, rand.nextInt(18)+6);
    //seed chess board with tiles
    initRiverTiles();
    establishListeners();
  }

  public void establishListeners() throws IOException {
    for(int i = 0; i < this.pieces.length; i++) {
      for(int j = 0; j< this.pieces[i].length; j++) {
        //create new tile
        if(i == riverNode.getRow() && j == riverNode.getCol()) {
          this.pieces[i][j] = riverNode;
        }
        else if(this.pieces[i][j] == null) {
          this.pieces[i][j] = new BoardTile(40, 40, i, j);
          if(this.pawn == null) {
            System.out.println("Empty");
          }
          this.pieces[i][j].movePiece(pawn);
          this.pieces[i][j].render();
        }

        int row = i, col = j;
        //assign click reaction
        //IE
        /*
        * MAIN LOGIC
        *
        *
        *
        * */
        this.pieces[i][j].setOnMouseClicked(t -> {
          //get object clicked

          BoardTile tile = (BoardTile)(t.getSource());
          //if in check and the moved piece is a king
          if(inCheck) {
            handleInput(tile.getRow(), tile.getCol());
          }
          else { //if not do standard turn
            //handle input
            handleInput(tile.getRow(), tile.getCol());
            //if game is over
            try {
              //if player wins
              //gameOver();
            } catch (Exception e) { e.printStackTrace(); }
          }
        });
        //add piece to grid
        board.add(this.pieces[i][j], i, j);
      }
    }
  }

  public void handleInput(int row, int col) {
    //if board count at max, move piece and then reset
    if(boardClickedCount == 1) {
      //assign lasst row and col
      this.row2 = row;
      this.col2 = col;
      move(); //move piece if possible
      this.boardClickedCount = 0;
      //reset piece states
      this.col1 = 0;
      this.col2 = 0;
      this.row1 = 0;
      this.row2 = 0;
      //reset piece color
    } else if(!this.pieces[row][col].hasPiece()) {
      this.col1 = col;
      this.row1 = row;
      this.pieces[row][col].setToClicked();
      this.boardClickedCount++;
    }
  }

  private void addPieces() {

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
    if(this.pieces[col1][row1].hasPiece() && ((row1 != row2) && (col1 != col2))) {
      System.out.println("Processing");
      if (!this.pieces[this.col2][this.row2].hasPiece() && ((row1 != row2) && (col1 != col2))) {
        //remove and replace pieces
        oldPiece = this.pieces[col1][row1].removePiece();
        this.pieces[col2][row2].movePiece(oldPiece);
        this.pieces[col2][row2].render();
        //increment turn counter if move successful
        incrementTurn();
      } else if(this.pieces[this.col2][this.row2].hasPiece()  && playerColor(currentPlayer) != this.pieces[this.col2][this.row2].getColor()) {
        //remove and replace pieces
        oldPiece = this.pieces[col1][row1].removePiece();
        this.pieces[col2][row2].movePiece(oldPiece);
        this.pieces[col2][row2].render();
        //increment turn counter if move successful
        incrementTurn();
        nextTurn.setText("Player " + getCurrentPlayer() + "'s turn");
      }
    }
    this.pieces[this.row1][this.col1].resetColor();
    this.pieces[this.row2][this.col2].resetColor();
  }

  //moves to next player turn
  public void incrementTurn() {
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
  public void gameOver() throws Exception {
    boolean checked = inCheck();
    boolean movesOut = outOfMoves();
    this.inCheck = checked;

    if(!checked && movesOut) {
      Main.resetStage(new String("Player " + getCurrentPlayer()+ "has won!"));
    } else if(checked && movesOut){
      Main.resetStage("Stalemate");
    }
  }

  public boolean inCheck() {
    return true;
  }

  public boolean outOfMoves() {
    return true;
  }

  public void initRiverTiles() {
    //bottom left
    for(int row = riverNode.getRow()-1, col = riverNode.getCol()+1; row>-1 && col < 23; row--, col++){
      this.pieces[row][col] = new RiverTile(40, 40, row, col);
    }

    for(int row = riverNode.getRow()+1, col = riverNode.getCol()+1; row < 25 && col < 23; row++, col++){
      this.pieces[row][col] = new RiverTile(40, 40, row, col);
    }

    for(int row = riverNode.getRow()-1, col = riverNode.getCol()-1; row>-1 && col > 1; row--, col--){
      this.pieces[row][col] = new RiverTile(40, 40, row, col);
    }

    for(int row = riverNode.getRow()+1, col = riverNode.getCol()-1; row < 25 && col > 1; row++, col--){
      this.pieces[row][col] = new RiverTile(40, 40, row, col);
    }
  }

  //gets the color that the current player num can move
  private Component.Color playerColor(int x) {
    if(x == 1) {
      return Component.Color.WHITE;
    } else {
      return Component.Color.BLACK;
    }
  }

  private class RiverTree {
    ArrayList<BoardTile> lane1;
    ArrayList<BoardTile> lane2;
    ArrayList<BoardTile> lane3;
    ArrayList<BoardTile> lane4;
  }
}
