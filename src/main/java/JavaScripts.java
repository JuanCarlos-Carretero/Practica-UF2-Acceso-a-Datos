import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * En esta clase tenemos los JavaScripts que utilizamos en las demas clases. (En este caso solo uso uno)
 */
public class JavaScripts {
    /**
     * Este metodo sirve para hacer scroll en la pagina generada.
     * @param veces le decimos las veces que hay que hacer scroll.
     * @param driver le hacemos llegar la variable driver de la clase WebScraping.
     * @param wait le hacemos llegar la variable wait de la clase WebScraping.
     * En el script tenemos window.scrollBy(0,1000) y los numeros que hay dentro son el movimiento que va a hacer por cada variable veces.
     */
    public void scrollWindow(int veces, WebDriver driver, WebDriverWait wait){
        for (int i = 0; i < veces; i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,1000)", "");
            wait.until(ExpectedConditions.elementToBeClickable(new By.ByClassName("img-responsive")));
        }
    }
}
