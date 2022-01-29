import jakarta.xml.bind.*;

import java.io.File;

public class Javaxb {

    Javaxb(EdicionColeccionista edicionColeccionista, File file) {
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(EdicionColeccionista.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        Marshaller jaxbMarshaller = null;
        try {
            jaxbMarshaller = jaxbContext.createMarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        try {
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (PropertyException e) {
            e.printStackTrace();
        }

        try {
            jaxbMarshaller.marshal(edicionColeccionista, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        try {
            jaxbMarshaller.marshal(edicionColeccionista, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}