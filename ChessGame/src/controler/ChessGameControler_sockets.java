/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import model.Coord;
import model.observable.ChessGame;
import socket.ChessGame_Socket;
import socket.Client;
import socket.Serveur;

/**
 *
 * @author Tristan
 */
public class ChessGameControler_sockets extends ChessGameAbstractControlers implements Runnable {

    ChessGame_Socket socket;

    public ChessGameControler_sockets(ChessGame chessGame, boolean isServeur) {
        super(chessGame);
        if (isServeur) {
            System.out.println("Démarrage du serveur");
            try {
                socket = new Serveur();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        } else {
            System.out.println("Démarrage du client");
            try {
                socket = new Client();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public boolean move(Coord initCoord, Coord finalCoord) {

        boolean ret = false;
        if (chessGame.move(initCoord.x, initCoord.y, finalCoord.x, finalCoord.y)) {
            ArrayList<Coord> listCoordonees = new ArrayList<Coord>();
            listCoordonees.add(initCoord);
            listCoordonees.add(finalCoord);
            System.out.println("Controleur : Envoie du mess");
            socket.send(listCoordonees);
            System.out.println("Controleur : Mess envoyé");
            ret = true;
        }
        return ret;

    }

    @Override
    public void run() {
        System.out.println("Debut du run");
        try {
            while (true) {
                Object o = socket.read();

                ArrayList<Coord> listCoordonees = (ArrayList<Coord>) o;

                Coord init = listCoordonees.get(0);
                Coord dest = listCoordonees.get(1);
                chessGame.move(init.x, init.y, dest.x, dest.y);
            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

}
