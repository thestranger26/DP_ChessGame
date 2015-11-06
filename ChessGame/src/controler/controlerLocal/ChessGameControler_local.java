/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler.controlerLocal;

import controler.ChessGameAbstractControlers;
import model.Coord;
import model.Couleur;
import controler.I_ChessGameControlers;
import java.util.List;
import java.util.Observer;
import model.PieceIHM;
import model.Pieces;
import model.observable.ChessGame;

/**
 *
 * @author Tristan
 */
public class ChessGameControler_local extends ChessGameAbstractControlers {

    public ChessGameControler_local(ChessGame chessGame) {
        super(chessGame);
    }
    
    @Override
    public boolean move(Coord initCoord, Coord finalCoord) {
       return chessGame.move(initCoord.x, initCoord.y, finalCoord.x, finalCoord.y);
    }

    @Override
    public String getType() {
        return "Jeu Local";
    }

    @Override
    public List<Coord> getCoordonneesPossibles(Coord coordInit) {
        return chessGame.getCoordonneesPossibles(coordInit);
    }

}
