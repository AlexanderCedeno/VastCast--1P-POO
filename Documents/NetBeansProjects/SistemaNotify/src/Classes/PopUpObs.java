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
/**
 * constructor de 5 parametros que sera usado para crear notificaciones en un rango de maximos y minimos
 * @param l Etiqueta
 * @param p Prioridad
 * @param vM Valor maximo
 * @param vMi Valor minimo
 * @param input Ingreso del usuario
 **/
    public PopUpObs(String l, String p, double vM, double vMi, String input) {
        super(l);
        priority = p;
        valueMax = vM;
        valueMin = vMi;
        this.input=input;
    }
/**
 * constructor de 4 parametros que sera usado para comparar con un unico valor en las observaciones
 * @param l Etiqueta
 * @param p Prioridad
 * @param vM Valor maximo
 * @param input Ingreso del usuario
 **/
    public PopUpObs(String l, String p, double vM, String input) {
        super(l);
        priority = p;
        valueMax = vM;
        this.input=input;
    }
/**
 * constructor para las propiedades con datos booleanos (true/false)
 * @param l Etiqueta
 * @param p Prioridad
 * @param input Ingreso del usuario
 **/
    public PopUpObs(String l, String p, String input) {
        super(l);
        priority = p;
        this.input=input;
    }
    
    public String getPriority(){
        return priority;
    }
    
/**
 * metodo que sirve para configurar las notificaciones y usa los valores dados por el usuario para comparar las observaciones
 * @return List Lista de tipo Observacion
 **/
    @Override
    public List<Observation> setPopUp() {
        List<Observation> obj = null;
        switch (input) {
            case "1":
                for (Propiedad p : propiedades) {
                    obj = p.getObservations().stream().filter(prop -> Double.parseDouble(prop.getValue()) > valueMax).map(prop -> prop).collect(Collectors.toList());
                    return obj;
                }   break;
            case "2":
                for (Propiedad p : propiedades) {
                    obj = p.getObservations().stream().filter(prop -> Double.parseDouble(prop.getValue()) > valueMin && Double.parseDouble(prop.getValue()) < valueMax).map(prop -> prop).collect(Collectors.toList());
                }   break;
            case "3":
                for (Propiedad p : propiedades) {
                    obj = p.getObservations().stream().filter(prop -> Double.parseDouble(prop.getValue()) < valueMax).map(prop -> prop).collect(Collectors.toList());
                }   break;
            case "t":
                for (Propiedad p : propiedades) {
                    obj = p.getObservations().stream().filter(prop -> Boolean.parseBoolean(prop.getValue()) == true).map(prop -> prop).collect(Collectors.toList());
                }   break;
            case "f":
                for (Propiedad p : propiedades) {
                    obj = p.getObservations().stream().filter(prop -> Boolean.parseBoolean(prop.getValue()) == false).map(prop -> prop).collect(Collectors.toList());
                }   break;
            default:
                break;
        }

        return obj;
    }

}
