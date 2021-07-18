/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kevin Valle
 * @author Kevin Cedeno
 * @author Gustavo Castro
 */
public abstract class PopUp {

    protected List<Propiedad> propiedades;
    protected String label;
    protected boolean state;

    public PopUp(String l) {
        label = l;
        state = true;
        propiedades = new ArrayList<>();
    }
    /**
     * metodo abstracto para configurar los requerimientos del usuario
     **/
    public abstract List<Observation> setPopUp();

    public String getLabel() {
        return label;
    }
    public boolean getState(){
        return state;
    }
    public void setState(boolean c){
        state=c;
    }
    /**
     * Agrega las propiedades a la lista de PopUp para luego aplicarles un seteo
     * @param p Propiedad que recibe el metodo
     **/
    public void addProp(Propiedad p) {
        propiedades.add(p);
    }
    public void setProp(){
        propiedades.clear();
    }

    public List<Propiedad> getListProp() {
        return propiedades;
    }

}
