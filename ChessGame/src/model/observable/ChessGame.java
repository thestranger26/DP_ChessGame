/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.observable;

import java.util.List;
import java.util.Observable;
import model.Coord;
import model.Couleur;
import model.Echiquier;
import model.PieceIHM;

/**
 *
 * @author Tristan
 */
public class ChessGame extends Observable {

    private Echiquier echiquier;

    public ChessGame() {
        echiquier = new Echiquier();

    }

    @Override
    public String toString() {
        String affichageEchiquier = echiquier.toString();
        String message = this.getMessage();
        return affichageEchiquier + " " + message;
    }

    public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
        boolean ret = false;
        
       // System.out.println("xInit : "+xInit+", yInit : "+yInit+", xFinal : "+xFinal+", yFinal : "+yFinal);
        
        if (echiquier.isMoveOk(xInit, yInit, xFinal, yFinal)) {
            echiquier.move(xInit, yInit, xFinal, yFinal);
            echiquier.switchJoueur();
            System.out.println("Move OK");
            ret = true;
        } else {
            System.out.println("Move NOK");
        }

        this.setChanged();
        this.notifyObservers();
        this.clearChanged();
        return ret;

    }

    public boolean isEnd() {
        return echiquier.isEnd();

    }

    public String getMessage() {

        return echiquier.getMessage();

    }

    public Couleur getColorCurrentPlayer() {
        return echiquier.getColorCurrentPlayer();

    }

    public List<PieceIHM> getListPiecesIHM() {
        return echiquier.getPiecesIHM();

    }

    public List<Coord> getCoordonneesPossibles(Coord coordInit) {
        return echiquier.getCoordonneesPossibles(coordInit);
    }
}
