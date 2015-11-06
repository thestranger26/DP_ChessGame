/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

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
public abstract class ChessGameAbstractControlers implements I_ChessGameControlers{

    protected ChessGame chessGame;
    

    public ChessGameAbstractControlers(ChessGame chessGame) {
        this.chessGame = chessGame;
    }
    
    
    @Override
    public abstract boolean move(Coord initCoord, Coord finalCoord);

    @Override
    public String getMessage() {
        return chessGame.getMessage();
    }

    @Override
    public boolean isEnd() {
        return chessGame.isEnd();
    }

    @Override
    public Couleur getColorCurrentPlayer() {
        return chessGame.getColorCurrentPlayer();
    }

    @Override
    public void addObserver(Observer o) {
        chessGame.addObserver(o);
    }
    
    @Override
    public List<PieceIHM> getListPiecesIHM() {
        return chessGame.getListPiecesIHM();
    }
    
    public abstract List<Coord> getCoordonneesPossibles(Coord coordInit);
}
