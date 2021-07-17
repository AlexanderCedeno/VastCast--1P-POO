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
    public List<User> users = new ArrayList<>();

    public void menu(List<String> cabecera, List<Device> devices) {
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
        System.out.println("******Se ha registrado su usuario correctamente *******");
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

    public void logIn(List<String> cabecera, List<Device> devices) {
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
                    programmNotify(usuario, cabecera);
                    enrollDevice(devices, usuario);
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
//debe aceptar si enrolla o no los dispositivos

    public void enrollDevice(List<Device> devices, User usuario) {
        System.out.println("");
        System.out.println("¿Desea registrar dispositivos en su cuenta? Y/N");
        String r = scan.nextLine();
        if (r.equals("Y")) {
            String n = "";
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
}
