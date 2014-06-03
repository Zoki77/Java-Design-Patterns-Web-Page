/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.foi.ud.ztintor.mvc;

import hr.foi.ud.ztintor.Dretva;
import hr.foi.ud.ztintor.WebStranica;
import hr.foi.ud.ztintor.memento.Caretaker;
import hr.foi.ud.ztintor.memento.Originator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zoran Tintor
 */
public class Model extends Observable {

    private int brojPoveznica;
    private ArrayList<Object> listaURLa = new ArrayList<>();
    private ArrayList<Object> prethodnalistaURLa = new ArrayList<>();
    private ArrayList<Object> listaWebStranica;
    private boolean postoji;
    private Caretaker caretaker = new Caretaker();
    private Originator originator = new Originator();
    private int broj = -1;
    private WebStranica ws;
    private WebStranica postojecaWs;
    private URL noviUrl;
    private boolean flag=false;

    //funkcija za pokretanje dretve
    public void pokreniDretvu(String stranica, View v) {
        try {
            ws = new WebStranica(0, System.currentTimeMillis(), 0, 0, 0);
            ws.setURL(new URL(stranica));
            listaWebStranica = new ArrayList<>();
            listaWebStranica.add(ws);
            setListaWebStranica(listaWebStranica);

            Dretva dretva = new Dretva(this, v);
            dretva.start();
        } catch (MalformedURLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // funkcija za učitavanje podataka iz web stranice
    public void ucitajPodatke() {
        broj++;
        BufferedReader in = null;
        try {
            brojPoveznica = 0;
            in = new BufferedReader(
                    new InputStreamReader(getWs().getURL().openStream()));
            String inputLine;
            String text;
            String linkText;
            int prvi = 0;
            int zadnji = 0;
            prethodnalistaURLa = new ArrayList<>();
            for (Object object : listaURLa) {
                URL urlTemp = new URL(((URL) object).toString());
                prethodnalistaURLa.add(urlTemp);
            }
            listaURLa = new ArrayList<>();
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.contains("<a ") || inputLine.contains("<a>")) {
                    while (true) {                        
                        prvi = inputLine.indexOf("href=", prvi) + 6;
                        if (prvi == 5) {
                            break;
                        }
                        brojPoveznica++;
                        text = inputLine.substring(prvi);
                        zadnji = text.indexOf("\"");
                        if (zadnji < 0) {
                            zadnji = text.indexOf("'");
                        }
                        if (text.startsWith("http:") || text.startsWith("www.")) {
                            linkText = text.substring(0, zadnji);
                        } else if (text.startsWith("\\")) {
                            linkText = getWs().getURL().toString() + text.substring(0, zadnji);
                        } else {
                            linkText = getWs().getURL().toString() + text.substring(1, zadnji);
                        }
                        URL link = new URL(linkText);
                        listaURLa.add(link);
                    }//while
                    prvi = 0;
                }
            }
            originator.set((ArrayList) listaURLa);
            caretaker.addMemento(originator.saveToMemento());
            setBrojPoveznica(brojPoveznica);
            setListaURLa(listaURLa);
            if (flag) {
                if (prethodnalistaURLa.size() == listaURLa.size()) {
                    for (int i = 0; i < prethodnalistaURLa.size(); i++) {
                        if (!prethodnalistaURLa.get(i).toString().equals(listaURLa.get(i).toString())) {
                            getWs().setBrojPromjena(getWs().getBrojPromjena() + 1);
                            setChanged();
                            notifyObservers();
                            break;
                        }
                    }
                } else {
                    getWs().setBrojPromjena(getWs().getBrojPromjena() + 1);
                    setChanged();
                    notifyObservers();
                }
            }
            flag=true;
            in.close();

        } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    // funkcija za izvršavanje akcija pri promjeni glavne stranice
    public void promjeniLink(String unos2) {
        flag=false;
        for (int i = 0; i < getListaURLa().size(); i++) {
            if (i + 1 == Integer.parseInt(unos2.trim())) {
                noviUrl = (URL) getListaURLa().get(i);
            }
        }
        getWs().setVrijeme(getWs().getVrijeme() + (System.currentTimeMillis() - getWs().getPocetnoVrijeme()));
        postoji = false;
        for (int j = 0; j < getListaWebStranica().size(); j++) {
            postojecaWs = (WebStranica) getListaWebStranica().get(j);
            if (postojecaWs.getURL().equals(noviUrl)) {
                postojecaWs.setPocetnoVrijeme(System.currentTimeMillis());
                setWs(postojecaWs);
                postoji = true;
            }
        }
        if (!postoji) {
            ws = new WebStranica(0, System.currentTimeMillis(), 0, 0, 0);
            ws.setURL(noviUrl);
            listaWebStranica = getListaWebStranica();
            listaWebStranica.add(ws);
            setListaWebStranica(listaWebStranica);
        }
    }

    // getteri i setteri
    public int getBrojPoveznica() {
        return brojPoveznica;
    }

    public void setBrojPoveznica(int brojPoveznica) {
        this.brojPoveznica = brojPoveznica;
    }

    public ArrayList<Object> getListaURLa() {
        return listaURLa;
    }

    public void setListaURLa(ArrayList<Object> listaURLa) {
        this.listaURLa = listaURLa;
    }

    public ArrayList<Object> getListaWebStranica() {
        return listaWebStranica;
    }

    public void setListaWebStranica(ArrayList<Object> listaWebStranica) {
        this.listaWebStranica = listaWebStranica;
    }

    public Caretaker getCaretaker() {
        return caretaker;
    }

    public void setCaretaker(Caretaker caretaker) {
        this.caretaker = caretaker;
    }

    public Originator getOriginator() {
        return originator;
    }

    public void setOriginator(Originator originator) {
        this.originator = originator;
    }

    public int getBroj() {
        return broj;
    }

    public void setBroj(int broj) {
        this.broj = broj;
    }

    public WebStranica getWs() {
        return ws;
    }

    public void setWs(WebStranica ws) {
        this.ws = ws;
    }
}
