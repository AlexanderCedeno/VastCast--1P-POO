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
import Settings.DevGenerator;

/**
 *
 * @author Kevin Cedeno
 * @author Kevin Valle
 * @author Gustavo Castro
 */
public class SistemaNotify {

    public static List<Observation> observations = new ArrayList<>();
    public static List<Device> devices = new ArrayList<>();
    public static List<Propiedad> propiedades = new ArrayList<>();
    public static List<String> cabecera = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Archivo a = new Archivo();
        //a.leerTxt(ruta);iot_telemetry_data_new.csv
        //String ruta="C:\\Users\\kevin\\Documents\\NetBeansProjects\\VastCast--1P-POO\\Documents\\NetBeansProjects\\SistemaNotify\\src\\Settings\\muestra.txt";//Ruta para prueba compañeros
        String ruta = "C:\\Users\\Walter Mix\\Documents\\NetBeansProjects\\SistemaNotify\\src\\Settings\\muestra.txt";//Ruta para prueba Cedeño
        a.leerTxt(ruta);
        List<String> datos = a.getData();
        FilterCollector filter = new FilterCollector();
        
        

        //Crea una lista con todos los id_dispositivos para recorrer las observaciones
        DevGenerator dg = new DevGenerator();
        //Crea lista con las propiedades en la cabecera
        dg.propertiesExtractor(a.Linea(ruta).split(","), cabecera);
        //Crea objetos a partir de la base de datos
        dg.createObject(datos, devices, filter, cabecera, ruta);

//verificacion de filtros y creacion de objetos de tipo Device con sus respectivas observaciones 
        /* for (Observation ob : observations) {
            System.out.println(ob.getValue());
        }*/
        for (Device pr : devices) {
            System.out.println(pr.getDevice());
            pr.getProperty();
            for (Propiedad pro : pr.getProperty()) {
                //  System.out.println(pro.getNombre());
                /* for (Observation ob : pro.getObservations()) {
            System.out.println(ob.getValue()+" "+ob.getDate());}*/

                //System.out.println(pro.getObservations());
                if (pro.getNombre().equals("light")) {
                    PopUpObs popUp = new PopUpObs(pro.getNombre(), "Peligro", "t");
                    popUp.addProp(pro);
                    System.out.println(popUp.setPopUp());
                }
                if (pro.getNombre().equals("temp")) {
                    if (!pro.getNombre().equals("light") && !pro.getNombre().equals("motion")) {
                        PopUpObs popUpo = new PopUpObs(pro.getNombre(), "Peligro", 22.0, "1");
                        popUpo.addProp(pro);
                        System.out.println(popUpo.setPopUp());
                    }
                }
            }
        }

    }
}
