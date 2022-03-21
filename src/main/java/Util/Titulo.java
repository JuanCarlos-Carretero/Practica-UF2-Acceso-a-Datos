package Util;

import Util.Util;

/**
 * Esta clase crea un titulo para la app.
 */
public class Titulo {
    /**
     * El metodo mostrar() sirve para mostrar por terminal un titulo.
     * @param titulo recibe un texto para colocarlo como titulo dentro de la app.
     */
    public void mostrar(String titulo){
        Util util = new Util();

        String color = util.ANSI_GREEN;
        char[] charArray = titulo.toCharArray();
        int letras = charArray.length;

        //System.out.println("+-----------------+");
        for (int i = 1; i <= letras; i++) {
            if (i == 1){
                System.out.print(color + "*--");
            } else if (i == letras) {
                System.out.println(color + "--*");
            } else{
                System.out.print( color + "-");
            }
        }

        //System.out.println("|  " + titulo +"  |");
        for (int i = 1; i <= letras; i++) {
            if (i == 1){
                System.out.print(color + "│");
                System.out.print(color + " " + titulo + " ");
            } else if (i == letras) {
                System.out.println(color + "│");
            }
        }

        //System.out.println("+-----------------+");
        for (int i = 1; i <= letras; i++) {
            if (i == 1){
                System.out.print(color + "*--");
            } else if (i == letras) {
                System.out.println(color + "--*" + util.ANSI_RESET);
            } else{
                System.out.print(color + "-");
            }
        }
    }
}
