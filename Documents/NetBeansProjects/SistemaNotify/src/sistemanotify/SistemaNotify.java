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
import Classes.PopUpDev;
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
        /*List<PopUpObs> xObs=new ArrayList<>();
        xObs.add(new PopUpObs("temp", "Peligro", 22.0, "1"));
        xObs.add(new PopUpObs("light", "Peligro", "t"));
        List<PopUpDev> dev=new ArrayList<>();
        for (Device pr : devices) {
            System.out.println(pr.getDevice());
            for (PopUpObs obs:xObs){
                PopUpDev clasico=new PopUpDev(obs.getLabel(),pr.getDevice());
                dev.add(clasico);
                System.out.println(clasico.getLabel());
                clasico.registerDev(pr);
                clasico.use_xObs(obs);
                System.out.println(clasico.setPopUp());
            }                  
                
            }
        }*/
        
        User user1=new User("JuanFranciscoAlelio");
        user1.createPopUp("motion");
        for (PopUpObs po:user1.getPopUpObs()){
        System.out.println(po.getLabel());
        }        
        /*System.out.println("Esto es aparte: "+'\n'+xObs);
        for (PopUpDev devi:dev){
        System.out.println(devi.getLabel());}*/
    }
}
