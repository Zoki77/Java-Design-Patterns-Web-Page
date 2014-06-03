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
public class Caretaker {

    private ArrayList<Object> savedStates = new ArrayList<>();

    public void addMemento(Object m) {
        savedStates.add(m);
    }

    public Object getMemento(int index) {
        return savedStates.get(index);
    }
    
}
