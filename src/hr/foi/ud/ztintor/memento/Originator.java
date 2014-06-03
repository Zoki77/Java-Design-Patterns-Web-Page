/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.foi.ud.ztintor.memento;


import java.util.ArrayList;

/**
 *
 * @author Zoran Tintor
 */
public class Originator {

    private ArrayList<Object> savedStates;


    public void set(ArrayList<Object> savedStates) {
        this.savedStates = savedStates;
    }

    public Object saveToMemento() {
        return new Memento(savedStates);
    }

    public void restoreLinkovi(Object m) {
        if (m instanceof Memento) {
            Memento memento = (Memento) m;
            savedStates = memento.getSavedState();
            System.out.println("-----------Linkovi------------");
            for (int i = 0; i < savedStates.size(); i++) {
                System.out.println((i+1) + ".  "+savedStates.get(i));
            }
        }
    }

    private static class Memento {

        private ArrayList<Object> savedStates;

        public Memento(ArrayList<Object> stateToSave) {
            savedStates = stateToSave;
        }

        public ArrayList<Object> getSavedState() {
            return savedStates;
        }
    }
}
