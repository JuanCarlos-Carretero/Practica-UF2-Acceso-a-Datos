import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    File fileCSV = new File("src/Documents/opencsv.csv");
    File fileJAVAXB = new File("src/Documents/edicionColeccionistaList.xml");
    CSVWriterEx csv;
    Javaxb javaxb;

    System.out.println(System.getenv("PATH"));
    System.out.println(System.getenv("HOME"));
    // System.out.println(System.getenv(""));

    System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
    // File pathBinary = new File("src/main/resources/firefox");
    // FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);
    // DesiredCapabilities desired = new DesiredCapabilities();
    FirefoxOptions options = new FirefoxOptions();
    // desired.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options.setBinary(firefoxBinary));
    WebDriver driver = new FirefoxDriver(options);
    driver.get("https://www.game.es/buscar/edicion-coleccionista/o=7&cf=000a+:GIDSb_aa0453:8b,New:-6b&ca=0000000006:1:GIDS");

    // Espera a que la pagina carge y las cookies aparezcan
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    wait.until(ExpectedConditions.elementToBeClickable(By.id("btnOverlayCookiesClose")));

    // Busca y dale a click en las cookies
    WebElement cookies = driver.findElement(By.id("btnOverlayCookiesClose"));
    cookies.click();

    for (int i = 0; i < 250; i++) {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("window.scrollBy(0,1000)", "");
      wait.until(ExpectedConditions.elementToBeClickable(new By.ByClassName("img-responsive")));
    }

    List<WebElement> searchItemEdiciones = driver.findElements(new By.ByClassName("search-item"));
    List<EdicionColeccionista> edicionColeccionistas = new ArrayList<>();

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
        plataforma = searchItem.findElement(new By.ByClassName("info-wrap")).getText();

        System.out.println(nombre);
        System.out.println(tipo);
        System.out.println(precio);
        System.out.println(imagen);
        System.out.println(plataforma);

        edicionColeccionistas.add(new EdicionColeccionista(nombre, tipo, precio, imagen, plataforma));
        javaxb = new Javaxb(new EdicionColeccionista(nombre, tipo, precio, imagen, plataforma), fileJAVAXB);

      } catch(Exception e){
        System.out.println("ya no hay mas");
        System.out.println(searchItemEdiciones.size());
      }
      System.out.println();
    }
    //Escribo todo en un csv desde el metodo constructor
    csv = new CSVWriterEx(edicionColeccionistas, fileCSV);


  }
}