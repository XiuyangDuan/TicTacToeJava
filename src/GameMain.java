import AI.NaiveAI;
import UI.TerminalUI;
import core.Core;

/**
 * Main function of the game
 */
public class GameMain {
    private static final int ROW = 3;
    private static final int COL = 3;

    public static void main(String[] args) {
        Core gameCore = new Core(ROW, COL, new NaiveAI());
        TerminalUI gameUI = new TerminalUI();
        GameLoop loop = new GameLoop(gameCore, gameUI);
        loop.startGameLoop();
    }

}
