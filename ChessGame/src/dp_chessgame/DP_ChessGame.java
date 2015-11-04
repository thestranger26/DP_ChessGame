/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dp_chessgame;

import controler.ChessGameControler_sockets;
import controler.I_ChessGameControlers;
import controler.controlerLocal.ChessGameControler_local;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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

        int dialogResult = JOptionPane.showConfirmDialog(null,"Serveur ?");
        
        boolean isServeur = false;
        
        if(dialogResult == JOptionPane.YES_OPTION) {
            isServeur = true;
        }
        
        ChessGame chessGame;
        I_ChessGameControlers chessGameControler;

        chessGame = new ChessGame();
        chessGame.toString();
        
       // chessGameControler = new ChessGameControler_local(chessGame);
        chessGameControler = new ChessGameControler_sockets(chessGame, isServeur);
        
        JFrame frame = new ChessGameView(chessGameControler);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
