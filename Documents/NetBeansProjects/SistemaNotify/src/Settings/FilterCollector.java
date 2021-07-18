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
    /**
     * metodo establecido que recibe una lista de String y retorna una lista de ID
     * @param datas Lista de string que recibe el metodo
     * @return List retorna una lista de ID
     **/
    public List<String> collectorId(List<String> datas) {
        List<String> listRI = new ArrayList<>();
        for (String i : datas) {
            String ides = i.split(",")[1];
            listRI.add(ides);
        }
        List<String> listIDu = new ArrayList<>();
        //distinct: devuelve string sin repetirse
        return listIDu = listRI.stream().distinct().collect(Collectors.toList());

    }
    /**
     * metodo que devuelve el indice de las propiedades
     * @param l Lista de string que recibe el metodo
     * @param s Busqueda
     * @return int Indice de la propiedad
     **/
    public int searchLabel(List<String> l, String s) {
        return l.indexOf(s) + 2;
    }
   
}
