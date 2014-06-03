/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.foi.ud.ztintor.mvc;

import java.io.Console;

/**
 *
 * @author Zoran Tintor
 */
public class Controler {

    private View v;
    private boolean petlja;
    private String input;

    public Controler(View v) {
        this.v = v;
    }

    // funkcija za provjru unosa
    public void provjeriUnos() {
        Console c = System.console();
        if (c == null) {
            v.fail();
            System.exit(1);
        }

        v.ispisiUpute();
        petlja = true;
        while (petlja) {
            input = c.readLine();
            String split[] = input.split(" ");
            switch (split[0]) {
                case "-B":
                    v.ispisiBrojPoveznica();
                    break;
                case "-I":
                    v.ispisiAdresePoveznica();
                    break;
                case "-J":
                    v.novaStranica(split[1]);
                    petlja = false;
                    break;
                case "-R":
                    v.obnoviStranicuRucno();
                    petlja = false;
                    break;
                case "-S":
                    v.ispisiStatistiku();
                    break;
                case "-Q":
                    v.izadi();
                    System.exit(1);
                    break;
            }
        }



    }
}