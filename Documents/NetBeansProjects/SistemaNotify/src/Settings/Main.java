/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Settings;

import Classes.Device;
import Classes.PopUpDev;
import Classes.PopUpObs;
import Classes.User;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Kevin Valle
 * @author Kevin Cedeno
 * @author Gustavo Castro
 */
public class Main {

    public Scanner scan = new Scanner(System.in);
    public List<User> users = new ArrayList<>();
    Writer writeCSV = new Writer();
    /**
     * metodo creado para inicializar el menu principal del programa
     * @param cabecera Cabecera que recibe el metodo
     * @param devices Lista de Dispositivos que recibe el metodo
     * @throws ParseException Usado para lanzar excepciones
     */
    public void menu(List<String> cabecera, List<Device> devices) throws ParseException {
        String opcion = "";
        while (!opcion.equals("3")) {
            System.out.println("");
            System.out.println("Sistema de notificación");
            System.out.println("1.-Registrar Usuario");
            System.out.println("2.-Iniciar Sesion");
            System.out.println("3.-Salir");

            System.out.print("Opcion:");
            opcion = scan.nextLine();
            System.out.println("");
            switch (opcion) {
                case "1":
                    registerUser();
                    break;
                case "2":
                    if (users.size() > 0) {
                        logIn(cabecera, devices);
                    } else {
                        System.out.println("Lo sentimos, no hay usuarios registrados");
                    }
                    break;

            }
        }
    }
    /**
     * metodo que valida si existen los dispisitivos ingresados por el usuario
     * @param devices Lista de dispositivos que recibe el metodo
     * @return Device Objeto de tipo Device
     **/
    public Device inputDevice(List<Device> devices) {
        System.out.print("Ingrese su dispositivo :");
        String device = scan.nextLine();

        for (Device dev : devices) {
            if (dev.getDevice().equals(device)) {
                return dev;
            }
        }
        return null;
    }
    /**
     * metodo que valida si la propiedad observable requerida existe
     * @param cabecera Lista de string donde esta almacenada la cabecera
     * @return String Propiedad que exista en el documento
     **/
    public String inputProperty(List<String> cabecera) {
        System.out.print("Ingrese etiqueta de Propiedad. ('co','humidity', 'light', 'lpg', 'motion', 'smoke', 'temp )");
        String label = scan.nextLine();

        for (String p : cabecera) {
            if (p.equals(label)) {
                return p;
            }
        }
        return null;
    }
    /**
     * metodo que permite registrar a los usuarios
     **/
    public void registerUser() {
        System.out.print("Ingrese id de usuario :");
        String userID = scan.nextLine();

        users.add(new User(userID));
        System.out.println("******Se ha registrado su usuario correctamente *******");
    }
    /**
     * metodo que valida si existe el nombre de usuario
     * @return User Usuario que retorna el metodo
     **/
    public User enterUser() {
        System.out.print("Ingrese su nombre de usuario: ");
        String nameUser = scan.nextLine();

        for (User u : users) {
            if (u.getIDUser().equals(nameUser)) {
                return u;
            }
        }
        return null;
    }
    /**
     * metodo para iniciar sesion
     * @param cabecera Lista de string donde esta la cabecera del documento
     * @param devices Lista de dispositivos que recibe el metodo
     * @throws ParseException Usado para lanzar excepciones
     **/
    public void logIn(List<String> cabecera, List<Device> devices) throws ParseException {
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
            System.out.println("");
            System.out.println("BIENVENIDO DE VUELTA..." + usuario.getIDUser());
            System.out.println("1.-Programar notificacion");
            System.out.println("2.-Generar notificaciones");
            System.out.println("3.-Desactivar notificaciones.");
            System.out.println("4.-Cerrar sesion");

            System.out.println("");
            System.out.print("Opcion:");
            opcion = scan.nextLine();
            System.out.println("");
            switch (opcion) {
                case "1":
                    programmPop(usuario, cabecera, devices);
                    break;
                case "2":
                    generatePopUp(usuario, devices);
                    break;
                case "3":
                    unablePopUp(usuario);
                    break;

            }
        }
    }
    /**
     * metodo que permite al usuario configurar las notificaiones por observacion
     * @param u Usuario que recibe el metodo
     * @param cabecera Lista de String donde esta la cabecera del documento
     **/
    public void setPop(User u, List<String> cabecera) {
        System.out.println("");
        System.out.println("Notificaciones para el usuario: " + u.getIDUser());
        System.out.println("");
        String n = "";
        while (!n.equals("N")) {

            String evaluator = "";
            while (!evaluator.equals("salida")) {
                String label = inputProperty(cabecera);
                if (label != null) {
                    u.createPopUp(label);
                    System.out.println("Se creo su configuración para: " + label);
                    evaluator = "salida";
                    String put = "";
                    while (!put.equals("N")) {
                        System.out.print("¿Desea configurar otra notificacion? Y/N:");
                        put = scan.nextLine();
                        if (put.equals("Y")) {
                            put = "N";
                            n = "no";
                        } else if (put.equals("N")) {
                            n = "N";
                        } else {
                            System.out.println("Entrada equivocada...");
                        }
                    }
                } else {

                    evaluator = "no";
                    System.out.println("No existe la propiedad. Vuelva a intentarlo ");
                }
            }

        }
    }
    /**
     * metodo que permite al usuario enrolarse a un dispositivo
     * @param devices Lista de Dispositivos
     * @param usuario Usuario que recibe el metodo
     **/
    public void enrollDevice(List<Device> devices, User usuario) {
        System.out.println("");
        System.out.println("¿Desea registrar dispositivos en su cuenta? Y/N");
        String r = scan.nextLine();
        if (r.equals("Y")) {
            String n = "";
            usuario.createPopUpDev();
            while (!n.equals("N")) {
                String evaluator = "";
                while (!evaluator.equals("salir")) {
                    Device dev = inputDevice(devices);
                    if (dev != null) {
                        usuario.enrollDev(dev);
                        evaluator = "salir";
                        String put = "";
                        while (!put.equals("N")) {
                            System.out.print("¿Desea configurar otro Dispositivo? Y/N:");
                            put = scan.nextLine();
                            if (put.equals("Y")) {
                                put = "N";
                                n = "no";
                            } else if (put.equals("N")) {
                                n = "N";
                            } else {
                                System.out.println("Entrada no valida...");
                            }
                        }
                    } else {
                        evaluator = "no";
                        System.out.println("No existe el dispositivo. Vuelva a intentarlo ");
                    }
                }
            }
        } else {
            System.out.println("De acuerdo...No ha registrado ningun dispositivo");
        }
    }
    /**
     * metodo crea el archivo y escribe sobre el(Llama al metodo WriteCsv de la clase Writer)
     * @param usuario Usuario que recibe el metodo
     * @param devices Lista de dispositivos que recibe el metodo
     * @param rutaWrite Ruta donde se almacenara o escribira el archivo
     * @throws ParseException Usado para lanzar excepciones
     **/
    public void generatePopUp(User usuario, List<Device> devices) throws ParseException {
        if (usuario.getPopUpObs().size() > 0) {
            System.out.println("Escriba la DIRECCION en la que desea guardar su archivo y NOMBRE del mismo"+'\n'+ "(Ejemplo:C:\\Users\\Walter Mix\\Documents\\Notificacion.csv)");
            
            String ruta=scan.nextLine();
            writeCSV.writeCsv(usuario, devices, ruta);
        } else {
            System.out.println("NO HA CONFIGURADO NOTIFICACIONES AÚN...");
        }
    }
    /**
     * metodo que permite configurar las notificaciones de acuerdo a su tipo(Por observacion o por Dispositivo)
     * @param u Usuario que recibe el metodo
     * @param cabecera Lista de string donde esta la cabecera del documento
     * @param devices Lista de dispositivos que recibe el metodo
     **/
    public void programmPop(User u, List<String> cabecera, List<Device> devices) {
        String opcion = "";
        while (!opcion.equals("3")) {
            System.out.println("");
            System.out.println("***Elija el tipo de notificacion**");
            System.out.println("1.-Notificacion por Observacion");
            System.out.println("2.-Notificaion por dispositivo");
            System.out.println("3.-Ir atras");

            System.out.println("");
            System.out.print("Opcion:");
            opcion = scan.nextLine();
            System.out.println("");
            switch (opcion) {
                case "1":

                    setPop(u, cabecera);

                    break;
                case "2":
                    if (u.getPopUpObs().size() > 0) {

                        enrollDevice(devices, u);

                    } else {
                        System.out.print("Debe configurar notificaciones por observacion antes: ");
                    }
                    break;

            }
        }
    }
    /**
     * metodo creado para desactivar las notificaciones
     * @param u Usuario que recibe el metodo
     **/
    public void unablePopUp(User u) {
        String opcion = "";
        while (!opcion.equals("2")) {
            System.out.println("");
            System.out.println("***Menu desactivar notificacion**");
            System.out.println("1.-Notificacion por Observacion");
            System.out.println("2.-Ir atras");

            System.out.println("");
            System.out.print("Opcion:");
            opcion = scan.nextLine();
            System.out.println("");
            switch (opcion) {
                case "1":
                    if (u.getPopUpObs().size() > 0) {
                        System.out.println("Ud cuenta con las siguientes notificaciones:");
                        for (PopUpObs obs : u.getPopUpObs()) {
                            System.out.println(obs.getLabel());
                        }
                        System.out.println("Escriba la etiqueta de la notificacion que desea desactivar:");
                        String input = scan.nextLine();
                        for (PopUpObs obs : u.getPopUpObs()) {
                            if (obs.getLabel().equals(input)) {
                                obs.setState(false);
                            } else {
                                System.out.println("Ya se ha desactivado la notificacion o ha ingresado mal la etiqueta");
                            }
                        }
                        System.out.println("Por favor vuelva a generar las notificaciones.");
                        opcion = "2";
                    } else {
                        System.out.print("No cuenta con notificaciones activas");
                    }

                    break;

            }
        }
    }
}
