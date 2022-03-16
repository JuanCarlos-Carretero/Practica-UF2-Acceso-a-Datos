package JBC;

import WebScrapping.Util;

/**
 * Esta clase es para crear mensajes predeterminados de colores por consola.
 */
public class Mensaje {
    WebScrapping.Util util = new Util();

    /**
     * Este metodo sirve para mostrar los errores por el terminal de color rojo.
     * @param texto Le paso el texto a mostrar.
     */
    void mostrarError(String texto){
        util.saltoLineaX(1);
        System.out.println("\033[31m" + texto + "\033[0m");
        util.saltoLineaX(1);
    }

    /**
     * Este metodo sirve para mostrar los warning por el terminal de color amarillo.
     * @param texto Le paso el texto a mostrar.
     */
    void mostrarWarn(String texto){
        util.saltoLineaX(1);
        System.out.println("\033[33m" + texto + "\033[0m");
        util.saltoLineaX(1);
    }

    /**
     * Este metodo sirve para mostrar informacion por el terminal de color azul.
     * @param texto Le paso el texto a mostrar.
     */
    void mostrarInfo(String texto){
        util.saltoLineaX(1);
        System.out.println("\033[36m" + texto + "\033[0m");
        util.saltoLineaX(1);
    }
}

