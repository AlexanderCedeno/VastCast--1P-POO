/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemanotify;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import Settings.Writer;
import Settings.Main;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.ZoneId;

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
    public static List<User> users = new ArrayList<>();
    public static Scanner scan = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {

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

       //Main menu = new Main();
       //menu.menu(cabecera, devices);
      //rangeDates();
      
      
       try 
          {    
                FileWriter fw=new FileWriter("C:\\Users\\Walter Mix\\Desktop\\Examen\\usuarios.csv", true);    
                fw.write(System.getProperty( "line.separator" ));
                fw.write(" Welcome to javaTpointoooooooooooo."); 
                fw.write(System.getProperty( "line.separator" ));
                fw.write(" Prueba."); 
                fw.close();    
          }
          catch(Exception e){System.out.println(e);}    
          System.out.println("Success...");   
       
       Writer csvWrite=new Writer();
       csvWrite.writeCsv();
       
       
       
       
       
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
 /*User user1=new User("JuanFranciscoAlelio");
        user1.createPopUp("motion");
        for (PopUpObs po:user1.getPopUpObs()){
        System.out.println(po.getLabel());
        }*/
        //registerUser();
        // System.out.println(usuario.getIDUser());
        // programmNotify();
    }

    public static void rangeDates() throws ParseException {
        Writer generator= new Writer();
        SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Debe ingresar el rango de fechas con el siguiente formato dd/MM/yyyy");
        System.out.print("Ingrese la fecha inicial dd/MM/yyyy: ");
        Date initDate=format.parse(scan.nextLine());
        System.out.print("Ingrese la fecha final dd/MM/yyyy: ");
        Date endDate=format.parse(scan.nextLine());

        LocalDate end = initDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                System.out.println(end);
        List<LocalDate> dates=generator.getRangeDates(end,endLocalDate);
        System.out.println(dates);
         

    }
}
