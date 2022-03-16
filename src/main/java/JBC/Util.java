package JBC;

/**
 * Esta clase es un cajon desastre, aqui es donde se mete todo lo que no tiene nombre propio y que no tiene sentido meter en ninguna otra clase.
 */
public class Util {
    /**
     * Este metodo sirve para hacer saltos de linea.
     * @param veces Indicamos las veces que queremos que haga el salto de linea.
     */
    public void saltoLineaX(int veces){
        for (int i = 0; i < veces; i++) {
            System.out.println();
        }
    }
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";
}
