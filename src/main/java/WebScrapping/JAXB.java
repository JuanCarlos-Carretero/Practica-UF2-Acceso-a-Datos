package WebScrapping;

import jakarta.xml.bind.*;

import java.io.File;
import java.util.List;

/**
 * Esta clase sirve para crear el XML.
 */
public class JAXB {
    EdicionesColeccionistas ec = new EdicionesColeccionistas();
    Videojuego vj;
    JAXBContext context;
    Marshaller marshaller;

    /**
     * Este metodo constructor sirve para crear el xml.
     * @param edicionesColecionista Aqui le pasamos la lista de Ediciones Coleccionista.
     * @param file Aqui le paso el file para que pueda borrar, escribir o crear el archivo donde le haya dicho.
     */
    JAXB(List<Videojuego> edicionesColecionista, File file) {
        file.delete();
        try {
            context = JAXBContext.newInstance(EdicionesColeccionistas.class);

            for (Videojuego videojuego : edicionesColecionista) {
                vj = new Videojuego(videojuego.nombre, videojuego.precio, videojuego.tipo, videojuego.imagen, videojuego.plataforma);
                ec.addEC(vj);
            }

            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(ec, file);
            //marshaller.marshal(datas, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}