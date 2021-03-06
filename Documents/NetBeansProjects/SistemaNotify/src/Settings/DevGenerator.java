/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Settings;

import java.util.ArrayList;
import java.util.List;
import Classes.Device;
import Classes.Propiedad;
import Classes.Observation;
import Settings.Archivo;

/**
 * @author Kevin Valle
 * @author Kevin Cedeno
 * @author Gustavo Castro
 */
public class DevGenerator {
    /**
     * metodo que crea los Dispositivos a partir de los datos dados en el csv
     * @param datos Lista de datos que recibe el metodo
     * @param devices Lista de dispositivos que recibe el metodo
     * @param filter Filtro que recibe el metodo
     * @param cabecera Lista que almacena los string de la cabecera del archivo
     * @param ruta Ruta o direccion donde se encuentra almacenado el documento
     **/
    public void createObject(List<String> datos, List<Device> devices, FilterCollector filter, List<String> cabecera, String ruta) {
        List<String> idDev = new ArrayList<>();
        idDev = filter.collectorId(datos);

        //Filtra las opciones dentro del csv y crea los dispositivos con sus respectivas propiedades y observaciones
        for (String dataID : idDev) {
            Device deviceN = new Device(dataID);
            for (String labels : cabecera) {
                Archivo a1 = new Archivo();
                a1.leerTxt(ruta);
                List<String> datas = a1.getData();
                int index = filter.searchLabel(cabecera, labels);
                Propiedad property = new Propiedad(labels);
                deviceN.evaluarProp(property);

                datas.stream().filter(id -> id.split(",")[1].equals(dataID)).map(id -> id).forEach(id -> {

                    Observation ob = new Observation(id.split(",")[index], id.split(",")[9]);
                    property.realizarObs(ob);
                });
                a1.IterO(datas, dataID);
            }
            devices.add(deviceN);
        }
    }
    /**
     * metodo que extrae solo las propiedades a partir de la cabecera
     * @param linea Array de string donde se almacena la linea del documento
     * @param cabecera Lista donde se almacena la cabecera del documento para remover los elementos
     **/
    //propertiesExtractor: 
    public void propertiesExtractor(String[] linea, List<String> cabecera) {
        for (String cab : linea) {
            cabecera.add(cab);
        }
        cabecera.remove("device");
        cabecera.remove("FECHA ");
        cabecera.remove("NO TOMAR EN CUENTA  ");

    }
}
