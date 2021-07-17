/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemanotify;

import java.text.ParseException;
import java.util.function.Predicate;
import java.util.Collection;
import java.util.stream.Collectors;
import java.io.FileNotFoundException;
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
        /*SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        Date initDate=format.parse("10/05/2021");
        Date endDate=format.parse("22/05/2021");
        Date fechaA= new Date();
        Writer generator= new Writer();
        LocalDate startLocalDate = initDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                System.out.println(startLocalDate);
        List<LocalDate> dates=generator.getRangeDates(startLocalDate,endLocalDate);
        System.out.println(dates);
         */

    }
 public static void menu() {
        String opcion = "";
        while (!opcion.equals("3")) {
            System.out.println("Sistema de notificación");
            System.out.println("1.-Registrar Usuario");
            System.out.println("2.-Iniciar Sesion");
            System.out.println("3.-Salir");

            System.out.print("Opcion:");
            opcion = scan.nextLine();
            switch (opcion) {
                case "1":
                    registerUser();
                    break;
                case "2":
                    if (users.size() > 0) {
                        logIn();
                    } else {
                        System.out.println("Lo sentimos, no hay usuarios registrados");
                    }
                    break;

            }
        }
    }

    public static Device inputDevice() {
        System.out.print("Ingrese su dispositivo :");
        String device = scan.nextLine();

        for (Device dev : devices) {
            if (dev.getDevice().equals(device)) {
                return dev;
            }
        }
        return null;
    }

    public static String inputProperty() {
        System.out.print("Ingrese etiqueta de Propiedad. ('co','humidity', 'light', 'lpg', 'motion', 'smoke', 'temp )");
        String label = scan.nextLine();

        for (String p : cabecera) {
            if (p.equals(label)) {
                return p;
            }
        }
        return null;
    }

    public static void registerUser() {
        System.out.print("Ingrese id de usuario :");
        String userID = scan.nextLine();

        users.add(new User(userID));
    }

    public static User enterUser() {
        System.out.print("Ingrese su nombre de usuario: ");
        String nameUser = scan.nextLine();

        for (User u : users) {
            if (u.getIDUser().equals(nameUser)) {
                return u;
            }
        }
        return null;
    }

    public static void logIn() {
        String i = "";
        User usuario = new User("inicializar");
        while (!i.equals("correcto")) {
            usuario = enterUser();

            if (usuario != null) {
                i = "correcto";
            } else {
                i = "";
                System.out.println("Nombre de usuario incorrecto, vuelva a intentarlo...");
            }
        }
        String opcion = "";

        while (!opcion.equals("4")) {

            System.out.println("Sistema de notificación");
            System.out.println("1.-Programar notificacion");
            System.out.println("2.-Generar notificaciones");
            System.out.println("3.-Desactivar notificaciones.");
            System.out.println("4.-Cerrar sesion");

            System.out.print("Opcion:");
            opcion = scan.nextLine();

            switch (opcion) {
                case "1":
                    programmNotify(usuario);
                    break;
                case "2":
                    //retirar();
                    break;
                case "3":
                    // retirar();
                    break;

            }
        }
    }

    public static void programmNotify(User u) {

        System.out.println("Notificaciones para el usuario: " + u.getIDUser());

        String n = "";
        while (!n.equals("N")) {

            String evaluator = "";
            while (!evaluator.equals("salida")) {
                String label = inputProperty();
                if (label != null) {
                    u.createPopUp(label);
                    System.out.println("Se creo su configuración para: ");
                    evaluator = "salida";
                    System.out.print("¿Desea configurar otra notificacion? Y/N:");
                    n = scan.nextLine();
                } else {
                    evaluator = "no";
                    System.out.println("No existe la propiedad. Vuelva a intentarlo ");
                }
            }

        }
    }
   

}
