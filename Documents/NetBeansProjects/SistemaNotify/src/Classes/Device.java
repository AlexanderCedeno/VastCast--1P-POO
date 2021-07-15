package Classes;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Kevin Cedeno
 * @author Kevin Valle
 * @author Gustavo Castro
 */
public class Device {

    private String idDevice;
    private List<Propiedad> propiedades;

    public Device(String id) {
        idDevice = id;
        propiedades=new ArrayList<>();
    }

    public void evaluarProp(Propiedad p) {
        propiedades.add(p);
    }

    public String getDevice() {
        return idDevice;
    }
    public List<Propiedad> getProperty(){
        return propiedades;
    }

}
