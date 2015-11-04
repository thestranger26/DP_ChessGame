/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.util.Observable;
import java.util.Observer;
import model.Coord;
import model.observable.ChessGame;

/**
 *
 * @author Tristan
 */
public class ChessGameControler_sockets extends ChessGameAbstractControlers implements Observer {

    public ChessGameControler_sockets(ChessGame chessGame) {
        super(chessGame);
    }

    
    @Override
    public boolean move(Coord initCoord, Coord finalCoord) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
