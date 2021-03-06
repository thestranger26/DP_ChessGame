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
import java.net.ServerSocket;
import java.net.Socket;
import static socket.AbstractSocket.out;
import static socket.SocketClient.socket;

/**
 *
 * @author Tristan
 */
public class SocketServeur extends AbstractSocket {

    public static ServerSocket s_socket = null;
    public static Socket socket = null;

    public SocketServeur(int port) {
        super();

        try {

            s_socket = new ServerSocket(port);
            System.out.println("Le serveur est à l'écoute du port " + s_socket.getLocalPort());

            socket = s_socket.accept();

            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());


            reception = new Reception(in);
            emission = new Emission(out);



        } catch (Exception e) {
            System.err.println("Le port " + socket.getLocalPort() + " est déjà utilisé !");
        }
    }

    public static void main(String[] args) {
    }

    @Override
    public void run() {
        while (true) {
        }
    }

    @Override
    public String getType() {
        return "Serveur";
    }
    
    public String getIp() {
        return socket.getInetAddress().getHostAddress();
    }
}
