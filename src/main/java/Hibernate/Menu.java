package Hibernate;

import javax.persistence.EntityManagerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;

/**
 * Esta clase sirve para mostrar menus
 */
public class Menu {
    private int option;
    private String opciones;

    /**
     * Esto es el constructor
     */
    public Menu() {
        super();
    }

    /**
     * Este metodo sirve para mostrar el menu cuando iniciamos
     *
     * @return devuelve la opcion que elegimos
     */
    public int mainMenu() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        do {

            System.out.println(" \nMENU PRINCIPAL \n");

            System.out.println("1. Rellenar Tablas");
            System.out.println("2. Crea un videojuego");
            System.out.println("3. Mostrar todos los videojuegos");
            System.out.println("4. Mostrar los videojuegos por precio");
            System.out.println("5. Borrar videojuegos por plataforma");
            System.out.println("6. Mostrar videojuegos por plataforma");
            System.out.println("7. Mostrar videojuegos por nombre");
            System.out.println("8. Salir");
            System.out.println("Escoge una opción: ");
            try {
                option = Integer.parseInt(br.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("valor no vàlid");
                e.printStackTrace();
            }
        } while (option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6 && option != 7 && option != 8);

        return option;
    }

    /**
     * Este metodo sirve para mostrar el menu de categoria
     *
     * @param c recibe la conexión
     * @return devuelve la categoria que elegiste
     */
    public String PlataformaMenu(Connection c, EntityManagerFactory entityManagerFactory) {
        PlataformaController plataformaController = new PlataformaController(c, entityManagerFactory);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (; ; ) {
            plataformaController.showPlataformas();
            /*System.out.println("Elige la categoria: ");*/
            try {
                opciones = br.readLine();
            } catch (NumberFormatException | IOException e) {
                System.out.println("valor no vàlid");
                e.printStackTrace();
            }
            return opciones;
        }
    }


}