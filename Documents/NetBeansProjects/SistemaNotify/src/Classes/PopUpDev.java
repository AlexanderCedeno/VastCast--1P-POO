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

    private String priority;
    List<Device> devices;
    List<PopUpObs> xObs;

    public PopUpDev(String l, String p) {
        super(l);
        priority=p;
        xObs = new ArrayList<>();
        devices=new ArrayList<>();
    }
    //use_xObs: metodo que usa las notificaciones por observacion y las agrega a la lista xObs
    public void use_xObs(PopUpObs Obs) {
        xObs.add(Obs);
    }
    //registerDev: metodo que agrega los dispositivos registrados por el usuario
    public void registerDev(Device d) {
        devices.add(d);
    }
    
    public void setD(){
        devices.clear();
    }
    public String getP(){
        return priority;
    }
       
    //
    @Override
    public List<Observation> setPopUp() {
        List<Observation> obj =new ArrayList<>();
        for (Device d : devices) {
            for (Propiedad p : d.getProperty()) {
            xObs.stream().filter(pUp -> pUp.getLabel().equals(p.getNombre())).map(pUp -> pUp).forEach(pUp -> {
                    if(pUp.getState()==true){
                    pUp.addProp(p);
                    for (Observation o:pUp.setPopUp()){
                        obj.add(o);
                    }
                    pUp.setProp();
            }});
            }
        }
        return obj;
    }

}
