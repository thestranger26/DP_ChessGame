/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.observable;

/**
 *
 * @author Tristan
 */
public class ChessGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    public String toString(); 
	public boolean move (int xInit, int yInit, int xFinal, int yFinal); 
	public boolean isEnd();
	public String getMessage();
	public Couleur getColorCurrentPlayer(); 

}
