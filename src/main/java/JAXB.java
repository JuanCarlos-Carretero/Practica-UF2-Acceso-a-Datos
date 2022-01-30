import jakarta.xml.bind.*;

import java.io.File;
import java.util.List;

public class JAXB {
    EdicionesColeccionistas ec = new EdicionesColeccionistas();
    Videojuego vj;
    JAXBContext context;
    Marshaller marshaller;

    JAXB(List<Videojuego> edicionesColecionista, File file) {
        file.delete();
        try {
            context = JAXBContext.newInstance(EdicionesColeccionistas.class);

            for (Videojuego vj : edicionesColecionista) {
                this.vj = new Videojuego(vj.nombre, vj.precio, vj.tipo, vj.imagen, vj.plataforma);
                ec.addEC(this.vj);
            }

            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(ec, file);
            //jaxbMarshaller.marshal(datas, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}