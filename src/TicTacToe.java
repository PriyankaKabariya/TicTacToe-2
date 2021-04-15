
import java.util.Scanner;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pkabariy
 */
public class TicTacToe extends JFrame {

    static JTextField txtrow,txtcolumn;
    private JDialog dialog;
    public JFrame frame;

    public final int BOARDSIZE = 3; // size of the board

    public enum Status {
        WIN, DRAW, CONTINUE
    }; // game states
    public char[][] board; // board representation
    public boolean firstPlayer; // whether it's player 1's move
    public boolean gameOver; // whether game is over

    // Constructor
    public TicTacToe() {
        board = new char[BOARDSIZE][BOARDSIZE];
        firstPlayer = true;
        gameOver = false;

    }

    // start game
    public void play() {
        JFrame f = new JFrame("TicTacToe");
        f.getContentPane().setLayout(new FlowLayout());
        JButton b=new JButton("Submit");  
        txtrow = new JTextField("Player X's turn Enter Row.", 20);
         txtcolumn = new JTextField("Player X's turn Enter Column.", 20);

        f.getContentPane().add(txtrow);
               f.getContentPane().add(txtcolumn);

        f.pack();
        f.setVisible(true);

        Scanner input = new Scanner(System.in);
        int row; // row for next move
        int column; // column for next move

        System.out.println("Player X's turn.");
        //jLabel1.setText("Player X's turn.");
        while (!gameOver) {
            char player = (firstPlayer ? 'X' : 'O');

            // player's turn
            do {
                System.out.printf(
                        "Player %c: Enter row (0, 1 or 2): ", player);
                row = input.nextInt();
                System.out.printf(
                        "Player %c: Enter column (0, 1 or 2): ", player);
                column = input.nextInt();
            } while (!validMove(row, column));

            board[row][column] = player;

            firstPlayer = !firstPlayer;

            printBoard();
            printStatus(player);
        }
    }

    // show game status in status bar
    private void printStatus(int player) {
        Status status = gameStatus();

        // check game status
        switch (status) {
            case WIN:
                System.out.printf("Player %c wins.", player);
                gameOver = true;
                break;
            case DRAW:
                System.out.println("Game is a draw.");
                gameOver = true;
                break;
            case CONTINUE:
                if (player == 'X') {
                    System.out.println("Player O's turn.");
                } else {
                    System.out.println("Player X's turn.");
                }
                break;
        }
    }

    // get game status
    private Status gameStatus() {
        int a;

        // check for a win on diagonals
        if (board[0][0] != 0 && board[0][0] == board[1][1]
                && board[0][0] == board[2][2]) {
            return Status.WIN;
        } else if (board[2][0] != 0 && board[2][0]
                == board[1][1] && board[2][0] == board[0][2]) {
            return Status.WIN;
        }

        // check for win in rows
        for (a = 0; a < 3; a++) {
            if (board[a][0] != 0 && board[a][0]
                    == board[a][1] && board[a][0] == board[a][2]) {
                return Status.WIN;
            }
        }

        // check for win in columns
        for (a = 0; a < 3; a++) {
            if (board[0][a] != 0 && board[0][a]
                    == board[1][a] && board[0][a] == board[2][a]) {
                return Status.WIN;
            }
        }

        // check for a completed game
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c] == 0) {
                    return Status.CONTINUE; // game is not finished
                }
            }
        }
        return Status.DRAW; // game is a draw
    }

    // display board
    public void printBoard() {
        TicPrint t = new TicPrint();
        System.out.println(" _______________________ ");

        for (int row = 0; row < BOARDSIZE; row++) {
            System.out.println("|       |       |       |");

            for (int column = 0; column < BOARDSIZE; column++) {
                t.printSymbol(column, board[row][column]);
            }

            System.out.println("|_______|_______|_______|");
        }
    }

    // validate move
    public boolean validMove(int row, int column) {
        return row >= 0 && row < 3 && column >= 0 && column < 3
                && board[row][column] == 0;
    }

}
