package Hibernate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.Connection;
import java.util.StringTokenizer;

/**
 * Esta clase sirve para controla la tabla de plataforma
 */
public class PlataformaController {
    private Connection connection;
    private EntityManagerFactory entityManagerFactory;
    Scanner scan;
    List<Plataforma> plataformas;

    /**
     * Este es el constructor de la clase
     *
     * @param connection           recibe la conexión hacia postgresql
     * @param entityManagerFactory recibe el entityManagerFactory
     */
    public PlataformaController(Connection connection, EntityManagerFactory entityManagerFactory) {
        this.connection = connection;
        this.entityManagerFactory = entityManagerFactory;
        this.scan = new Scanner(System.in);
        plataformas = new ArrayList<>();
    }

    /**
     * En este metodo leemos el fichero y lo metemos dentro de una lista
     *
     * @param file recibe la ruta del fichero
     * @return devuelve la lista de plataforma
     * @throws IOException
     */
    public List<Plataforma> readPlataformaFile(String file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String linea = "";

        while ((linea = br.readLine()) != null) {
            List<String> listToken = getTokenList(linea, ",");
            plataformas.add(new Plataforma(listToken.get(4)));
            System.out.println(listToken.get(4));
        }
        return plataformas;
    }

    /**
     * Sirve para añadir una plataforma
     *
     * @param plataforma
     */
    public void addPLataforma(Plataforma plataforma) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(plataforma);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * En este metodo podemos dividir las linias
     *
     * @param string     recibe una linia
     * @param delimiters recibe cual es el separador
     * @return devuelve un array de todas las palabras separadas
     */
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
     * Este metodo sirve para mostrar las plataformas
     */
    public void showPlataformas() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Plataforma> result = em.createQuery("from Plataforma", Plataforma.class).getResultList();
        for (Plataforma plataforma : result) {
            System.out.println(plataforma.toString());
        }
        em.getTransaction().commit();
        em.close();
    }
}
