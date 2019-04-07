package AI;

import Data.GameBoard;
import Data.UserInput;

/**
 * Interface for Computer AI
 */
public interface InterfaceAI {
    //AI should be able to determine where to place the next step

    /**
     * getComputerUpdate
     * @param board game board object
     * @return UserInput object
     */
    UserInput getComputerUpdate(GameBoard board);

}
