import Data.Message;
import Data.UserInput;
import UI.InterfaceUI;
import core.Core;

/**
 * Game loop
 */
public class GameLoop {

    private Core gameCore;
    private InterfaceUI gameUI;

    private UserInput currInput;

    public GameLoop(Core gameCore, InterfaceUI gameUI) {
        this.gameCore = gameCore;
        this.gameUI = gameUI;
    }

    /**
     * Game loop
     */
    public void startGameLoop() {
        //start
        notifyIntroduction();
        notifyDisplayChange();

        //main loop
        while(true) {
            notifyMessage(Message.MessageType.UserTurn);
            //set member currInput
            currInput = getUserInput();
            while( !getUserInputSuccessful(currInput)  || !isUserInputValid()) {
                notifyWarning(Message.ErrorType.InvalidInput);
                currInput = getUserInput();
            }
            gameCore.humanUpdate(currInput);
            notifyDisplayChange();
            if(gameCore.isGameOver()) {
                notifyMessage(Message.MessageType.GameOver);
                break;
            }
            notifyMessage(Message.MessageType.ComputerTurn);
            gameCore.computerUpdate();
            notifyDisplayChange();
            if(gameCore.isGameOver()) {
                notifyMessage(Message.MessageType.GameOver);
                notifyResults(gameCore.getResults());
                break;
            }
        }
        //end
        notifyResults(gameCore.getResults());
    }

    //UserInput fetch validation
    private boolean getUserInputSuccessful(UserInput currInput) {
        return currInput.getCol() != Integer.MIN_VALUE && currInput.getRow() != Integer.MIN_VALUE;
    }

    //notify the UI to get user input
    private UserInput getUserInput() {
        return gameUI.getUserInput();
    }

    //UserInput data validation
    private boolean isUserInputValid() {
        return gameCore.isUserInputValid(currInput);
    }

    //notify the UI to display introduction
    private void notifyIntroduction() {
        gameUI.notifyIntroduction();
    }

    //notify the UI to display board change
    private void notifyDisplayChange() {
        gameUI.notifyDisplayChange(gameCore.getBoardInfo());
    }

    //notify the UI to display message
    private void notifyMessage(Message.MessageType msg) {
        gameUI.notifyMessage(msg);
    }

    //notify the UI to display results
    private void notifyResults(Message.ResultType results) {
        gameUI.notifyResults(results);
    }

    //notify the UI to display warning message
    private void notifyWarning(Message.ErrorType err) {
        gameUI.notifyWarning(err);
    }

}
