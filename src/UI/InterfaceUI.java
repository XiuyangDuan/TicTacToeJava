package UI;

import Data.GameBoard;
import Data.Message;
import Data.UserInput;

/**
 * Interface for game UI
 */
public interface InterfaceUI {

    UserInput getUserInput();

    void notifyDisplayChange(GameBoard boardInfo);

    void notifyMessage(Message.MessageType msg);

    void notifyWarning(Message.ErrorType err);

    void notifyResults(Message.ResultType results);

    void notifyIntroduction();
}
