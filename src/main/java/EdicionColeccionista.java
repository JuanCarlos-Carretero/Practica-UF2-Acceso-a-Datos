import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
@XmlRootElement
public class EdicionColeccionista implements Serializable {
    String nombre;
    String precio;
    String tipo;
    String imagen;
    String plataforma;

    @XmlElement(name = "Nombre")
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @XmlElement(name = "Precio")
    public void setPrecio(String precio) {
        this.precio = precio;
    }
    @XmlElement(name = "Tipo")
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    @XmlElement(name = "Imagen")
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    @XmlElement(name = "Plataforma")
    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    EdicionColeccionista(String nombre, String precio, String tipo, String imagen, String plataforma){
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.imagen = imagen;
        this.plataforma = plataforma;
    }
}
