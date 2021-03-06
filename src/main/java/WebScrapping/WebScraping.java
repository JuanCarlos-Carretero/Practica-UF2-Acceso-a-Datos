package WebScrapping;

import Util.Mensaje;
import Util.Titulo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase hace el WebScrapping.
 */
public class WebScraping implements Runnable {
    Titulo titulo = new Titulo();
    SistemaOperativo so = new SistemaOperativo();
    JavaScripts JS = new JavaScripts();
    Mensaje msn = new Mensaje();

    CSVWriterEx csv;
    JAXB jaxb;

    File fileCSV = new File("src/Documents/opencsv.csv");
    File fileJAXB = new File("src/Documents/edicionColeccionistaList.xml");

    /**
     * Este metodo es llamado en la clase WebScrapping.InicioApp.Main dentro del metodo main para iniciar la app.
     */
    @Override
    public void run() {
        titulo.mostrar("WebScrapping.WebScraping Juanka");
        msn.mostrarInfo("Esta app necesita del navegador Mozilla Firefox si no lo tiene debera descargarlo.");

        // Aqui elijo cual sistema operativo estoy usando
        System.out.println("¿Cual es tu Sistema Operativo(SO)?");
        WebDriver driver = so.elegirSO();

        // Aqui imprime la ruta del pc y la del usuario
        System.out.println(System.getenv("PATH"));
        System.out.println(System.getenv("HOME"));

        // Espera a que la pagina carge y las cookies aparezcan
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOverlayCookiesClose")));

        // Busca y dale a click en cerrar en las cookies
        WebElement cookies = driver.findElement(By.id("btnOverlayCookiesClose"));
        cookies.click();

        // Hago scroll en el navegador
        JS.scrollWindow(250, driver, wait);

        List<WebElement> searchItemEdiciones = driver.findElements(new By.ByClassName("search-item"));
        List<Videojuego> edicionColeccionistas = new ArrayList<>();

        System.out.println("Todas las Ediciones");
        String nombre;
        String tipo;
        String precio;
        String imagen;
        String plataforma;

        for (WebElement searchItem : searchItemEdiciones) {

            try {
                nombre = searchItem.findElement(new By.ByClassName("title")).getText();
                tipo = searchItem.findElement(new By.ByClassName("buy--type")).getText();
                precio = searchItem.findElement(new By.ByClassName("buy--price")).getText();
                imagen = searchItem.findElement(new By.ByTagName("img")).getAttribute("src");
                plataforma = searchItem.findElement(new By.ByClassName("btn-sm")).getText();

                System.out.println(nombre);
                System.out.println(precio);
                System.out.println(tipo);
                System.out.println(imagen);
                System.out.println(plataforma);

                Videojuego ec = new Videojuego();
                ec.setNombre(nombre);
                ec.setPrecio(precio);
                ec.setTipo(tipo);
                ec.setImagen(imagen);
                ec.setPlataforma(plataforma);

                edicionColeccionistas.add(ec);


            } catch(Exception e){
                System.out.println("Hay " + searchItemEdiciones.size() + " Ediciones Coleccionistas.");
            }
            System.out.println();
        }
        // Escribo todo en un csv desde el metodo constructor
        csv = new CSVWriterEx(edicionColeccionistas, fileCSV);

        //Recorro la lista y escribo en un xml
        jaxb = new JAXB(edicionColeccionistas, fileJAXB);

        driver.close();
    }
}
