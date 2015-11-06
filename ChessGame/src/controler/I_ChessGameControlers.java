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
public interface I_ChessGameControlers {

    public boolean move(Coord initCoord, Coord finalCoord);
    public String getMessage();	
    public boolean isEnd();	
    public Couleur getColorCurrentPlayer(); 
    public void addObserver(Observer o);

    public List<PieceIHM> getListPiecesIHM();

    public String getType();

    public List<Coord> getCoordonneesPossibles(Coord coordInit);
   
}
