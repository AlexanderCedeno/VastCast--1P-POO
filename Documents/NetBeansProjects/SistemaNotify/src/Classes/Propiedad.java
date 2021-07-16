package Classes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kevin Valle
 * @author Kevin Cedeno
 * @author Gustavo Castro
 */
public class Propiedad {

    private String nombre;
    private List<Observation> observaciones;

    public Propiedad(String n) {
        nombre = n;
        observaciones = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }
    //realizarObs: metodo que sirve para registrar las observaciones que realiza el dispostivo (agrega a la lista Propiedades las observaciones)
    public void realizarObs(Observation o) {
        observaciones.add(o);

    }

    public List<Observation> getObservations() {
        return observaciones;
    }

}
