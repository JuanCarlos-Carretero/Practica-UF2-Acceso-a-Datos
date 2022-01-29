import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JavaScripts {
    public void scrollWindow(int veces, WebDriver driver, WebDriverWait wait){
        for (int i = 0; i < veces; i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,1000)", "");
            wait.until(ExpectedConditions.elementToBeClickable(new By.ByClassName("img-responsive")));
        }
    }
}
