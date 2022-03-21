package Util;

import java.util.Scanner;

/**
 * Esta clase sirve para crear menus.
 */
public class Menu {
    /**
     * Este metodo sirve para crear las opciones del menu.
     * @param opciones le paso un arraylist con las opciones a mostrar.
     * @return Retorna lo que haya escrito el usuario.
     */
    Scanner scan = new Scanner(System.in);
    public String elegirOpcion(String[] opciones){

        boolean seguirPidiendo = true;
        String opcion = " ";


        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ". " + opciones[i]);
        }
        while(seguirPidiendo) {
            System.out.println("\nOpcion:");
            opcion = scan.nextLine();

            try {
                if (Integer.parseInt(opcion) > opciones.length) {
                    Mensaje mensaje = new Mensaje();
                    mensaje.mostrarError("Esa opcion no existe");
                } else {
                    seguirPidiendo = false;
                }
            } catch (Exception e) {
                System.out.println("¡Introduzca una opcion!");
                seguirPidiendo = true;
            }
        }
        return opcion;
    }
}