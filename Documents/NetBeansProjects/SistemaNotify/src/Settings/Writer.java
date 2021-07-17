package Settings;

import Classes.Device;
import Classes.Observation;
import Classes.PopUpDev;
import Classes.PopUpObs;
import Classes.Propiedad;
import Classes.User;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kevin Cedeño
 * @author Gustavo Castro
 * @author Kevin Valle
 */
public class Writer {

    Scanner scan1 = new Scanner(System.in);

    //WriteCsv: metodo que creara un nuevo archivo y escribira las observaciones 
    public void writeCsv(User u, List<Device> devices, String ruta) throws ParseException {
        String rutaSalida = ruta; // Ruta y Nombre del archivo
        boolean ex = new File(rutaSalida).exists(); // Verifica si existe

        // Si existe un archivo llamado asi lo borra
        if (ex) {
            File archivoUsuarios = new File(rutaSalida);
            archivoUsuarios.delete();
        }

        try {
            //Lista con el rango de fechas
            List<LocalDate> date = rangeDates();

            // Crea el archivo
            PrintWriter line = new PrintWriter(new FileWriter(rutaSalida, true));

            line.printf("%-30s  %-30s  %-10s%n", "", "NOTIFICACIONES", "");
            line.printf("%-10s  %-1s%n ", "Nombre de Usuario:", u.getIDUser());
            line.write(System.getProperty("line.separator"));

            line.flush(); // Deja de escribir en el archivo
            if (u.getDevicesRoll().size() > 0) {
                for (Device d : u.getDevicesRoll()) {
                    line.write(System.getProperty("line.separator"));
                    line.printf("%-10s  %-1s%n ", "ID de dispositivo:", d.getDevice());
                    for (PopUpDev pop : u.getPopUpDev()) {
                        line.write(System.getProperty("line.separator"));
                        line.println("Observaciones para: " + pop.getLabel());
                        pop.registerDev(d);
                        if (pop.setPopUp().size() > 0) {
                            line.printf("%-30s**  %-30s**  %-10s%n", pop.getP(), pop.getP(), pop.getP());
                            filterDates(date, pop.setPopUp(), line);
                        } else {
                            line.println("NO SE HAN ENCONTRADO VALORES EN LOS RANGOS SOLICITADOS...");
                        }
                        pop.setD();
                    }

                }
                System.out.println("Archivo creado :):):):):):)::))");
                line.flush();
                line.close();
            } else if (u.getDevicesRoll().isEmpty()) {
                for (Device d : devices) {
                    line.write(System.getProperty("line.separator"));
                    line.printf("%-10s  %-1s%n ", "ID de dispositivo:", d.getDevice());
                    for (Propiedad p : d.getProperty()) {
                        u.getPopUpObs().stream().filter(Obs -> Obs.getLabel().equals(p.getNombre())).map(Obs -> Obs).forEach(Obs -> {
                            line.write(System.getProperty("line.separator"));
                            line.println("Observaciones para: " + Obs.getLabel());
                            Obs.addProp(p);
                            if (Obs.setPopUp().size() > 0) {
                                line.printf("%-30s**  %-30s**  %-10s%n", Obs.getPriority(), Obs.getPriority(), Obs.getPriority());
                                try {
                                    filterDates(date, Obs.setPopUp(), line);
                                } catch (ParseException ex1) {
                                    Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex1);
                                }
                            } else {
                                line.println("NO SE HAN ENCONTRADO VALORES EN LOS RANGOS SOLICITADOS...");
                            }
                            Obs.setProp();
                        });
                    }

                }
                System.out.println("Archivo creado :):):):):):)::))");
                line.flush();
                line.close();
            }

            line.flush();

            line.close(); // Cierra el archivo

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //GetRangeDates: metodo que arma una lista con los rangos en las fechas dadas
    public List<LocalDate> getRangeDates(LocalDate initDate, LocalDate endDate) {
        return initDate.datesUntil(endDate.plusDays(1)).collect(Collectors.toList());
    }

    //rangeDates: metodo que pide los rangos de fechas que serán ingresados.
    public List<LocalDate> rangeDates() throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Debe ingresar el rango de fechas con el siguiente formato dd/MM/yyyy");
        System.out.print("Ingrese la fecha inicial dd/MM/yyyy: ");
        Date initDate = format.parse(scan1.nextLine());
        System.out.print("Ingrese la fecha final dd/MM/yyyy: ");
        Date endDate = format.parse(scan1.nextLine());

        LocalDate end = initDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        List<LocalDate> dates = getRangeDates(end, endLocalDate);
        return dates;

    }

    //transformDate: metodo que transformara las fechas de las observaciones para fitlrarlas 
    public LocalDate transformDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date toTransform = format.parse(date);
        LocalDate newDate = toTransform.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return newDate;
    }

    //filterDates:metodo que filtra las fechas y escribe las coincidencias
    public void filterDates(List<LocalDate> date, List<Observation> pop, PrintWriter line) throws ParseException {
        for (LocalDate dates : date) {
            for (Observation o : pop) {

                if (dates.compareTo(transformDate(o.getDate().split(" ")[0])) == 0) {
                    line.printf("%-25s  %-10s  %-10s%n", o.getValue(), "Fecha: ", o.getDate());
                }
            }
        }
    }
}
