package Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Kevin Valle
 * @author Kevin Cedeno
 * @author Gustavo Castro
 */
public class User {

    private List<Device> devices;
    private List<PopUpObs> xObs;
    private List<PopUpDev> xDev;
    private String id_User;

    public User(String id) {
        id_User = id;
        devices = new ArrayList<>();
        xObs = new ArrayList<>();
        xDev = new ArrayList<>();
    }

    public String getIDUser() {
        return id_User;
    }

    public List<Device> getDevicesRoll() {
        return devices;
    }

    public List<PopUpObs> getPopUpObs() {
        return xObs;
    }

    public List<PopUpDev> getPopUpDev() {
        return xDev;
    }

    public void enrollDev(Device d) {
        devices.add(d);
    }
    
    public void createPopUpDev(){
       
         for (PopUpObs obs:xObs){
                PopUpDev clasico=new PopUpDev(obs.getLabel(), obs.getPriority());            
                clasico.use_xObs(obs);
                xDev.add(clasico);
                }         
    }
    
    public void createPopUp(String l) {
        Scanner sc = new Scanner(System.in);
        if (!l.equals("light") && !l.equals("motion")) {
            String opcion = "";
            while (!opcion.equals("salida")) {
                System.out.println("Elija para que rangos desea configurar:");
                System.out.println("1.-Para rangos: ValorObservacion > vMax");
                System.out.println("2.Para rangos entre: vMin>ValorObservacion<vMax");
                System.out.println("3.-Para rangos: ValorObservacion < vMin");

                System.out.print("Opcion:");
                opcion = sc.nextLine();
                String priority = "";
                double vMax;
                double vMin = 0;
                switch (opcion) {

                    case "1":
                        System.out.print("ESCRIBA UNA PRIORIDAD(Ejmplo: Peligro): ");
                        priority = sc.nextLine();
                        System.out.print("Escriba el valor máximo: ");
                        vMax = sc.nextDouble();
                        xObs.add(new PopUpObs(l, priority, vMax, opcion));
                        opcion="salida";
                        break;
                    case "2":
                        System.out.print("ESCRIBA UNA PRIORIDAD(Ejmplo: Moderado): ");
                        priority = sc.nextLine();
                        System.out.print("Escriba el valor máximo: ");
                        vMax = sc.nextDouble();
                        System.out.print("Escriba el valor mínimo: ");
                        vMin = sc.nextDouble();
                        xObs.add(new PopUpObs(l, priority, vMax, vMin, opcion));
                        opcion="salida";
                        break;
                    case "3":
                        System.out.print("ESCRIBA UNA PRIORIDAD(Ejmplo: Controlado): ");
                        priority = sc.nextLine();
                        System.out.print("Escriba el valor mínimo: ");
                        vMin = sc.nextDouble();
                        xObs.add(new PopUpObs(l, priority, vMin, opcion));
                        opcion="salida";
                        break;

                }
            }

        }else if (l.equals("light")||l.equals("motion")){
            String opcion = "";
            while (!opcion.equals("salida")) {
                System.out.println("Elija la opción que desea configurar(t)o(f):");
                System.out.println("Escriba la letra t para las observaciones que sean TRUE");
                System.out.println("Escriba la letra f Para las observaciones que sean FALSE");


                System.out.print("Opcion:");
                opcion = sc.nextLine();
                String priority = "";
                switch (opcion) {

                    case "t":
                        System.out.print("Escriba una Prioridad(Ejmplo: Peligro, Controlado, etc): ");
                        priority = sc.nextLine();
                        xObs.add(new PopUpObs(l, priority, opcion));
                        opcion="salida";
                        break;
                    case "f":
                        System.out.print("Escriba una Prioridad(Ejmplo: Controlado, Moderado, etc): ");
                        priority = sc.nextLine();
                        xObs.add(new PopUpObs(l, priority, opcion));
                        opcion="salida";
                        break;
                }
            }
        }
    }
}
