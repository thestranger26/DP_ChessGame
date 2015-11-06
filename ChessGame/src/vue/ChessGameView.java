/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

/**
 *
 * @author Tristan
 */
import controler.I_ChessGameControlers;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import model.Coord;
import model.PieceIHM;
import model.observable.ChessGame;

public class ChessGameView extends JFrame implements MouseListener, MouseMotionListener, Observer {

    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;
    private I_ChessGameControlers controleur;
    int x_origin;
    int y_origin;
    JPanel informations_panel;
    JTextArea informations_textArea;
    JScrollPane informations_scrollPane;

    public ChessGameView(I_ChessGameControlers controleur) {

        super(controleur.getType());


        this.setLayout(new GridBagLayout());


        this.controleur = controleur;
        this.controleur.addObserver(this);


        Dimension boardSize = new Dimension(600, 600);

        getContentPane().setPreferredSize(new Dimension(900, 600));



        //  Use a Layered Pane for this this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);

        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane 

        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout(new GridLayout(8, 8));
        chessBoard.setPreferredSize(boardSize);
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel(new BorderLayout());
            chessBoard.add(square);

            int row = (i / 8) % 2;
            if (row == 0) {
                square.setBackground(i % 2 == 0 ? Color.blue : Color.white);
            } else {
                square.setBackground(i % 2 == 0 ? Color.white : Color.blue);
            }
        }

        //Add a few pieces to the board
        java.util.List<PieceIHM> listePieces = controleur.getListPiecesIHM();

        this.refreshView(listePieces);



        informations_textArea = new JTextArea("Bienvenue dans le jeu \nsuperMegaBienDeLaMortQuiTueTasVu\nSUPER CHESS !");
        informations_textArea.setText(informations_textArea.getText() + "\n**********************");
        informations_textArea.setText(informations_textArea.getText() + "\nLes blancs commençent !!\n\n");
        informations_textArea.setEnabled(false);
        informations_textArea.setLineWrap(true);

        informations_scrollPane = new JScrollPane(informations_textArea);
        informations_scrollPane.setPreferredSize(new Dimension(300, 600));

        getContentPane().add(informations_scrollPane);


    }

    public void mousePressed(MouseEvent e) {
        chessPiece = null;
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());

        if (c instanceof JPanel) {
            return;
        }

        x_origin = e.getX();
        y_origin = e.getY();

        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel) c;
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
        affichageDestinations(e.getX(), e.getY());
    }

    //Move the chess piece around
    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) {
            return;
        }
        chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
    }

    //Drop the chess piece back onto the chess board
    public void mouseReleased(MouseEvent e) {
        if (chessPiece == null) {
            return;
        }
        ////////////////////
        // RECUPERATION DE L'ORIGINE


        int x_init = x_origin / 75;
        int y_init = 7 - (y_origin / 75);

        x_origin = 0;
        y_origin = 0;

        ////////////////////
        // RECUPERATION DE LA DESTINATION
        chessPiece.setVisible(false);

        int x_dest = e.getX() / 75;
        int y_dest = 7 - (e.getY() / 75);
        if (x_init != x_dest || y_init != y_dest) {
            controleur.move(new Coord(x_init, y_init), new Coord(x_dest, y_dest));
        } else {
            refreshView(controleur.getListPiecesIHM());
        }

    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void update(Observable o, Object arg) {
        this.refreshView(controleur.getListPiecesIHM());
        informations_textArea.setText(informations_textArea.getText() + "\n" + controleur.getMessage());
    }

    //Supprime toutes les pieces du plateau, puis reaffiche avec la nouvelle position
    public void refreshView(java.util.List<PieceIHM> listePiece) {

        /////////////////////////////////////
        // On commence par tout supprimer
        chessBoard.removeAll();

        // Puis on reconstruit tout
        Dimension boardSize = new Dimension(600, 600);
        chessBoard.setLayout(new GridLayout(8, 8));
        chessBoard.setPreferredSize(boardSize);
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel(new BorderLayout());
            chessBoard.add(square);

            int row = (i / 8) % 2;
            if (row == 0) {
                square.setBackground(i % 2 == 0 ? Color.blue : Color.white);
            } else {
                square.setBackground(i % 2 == 0 ? Color.white : Color.blue);
            }
        }

        //Add a few pieces to the board




        /////////////////////////////////////
        // Puis on réaffiche toutes les pièces
        JLabel piece;
        JPanel panel;

        //On demande au controleur la liste des types de pièces


        for (PieceIHM p : listePiece) {

            //Pour chaque type de pièce, on récupère les coordoonées des différentes pièces de ce type
            java.util.List<Coord> listeCoord = p.getList();
            for (Coord c : listeCoord) {
                //Pour chaque pièce, on l'affiche en allant chercher l'image correspondante.
                piece = new JLabel(new ImageIcon("images/" + p.getTypePiece() + p.getCouleur().toString() + "S.png"));
                panel = (JPanel) chessBoard.getComponent(((7 - (c.y)) * 8 + (c.x)));


                panel.add(piece);
            }
        }
        this.revalidate();

    }

    private void affichageDestinations(int x, int y) {
        int x_init = x / 75;
        int y_init = 7 - (y / 75);
        Coord coordInit = new Coord(x_init, y_init);
        java.util.List<Coord> list = controleur.getCoordonneesPossibles(coordInit);
        for (Coord c : list) {
            chessBoard.getComponent(((7 - (c.y)) * 8 + (c.x))).setBackground(Color.cyan);
        }
    }
}
