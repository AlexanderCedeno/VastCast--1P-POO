package Settings;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Archivo{
private List<String> data= new ArrayList<>();
public void leerTxt(String direccion){
String nombreArchivo = direccion;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(nombreArchivo);
			bufferedReader = new BufferedReader(fileReader);

			//StringBuilder stringBuilder = new StringBuilder("");
			String linea=bufferedReader.readLine();
			while ((linea = bufferedReader.readLine()) != null) {
				// Lee línea por línea, omitiendo los saltos de línea
				//stringBuilder.append(linea + "\n");
        data.add(linea.replace("\"", ""));
			}



		} catch (IOException e) {
			System.out.println("Excepción leyendo archivo: " + e.getMessage());
		} finally {
			try {
				if (fileReader != null)
					fileReader.close();
				if (bufferedReader != null)
					bufferedReader.close();
			} catch (IOException e) {
				System.out.println("Excepción cerrando: " + e.getMessage());
			}
		}
		}
public List<String> getData(){
  return data;
}

public List<String> IterO (List<String> l, String i){
  Iterator <String > it = l.iterator();
while (it.hasNext()) {
  String nombre = it.next();
  String no=nombre.split(",")[1];
  if (no.equals(i)) {
    it.remove();
  }

}  return l;
  }



}

