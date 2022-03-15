package BaseDeDatos;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);
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

            String[] opciones = {"Rellenar Tablas", "Crear Tablas", "Borrar Todas las Tablas", "Buscar videojuegos por nombre", "Buscar Videojuegos por plataforma", "Modificar el nombre de un videojuego", "Eliminar videojuego", "Eliminar videojuego por plataforma", "Crear edicion coleccionista", "Mostrar Plataformas", "Cerrar"};
            int opcion = menu.elegirOpcion(opciones);

            switch (opcion) {
                case 1:
                    edicionesController.rellenar();
                    break;
                case 2:
                    edicionesController.crearTablas();
                    break;
                case 3:
                    edicionesController.borrarTablas();
                    break;
                case 4:
                    edicionesController.showVideoJuegoPorNombre();
                    break;
                case 5:
                    edicionesController.showVideoJuegoPorPlataforma();
                    break;
                case 6:
                    edicionesController.modificarNombreVideoJuego();
                    break;
                case 7:
                    edicionesController.borrarVideojuego();
                    break;
                case 8:
                    edicionesController.borrarVideoJuegoPorPlataforma();
                    break;
                case 9:
                    edicionesController.crearEdicionColeccionista();
                    break;
                case 10:
                    edicionesController.showPlataforma();
                    break;
                case 11:
                    seguir = false;
                    break;
            }
        }
    }
}