package Classes;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Kevin Valle
 * @author Kevin Cedeno
 * @author Gustavo Castro
 */
public class PopUpObs extends PopUp {

    private String priority;
    private double valueMax;
    private double valueMin;
    private String input;
    
//sobrecarga de constructores
//PopUpObs (1): constructor de 4 parametros que sera usado para crear notificaciones en un rango de maximos y minimos
    public PopUpObs(String l, String p, double vM, double vMi, String input) {
        super(l);
        priority = p;
        valueMax = vM;
        valueMin = vMi;
        this.input=input;
    }
//PopUpObs (2): constructor de 3 parametros que sera usado para comparar con un unico valor en las observaciones

    public PopUpObs(String l, String p, double vM, String input) {
        super(l);
        priority = p;
        valueMax = vM;
        this.input=input;
    }
//PopUpObs (3): constructor para las propiedades con datos booleanos (true/false)

    public PopUpObs(String l, String p, String input) {
        super(l);
        priority = p;
        this.input=input;
    }
//setPopUp: metodo que sirve para configurar las notificaciones y usa los valores dados por el usuario para comparar las observaciones
    @Override
    public List<Observation> setPopUp() {
        List<Observation> obj = null;
        if (input.equals("1")) {
            for (Propiedad p : propiedades) {
                obj = p.getObservations().stream().filter(prop -> Double.parseDouble(prop.getValue()) > valueMax).map(prop -> prop).collect(Collectors.toList());
                return obj;
            }

        } else if (input.equals("2")) {
            for (Propiedad p : propiedades) {
                obj = p.getObservations().stream().filter(prop -> Double.parseDouble(prop.getValue()) > valueMin && Double.parseDouble(prop.getValue()) < valueMax).map(prop -> prop).collect(Collectors.toList());
            }
        } else if (input.equals("3")) {
            for (Propiedad p : propiedades) {
                obj = p.getObservations().stream().filter(prop -> Double.parseDouble(prop.getValue()) < valueMax).map(prop -> prop).collect(Collectors.toList());
            }
        } else if (input.equals("t")) {
            for (Propiedad p : propiedades) {
                obj = p.getObservations().stream().filter(prop -> Boolean.parseBoolean(prop.getValue()) == true).map(prop -> prop).collect(Collectors.toList());
            }
        } else if (input.equals("f")) {
            for (Propiedad p : propiedades) {
                obj = p.getObservations().stream().filter(prop -> Boolean.parseBoolean(prop.getValue()) == false).map(prop -> prop).collect(Collectors.toList());
            }
        }

        return obj;
    }

}
