import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class Registro {
    String ruta = "src/EdicionesColeccionistaGame.csv";
    File file = new File(ruta);
    public void guardarTodosLosDatos(List<EdicionColeccionista> edicionesColeccionista) {
        file.delete();
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));

            for (EdicionColeccionista edicionColeccionista: edicionesColeccionista) {
                try {
                    oos.writeObject((EdicionColeccionista) (edicionColeccionista));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Se han guardado los documentos en " + ruta);
    }
}
