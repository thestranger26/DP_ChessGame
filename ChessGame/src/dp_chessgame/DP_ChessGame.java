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
import model.Couleur;
import model.observable.ChessGame;
import socket.AbstractSocket;
import socket.SocketClient;
import socket.SocketServeur;
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

        int dialogResult = JOptionPane.showConfirmDialog(null, "Serveur ?");

        boolean isServeur = false;

        if (dialogResult == JOptionPane.YES_OPTION) {
            isServeur = true;
        }

        ChessGame chessGame;
        I_ChessGameControlers chessGameControler;

        chessGame = new ChessGame();
        chessGame.toString();


        ////////////////////////////////////////////////
        // Definition de la couleur et du type de socket
        // en fonction du choix du joueur
        Couleur couleur = null;
        AbstractSocket socket = null;
        if (isServeur) {
            System.out.println("Démarrage du serveur");
            try {
                couleur = Couleur.BLANC;
                socket = new SocketServeur();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        } else {
            System.out.println("Démarrage du client");
            try {
                couleur = Couleur.NOIR;
                socket = new SocketClient();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }

        if (socket != null) {

            // chessGameControler = new ChessGameControler_local(chessGame);
            chessGameControler = new ChessGameControler_sockets(chessGame, socket, couleur);

            JFrame frame = new ChessGameView(chessGameControler);
            frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setResizable(true);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } else {
            if (isServeur) {
                JOptionPane.showMessageDialog(null, "Un serveur est déjà connecté sur ce port");
            }
        }
    }
}
