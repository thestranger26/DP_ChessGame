/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tristan
 */
public class Emission {

    private ObjectOutputStream out;
    
    Emission(ObjectOutputStream out) {
        this.out = out;
    }

    public void send(Object mess) {
       
        try {
            out.writeObject(mess);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Emission.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
