import com.opencsv.CSVWriter;
import java.io.*;
import java.util.List;

public class CSVWriterEx {
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
