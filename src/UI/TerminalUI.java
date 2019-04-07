package UI;

import Data.GameBoard;
import Data.Message;
import Data.UserInput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * UI based on terminal
 */
public class TerminalUI implements InterfaceUI{
    @Override
    public void notifyDisplayChange(GameBoard boardInfo) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < boardInfo.getRowSize(); i++) {
            for(int j = 0; j < boardInfo.getColSize(); j++) {
                sb.append("|");
                switch (boardInfo.getGrid(i, j)) {
                    case Empty:
                        sb.append("O");
                        break;
                    case Black:
                        sb.append("*");
                        break;
                    case White:
                        sb.append('#');
                }
            }
            sb.append("|\n");
        }
        System.out.println(sb);
    }

    @Override
    public void notifyMessage(Message.MessageType msg) {
        System.out.println(msg);
    }

    @Override
    public void notifyWarning(Message.ErrorType err) {
        System.out.println(err);
    }

    @Override
    public UserInput getUserInput() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String name = reader.readLine();
            String[] words = name.split(" ");
            int row = Integer.parseInt(words[0]) - 1;
            int col = Integer.parseInt(words[1]) - 1;
            UserInput currInput = new UserInput(row, col);
            return currInput;
        } catch (Exception e) {
            return new UserInput(Integer.MIN_VALUE, Integer.MIN_VALUE);
        }
    }

    @Override
    public void notifyResults(Message.ResultType results) {
        System.out.println(results);
    }

    @Override
    public void notifyIntroduction() {
        System.out.println("Welcome to Tic Tac Toe");
        System.out.println("Please input row number and column number, ");
        System.out.println("Using space between two numbers, for example: 2 3");
        System.out.println("Human *    Computer #    Empty O");
    }
}
