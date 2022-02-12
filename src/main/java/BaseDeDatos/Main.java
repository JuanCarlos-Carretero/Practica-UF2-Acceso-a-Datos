package BaseDeDatos;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        Menu menu = new Menu();
        Titulo titulo = new Titulo();
        titulo.mostrar("MENU PRINCIPAL");

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection c = connectionFactory.connect();

        EdicionesController edicionesController = new EdicionesController(c);
        TodoController todoController = new TodoController(c);


        String[] opciones = { "Crear Tablas", "Rellenar Tablas", "Borrar Todas las Tablas", "Buscar videojuegos por nombre", "Buscar Videojuegos por plataforma", "Modificar el nombre de un videojuego", "Eliminar videojuego", "Eliminar videojuego por plataforma", "Crear edicion coleccionista", "Mostrar Plataformas", "Cerrar"};
        System.out.println(opciones.length);
        int opcion = menu.elegirOpcion(opciones);

        switch (opcion){
            case 1:
                edicionesController.crearTablas();
            case 2:
                todoController.rellenar();
            case 3:
                edicionesController.borrarTablas();
            case 4:
                edicionesController.showVideoJuegoPorNombre();
            case 5:
                edicionesController.showVideoJuegoPorPlataforma();
            case 6:
                edicionesController.modificarNombreVideoJuego();
            case 7:
                edicionesController.borrarVideojuego();
            case 8:
                edicionesController.borrarVideoJuegoPorPlataforma();
            case 9:
                edicionesController.crearEdicionColeccionista();
            case 10:
                edicionesController.showPlataforma();
            case 11:
                break;
        }
    }
}