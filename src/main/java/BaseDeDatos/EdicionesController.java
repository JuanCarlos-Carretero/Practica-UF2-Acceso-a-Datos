package BaseDeDatos;

import java.sql.*;
import java.util.Locale;
import java.util.Scanner;

/**
 * Esta clase sirve para controlar la tabla edicion situada en la base de datos
 */
public class EdicionesController {
    private Connection connection;
    Scanner scan;
    Menu menu = new Menu();
    Titulo titulo;

    /**
     * Este es el metodo constructor
     * @param connection recibe la coneccion hacia postgres
     */
    public EdicionesController(Connection connection) {
        this.connection = connection;
        this.scan = new Scanner(System.in);
        titulo = new Titulo();
    }

    /**
     * Este metodo sirve para crear un videojuego
     */
    public void crearEdicionColeccionista() {
        try {

            titulo.mostrar("Crear videojuego");

            System.out.println("Nombre:");
            String nombre = scan.nextLine();

            System.out.println("Precio: (Ejemplo; 19 '99 €");
            String precio = scan.nextLine();

            System.out.println("Tipo: (RESERVA o COMPRAR)");
            String tipo = scan.nextLine().toUpperCase(Locale.ROOT);

            System.out.println("Imagen:");
            String imagen = scan.nextLine();

            System.out.println("Plataforma:");
            String plataforma = scan.nextLine().toUpperCase(Locale.ROOT);

            String sql = "INSERT INTO videojuego " +
                    "(nom, precio, imagen) VALUES (?,?,?)";

            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, nombre);
            pst.setString(2, precio);
            pst.setString(3, imagen);

            pst.executeUpdate();

            pst.close();

            sql = "INSERT INTO tipo " +
                    "(tipo) VALUES (?)";
            pst = connection.prepareStatement(sql);
            pst.setString(1, tipo);

            pst.executeUpdate();

            pst.close();

            sql = "INSERT INTO plataforma " +
                    "(plataforma) VALUES (?)";
            pst = connection.prepareStatement(sql);
            pst.setString(1, plataforma);

            pst.executeUpdate();

            pst.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Este metodo sirve para borrar la tabla videojuego
     */
    public void borrarTablas() {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("DROP table videojuego");
            st.close();

        } catch (SQLException e) {
            System.out.println("Error: La tabla videojuego no existe");
        }
    }

    /**
     * Este metodo sirve para crear la tabla videojuego
     */
    public void crearTablas(){
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("CREATE TABLE videojuego(id serial primary key, nombre varchar(20), precio varchar(20), imagen text)");
            st.close();

        } catch (SQLException e) {
            System.out.println("Error: La tabla videojuego ya existe");
        }
    }

    /**
     * Este metodo sirve para mostrar los videojuegos por un texto especifico.
     */
    public void showVideoJuegoPorNombre(){
        ResultSet rs = null;
        System.out.println("Escribe para mostrar info sobre la edicion que buscas: ");
        String nombre = scan.nextLine();
        String sql = "SELECT * FROM videojuego WHERE nombre LIKE '%" + nombre + "%'";

        try{
            Statement st = connection.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("\n" + "Nombre: " + rs.getString("nombre") + "\n" +
                        "Precio: " + rs.getString("precio") + "\n" +
                        "Imagen: " + rs.getString("imagen"));
            }
            rs.close();
            st.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Este metodo sirve para mostrar ediciones por plataformas
     */
    public void showVideoJuegoPorPlataforma(){
        ResultSet rs = null;
        System.out.println("Plataforma: ");
        String plataforma = scan.nextLine().toUpperCase(Locale.ROOT);
        String sql = "SELECT * FROM videojuego, plataforma, ids where plataforma.plataforma LIKE '%" + plataforma + "%'";

        try{
            Statement st = connection.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("\n" + "Nombre: " + rs.getString("videojuego.nombre") + "\n" +
                        "Precio: " + rs.getString("videojuego.precio") + "\n" +
                        "Imagen: " + rs.getString("videojuego.imagen") + "\n" +
                        "Plataforma: " + rs.getString("plataforma.plataforma") + "\n" +
                        "Tipo: " + rs.getString("tipo.tipo") + "\n");
            }

            rs.close();
            st.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Este metodo sirve para mostrar los nombres de las ediciones
     */
    public void showCampeonNom(){
        ResultSet rs = null;
        String sql = "SELECT nombre FROM videojuego";
        try{
            Statement st = connection.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("- " + rs.getString("nombre"));
            }


            rs.close();
            st.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("Elige el videojuego: ");
    }

    /**
     * Este metodo sirve para mostrar plataformas
     */
    public void showPlataforma(){
        System.out.println("\n" + "Plataformas: ");
        ResultSet rs = null;
        String sql = "SELECT * FROM plataforma";
        try{
            Statement st = connection.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("Plataforma: " + rs.getString("plataforma") + "\n");
            }

            rs.close();
            st.close();

        }catch (SQLException e){
            System.out.println("Error: La tabla plataforma no existe");
        }
    }

    /**
     * Este metodo sirve para modificar el nombre de un videojuego
     */
    public void modificarNombreVideoJuego(){
        try {
            Statement st = connection.createStatement();
            /*Aqui tengo que poner un menu*/

            String nombre = scan.nextLine();
            System.out.println("Escribe el nuevo nombre: ");
            String nuevoNombre = scan.nextLine();

            st.executeUpdate("update videojuego set nombre='" + nuevoNombre + "' where nombre='" + nombre + "'");
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Este metodo sirve para borrar un videojuego
     */
    public void borrarVideojuego(){
        try {
            Statement st = connection.createStatement();
            System.out.println("¿Cual quieres eliminar?: ");
            String nombre = scan.nextLine();
            st.executeUpdate("delete from videojuego where nombre='" + nombre + "'");
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Este metodo sirve para borrar videojuegos por plataformas
     */
    public void borrarVideoJuegoPorPlataforma(){
        try {
            Statement st = connection.createStatement();
            System.out.println("Por cual plataforma quieres eliminar: ");
            String plataforma = scan.nextLine();
            st.executeUpdate("delete from videojuego, plataforma where id.plataforma='" + plataforma + "'");
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}