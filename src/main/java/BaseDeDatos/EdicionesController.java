package BaseDeDatos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
     * Este metodo sirve para rellenar datos de un fichero hacia las tablas de base de datos
     */
    public void rellenar() {
        String[] datos;
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("src/Documents/opencsv.csv")));
            String linia;

            Statement stp = connection.createStatement();
            stp.executeUpdate("CREATE TABLE IF NOT EXISTS plataforma(idplataforma smallint primary key, nombre varchar(20))");
            stp.executeUpdate("CREATE TABLE IF NOT EXISTS videojuego(nombre text, tipocompra varchar(20), precio varchar(15), imagen text, idplataforma smallint references plataforma(idplataforma))");

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

            System.out.println("Plataforma:");
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
                    "(nom,tipocompra,precio,imagen,idplataforma) VALUES (?,?,?,?,?)";

            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, nombre);
            pst.setString(2,tipoCompra);
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