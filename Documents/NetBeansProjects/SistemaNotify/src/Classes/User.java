package Classes;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Kevin Valle
 * @author Kevin Cedeno
 * @author Gustavo Castro
 */
public class User {
    private List<Device> devices;
    private List<PopUpObs> xObs;
    private List<PopUpDev> xDev;
    private String id_User;

    public User(String id) {
        id_User = id;
        devices=new ArrayList<>();
        xObs= new ArrayList<>();
        xDev=new ArrayList<>();
    }
    
    
    public String getIDUser(){
        return id_User;
    }
    
    public List<Device> getDevicesRoll(){
        return devices;
    }
    
    public List<PopUpObs> getPopUpObs(){
        return xObs;
    }
    public List<PopUpDev> getPopUpDev(){
        return xDev;
    }
}
