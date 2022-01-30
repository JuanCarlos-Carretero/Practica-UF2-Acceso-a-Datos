import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
@XmlRootElement(name = "VideoJuego")
public class Videojuego implements Serializable {
    String nombre;
    String precio;
    String tipo;
    String imagen;
    String plataforma;

    Videojuego(String nombre, String precio, String tipo, String imagen, String plataforma){
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.imagen = imagen;
        this.plataforma = plataforma;
    }

    Videojuego(){

    }

    @XmlElement(name = "Nombre")
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }

    @XmlElement(name = "Precio")
    public void setPrecio(String precio) {
        this.precio = precio;
    }
    public String getPrecio() {
        return precio;
    }

    @XmlElement(name = "Tipo")
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getTipo() {
        return tipo;
    }

    @XmlElement(name = "Imagen")
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    public String getImagen() {
        return imagen;
    }

    @XmlElement(name = "Plataforma")
    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }
    public String getPlataforma() {
        return plataforma;
    }
}
