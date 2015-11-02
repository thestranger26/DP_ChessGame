/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import model.Coord;
import model.Couleur;

/**
 *
 * @author Tristan
 */
public interface ChessGameControlers {

    public boolean move(Coord initCoord, Coord finalCoord);
    public String getMessage();	
    public boolean isEnd();	
    public Couleur getColorCurrentPlayer(); 
}
