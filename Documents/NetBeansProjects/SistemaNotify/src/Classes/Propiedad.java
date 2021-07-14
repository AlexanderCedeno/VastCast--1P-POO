package Classes;

import java.util.ArrayList;
import java.util.List;

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

    public void realizarObs(Observation o) {
        observaciones.add(o);

    }

    public List<Observation> getArray() {

        return observaciones;
    }

    public void imprimirValor() {

        for (Observation c : observaciones) {
            System.out.println(c.getValue());
        }
      
    }
}
