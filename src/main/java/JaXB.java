import jakarta.xml.bind.*;

import java.io.File;
import java.util.List;

public class JaXB {

    JaXB(String nombre, String precio, String tipo, String imagen, String plataforma, File file) {
        EdicionesColeccionistas ec ;
        Videojuego vj ;
        Marshaller marshaller;
        Unmarshaller unmarshaller;
        JAXBContext context;


        try {
            context = JAXBContext.newInstance(EdicionesColeccionistas.class);
            unmarshaller = context.createUnmarshaller();
            ec = (EdicionesColeccionistas) unmarshaller.unmarshal(file);

            vj = new Videojuego(nombre,precio,tipo,imagen,plataforma);
            List<Videojuego> serverList = ec.getEC();
            serverList.add(vj);
            ec.setVJ(serverList);

            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(ec, file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}