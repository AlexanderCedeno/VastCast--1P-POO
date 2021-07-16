/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Settings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 * @author Kevin Valle
 * @author Kevin Cedeno
 * @author Gustavo Castro
 */
public class FilterCollector {
  
   /* public void filter(){
        
        
        
        
    }
    */
    //collectorId: Metodo establecido que recibe una lista de String y retorna una lista de ID
    public List<String> collectorId(List<String> datas){
        List<String> listRI = new ArrayList<>();
        for (String i : datas) {
            String ides = i.split(",")[1];
            listRI.add(ides);
        }
        List<String> listIDu= new ArrayList<>();
        //distinct: devuelve string sin repetirse
        return listIDu=listRI.stream().distinct().collect(Collectors.toList());
        
    }
    
    public int searchLabel( List<String> l,String s){

       // l.remove(9);
       // l.remove(0);
        return l.indexOf(s)+2;
    }
       
}
