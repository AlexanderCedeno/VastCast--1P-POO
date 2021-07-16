package Classes;

import java.util.Scanner;
import java.util.function.Predicate;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kevin Valle
 * @author Kevin Cedeno
 * @author Gustavo Castro
 */
public class PopUpDev extends PopUp {

    private String Id_Dev;
    List<Device> devices;
    List<PopUpObs> xObs;

    public PopUpDev(String l, String Id_Dev) {
        super(l);
        this.Id_Dev = Id_Dev;
        xObs = new ArrayList<>();
    }
    //use_xObs: metodo que usa las notificaciones por observaciony las agrega a la lista xObs
    public void use_xObs(PopUpObs Obs) {
        xObs.add(Obs);
    }
    //registerDev: metodo que agrega los dispositivos registrados por el usuario
    public void registerDev(Device d) {
        devices.add(d);
    }
    //
    @Override
    public List<Observation> setPopUp() {
        List<Observation> obj = null;
        for (Device d : devices) {
            for (Propiedad p : d.getProperty()) {

            }
        }
        return obj;
    }

}
