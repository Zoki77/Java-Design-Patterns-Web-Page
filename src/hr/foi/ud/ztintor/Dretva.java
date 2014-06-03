/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.foi.ud.ztintor;

import hr.foi.ud.ztintor.mvc.Controler;
import hr.foi.ud.ztintor.mvc.Model;
import hr.foi.ud.ztintor.mvc.View;

/** Dretva za rad na određenoj stranici.
 *
 * @author Zoran Tintor
 */
public class Dretva extends Thread {

    private Model model;
    private Controler controler;
    private View view;

    public Dretva(Model m, View v) {
        this.model = m;
        this.view = v;
    }

    @Override
    public synchronized void start() {
        super.start(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        controler = new Controler(view);
        while (true) {

            model.ucitajPodatke();
            controler.provjeriUnos();
        }

    }

    @Override
    public void interrupt() {
        super.interrupt(); //To change body of generated methods, choose Tools | Templates.
    }
}
