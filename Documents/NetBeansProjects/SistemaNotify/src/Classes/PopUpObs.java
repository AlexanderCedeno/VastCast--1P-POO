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
 */
public class PopUpObs extends PopUp{
private String priority;
private double valueMax;
private double valueMin;

//costructor para rangos
public PopUpObs(String l, String p, double vM, double vMi){
  super(l);
  priority=p;
  valueMax=vM;
  valueMin=vMi;
}
//constructor para valores mayores
public PopUpObs(String l, String p, double vM){
  super(l);
  priority=p;
  valueMax=vM;
}

@Override
public List<Observation> setPopUp(String input){
    List<Observation> obj=null;
    if (input.equals("1")){
  for (Propiedad p:propiedades){
   obj=p.getObservations().stream().filter(prop-> Double.parseDouble(prop.getValue())>valueMax).map(prop-> prop).collect(Collectors.toList());
  return obj;  
  }

    }
    else if(input.equals("2")){
         System.out.println(priority);
        for (Propiedad p:propiedades){
        p.getObservations().stream().filter(prop-> Double.parseDouble(prop.getValue())>valueMin&&Double.parseDouble(prop.getValue())<valueMax).map(prop-> prop).collect(Collectors.toList());}
    }
    else if(input.equals("3")){
        System.out.println(priority);
        for (Propiedad p:propiedades){
        p.getObservations().stream().filter(prop-> Double.parseDouble(prop.getValue())<valueMin&&Double.parseDouble(prop.getValue())<valueMax).map(prop-> prop).collect(Collectors.toList());
    }
  
};


return obj;
}


}