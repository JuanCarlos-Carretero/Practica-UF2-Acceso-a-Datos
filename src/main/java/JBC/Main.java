package JBC;

import java.sql.Connection;
import java.util.Scanner;
/**
 * La clase Main es la primera que se va a ejecutar.
 * @version 5.0, 30/01/22
 * @author Juan Carlos Carretero Roldan
 */

public class Main {
    public static Scanner scan = new Scanner(System.in);

    /**
     * Este metodo permite lanzar la app.
     */
    public static void main(String[] args) {
        Menu menu = new Menu();
        Titulo titulo = new Titulo();
        boolean seguir = true;

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection c = connectionFactory.connect();

        EdicionesController edicionesController = new EdicionesController(c);

        while (seguir) {
            for (int i = 0; i < 20; i++) {
                System.out.println();
            }
            titulo.mostrar("MENU PRINCIPAL");

            String[] opciones = {"Crear Tablas", "Rellenar Tablas", "Mostrar Videojuegos", "Mostrar Plataformas", "Buscar videojuegos por nombre", "Buscar Videojuegos por plataforma", "Modificar el nombre de un videojuego", "Crear edicion coleccionista", "Eliminar videojuego por nombre", "Eliminar videojuego por plataforma", "Borrar Todas las Tablas", "Cerrar"};
            int opcion = menu.elegirOpcion(opciones);

            switch (opcion) {
                case 1:
                    edicionesController.crearTablas();
                    break;
                case 2:
                    edicionesController.rellenar();
                    break;
                case 3:
                    edicionesController.showNombreVideojuegos();
                    break;
                case 4:
                    edicionesController.showPlataforma();
                    break;
                case 5:
                    edicionesController.showVideoJuegoPorNombre();
                    break;
                case 6:
                    edicionesController.showVideoJuegoPorPlataforma();
                    break;
                case 7:
                    edicionesController.modificarNombreVideoJuego();
                    break;
                case 8:
                    edicionesController.crearEdicionColeccionista();
                    break;
                case 9:
                    edicionesController.borrarVideojuegoPorNombre();
                    break;
                case 10:
                    edicionesController.borrarVideoJuegoPorPlataforma();
                    break;
                case 11:
                    edicionesController.borrarTablas();
                    break;
                case 12:
                    seguir = false;
                    break;
            }
        }
    }
}