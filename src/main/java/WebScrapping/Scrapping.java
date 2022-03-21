package WebScrapping;

import java.util.Scanner;
/**
 * Esta clase sirve para runear el scrapeo.
 */
public class Scrapping implements Runnable {
static Scanner scan = new Scanner(System.in);

  /**
   * Este metodo permite lanzar el scrapeo.
   */
  @Override
  public void run() {
    WebScraping WS = new WebScraping();
    WS.run();
  }
}