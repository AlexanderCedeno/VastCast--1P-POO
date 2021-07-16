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

    public void createPopUp(String l) {
        Scanner sc = new Scanner(System.in);
        if (!l.equals("light") && !l.equals("motion")) {
            String opcion = "";
            while (!opcion.equals("1") || !opcion.equals("2") || !opcion.equals("3")) {
                System.out.println("Elija para que rangos desea configurar:");
                System.out.println("1.-Para rangos: ValorObservacion > vMax");
                System.out.println("2.Para rangos entre: vMin>ValorObservacion<vMax");
                System.out.println("3.-Para rangos: ValorObservacion < vMin");

                System.out.print("Opcion:");
                opcion = sc.nextLine();
                String label = "";
                double vMax = 0;
                double vMin = 0;
                switch (opcion) {

                    case "1":
                        System.out.print("Escriba una etiqueta(Ejmplo: Peligro): ");
                        label = sc.nextLine();
                        System.out.print("Escriba el valor máximo: ");
                        vMax = sc.nextInt();
                        xObs.add(new PopUpObs(l, label, vMax, opcion));
                        break;
                    case "2":
                        System.out.print("Escriba una etiqueta(Ejmplo: Moderado): ");
                        label = sc.nextLine();
                        System.out.print("Escriba el valor máximo: ");
                        vMax = sc.nextDouble();
                        System.out.print("Escriba el valor mínimo: ");
                        vMin = sc.nextDouble();
                        xObs.add(new PopUpObs(l, label, vMax, vMin, opcion));
                        break;
                    case "3":
                        System.out.print("Escriba una etiqueta(Ejmplo: Controlado): ");
                        label = sc.nextLine();
                        System.out.print("Escriba el valor mínimo: ");
                        vMin = sc.nextInt();
                        xObs.add(new PopUpObs(l, label, vMin, opcion));
                        break;

                }
            }

        }
    }
}
