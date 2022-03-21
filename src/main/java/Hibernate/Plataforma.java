package Hibernate;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Esta clase sirve para reflejar el objeto de categoria con su estructura
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "plataforma")
public class Plataforma implements Serializable {
    @Id
    @Column(name = "nombreplataforma", length = 30)
    String plataforma;

    /**
     * Este es el constructor de la clase
     *
     * @param plataforma recibe la plataforma
     */
    public Plataforma(String plataforma) {
        super();
        this.plataforma = plataforma;
    }

    /**
     * Esto es un constructor
     */
    public Plataforma() {

    }

    /**
     * Esto es para coger la plataforma
     *
     * @return devuelve la plataforma
     */
    public String getPlataforma() {
        return plataforma;
    }

    /**
     * Sirve para asignar una plataforma
     *
     * @param plataforma recibe el que le ponemos a plataforma
     */
    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    /**
     * Este metodo sirve para indicar el formato en que lo va a mostrar
     *
     * @return los paramentros que le asignamos
     */
    @Override
    public String toString() {
        return "Plataforma{" +
                "plataforma= " + plataforma +
                '}';
    }
}
