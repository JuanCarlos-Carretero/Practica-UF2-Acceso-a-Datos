package JBC;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.Scanner;

/**
 * Esta clase sirve para controlar la tabla edicion situada en la base de datos
 */
public class EdicionesController {
    private Connection connection;
    Scanner scan;
    Menu menu = new Menu();
    Titulo titulo;
    Util util = new Util();
    Mensaje mensaje = new Mensaje();

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
     * Este metodo sirve para rellenar datos de un fichero hacia las tablas de base de datos
     */
    public void rellenar() {
        String[] datos;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("src/Documents/opencsv.csv")));
            String linia;

            Statement stp = connection.createStatement();
            stp.executeUpdate("CREATE TABLE IF NOT EXISTS plataforma(idplataforma smallint primary key, nombrePlataforma varchar(20))");
            stp.executeUpdate("CREATE TABLE IF NOT EXISTS videojuego(nombre text, precio varchar(15), tipocompra varchar(20), imagen text, idplataforma smallint references plataforma(idplataforma))");

            while ((linia = br.readLine()) != null) {
                datos = linia.split(",");

                try {
                    String nom = datos[0];
                    String precio = datos[1];
                    String tipoCompra = datos[2];
                    String imagen = datos[3];
                    String plataforma = datos[4];

                    int idPlataforma;

                    switch (plataforma) {
                        case "\"PLAYSTATION 4\"":
                            idPlataforma = 1;
                            break;
                        case "\"PLAYSTATION 5\"":
                            idPlataforma = 2;
                            break;
                        case "\"PC SOFTWARE\"":
                            idPlataforma = 3;
                            break;
                        case "\"XBOX ONE\"":
                            idPlataforma = 4;
                            break;
                        case "\"NINTENDO SWITCH\"":
                            idPlataforma = 5;
                            break;
                        default:
                            idPlataforma = 0;
                            break;
                    }

                    String sql = "SELECT COUNT(idplataforma) as size FROM plataforma WHERE idplataforma = ?";
                    PreparedStatement pst = connection.prepareStatement(sql);
                    pst.setInt(1, idPlataforma);

                    ResultSet rs = pst.executeQuery();

                    while (rs.next()) {
                        int rsSize = rs.getInt("size");

                        if((rsSize == 0)){
                            try {
                                sql = "INSERT INTO plataforma(idplataforma, nombrePlataforma) VALUES (?,?)";

                                pst = connection.prepareStatement(sql);
                                pst.setInt(1, idPlataforma);
                                pst.setString(2, plataforma);

                                pst.executeUpdate();
                                pst.close();
                                System.out.println(idPlataforma);
                            }catch(SQLException e){
                                }
                        } else{
                            System.out.println("Esa plataforma ya esta en la base de datos");
                        }
                    }

                    sql = "INSERT INTO videojuego(nombre, tipoCompra, precio, imagen, idplataforma) VALUES (?,?,?,?,?)";

                    pst = connection.prepareStatement(sql);
                    pst.setString(1, nom);
                    pst.setString(2, precio);
                    pst.setString(3, tipoCompra);
                    pst.setString(4, imagen);
                    pst.setInt(5, idPlataforma);

                    pst.executeUpdate();
                    pst.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.getStackTrace();
        }

        continuar();
    }

    /**
     * Este metodo sirve para preguntar si quieres seguir en la app o salir
     */
    private void continuar() {
        System.out.println("\n" + "¿Quieres continuar?");
        String[] opciones = {"Si", "No"};
        int opcion = menu.elegirOpcion(opciones);

        switch (opcion) {
            case 1:
                break;
            case 2:
                System.exit(0);
        }
    }

    /**
     * Este metodo sirve para crear un videojuego
     */
    public void crearEdicionColeccionista() {
        try {

            titulo.mostrar("Crear videojuego");

            System.out.println("Nombre:");
            String nombre = "\"" + scan.nextLine() + "\"";

            System.out.println("Precio: (Ejemplo; 19 '99 €)");
            String precio = "\"" + scan.nextLine() + "\"";

            System.out.println("Tipo:");
            String[] opciones = {"RESERVA", "COMPRA"};
            int opcion = menu.elegirOpcion(opciones);
            String tipoCompra = null;

            switch (opcion){
                case 1:
                    tipoCompra = "\"RESERVA\"";
                    break;
                case 2:
                    tipoCompra = "\"COMPRA\"";
                    break;
            }

            System.out.println("Imagen: (Insertar enlace a la imagen)");
            String imagen = "\"" + scan.nextLine() + "\"";

            System.out.println("\n" + "Plataforma:");
            opciones = new String[]{"PLAYSTATION 4", "PLAYSTATION 5", "PC SOFTWARE", "XBOX ONE", "NINTENDO SWITCH"};
            opcion = menu.elegirOpcion(opciones);
            int idPlataforma = 0;
            String plataforma = null;

            switch(opcion){
                case 1:
                    idPlataforma = 1;
                    plataforma = "\"PLAYSTATION 4\"";
                    break;
                case 2:
                    idPlataforma = 2;
                    plataforma = "\"PLAYSTATION 5\"";
                    break;
                case 3:
                    idPlataforma = 3;
                    plataforma = "\"PC SOFTWARE\"";
                    break;
                case 4:
                    idPlataforma = 4;
                    plataforma = "\"XBOX ONE\"";
                    break;
                case 5:
                    idPlataforma = 5;
                    plataforma = "\"NINTENDO SWITCH\"";
                    break;
            }

            String sql = "INSERT INTO videojuego " +
                    "(nombre,tipocompra,precio,imagen,idplataforma) VALUES (?,?,?,?,?)";

            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, nombre);
            pst.setString(2, tipoCompra);
            pst.setString(3, precio);
            pst.setString(4, imagen);
            pst.setInt(5, idPlataforma);

            pst.executeUpdate();

            pst.close();

            sql = "SELECT COUNT(idplataforma) as size FROM plataforma WHERE idplataforma = ?";
            pst = connection.prepareStatement(sql);
            pst.setInt(1, idPlataforma);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int rsSize = rs.getInt("size");

                if((rsSize == 0)){
                    try {
                        sql = "INSERT INTO plataforma(idplataforma, nombre) VALUES (?,?)";

                        pst = connection.prepareStatement(sql);
                        pst.setInt(1, idPlataforma);
                        pst.setString(2, plataforma);

                        pst.executeUpdate();
                        pst.close();
                        System.out.println(idPlataforma);
                    }catch(SQLException e){
                    }
                } else{
                    System.out.println("Esa plataforma ya esta en la base de datos");
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        continuar();
    }

    /**
     * Este metodo sirve para borrar las tablas dentro de la base de edatos
     */
    public void borrarTablas() {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("DROP table videojuego");
            st.executeUpdate("DROP table plataforma");
            st.close();

        } catch (SQLException e) {
            System.out.println("Error: La tabla videojuego no existe");
        }

        continuar();
    }

    /**
     * Este metodo sirve para crear la tabla videojuego y la tabla videojuego
     */
    public void crearTablas(){
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("CREATE TABLE IF NOT EXISTS plataforma(idplataforma smallint primary key, nombrePlataforma varchar(20))");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS videojuego(nombre text, precio varchar(15), tipocompra varchar(20), imagen text, idplataforma smallint references plataforma(idplataforma))");
            st.close();

        } catch (SQLException e) {
            System.out.println("Error: La tabla videojuego ya existe");
        }

        continuar();
    }

    /**
     * Este metodo sirve para mostrar los videojuegos por un texto especifico.
     */
    public void showVideoJuegoPorNombre(){
        ResultSet rs;
        System.out.println("Escribe para mostrar info sobre la edicion que buscas: ");
        String nombre = scan.nextLine();
        String sql = "SELECT * FROM videojuego, plataforma WHERE nombre LIKE '%" + nombre + "%'";

        try{
            Statement st = connection.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("\n" + util.ANSI_YELLOW + "Nombre: " + util.ANSI_RESET + rs.getString("nombre") + "\n" +
                        util.ANSI_CYAN + "Precio: " + util.ANSI_RESET + rs.getString("precio") + "\n" +
                        util.ANSI_PURPLE + "Imagen: " + util.ANSI_RESET + rs.getString("imagen") + "\n" +
                        util.ANSI_GREEN + "Plataforma: " + util.ANSI_RESET + rs.getString("nombreplataforma") + "\n" +
                        util.ANSI_RED +"Tipo: " + util.ANSI_RESET + rs.getString("tipocompra") + "\n");
            }
            rs.close();
            st.close();

        }catch (SQLException e){
            e.printStackTrace();
        }

        continuar();
    }

    /**
     * Este metodo sirve para mostrar ediciones por plataformas
     */
    public void showVideoJuegoPorPlataforma(){
        ResultSet rs = null;
        System.out.println("Plataforma: ");
        String[] opciones = new String[]{"PLAYSTATION 4", "PLAYSTATION 5", "PC SOFTWARE", "XBOX ONE", "NINTENDO SWITCH"};
        int opcion = menu.elegirOpcion(opciones);
        String plataforma = null;

        switch(opcion){
            case 1:
                plataforma = "\"PLAYSTATION 4\"";
                break;
            case 2:
                plataforma = "\"PLAYSTATION 5\"";
                break;
            case 3:
                plataforma = "\"PC SOFTWARE\"";
                break;
            case 4:
                plataforma = "\"XBOX ONE\"";
                break;
            case 5:
                plataforma = "\"NINTENDO SWITCH\"";
                break;
        }
        String sql = "SELECT * FROM videojuego, plataforma where nombreplataforma LIKE '%" + plataforma + "%'";

        try{
            Statement st = connection.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println("\n" + util.ANSI_YELLOW + "Nombre: " + util.ANSI_RESET + rs.getString("nombre") + "\n" +
                        util.ANSI_CYAN + "Precio: " + util.ANSI_RESET + rs.getString("precio") + "\n" +
                        util.ANSI_PURPLE + "Imagen: " + util.ANSI_RESET + rs.getString("imagen") + "\n" +
                        util.ANSI_GREEN + "Plataforma: " + util.ANSI_RESET + rs.getString("nombreplataforma") + "\n" +
                        util.ANSI_RED +"Tipo: " + util.ANSI_RESET + rs.getString("tipocompra") + "\n");
            }

            rs.close();
            st.close();

        }catch (SQLException e){
            e.printStackTrace();
        }

        continuar();
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
                System.out.println("Plataforma: " + rs.getString("nombreplataforma") + "\n");
            }

            rs.close();
            st.close();

        }catch (SQLException e){
            System.out.println("Error: La tabla plataforma no existe");
        }

        continuar();
    }

    /**
     * Este metodo sirve para mostrar plataformas
     */
    public void showNombreVideojuegos(){
        System.out.println("\n" + "Videojuego: ");
        ResultSet rs = null;
        String sql = "SELECT nombre, nombreplataforma FROM videojuego, plataforma";
        try{
            Statement st = connection.createStatement();

            rs = st.executeQuery(sql);

            while (rs.next()) {
                System.out.println(util.ANSI_YELLOW + "Videojuego: " + util.ANSI_RESET + rs.getString("nombre") + "," + util.ANSI_GREEN + " Plataforma: " + util.ANSI_RESET + rs.getString("nombreplataforma") + "\n");
            }

            rs.close();
            st.close();

        }catch (SQLException e){
            System.out.println("Error: La tabla videojuego no existe");
        }

        continuar();
    }

    /**
     * Este metodo sirve para modificar el nombre de un videojuego
     */
    public void modificarNombreVideoJuego(){
        try {
            Statement st = connection.createStatement();
            System.out.println("Escribe el nombre completo del videojuego: ");
            String nombre = "\"" +  scan.nextLine() + "\"";
            System.out.println("Escribe el nuevo nombre: ");
            String nuevoNombre = "\"" + scan.nextLine() + "\"";

            st.executeUpdate("UPDATE videojuego SET nombre='" + nuevoNombre + "' WHERE nombre='" + nombre + "'");
            st.close();

        } catch (SQLException e) {
            mensaje.mostrarError("Ese nombre no existe");
        }

        continuar();
    }

    /**
     * Este metodo sirve para borrar un videojuego
     */
    public void borrarVideojuegoPorNombre(){
        try {
            Statement st = connection.createStatement();
            System.out.println("¿Cual quieres eliminar?: ");
            System.out.println("(Nombre completo: Esto eliminira todos los que tengan el mismo nombre)");
            String nombre = scan.nextLine();
            st.executeUpdate("DELETE FROM videojuego WHERE nombre='" + nombre + "'");
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        continuar();
    }

    /**
     * Este metodo sirve para borrar videojuegos por plataformas
     */
    public void borrarVideoJuegoPorPlataforma(){
        try {
            Statement st = connection.createStatement();
            System.out.println("Por cual plataforma quieres eliminar: ");
            String[] opciones = new String[]{"PLAYSTATION 4", "PLAYSTATION 5", "PC SOFTWARE", "XBOX ONE", "NINTENDO SWITCH"};
            int opcion = menu.elegirOpcion(opciones);
            int plataforma = 0;

            switch(opcion){
                case 1:
                    plataforma = 1;
                    break;
                case 2:
                    plataforma = 2;
                    break;
                case 3:
                    plataforma = 3;
                    break;
                case 4:
                    plataforma = 4;
                    break;
                case 5:
                    plataforma = 5;
                    break;
            }
            st.executeUpdate("delete from videojuego where idplataforma='" + plataforma + "'");
            st.executeUpdate("delete from plataforma where idplataforma='" + plataforma + "'");
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        continuar();
    }
}