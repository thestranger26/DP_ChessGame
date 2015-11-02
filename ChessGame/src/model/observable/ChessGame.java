/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.observable;
import java.util.List;
import java.util.Observable;
import model.Couleur;
import model.Echiquier;
import model.PieceIHM;
import model.Pieces;

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
    public String toString()
    {
        String  affichageEchiquier = echiquier.toString();
        String message = this.getMessage();
        return affichageEchiquier+" "+message;
    }
    public boolean move (int xInit, int yInit, int xFinal, int yFinal)
    {
        
        if(echiquier.isMoveOk(xInit, yInit, xFinal, yFinal))
        {
            echiquier.move(xInit, yInit, xFinal, yFinal);
            echiquier.switchJoueur();
            return true;
        }
        else
        {
            return false;
        }
        
        
    }
    public boolean isEnd()
    {
        return echiquier.isEnd();
        
    }
    public String getMessage()
    {
        
        return echiquier.getMessage();
        
    }
    public Couleur getColorCurrentPlayer()
    {
        return echiquier.getColorCurrentPlayer();
        
    }

    public List<PieceIHM> getListPiecesIHM() {
        return echiquier.getPiecesIHM();
        
    }

}
