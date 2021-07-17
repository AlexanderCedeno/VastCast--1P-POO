/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Settings;

import Classes.Device;
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
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Kevin Cedeño
 * @author Gustavo Castro
 * @author Kevin Valle
 */
public class Writer {
    
public void writeCsv(User u){
   String salidaArchivo = "C:\\Users\\Walter Mix\\Documents\\Prueba.csv"; // Nombre del archivo
        boolean existe = new File(salidaArchivo).exists(); // Verifica si existe
        
        // Si existe un archivo llamado asi lo borra
        if(existe) {
            File archivoUsuarios = new File(salidaArchivo);
            archivoUsuarios.delete();
        }
        
        try {
            // Crea el archivo
            PrintWriter salidaCSV = new PrintWriter(new FileWriter(salidaArchivo, true));
            if (u.getDevicesRoll().size()>0){
                for (Device d:u.getDevicesRoll()){
                
            }}
            // Datos para identificar las columnas
            salidaCSV.printf( "%-30s  %-30s  %-10s%n", "", "NOTIFICACIONES",  "" );
            salidaCSV.write(System.getProperty( "line.separator" ));
            salidaCSV.printf( "%-10s  %-1s%n ", "ID-Device:", "NOTIFICACIONES" );
            salidaCSV.write(System.getProperty( "line.separator" ));
            salidaCSV.write("Email ");
            
            salidaCSV.flush(); // Deja de escribir en el archivo
            
            // Recorremos la lista y lo insertamos en el archivo
            //for(Usuario user : usuarios) {
                salidaCSV.write("Carlos Julio");
                salidaCSV.write("099858562");
                salidaCSV.write("Gmail.oma");
                
                salidaCSV.flush(); // Deja de escribir en el archivo
           // }
            
            salidaCSV.close(); // Cierra el archivo
            
        } catch(IOException e) {
            e.printStackTrace();
        }      
}
    
    
public List<LocalDate> getRangeDates(LocalDate initDate, LocalDate endDate) {
    return initDate.datesUntil(endDate.plusDays(1)).collect(Collectors.toList());
}
    
}
