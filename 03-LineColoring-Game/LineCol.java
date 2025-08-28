public class LineCol {

    public static final int BLUE = 1;
    public static final int NO_WINNING_MOVE = -1;
    public static final int RED = 2;
    public static final int UNCOLORED = 0;

    public LineCol() {
    }

    public static int getWinningMove(int[] state, int player) {
        for (int i = 0; i < state.length; i++) {
            if (state[i] == UNCOLORED && isValidMove(state, i, player)) {
                int[] newState = state.clone();
                newState[i] = player;

                if (isWinning(newState, player) ||
                        (!isWinning(newState, 3 - player) && getWinningMove(newState, 3 - player) == NO_WINNING_MOVE)) {
                    return i;
                }
            }
        }
        return NO_WINNING_MOVE;
    }

    private static boolean isWinning(int[] state, int player) {
        for (int i = 0; i <= state.length - 3; i++) {
            if (state[i] == player && state[i + 1] == player && state[i + 2] == player) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValidMove(int[] state, int index, int player) {
        if (index > 0 && state[index - 1] == player) {
            return false;
        }
        if (index < state.length - 1 && state[index + 1] == player) {
            return false;
        }

        return true;
    }
}
