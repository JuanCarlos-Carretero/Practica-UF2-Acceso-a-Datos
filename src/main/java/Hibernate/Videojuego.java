package Hibernate;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Esta clase sirve para reflejar el objeto de videojuego con su estructura
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "videojuego")
public class Videojuego implements Serializable {
    @Id
    @Column(name = "id", length = 256)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "nombre", length = 256)
    String nombre;
    @Column(name = "imagen", length = 256)
    String imagen;
    @Column(name = "precio", length = 20)
    String precio;
    @Column(name = "tipocompra", length = 256)
    String tipocompra;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "nombreplataforma")
    public Plataforma plataforma;

    /**
     * Sirve para coger la plataforma
     *
     * @return devuelve la plataforma
     */
    public Plataforma getPlataforma() {
        return plataforma;
    }

    /**
     * Sirve para asignar una plataforma
     *
     * @param plataforma recibe el que le asignamos a plataforma
     */
    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }

    /**
     * Sirve para coger un nombre
     *
     * @return devuelve el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sirve para asignar un nombre
     *
     * @param nombre recibe el nombre que le asignamos
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Sirve para coger una imagen
     *
     * @return devuelve la imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Sirve para asignar una imagen
     *
     * @param imagen recibe la imagen que le asignamos
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * Sirve para coger un precio
     *
     * @return devuelve el precio
     */
    public String getPrecio() {
        return precio;
    }

    /**
     * Sirve para asignar un precio
     *
     * @param precio recibe el precio que le asignamos
     */
    public void setPrecio(String precio) {
        this.precio = precio;
    }

    /**
     * Sirve para coger un tipodecompra
     *
     * @return devuelve el tipodecompra
     */
    public String getTipocompra() {
        return tipocompra;
    }

    /**
     * Sirve para asignar un tipocompra
     *
     * @param tipocompra recibe el tipocompra que le asignamos
     */
    public void setTipocompra(String tipocompra) {
        this.tipocompra = tipocompra;
    }

    /**
     * Sirve para coger un id
     *
     * @return devuelve el id
     */
    public int getId() {
        return id;
    }

    /**
     * Sirve para asignar un id
     *
     * @param id recibe el id que le asignamos
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Este metodo es el constructor de la clase
     *
     * @param plataforma recibe la plataforma
     * @param nombre     recibe el nombre
     * @param imagen     recibe la imagen
     * @param precio     recibe el precio
     * @param tipocompra recibe la tipodecompra
     */
    public Videojuego(String nombre, String precio, String tipocompra, String imagen, Plataforma plataforma) {
        super();
        this.plataforma = plataforma;
        this.nombre = nombre;
        this.imagen = imagen;
        this.precio = precio;
        this.tipocompra = tipocompra;
    }

    /**
     * Esto es un constructor
     */
    public Videojuego() {
    }

    /**
     * Este metodo sirve para indicar el formato en que lo va a mostrar
     *
     * @return los parametros que le asignamos
     */
    @Override
    public String toString() {
        return "Videojuego{" +
                "plataforma=" + plataforma + '\'' +
                "nombre=" + nombre + '\'' +
                "imagen=" + imagen + '\'' +
                "precio=" + precio + '\'' +
                "tipocompra=" + tipocompra + '\'' +
                '}';
    }
}