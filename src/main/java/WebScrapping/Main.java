package WebScrapping;

import java.util.Scanner;
/**
 * La clase WebScrapping.Main es la primera que se va a ejecutar.
 * @version 5.0, 30/01/22
 * @author Juan Carlos Carretero Roldan
 */
public class Main {
static Scanner scan = new Scanner(System.in);

  /**
   * Este metodo permite lanzar la app.
   */
  public static void main(String[] args) {
    WebScraping WS = new WebScraping();
    WS.run();
  }
}