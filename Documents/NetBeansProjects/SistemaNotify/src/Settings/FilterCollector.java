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
 *
 * @author Walter Mix
 */
public class FilterCollector {
    
   /* public void filter(){
        
        
        
        
    }
    */
    
    public List<String> collectorId(List<String> datas){
        List<String> listRI = new ArrayList<>();
        for (String i : datas) {
            String ides = i.split(",")[1];
            listRI.add(ides);
        }
        List<String> listIDu= new ArrayList<>();
        return listIDu=listRI.stream().distinct().collect(Collectors.toList());
        
    }
    
}
