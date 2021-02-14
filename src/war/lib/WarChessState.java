package war.lib;

import javafx.scene.paint.Color;

public class WarChessState {
    public static int currentPlayer = 1;

    public static void takeTurn() {
        currentPlayer++;
        if (currentPlayer > 2) {
            currentPlayer = 1;
        }
    }

    public static Color playerColor() {
        return currentPlayer == 1 ? Color.WHITE : Color.BLACK;
    }

    enum State {
        PREP, BATTLE, GAMEOVER
    }
    public static State state = State.PREP;
}
