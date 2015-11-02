/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler.controlerLocal;

import model.Coord;
import model.Couleur;
import controler.ChessGameControlers;
import model.observable.ChessGame;

/**
 *
 * @author Tristan
 */
public class ChessGameControler implements ChessGameControlers{

    ChessGame chessGame;

    public ChessGameControler(ChessGame chessGame) {
        this.chessGame = chessGame;
    }
    
    @Override
    public boolean move(Coord initCoord, Coord finalCoord) {
       return chessGame.move(initCoord.x, initCoord.y, finalCoord.x, finalCoord.y);
    }

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
}