package WebScrapping;

import com.opencsv.CSVWriter;
import java.io.*;
import java.util.List;

/**
 * Esta clase sirve para crear el csv.
 */
public class CSVWriterEx {
    /**
     * Este metodo constructor sirve para crear el csv
     * @param edicionColeccionistas Recibe una lista con la cual va a trabajar para escribir en el csv
     * @param file Recibe un file con el cual va a trabajar a la hora de borrar el archivo o a la g¡hora de crear/escribir dentro de el.
     */
    CSVWriterEx(List<Videojuego> edicionColeccionistas, File file){
        CSVWriter csvWriter;
        String[] infoJuegos =  new String[5];
        file.delete();
        for (Videojuego ec: edicionColeccionistas) {
            infoJuegos[0] = ec.nombre;
            infoJuegos[1] = ec.precio;
            infoJuegos[2] = ec.tipo;
            infoJuegos[3] = ec.imagen;
            infoJuegos[4] = ec.plataforma;

            try {
                csvWriter = new CSVWriter(new FileWriter(file, true));
                csvWriter.writeNext(infoJuegos);
                csvWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Ruta del archivo " + file.getPath());
    }
}
