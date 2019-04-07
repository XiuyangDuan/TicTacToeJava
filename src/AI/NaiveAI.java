package AI;

import Data.GameBoard;
import Data.GridType;
import Data.UserInput;

/**
 * Naive Computer AI
 */
public class NaiveAI implements InterfaceAI{

    @Override
    public UserInput getComputerUpdate(GameBoard board) {
        for(int i = 0; i < board.getRowSize(); i++) {
            for(int j = 0; j < board.getColSize(); j++) {
                if(board.getGrid(i, j) == GridType.Empty) {
                    return new UserInput(i, j);
                }
            }
        }
        return null;
    }
}
