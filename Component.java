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

    // NOTE: col and row are private so we will need a getter function to access them
    // bishop can only go diagonal (forward or backward), cannot jump over other pieces, has access to only half the squares on the board
    // kings bishop, queen’s bishop
    // Due to diagonal movement, each bishop remains on either the white or black squares
    public class Bishop extends Component {
        @Override
        public boolean canMoveTo(int col, int row) {

            // checks that coordinal movement always has slope of +/- 1
            if (((this.col - col) / (this.row - row)) == 1 ||
                    (((this.col - col) / (this.row - row)) == -1)) {
                return true;

            } else if ((this.col == col) || (this.row == row)) {

                return false;

            }
        }

        @Override
        public void render() {
            
        }
    }


    public class Queen extends Component {
        @Override
        public boolean canMoveTo(int col, int row) {

            // checks boundaries if necessary?
            if (this.col > 24 || this.col < 0 || this.row > 24 || this.row < 0) {
                return false;

            } else if ((this.col == col) || (this.row == row)) {

                return false;

            }

            if (((this.col - col) / (this.row - row)) == 1 ||
                    (((this.col - col) / (this.row - row)) == -1) && (this.col == col) || (this.row == row) {


                return true;

            } 
        }

        /*
        {-1, 0}
        {1, 0}
        {0, 1}
        {0, -1}
    */
        public class Rook extends Component {
            @Override
            public boolean canMoveTo(int col, int row) {

                // checks boundaries if necessary?
                // hasn't moved
                if (this.col > 24 || this.col < 0 || this.row > 24 || this.row < 0) {
                    return false;
                } else if ((this.col == col) || (this.row == row)) {

                    return false;

                } else {
                    return true;
                }
            }
        }
