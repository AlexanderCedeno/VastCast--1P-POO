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
//Importar desde el paquete clase
import Classes.Observation;
import Classes.Propiedad;
import Classes.User;
import Classes.Device;

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
     * @throws ParseException Usado para lanzar excepciones
     */
    public static void main(String[] args) throws ParseException {
        //Creacion de oobjeto archivo para acceder al archivo que queremos leer.
        Archivo a = new Archivo();

        System.out.println("Por favor introduzca la RUTA y NOMBRE de la DATA que quiere leer"+'\n'+ "(Ejemplo:C:\\Users\\Walter Mix\\Downloads\\iot_telemetry_data_new.csv)");
        //Pide ruta de archivo a leer
        System.out.print("Dirección:");
        String n="";
        String rutaLeer = null;
        while(!n.equals("salir")){
            rutaLeer = scan.nextLine();
        if (a.validateRuta(rutaLeer)==true){
            n="salir";
        }else{n="";
            System.out.println("No se ha encontrado el archivo, vuelva ha intentarlo");};  
        }
        System.out.println("Espere un momento por favor...");
        //String rutaLeer = "src\\files\\iot_telemetry_data_new.csv";//Ruta para prueba Cedeño
        a.leerTxt(rutaLeer);
        List<String> datos = a.getData();
        FilterCollector filter = new FilterCollector();

        
        //En este apartado vamos a extraer la cabecera y a crear los objetos de dispositivos y sus observaciones
        //Crea una lista con todos los id_dispositivos para recorrer las observaciones
        DevGenerator dg = new DevGenerator();
        //Crea lista con las propiedades en la cabecera
        dg.propertiesExtractor(a.Linea(rutaLeer).split(","), cabecera);
        //Crea objetos a partir de la base de datos
        dg.createObject(datos, devices, filter, cabecera, rutaLeer);

        //Ruta en donde se creará el archivo (CAMBIAR SI ES NECESARIO)
        //String rutaWrite = "C:\\Users\\Walter Mix\\Documents\\MisNotificaciones.csv";

        
        //Se crea un objeto para inciar el menu del programa
        Main principal = new Main();
        principal.menu(cabecera, devices);

    }
}
