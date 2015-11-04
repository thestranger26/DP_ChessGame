/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Coord;

/**
 *
 * @author Tristan
 */
public class Reception implements Runnable {

    private ObjectInputStream in;
    private Object message = null;

    Reception(ObjectInputStream in) {
        this.in = in;
    }

 
    public Object read() {
        try {
             message = in.readObject();
            
            
            //System.out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Reception.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
    }

    @Override
    public void run() {
        while (true) {
            
        }
    }
  
}
