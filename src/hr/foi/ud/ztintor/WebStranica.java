/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.foi.ud.ztintor;

import java.net.URL;

/**
 *
 * @author Zoran Tintor
 */
public class WebStranica {

    private URL URL;
    private long vrijeme;
    private long pocetnoVrijeme;
    private int rucneObnove;
    private int autoObnove;
    private int brojPromjena;

    public WebStranica(long vrijeme, long pocetnoVrijeme, int rucneObnove, int autoObnove, int brojPromjena) {
        this.vrijeme = vrijeme;
        this.pocetnoVrijeme = pocetnoVrijeme;
        this.rucneObnove = rucneObnove;
        this.autoObnove = autoObnove;
        this.brojPromjena = brojPromjena;
    }

    public URL getURL() {
        return URL;
    }

    public void setURL(URL URL) {
        this.URL = URL;
    }

    public long getVrijeme() {
        return vrijeme;
    }

    public void setVrijeme(long vrijeme) {
        this.vrijeme = vrijeme;
    }

    public int getRucneObnove() {
        return rucneObnove;
    }

    public void setRucneObnove(int rucneObnove) {
        this.rucneObnove = rucneObnove;
    }

    public int getAutoObnove() {
        return autoObnove;
    }

    public void setAutoObnove(int autoObnove) {
        this.autoObnove = autoObnove;
    }

    public int getBrojPromjena() {
        return brojPromjena;
    }

    public void setBrojPromjena(int brojPromjena) {
        this.brojPromjena = brojPromjena;
    }

    public long getPocetnoVrijeme() {
        return pocetnoVrijeme;
    }

    public void setPocetnoVrijeme(long pocetnoVrijeme) {
        this.pocetnoVrijeme = pocetnoVrijeme;
    }
}
