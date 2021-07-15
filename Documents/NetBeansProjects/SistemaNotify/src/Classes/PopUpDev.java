package Classes;

import java.util.Scanner;
import java.util.function.Predicate;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

public class PopUpDev extends PopUp{
    private String Id_Dev;
    List<Device> devices;
    List<PopUpObs> xObs;

    public PopUpDev(String l,String Id_Dev) {
        super(l);
        this.Id_Dev = Id_Dev;
        xObs= new ArrayList<>();
    }
    
    public void use_xObs(PopUpObs Obs){
        xObs.add(Obs);
    }
    @Override
    public void setPopUp(String input){
        System.out.println("123 123 123");
    }
    
}
