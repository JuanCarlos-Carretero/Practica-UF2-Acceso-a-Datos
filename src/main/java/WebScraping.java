import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WebScraping {
    WebScraping(){
        JavaScripts JS = new JavaScripts();
        File fileCSV = new File("src/Documents/opencsv.csv");
        File fileJAXB = new File("src/Documents/edicionColeccionistaList.xml");
        Mensaje msn = new Mensaje();
        CSVWriterEx csv;
        JAXB jaxb;

        msn.mostrarInfo("Esta app necesita del navegador Mozilla Firefox si no lo tiene debera descargarlo.");
        System.out.println();

        //Aqui elijo cual sistema operativo estoy usando
        SistemaOperativo so = new SistemaOperativo();
        System.out.println("¿Cual es tu Sistema Operativo(SO)?");
        WebDriver driver = so.elegirSO();

        System.out.println(System.getenv("PATH"));
        System.out.println(System.getenv("HOME"));

        // Espera a que la pagina carge y las cookies aparezcan
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOverlayCookiesClose")));

        // Busca y dale a click en las cookies
        WebElement cookies = driver.findElement(By.id("btnOverlayCookiesClose"));
        cookies.click();

        JS.scrollWindow(250, driver, wait);

        List<WebElement> searchItemEdiciones = driver.findElements(new By.ByClassName("search-item"));
        List<Videojuego> edicionColeccionistas = new ArrayList<>();

        System.out.println("Todas las Ediciones");
        String imagen;
        String nombre;
        String tipo;
        String precio;
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
        //Escribo todo en un csv desde el metodo constructor
        csv = new CSVWriterEx(edicionColeccionistas, fileCSV);

        //Recorro la lista y escribo en un xml
        jaxb = new JAXB(edicionColeccionistas, fileJAXB);

        driver.close();
    }
}
