import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    System.out.println(System.getenv("PATH"));
    System.out.println(System.getenv("HOME"));
    // System.out.println(System.getenv(""));

    System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
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

    //Juegos Switch
    //pedir ayuda profe no saber como usra los checkboxes
    WebElement switchfilter = driver.findElement(new By.ByTagName("input"));
    switchfilter.click();

    System.out.println("Juegos Switch");
    for (WebElement searchItemSwitch : searchItemEdiciones) {
      try {
      System.out.println(searchItemSwitch.findElement(new By.ByClassName("title")).getText());
      System.out.println(searchItemSwitch.findElement(new By.ByClassName("buy--type")).getText());
      System.out.println(searchItemSwitch.findElement(new By.ByClassName("buy--price")).getText());
      System.out.println(searchItemSwitch.findElement(new By.ByTagName("img")).getAttribute("src"));
      } catch(Exception e){
        System.out.println("ya no hay mas");
        System.out.println(searchItemEdiciones.size());
      }
      System.out.println();
    }
  }
}