package Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.*;


/**
 * Esta clase sirve para controlar la tabla videojuego
 */
public class VideojuegoController {
    private Connection connection;
    private EntityManagerFactory entityManagerFactory;
    Scanner scan;
    List<Videojuego> videojuegos;
    Menu menu = new Menu();

    PlataformaController plataformaController = new PlataformaController(connection, entityManagerFactory);

    /**
     * Este es el constructor de la clase Videojuego
     *
     * @param connection           recibe la conexión hacia postgres
     * @param entityManagerFactory recibe el entityManagerFactory
     */
    public VideojuegoController(Connection connection, EntityManagerFactory entityManagerFactory) {
        this.connection = connection;
        this.entityManagerFactory = entityManagerFactory;
        this.scan = new Scanner(System.in);
        videojuegos = new ArrayList();
    }

    /**
     * En este metodo leemos el fichero y lo metemos dentro de una lista
     *
     * @param file recibe la ruta del fichero
     * @return devuelve la lista de videojuegos
     * @throws IOException
     */
    public List<Videojuego> readVideojuegoFile(String file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String linea = "";

        while ((linea = br.readLine()) != null) {
            List<String> listToken = getTokenList(linea, ",");
            videojuegos.add(new Videojuego(listToken.get(0), listToken.get(1), listToken.get(2), listToken.get(3), new Plataforma(listToken.get(4))));
            System.out.println(listToken.get(0));
            System.out.println(listToken.get(1));
            System.out.println(listToken.get(2));
            System.out.println(listToken.get(3));
            System.out.println(listToken.get(4));
        }
        return videojuegos;
    }

    /**
     * Sirve para añadir un Videojuego
     *
     * @param videojuego
     */
    public void addVideojuego(Videojuego videojuego) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(videojuego);
        em.getTransaction().commit();
        em.close();
    }

    private static List<String> getTokenList(String string, String delimiters) {

        List<String> listTokens = new ArrayList<String>();

        try {

            StringTokenizer st = new StringTokenizer(string, delimiters);

            while (st.hasMoreTokens()) {

                //add token to the list
                listTokens.add(st.nextToken());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listTokens;
    }

    /**
     * Este metodo sirve para mostrar todos los videojuegos
     */
    public void mostrarVideojuegos() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Videojuego> result = em.createQuery("from Videojuego", Videojuego.class)
                .getResultList();
        for (Videojuego videojuego : result) {
            System.out.println(videojuego.toString());
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para crear un nuevo Videojuego
     */
    public void crearVideojuego() {
        System.out.println("Añade un nombre: ");
        String nuevoNombre = "\"" + scan.nextLine() + "\"";
        System.out.println("Añade el precio: ");
        String nuevoPrecio = "\"" + scan.nextLine() + "\"";
        System.out.println("Añade un tipo de compra: ");
        String nuevoTipoDeCompra = "\"" + scan.nextLine().toUpperCase(Locale.ROOT) + "\"";
        System.out.println("Añade la url de la imagen: ");
        String nuevaImagen = "\"" + scan.nextLine() + "\"";
        System.out.println("Añade una Plataforma: ");
        String plataforma = "\"" + menu.PlataformaMenu(connection, entityManagerFactory) + "\"";
        addVideojuego(new Videojuego(nuevoNombre, nuevoPrecio, nuevoTipoDeCompra, nuevaImagen, new Plataforma(plataforma)));
    }

    /**
     * Este metodo sirve para mostrar los videojuegos por un precio
     */
    public void mostrarPorPrecio() {
        System.out.println("Escribe un precio: ");
        String precio = scan.nextLine();

        String sql = "from Videojuego where precio like '\"" + precio + "%'";

        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();
        List<Videojuego> result = em.createQuery(sql, Videojuego.class).getResultList();
        for (Videojuego videojuego : result) {
            System.out.println(videojuego.toString());
        }

        em.close();
    }

    /**
     * Este metodo sirve para borrar videojuegos por plataforma
     */
    public void borrarVideojuegoPorPLataforma() {
        String plataforma = "\"" + menu.PlataformaMenu(connection, entityManagerFactory) + "\"";
        String sql = "from Videojuego where plataforma='" + plataforma + "'";

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Videojuego> result = em.createQuery(sql, Videojuego.class).getResultList();
        for (Videojuego videojuego : result) {
            em.remove(videojuego);
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo sirve para mostrar los videojuego de una plataforma
     */
    public void mostrarPorPLataforma() {
        String plataforma ="\"" + menu.PlataformaMenu(connection, entityManagerFactory) + "\"";
        String sql = "from Videojuego where plataforma ='" + plataforma + "'";

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Videojuego> result = em.createQuery(sql, Videojuego.class).getResultList();
        for (Videojuego videojuego : result) {
            System.out.println(videojuego.toString());
        }
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Este metodo muestra los videojuego que contenga un nombre determinado
     */
    public void mostrarPorNombre() {
        System.out.println("Escribe un nombre: ");
        String nom = scan.nextLine();

        String sql = "from Videojuego where nombre like '%" + nom + "%'";

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Videojuego> result = em.createQuery(sql, Videojuego.class).getResultList();
        for (Videojuego videojuego : result) {
            System.out.println(videojuego.toString());
        }
        em.getTransaction().commit();
        em.close();
    }
}
