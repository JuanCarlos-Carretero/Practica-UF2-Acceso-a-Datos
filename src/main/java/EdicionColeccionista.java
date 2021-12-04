import java.io.Serializable;

public class EdicionColeccionista implements Serializable {
    String nombre;
    String precio;
    String tipo;
    String imagen;

    EdicionColeccionista(String nombre, String precio, String tipo, String imagen){
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.imagen = imagen;
    }
}
