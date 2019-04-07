package Data;

/**
 * class of the game board
 */
public class GameBoard {

    private GridType[][] grids;

    /**
     * Constructor
     * @param row row number
     * @param col col number
     */
    public GameBoard(int row, int col) {
        grids = new GridType[row][col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                grids[i][j] = GridType.Empty;
            }
        }
    }

    /**
     * get row size
     * @return size of the row
     */
    public int getRowSize() {
        if(grids == null) {
            try {
                throw new Exception("game board not properly initialized");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return grids.length;
    }

    /**
     * get column size
     * @return size of the column
     */
    public int getColSize() {
        if(grids.length == 0) {
            try {
                throw new Exception("game board not properly initialized");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return grids[0].length;
    }

    /**
     * Setter for the grid
     * @param row row index of the grid
     * @param col col index of the grid
     * @param type type of the grid
     */
    public void setGrid(int row, int col, GridType type) {
        grids[row][col] = type;
    }

    /**
     * get grid info (black, white or empty)
     * @param row row index of the grid
     * @param col col index of the grid
     * @return the grid type
     */
    public GridType getGrid(int row, int col) {
        return grids[row][col];
    }

    /**
     * check if all grids are occupied
     * @return true if full, false if not
     */
    public boolean isFull() {
        for(int i = 0; i < grids.length; i++) {
            for(int j = 0; j < grids[0].length; j++) {
                if(grids[i][j] == GridType.Empty) {
                    return false;
                }
            }
        }
        return true;
    }
}
