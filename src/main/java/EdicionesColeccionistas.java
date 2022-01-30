import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase la he creado para asi poder crear el XML mas facilmente, aqui es donde guardo la lista de Ediciones Coleccionista.
 */
@XmlRootElement(name="EdicionesColeccionistas")
public class EdicionesColeccionistas {
    List<Videojuego> videojuegos;
    /**
     * Este metodo constructor crea la lista.
     */
    EdicionesColeccionistas(){
        videojuegos = new ArrayList<>();
    }

    /**
     * Este metodo lo he creado por peticion del Marshaller.
     * @return Retorna una lista de videojuegos.
     */
    public List<Videojuego> getEC() {
        return videojuegos;
    }

    /**
     * Este metodo lo he creado por peticion del Marshaller.
     * @param vj Recibe e iguala unas listas de videojuegos.
     */
    @XmlElement(name = "VideoJuego")
    public void setEC(List<Videojuego> vj) {
        videojuegos = vj;
    }

    /**
     * Con este metodo añado un videojuego a la lista de ediciones coleccionista.
     * @param vj Aqui le paso un videojuego con su info.
     */
    public void addEC(Videojuego vj){
        videojuegos.add(vj);
    }
}

