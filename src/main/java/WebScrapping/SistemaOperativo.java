package WebScrapping;

import Util.Menu;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
/**
 * Esta clase permite elegir el sistema operativo desde el que trabajas.
 */
public class SistemaOperativo {
    /**
     * Este metodo permite elegir el sistema operativo desde el que trabajas.
     * @return Este devuelve el Webdriver con la info del geckodriver segun el sistema operativo.
     */
    public WebDriver elegirSO(){
        Menu menu = new Menu();
        WebDriver driver = null;
        String[] opcionesSO = {"Windows", "Linux"};
        String opcionSO = menu.elegirOpcion(opcionesSO);

        if ("1".equals(opcionSO)){
            System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);
            driver.get("https://www.game.es/buscar/edicion-coleccionista/o=7&cf=000a+:GIDSb_aa0453:8b,New:-6b&ca=0000000006:1:GIDS");

        } else if ("2".equals(opcionSO)){
            System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);
            driver.get("https://www.game.es/buscar/edicion-coleccionista/o=7&cf=000a+:GIDSb_aa0453:8b,New:-6b&ca=0000000006:1:GIDS");
        }
        return driver;
    }
}
