/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Settings;

import Classes.Device;
import Classes.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Walter Mix
 */
public class Main {

    public Scanner scan = new Scanner(System.in);
    public static List<User> users = new ArrayList<>();

    public void menu(List<String> cabecera) {
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
                        logIn(cabecera);
                    } else {
                        System.out.println("Lo sentimos, no hay usuarios registrados");
                    }
                    break;

            }
        }
    }

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

    public void registerUser() {
        System.out.print("Ingrese id de usuario :");
        String userID = scan.nextLine();

        users.add(new User(userID));
    }

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

    public void logIn(List<String> cabecera) {
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
                    programmNotify(usuario, cabecera);
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

    public void programmNotify(User u, List<String> cabecera) {

        System.out.println("Notificaciones para el usuario: " + u.getIDUser());

        String n = "";
        while (!n.equals("N")) {

            String evaluator = "";
            while (!evaluator.equals("salida")) {
                String label = inputProperty(cabecera);
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

    public void enrollDevice(List<Device> devices, User usuario) {
        String n = "";
        while (!n.equals("N")) {
            String evaluator = "";
            while (!evaluator.equals("salir")) {
                Device dev = inputDevice(devices);
                if (dev != null) {
                    usuario.enrollDev(dev);
                    evaluator = "salir";
                    System.out.print("¿Desea configurar otro Dispositivo? Y/N:");
                    n = scan.nextLine();
                } else {
                    evaluator = "no";
                    System.out.println("No existe el dispositivo. Vuelva a intentarlo ");
                }
            }
        }
    }
}
