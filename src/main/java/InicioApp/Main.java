package InicioApp;

import Hibernate.Hibernate;
import JDBC.JDBC;
import Util.Menu;
import Util.Titulo;
import Util.Util;
import WebScrapping.Scrapping;

/**
 * La clase InicioApp.Main es la primera que se va a ejecutar.
 *
 * @author Juan Carlos Carretero Roldan
 * @version 5.0, 30/01/22
 */
public class Main {
    public static void main(String[] args) {

        Util util = new Util();

        Titulo titulo = new Titulo();
        titulo.mostrar("Menu Principal");

        Menu menu = new Menu();
        String[] opciones = {"WebScrapping", "JDBC", "Hibernate", "Salir"};
        int opcion = Integer.parseInt(menu.elegirOpcion(opciones));

        switch(opcion){
            case 1:
                Scrapping scrapping = new Scrapping();
                util.saltoLineaX(20);
                scrapping.run();
                break;
            case 2:
                JDBC jdbc = new JDBC();
                util.saltoLineaX(20);
                jdbc.run();
                break;
            case 3:
                Hibernate hibernate = new Hibernate();
                util.saltoLineaX(20);
                hibernate.run();
                break;
            case 4:
                System.exit(0);
        }
    }
}
