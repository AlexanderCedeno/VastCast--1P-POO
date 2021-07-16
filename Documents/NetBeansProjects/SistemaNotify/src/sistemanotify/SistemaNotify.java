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
        // TODO code application logic here
        Archivo a = new Archivo();
        //a.leerTxt(ruta);iot_telemetry_data_new.csv
        String ruta="";//Ruta para prueba compañeros
        //String ruta = "C:\\Users\\Walter Mix\\Documents\\NetBeansProjects\\SistemaNotify\\src\\Settings\\muestra.txt";//Ruta para prueba Cedeño
        a.leerTxt(ruta);
        List<String> datos = a.getData();
        FilterCollector filter = new FilterCollector();

        for (String cab : a.Linea(ruta).split(",")) {
            cabecera.add(cab);
        }
        cabecera.remove("device");
        cabecera.remove("FECHA ");
        cabecera.remove("NO TOMAR EN CUENTA  ");

        //Crea una lista con todos los id_dispositivos para recorrer las observaciones
        DevGenerator dg = new DevGenerator();
        dg.createObject(datos, devices, filter, cabecera, ruta);
        /*
        List<String> idDev = new ArrayList<>();
        idDev = filter.collectorId(datos);

        //Filtra las opciones dentro del csv y crea los dispositivos con sus respectivas propiedades y observaciones
        for (String dataID : idDev) {
            Device deviceN = new Device(dataID);
            for (String labels : cabecera) {
                Archivo a1 = new Archivo();
                a1.leerTxt(ruta);
                List<String> datas = a1.getData();
                int index = filter.searchLabel(cabecera, labels);
                Propiedad property = new Propiedad(labels);
                deviceN.evaluarProp(property);

                datas.stream().filter(id -> id.split(",")[1].equals(dataID)).map(id -> id).forEach(id -> {

                    Observation ob = new Observation(id.split(",")[index], id.split(",")[9]);
                    property.realizarObs(ob);
                });
                a1.IterO(datas, dataID);
            }
            devices.add(deviceN);
        }
*/
        
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
                    PopUpObs popUp = new PopUpObs(pro.getNombre(), "Peligro");
                    popUp.añadirProp(pro);
                    System.out.println(popUp.setPopUp("t"));
                }
                if (pro.getNombre().equals("temp")) {
                    if (!pro.getNombre().equals("light") && !pro.getNombre().equals("motion")) {
                        PopUpObs popUpo = new PopUpObs(pro.getNombre(), "Peligro", 22.0);
                        popUpo.añadirProp(pro);
                        System.out.println(popUpo.setPopUp("1"));
                    }
                }
            }
        }

    }
}
