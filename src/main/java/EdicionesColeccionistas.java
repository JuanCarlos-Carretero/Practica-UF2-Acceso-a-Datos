import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="EdicionesColeccionistas")
public class EdicionesColeccionistas {
    List<Videojuego> datos;
    EdicionesColeccionistas(){
        datos = new ArrayList<>();
    }

    public List<Videojuego> getEC() {
        return datos;
    }

    @XmlElement(name = "VideoJuego")
    public void setVJ(List<Videojuego> datos) {
        this.datos = datos;
    }
}

