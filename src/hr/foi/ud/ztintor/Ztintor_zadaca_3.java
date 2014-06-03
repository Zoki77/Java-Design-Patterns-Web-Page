/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.foi.ud.ztintor;

import hr.foi.ud.ztintor.mvc.Model;
import hr.foi.ud.ztintor.mvc.View;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Glavna klasa.
 *
 * @author Zoran Tintor
 */
public class Ztintor_zadaca_3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        long pocetak;
        long kraj;
        pocetak = System.currentTimeMillis();
        Model m = new Model();
        View v = new View(m);        
        m.addObserver(v);
        m.pokreniDretvu(args[0], v);
        
        while (true) {
            
            kraj = System.currentTimeMillis();
            try {
                Thread.currentThread().sleep(Integer.parseInt(args[1]) * 1000 - (kraj - pocetak));
            } catch (InterruptedException ex) {
                Logger.getLogger(Ztintor_zadaca_3.class.getName()).log(Level.SEVERE, null, ex);
            }
            pocetak = System.currentTimeMillis();
            
            m.ucitajPodatke();
            m.getWs().setAutoObnove(m.getWs().getAutoObnove() + 1);
            v.obnoviStranicuAuto();
        }//while

        
    }
}
