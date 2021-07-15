package Classes;
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

public void setPopUp(String input){
    if (input.equals("1")){
        System.out.print(priority);
  for (Propiedad p:propiedades){
  p.getObservations().stream().filter(prop-> prop.getValue()>valueMax).map(prop-> prop).forEach(prop->{
      System.out.println(prop.getValue());
  });}
    }
    else if(input.equals("2")){
         System.out.print(priority);
        for (Propiedad p:propiedades){
        p.getObservations().stream().filter(prop-> prop.getValue()>valueMin&&prop.getValue()<valueMax).map(prop-> prop).forEach(prop->{
        System.out.println(prop.getValue());
  });}
    }
  
}



}