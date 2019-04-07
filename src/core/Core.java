package core;

import AI.InterfaceAI;
import Data.GameBoard;
import Data.GridType;
import Data.Message;
import Data.UserInput;

/**
 * Core logic of the game
 */
public class Core {
    private GameBoard board;
    private InterfaceAI gameAI;
    private GridType winnerType;

    /**
     * Constructor
     * @param row row number
     * @param col column number
     * @param gameAI Computer AI instance
     */
    public Core(int row, int col, InterfaceAI gameAI) {
        board = new GameBoard(row, col);
        this.gameAI = gameAI;
        winnerType = GridType.Empty;
    }

    /**
     * Check if game is over
     * @return true if game is over, false if not
     */
    public boolean isGameOver() {
        return checkRow(board) || checkCol(board) || checkSlash(board) || isFull();
    }

    /**
     * Get the result of the game (human wins, computer wins or tie)
     * @return MessageType.ResultType
     */
    public Message.ResultType getResults() {
        switch (winnerType) {
            case Black:
                return Message.ResultType.HumanWin;
            case White:
                return Message.ResultType.ComputerWin;
            case Empty:
                return Message.ResultType.Tie;
        }
        return Message.ResultType.Tie;
    }

    /**
     * Get the information of game board
     * @return a board instance
     */
    public GameBoard getBoardInfo() {
        return board;
    }

    /**
     * Update the board based on human move
     * @param currInput UserInput instance
     */
    public void humanUpdate(UserInput currInput) {
        applyDataChange(currInput, GridType.Black);
    }

    /**
     * Update the board based on computer move
     */
    public void computerUpdate() {
        UserInput res = gameAI.getComputerUpdate(board);
        applyDataChange(res, GridType.White);
    }

    //change the data storage
    private void applyDataChange(UserInput currInput, GridType type) {
        board.setGrid(currInput.getRow(), currInput.getCol(), type);
    }

    //check the whole row
    //true: game over
    //false: game continue
    private boolean checkRow(GameBoard board) {
        for(int j = 0; j < board.getColSize(); j++) {
            int acc = 0;
            GridType type = GridType.Empty;
            for(int i = 0; i < board.getRowSize(); i++) {
                if(board.getGrid(i, j) == type && type != GridType.Empty) {
                    acc++;
                } else {
                    acc = 0;
                    type = board.getGrid(i, j);
                }
                if(acc >= 2) {
                    winnerType = type;
                    return true;
                }
            }
        }
        return false;
    }
    //check the whole column
    //true: game over
    //false: game continue
    private boolean checkCol(GameBoard board) {
        for(int i = 0; i < board.getRowSize(); i++) {
            int acc = 0;
            GridType type = GridType.Empty;
            for(int j = 0; j < board.getColSize(); j++) {
                if(board.getGrid(i, j) == type && type != GridType.Empty) {
                    acc++;
                } else {
                    acc = 0;
                    type = board.getGrid(i, j);
                }
                if(acc >= 2) {
                    winnerType = type;
                    return true;
                }
            }
        }
        return false;
    }

    //check the slash line
    //true: game over
    //false: game continue
    private boolean checkSlash(GameBoard board) {
        if(board.getColSize() != board.getRowSize()) {
            return false;
        }
        int N = board.getColSize() - 1;
        GridType typeLeftTop = GridType.Empty;
        GridType typeRightTop = GridType.Empty;
        int accLeftTop = 0;
        int accRightTop = 0;
        for(int i = 0; i < board.getRowSize(); i++) {
            if(board.getGrid(i, i) == typeLeftTop && typeLeftTop != GridType.Empty) {
                accLeftTop++;
            } else {
                accLeftTop = 0;
                typeLeftTop = board.getGrid(i, i);
            }
            if(accLeftTop >= 2) {
                winnerType = typeLeftTop;
                return true;
            }

            if(board.getGrid(i, N - i) == typeRightTop && typeRightTop != GridType.Empty) {
                accRightTop++;
            } else {
                accRightTop = 0;
                typeRightTop = board.getGrid(i, N - i);
            }
            if(accRightTop >= 2) {
                winnerType = typeRightTop;
                return true;
            }
        }
        return false;
    }

    //check if the whole board is occupied
    private boolean isFull() {
        return board.isFull();
    }

    //check user input
    public boolean isUserInputValid(UserInput currInput) {
        return  currInput.getRow() < board.getRowSize() // out of bound, should check this first
                && currInput.getCol() < board.getColSize() // out of bound, should check this first
                && board.getGrid(currInput.getRow(), currInput.getCol()) == GridType.Empty; //should not be occupied
    }
}
