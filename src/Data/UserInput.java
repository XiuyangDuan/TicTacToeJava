package Data;


/**
 * User input: the selection use made to place a piece
 */
public class UserInput {
    // user input starts from 1, data starts from 0
    private int row;
    private int col;

    public UserInput(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
