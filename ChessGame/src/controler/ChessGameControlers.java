/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.util.List;
import java.util.Observer;
import model.Coord;
import model.Couleur;
import model.PieceIHM;
import model.Pieces;

/**
 *
 * @author Tristan
 */
public interface ChessGameControlers {

    public boolean move(Coord initCoord, Coord finalCoord);
    public String getMessage();	
    public boolean isEnd();	
    public Couleur getColorCurrentPlayer(); 
    public void addObserver(Observer o);

    public List<PieceIHM> getListPiecesIHM();
}
