package sample.Lib;

import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.GridPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import sample.AddDialog;
import sample.Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
  King whiteKing = new King(Component.Color.WHITE);
  King blackKing = new King(Component.Color.BLACK);
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

  HashMap<String, Component> componentMap = createComponentMap();

  /**

   **/
  public WarChessGame(int squareLengthCells, GridPane pane, Label nextTurn) throws IOException {
    this.whitePieces = new ArrayList<>();
    this.blackPieces = new ArrayList<>();
    this.board = pane;
    this.pieces = new BoardTile[squareLengthCells][squareLengthCells];

    Random rand = new Random();
    this.nextTurn = nextTurn;
    riverNode = new RiverTile(40,40,rand.nextInt(18)+6, rand.nextInt(18)+6);
    //seed chess board with tiles
    initRiverTiles();
    establishListeners();

    int randRow1 = rand.nextInt(NEWRULE_BOARD_LENGTH);
    int randCol1 = rand.nextInt(NEWRULE_BOARD_LENGTH);
    int randRow2 = rand.nextInt(NEWRULE_BOARD_LENGTH);
    int randCol2 = rand.nextInt(NEWRULE_BOARD_LENGTH);
    this.pieces[randRow1][randCol1].setComponent(whiteKing);
    this.pieces[randRow2][randCol2].setComponent(blackKing);
    this.pieces[randRow1][randCol1].render();
    this.pieces[randRow2][randCol2].render();
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
//          this.pieces[i][j].setPieces(this.pieces);
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
              gameOver();
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
      //assign last row and col
      this.row2 = row;
      this.col2 = col;
//      if (this.pieces[row1][col1].hasPiece()) {
//        if (this.pieces[row1][col1].canMoveTo(this.col2, this.row2)) {
//          move(); //move piece if possible
//        }
//      }
      move();
      this.boardClickedCount = 0;
      //reset piece states
      this.col1 = 0;
      this.col2 = 0;
      this.row1 = 0;
      this.row2 = 0;
      //reset piece color
    } else if (this.pieces[row][col].hasPiece()) {
      this.col1 = col;
      this.row1 = row;
      this.pieces[row][col].setToClicked();
      this.boardClickedCount++;
    } else if(!this.pieces[row][col].hasPiece()) {
      this.col1 = col;
      this.row1 = row;
      this.pieces[row][col].setToClicked();
      this.boardClickedCount++;

      // prompt to add pieces
      String key = AddDialog.showDialog(playerColor(currentPlayer));
      addComponent(row, col, key);
    }
  }

  private void addComponent(int row, int col, String key) {
    if (key != null) {
      Component it = componentMap.get(key);
      this.pieces[row][col].setComponent(it);
      this.pieces[row][col].render();

      if (key.contains("white")) {
        whitePieces.add(it);
      } else if (key.contains("black")) {
        blackPieces.add(it);
      }
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
//    if(this.pieces[row1][col1].hasPiece() && ((row1 != row2) && (col1 != col2))) {
//      System.out.println("Processing");
//      if (!this.pieces[this.row2][this.col2].hasPiece() && ((row1 != row2) && (col1 != col2))) {
//        //remove and replace pieces
//        oldPiece = this.pieces[row1][col1].removePiece();
//        this.pieces[row2][col2].movePiece(oldPiece);
//        this.pieces[row2][col2].render();
//        //increment turn counter if move successful
//        incrementTurn();
//      } else if(this.pieces[this.row2][this.col2].hasPiece()  && playerColor(currentPlayer) != this.pieces[this.row2][this.col2].getColor()) {
//        //remove and replace pieces
//        oldPiece = this.pieces[row1][col1].removePiece();
//        this.pieces[row2][col2].movePiece(oldPiece);
//        this.pieces[row2][col2].render();
//        //increment turn counter if move successful
//        incrementTurn();
//        nextTurn.setText("Player " + getCurrentPlayer() + "'s turn");
//      }
//    }
    if(this.pieces[row1][col1].hasPiece() && ((row1 != row2) && (col1 != col2))) {
      System.out.println("Processing");
      oldPiece = this.pieces[row1][col1].removePiece();
      this.pieces[row2][col2].movePiece(oldPiece);
      this.pieces[row2][col2].render();
      //increment turn counter if move successful
      incrementTurn();
      nextTurn.setText("Player " + getCurrentPlayer() + "'s turn");
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
    if(getCurrentPlayer() == 1) {
      //check if any pieces can move to the king space
      for(int i  = 0; i < this.blackPieces.size(); i++) {
        if(this.blackPieces.get(i) == null && this.blackPieces.get(i).canMoveTo(whiteKing.getCol(), whiteKing.getRow())) {
          return true;
        }
      }
    } else {
      for(int i  = 0; i < this.whitePieces.size(); i++) {
        if(this.whitePieces.get(i) == null && this.whitePieces.get(i).canMoveTo(blackKing.getCol(), blackKing.getRow())) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean outOfMoves() {
    //if
    int kingSpaces = 9;
    int kingRow;
    int kingCol;
    Component nextPiece;
    boolean[][] taken = new boolean[3][3];
    if(currentPlayer == 1) {
      kingRow = whiteKing.getRow();
      kingCol = whiteKing.getCol();
      for(int i  = 0; i < this.blackPieces.size(); i++) {
        nextPiece = blackPieces.get(i);
        if(nextPiece !=null) {
          for(int mod1 = -1; mod1< 2; mod1++) {
            for(int mod2 = -1; mod2 < 2; mod2++) {
              if(nextPiece.canMoveTo(mod1+kingCol, mod2 +kingRow) && !taken[mod1+1][mod2+1]){
                kingSpaces--;
                taken[mod1+1][mod2+1] = true;
              }
            }
          }
        }
      }
    } else {
      kingRow = blackKing.getRow();
      kingCol = blackKing.getCol();
      for(int i  = 0; i < this.whitePieces.size(); i++) {
        nextPiece =whitePieces.get(i);
        if(nextPiece !=null) {
          for(int mod1 = -1; mod1< 2; mod1++) {
            for(int mod2 = -1; mod2 < 2; mod2++) {
              if(nextPiece.canMoveTo(mod1+kingCol, mod2 +kingRow) && !taken[mod1+1][mod2+1]) {
                kingSpaces--;
                taken[mod1+1][mod2+1] = true;
              }
            }
          }
        }
      }
    }
    return kingSpaces == 0;
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
  public Component.Color playerColor(int x) {
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

  private HashMap<String, Component> createComponentMap() {
    HashMap<String, Component> map = new HashMap<>();
    map.put("bishop_black", new Bishop(Component.Color.BLACK));
    map.put("bishop_white", new Bishop(Component.Color.WHITE));
    map.put("knight_black", new Knight(Component.Color.BLACK));
    map.put("knight_white", new Knight(Component.Color.WHITE));
    map.put("pawn_black", new Pawn(Component.Color.BLACK));
    map.put("pawn_white", new Pawn(Component.Color.WHITE));
    map.put("queen_black", new Queen(Component.Color.BLACK));
    map.put("queen_white", new Queen(Component.Color.WHITE));
    map.put("rook_black", new Rook(Component.Color.BLACK));
    map.put("rook_white", new Rook(Component.Color.WHITE));
    map.put("king_black", new King(Component.Color.BLACK));
    map.put("king_white", new King(Component.Color.WHITE));
    map.put("triangle_white", new Boat(Component.Color.WHITE));
    map.put("triangle_black", new Boat(Component.Color.BLACK));
    return map;
  }
}
