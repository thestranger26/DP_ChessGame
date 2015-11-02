/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dp_chessgame;

import controler.controlerLocal.ChessGameControler;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import model.observable.ChessGame;
import vue.ChessGameCmdLine;
import vue.ChessGameView;

/**
 *
 * @author Tristan
 */
public class DP_ChessGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        ChessGame chessGame;
        ChessGameControler chessGameControler;

        chessGame = new ChessGame();
        chessGame.toString();
        
        chessGameControler = new ChessGameControler(chessGame);
        
        
        JFrame frame = new ChessGameView(chessGameControler);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
