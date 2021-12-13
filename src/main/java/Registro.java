import java.io.*;
import java.util.List;

public class Registro {
    String ruta = "src/EdicionesColeccionistaGame.csv";
    File file = new File(ruta);
    public void guardarTodosLosDatos(List<EdicionColeccionista> edicionesColeccionista) {
        file.delete();
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(file));

            for (EdicionColeccionista edicionColeccionista: edicionesColeccionista) {
                try {
                    bw.write(edicionColeccionista.nombre + " - " + edicionColeccionista.precio + " - " + edicionColeccionista.tipo + " - " + edicionColeccionista.imagen + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Se han guardado los documentos en " + ruta);
    }
}
