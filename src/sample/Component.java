package sample.Lib;

public abstract class Component {
    private abstract String imagePath; //the path to the piece image ie. "sample.lin.images.(place image here)"
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
    }


    enum Color {
        BLACK, WHITE;
    }
}

public class Pawn extends Component {
    @Override
    public boolean canMoveTo(int col, int row) {

        // checks boundaries if necessary?
        if (this.col > 24 || this.col < 0 || this.row > 24 || this.row < 0) {
            return false;
        }

        // covers all 8 positions
        if ((this.col == col || this.col == col + 1 || this.col == col - 1) &&
                (this.row = row || this.row = row + 1 || this.row = row - 1)) {

            // returns false if pawn tries to stay in same position
            if (this.col == col && this.row == row) {
                return false;
            }

            return true;
        } else {
            return false;
        }
    }
}

public class Knight extends Component {
    @Override
    public boolean canMoveTo(int col, int row) {

        // covers 2 forward, backward and 1 left, right
        if ((this.col == col + 1 || this.col == col - 1) &&
                (this.row == row + 2 || this.row == row - 2)) {
            return true;

        // covers 1 forward, backward and 2 left, right
        } else if ((this.col == col + 2 || this.col == col - 2) &&
                (this.row == row + 1 || this.row == row - 1)) {
            return true;
        } else {
            return false;
        }
    }
}

public class Bishop extends Component {
    @Override
    public boolean canMoveTo(int col, int row) {

        // checks boundaries if necessary?
        if (this.col > 24 || this.col < 0 || this.row > 24 || this.row < 0) {
            return false;
        }

        // checks that coordinal movement always has slope of +/- 1
        if ( ((this.col - col) / (this.row - row)) == 1 ||
                ( ((this.col - col) / (this.row - row)) == -1) {
            return true;

        } else {
            return false;
        }
    }
}

public class Rook extends Component {
    @Override
    public boolean canMoveTo(int col, int row) {

        // checks boundaries if necessary?
        if (this.col > 24 || this.col < 0 || this.row > 24 || this.row < 0) {
            return false;
        }

        // checks that rooks stays in same column or same row
        if ( (this.col == col || this.row == row) {
            return true;

        } else {
            return false;
        }
    }
}

public class King extends Component {
    @Override
    public boolean canMoveTo(int col, int row) {

        // checks boundaries if necessary?
        if (this.col > 24 || this.col < 0 || this.row > 24 || this.row < 0) {
            return false;
        }

        // covers all 8 positions
        if ((this.col == col || this.col == col + 1 || this.col == col - 1) &&
                (this.row = row || this.row = row + 1 || this.row = row - 1)) {

            // returns false if king tries to stay in same position
            if (this.col == col && this.row == row) {
                return false;
            }

            return true;
        } else {
            return false;
        }
    }
}