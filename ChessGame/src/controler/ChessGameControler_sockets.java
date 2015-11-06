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
import model.Couleur;
import model.observable.ChessGame;
import socket.AbstractSocket;
import socket.SocketClient;
import socket.SocketServeur;

/**
 *
 * @author Tristan
 */
public class ChessGameControler_sockets extends ChessGameAbstractControlers implements Runnable {

    AbstractSocket socket;
    Couleur couleur;

    public ChessGameControler_sockets(ChessGame chessGame, AbstractSocket socket, Couleur couleur) {
        super(chessGame);
        
        this.socket = socket;
        this.couleur = couleur;

        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public boolean move(Coord initCoord, Coord finalCoord) {

        boolean ret = false;
        
        if (!chessGame.getColorCurrentPlayer().equals(couleur)) {
            initCoord.x = -1;
            initCoord.y = -1;
            finalCoord.x = -1;
            finalCoord.y = -1;
        }
          
        if(chessGame.move(initCoord.x, initCoord.y, finalCoord.x, finalCoord.y)) {
            
            ArrayList<Coord> listCoordonees = new ArrayList<Coord>();
            listCoordonees.add(initCoord);
            listCoordonees.add(finalCoord);
            socket.send(listCoordonees);
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

    @Override
    public String getType() {
        return socket.getType() + " " + couleur.toString();
    }

}
