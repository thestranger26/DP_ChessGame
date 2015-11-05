/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Tristan
 */
public class SocketClient extends AbstractSocket {

    public static Socket socket = null;

   public SocketClient() {

        try {

            System.out.println("Demande de connexion");
            socket = new Socket("127.0.0.1", 2009);
            System.out.println("Connexion établie avec le serveur, authentification :"); // Si le message s'affiche c'est que je suis connecté

            
            out = new ObjectOutputStream(socket.getOutputStream());
            in =  new ObjectInputStream(socket.getInputStream());
            
            emission = new Emission(out);
            reception = new Reception(in);
            
            
            
        } catch (UnknownHostException e) {
            System.err.println("Impossible de se connecter à l'adresse " + socket.getLocalAddress());
        } catch (IOException e) {
            System.err.println("Aucun serveur à l'écoute du port " + socket.getLocalPort());
        }

    }

  
    @Override
    public void run() {
       while (true) {
           
       }
    }

    @Override
    public String getType() {
        return "Client";
    }
}
