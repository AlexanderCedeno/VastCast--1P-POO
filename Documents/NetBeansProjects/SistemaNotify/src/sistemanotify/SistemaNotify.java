/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemanotify;

import java.util.function.Predicate;
import java.util.Collection;
import java.util.stream.Collectors;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;
import java.util.*;
//Importar desde el paquete clase
import Classes.Observation;
import Classes.PopUp;
import Classes.PopUpObs;
import Classes.Propiedad;
import Classes.User;
import Classes.Device;
//Importar desde el paquete Settings
import Settings.Archivo;
import Settings.FilterCollector;
/**
 *
 * @author Kevin Cedeno
 * @author Kevin Valle
 */
public class SistemaNotify {

    public static List<Observation> observations = new ArrayList<>();
    public static List<Device> devices = new ArrayList<>();
    public static List<Propiedad> propiedades=new ArrayList<>();
    public static List<String> cabecera=new ArrayList<>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Archivo a = new Archivo();
        String ruta="C:\\Users\\Walter Mix\\Documents\\NetBeansProjects\\SistemaNotify\\src\\Settings\\muestra.txt";
        //a.leerTxt("C:\\Users\\Walter Mix\\Documents\\NetBeansProjects\\SistemaNotify\\src\\Settings\\iot_telemetry_data_new.csv");
        a.leerTxt(ruta);
        List<String> datas = a.getData();
        FilterCollector filter=new FilterCollector();
        String entrada="temp";
        int index=filter.searchLabel(a.Linea(ruta), cabecera, entrada);
        
        
        //Crea una lista con todos los id_dispositivos para recorrer las observaciones
        List<String> idDev=new ArrayList<>();
        idDev=filter.collectorId(datas);

        

        //Filtra las opciones dentro del csv
         for (String dataID : idDev) {
             Device deviceN= new Device(dataID);
             devices.add(deviceN);
             propiedades.add(new Propiedad(entrada));
            datas.stream().filter(id -> id.split(",")[1].equals(dataID)).map(id -> id.split(",")[index]).forEach(id -> {
                

                observations.add(new Observation(id));

            });
            a.IterO(datas, dataID);

       }

// System.out.println(observations.size());
        for (Observation ob : observations) {
            System.out.println(ob.getValue());
        }
 
        for (Device pr:devices){
            System.out.println(pr.getDevice());
        }
        for (Propiedad pro:propiedades){
            System.out.println(pro.getNombre());
        }
}
}