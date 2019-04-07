package Data;

/**
 * Message class
 */
public class Message {
    public enum MessageType {
        GameOver,
        UserTurn,
        ComputerTurn
    }
    public enum ErrorType {
        InvalidInput
    }
    public enum ResultType {
        HumanWin,
        ComputerWin,
        Tie
    }
}
