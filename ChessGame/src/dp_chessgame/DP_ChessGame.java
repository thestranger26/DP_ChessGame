/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dp_chessgame;

import controler.ChessGameControler_sockets;
import controler.I_ChessGameControlers;
import controler.controlerLocal.ChessGameControler_local;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import model.Couleur;
import model.observable.ChessGame;
import socket.AbstractSocket;
import socket.SocketClient;
import socket.SocketInformations;
import socket.SocketServeur;
import vue.ChessGameAccueil;
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
    private static SocketInformations informations;
    
    public static void main(String[] args) {
        // TODO code application logic here
        informations = new SocketInformations();
        JFrame jframe = new ChessGameAccueil(informations);
        jframe.setVisible(true);
        addWindowsListener(jframe);
        
    }

    private static boolean jouer() {
        boolean ret = true;
        if (!informations.isJeuReseau) {
            ChessGame chessGame;
            I_ChessGameControlers chessGameControler;

            chessGame = new ChessGame();
            chessGameControler = new ChessGameControler_local(chessGame);

            JFrame frame = new ChessGameView(chessGameControler);
            frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            frame.pack();
            frame.setResizable(true);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } else {
            ChessGame chessGame;
            I_ChessGameControlers chessGameControler;

            chessGame = new ChessGame();
            chessGame.toString();


            ////////////////////////////////////////////////
            // Definition de la couleur et du type de socket
            // en fonction du choix du joueur
            Couleur couleur = null;
            AbstractSocket socket = null;
            if (informations.isServeur) {
                System.out.println("Démarrage du serveur");
                try {
                    couleur = Couleur.BLANC;
                    JOptionPane jop = new JOptionPane();
                    jop.showMessageDialog(null,"Attente d'un client...");
                    socket = new SocketServeur(informations.port);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else {
                System.out.println("Démarrage du client");
                try {
                    couleur = Couleur.NOIR;
                    socket = new SocketClient(informations.ip, informations.port);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }

            if (socket != null) {

                // chessGameControler = new ChessGameControler_local(chessGame);
                chessGameControler = new ChessGameControler_sockets(chessGame, socket, couleur);

                JFrame frame = new ChessGameView(chessGameControler);
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                frame.pack();
                frame.setResizable(true);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            } else {
                ret = false;
                if (informations.isServeur) {
                    JOptionPane.showMessageDialog(null, "Un serveur est déjà connecté sur ce port");
                } else {
                    JOptionPane.showMessageDialog(null, "Impossible de se connecter au serveur."
                            + "\nVeuillez vérifier les paramètres de connexion");
                }
            }
        }
        return ret;
    }
    
    private static void addWindowsListener(JFrame jframe) {
        jframe.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {}

            @Override
            public void windowClosing(WindowEvent e) {}

            @Override
            public void windowClosed(WindowEvent e) { jouer();}

            @Override
            public void windowIconified(WindowEvent e) {}

            @Override
            public void windowDeiconified(WindowEvent e) {}

            @Override
            public void windowActivated(WindowEvent e) {}

            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
    }
}
