package launcher.localLauncher;

import model.observable.ChessGame;
import vue.ChessGameCmdLine;
import controler.controlerLocal.ChessGameControler_local;


/**
 * @author francoise.perrin
 * Lance l'exécution d'un jeu d'échec en mode console.
 */
public class LauncherCmdLine {
	
	public static void main(String[] args) {		
		
		ChessGame chessGame;
		ChessGameControler_local chessGameControler;		
		
		chessGame = new ChessGame();	
		chessGameControler = new ChessGameControler_local(chessGame);
		
		new ChessGameCmdLine(chessGameControler);
                
	}

}
