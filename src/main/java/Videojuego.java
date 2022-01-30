import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

/**
 * Aqui es donde se guarda la info de las ediciones Coleccionistas.
 */
@XmlRootElement(name = "VideoJuego")
public class Videojuego implements Serializable {
    String nombre;
    String precio;
    String tipo;
    String imagen;
    String plataforma;

    /**
     * Este es el metodo constructor de la clase.
     * @param nombre aqui le paso el nombre del videojuego.
     * @param precio aqui le paso el precio del videojuego.
     * @param tipo aqui le paso el tipo de compra del videojuego.
     * @param imagen aqui le paso la imagen de imagen del videojuego.
     * @param plataforma aqui le paso la plataforma del videojuego.
     */
    Videojuego(String nombre, String precio, String tipo, String imagen, String plataforma){
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.imagen = imagen;
        this.plataforma = plataforma;
    }

    /**
     * Este es el metodo constructor vacio, ya que Marshaller lo requiere.
     */
    Videojuego(){

    }

    /**
     * Este metodo es un setter en el que cambio el nombre.
     * @param nombre Aqui cambio la info del nombre por la que le pase.
     */
    @XmlElement(name = "Nombre")
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * He creado este metodo ya que Marshaller lo requiere.
     * @return Retorna un nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Este metodo, es un setter en el que cambio el precio.
     * @param precio Aqui cambio la info del precio por la que le pase.
     */
    @XmlElement(name = "Precio")
    public void setPrecio(String precio) {
        this.precio = precio;
    }

    /**
     * He creado este metodo, ya que Marshaller lo requiere.
     * @return Retorna un precio.
     */
    public String getPrecio() {
        return precio;
    }

    /**
     * Este metodo es un setter en el que cambio el tipo.
     * @param tipo Aqui cambio la info del tipo por la que le pase.
     */
    @XmlElement(name = "Tipo")
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * He creado este metodo, ya que Marshaller lo requiere.
     * @return Retorna un tipo de compra.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Este metodo es un setter en el que cambio la imagen.
     * @param imagen Aqui cambio la info de la imagen por la que le pase.
     */
    @XmlElement(name = "Imagen")
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * He creado este metodo, ya que Marshaller lo requiere.
     * @return Retorna una imagen.
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Este metodo es un setter en el que cambio la plataforma.
     * @param plataforma  Aqui cambio la info de la plataforma por la que le pase.
     */
    @XmlElement(name = "Plataforma")
    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    /**
     * He creado este metodo, ya que Marshaller lo requiere.
     * @return Retorna una plataforma.
     */
    public String getPlataforma() {
        return plataforma;
    }
}
