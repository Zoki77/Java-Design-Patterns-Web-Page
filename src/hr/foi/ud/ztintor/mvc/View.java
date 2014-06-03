/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.foi.ud.ztintor.mvc;

import hr.foi.ud.ztintor.WebStranica;
import java.util.Observable;

/**
 *
 * @author Zoran Tintor
 */
public class View implements java.util.Observer {

    private Model m;
    private WebStranica ws;

    public View(Model m) {
        this.m = m;
    }

    // funkcija za ispis uputa
    public void ispisiUpute() {
        System.out.println("----------KOMANDE-------------");
        System.out.println("-B - ispis ukupnog broja poveznica");
        System.out.println("-I - ispis adresa poveznica s rednim brojem");
        System.out.println("-J n - prijelaz na poveznicu s rednim brojem n te ucitavanje web stranice");
        System.out.println("-R - obnovi vazecu web stranicu");
        System.out.println("-S - ispis statistike");
        System.out.println("-Q - prekid rada programa");
    }

    // funkcija za ispis broja poveznica
    public void ispisiBrojPoveznica() {
        System.out.println("Ukupan broj poveznica: " + m.getBrojPoveznica());
    }

    // funkcija za ispis svih adresa poveznica
    public void ispisiAdresePoveznica() {
        System.out.println("-----Linkovi---------");
        m.getOriginator().restoreLinkovi(m.getCaretaker().getMemento(m.getBroj()));
    }

    // funkcija za  ispis kod nove stranice
    public void novaStranica(String unos2) {

        m.promjeniLink(unos2);
        System.out.println("----Nova stranica-----");
        System.out.println(m.getWs().getURL());
    }

    // funkcija za ispis pri ručnoj obnovi
    public void obnoviStranicuRucno() {
        m.getWs().setRucneObnove(m.getWs().getRucneObnove() + 1);
        System.out.println("Stranica rucno obnovljena");
    }

    // funkcija za ispis pri automatskoj obnovi
    public void obnoviStranicuAuto() {
        System.out.println("Stranica automatski obnovljena");
    }

    // funkcija za ispis statistike
    public void ispisiStatistiku() {
        System.out.println("----Koristene stranice-----");
        for (int i = 0; i < m.getListaWebStranica().size(); i++) {
            ws = (WebStranica) m.getListaWebStranica().get(i);
            if (ws.getURL().equals(m.getWs().getURL())) {
                System.out.println(i + 1 + ".  " + ws.getURL() + " Rucna:" + ws.getRucneObnove() + " Auto: " + ws.getAutoObnove() + "  Vrijeme: " + ((ws.getVrijeme() + (System.currentTimeMillis() - ws.getPocetnoVrijeme())) / 1000) + "s" + "  Broj promjena:" + ws.getBrojPromjena());
            } else {
                System.out.println(i + 1 + ".  " + ws.getURL() + " Rucna:" + ws.getRucneObnove() + " Auto: " + ws.getAutoObnove() + "  Vrijeme: " + (ws.getVrijeme() / 1000) + "s" + "  Broj promjena:" + ws.getBrojPromjena());
            }
        }
    }
    
    // funkcija za ispis pri izlazku iz programa
    public void izadi() {
        System.out.println("Kraj programa");
    }

    // funkcija za ispis greške pri radu s konzolom
    public void fail() {
        System.err.println("console fail!");
    }

    // observer
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Dogodila se promjena");
    }
}
