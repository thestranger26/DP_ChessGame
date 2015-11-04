/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 *
 * @author Tristan
 */
public abstract class ChessGame_Socket implements Runnable  {

    protected static Reception reception;
    protected static Emission emission;
    protected static ObjectOutputStream out = null;
    protected static ObjectInputStream in = null;
    
    private boolean ok;
    
    public static void main(String[] args) {}
    
    public void send(Object message) {
        emission.send (message);    
    }
    public Object read(){

      Object o = reception.read();
      return o;
    }
    
    public void run() {
        while (ok) {
            
        }
    }
    
}
