package Classes;
import java.util.ArrayList;
import java.util.Scanner;
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

public void setPopUp(){
  double obs=0;
  for (Propiedad p: propiedades){
    if (p.getNombre().equals(label)){
          System.out.println("Observaciones para :"+ p.getNombre());
      for (Observation o:p.getArray()){
        obs=Double.parseDouble(o.getValue());
        if (obs>valueMax){
          System.out.println(priority+" :"+ obs + " >" + valueMax);
        }
      }
    }
  }
  
}



}