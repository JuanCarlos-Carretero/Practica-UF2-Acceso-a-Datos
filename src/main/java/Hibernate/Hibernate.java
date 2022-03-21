package Hibernate;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Esta clase sirve para runear el hibernate
 */
public class Hibernate implements Runnable {

    /**
     * Este metodo sirve para crear el Manager de Entity que tenemos anotado en las clases
     *
     * @return
     */
    public static EntityManagerFactory createEntityManagerFactory() {
        EntityManagerFactory emf;
        try {
            emf = Persistence.createEntityManagerFactory("ColectorEditionDDBB");
        } catch (Throwable ex) {
            System.err.println("Failed to create EntityManagerFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        return emf;
    }

    /**
     * Este metodo permite lanzar el hibernate.
     */
    @Override
    public void run() {
        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection c = connectionFactory.connect();

        //SessionFactory sessionFactory = buildSessionFactory();
        EntityManagerFactory entityManagerFactory = createEntityManagerFactory();
        //sessionObj = buildSessionFactory().openSession();


        VideojuegoController videojuegoController = new VideojuegoController(c, entityManagerFactory);
        PlataformaController plataformaController = new PlataformaController(c, entityManagerFactory);
        Scanner scan = new Scanner(System.in);

        Menu menu = new Menu();
        int opcio = menu.mainMenu();

        while (opcio > 0 && opcio < 8) {
            switch (opcio) {

                case 1:
                    List<Plataforma> plataformas = null;
                    try {
                        plataformas = plataformaController.readPlataformaFile("src/Documents/opencsv.csv");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    for (Plataforma plataforma : plataformas) {
                        try {
                            plataformaController.addPLataforma(plataforma);
                        } catch (Exception e) {
                        }
                    }

                    List<Videojuego> videojuegos = null;
                    try {
                        videojuegos = videojuegoController.readVideojuegoFile("src/Documents/opencsv.csv");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    for (Videojuego videojuego : videojuegos) {
                        videojuegoController.addVideojuego(videojuego);
                    }
                    break;
                case 2:
                    videojuegoController.crearVideojuego();
                    break;
                case 3:
                    videojuegoController.mostrarVideojuegos();
                    break;
                case 4:
                    videojuegoController.mostrarPorPrecio();
                    break;
                case 5:
                    videojuegoController.borrarVideojuegoPorPLataforma();
                    break;
                case 6:
                    videojuegoController.mostrarPorPLataforma();
                    break;
                case 7:
                    videojuegoController.mostrarPorNombre();
                    break;
                case 8:
                    System.out.println("Au-revoir!");
                    System.exit(1);
                    break;
            }
            opcio = menu.mainMenu();
        }
    }
}
