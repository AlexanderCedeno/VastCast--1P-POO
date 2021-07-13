package Classes;

import java.util.ArrayList;
import java.util.List;

public class Device {

    private String idDevice;
    private List<Propiedad> propiedades;

    public Device(String id) {
        idDevice = id;
    }

    public void evaluarProp(Propiedad p) {
        propiedades.add(p);
    }

    public String getDevice() {
        return idDevice;
    }

}
