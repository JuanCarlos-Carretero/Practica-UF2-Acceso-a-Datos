package BaseDeDatos;

import java.io.IOException;

/**
 * Esta clase sirve para mostrar menus
 */
public class Menu {
    int elegirOpcion(String[] opciones){

        boolean seguirPidiendo = true;
        int opcion = 0;


        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ". " + opciones[i]);
        }
        while(seguirPidiendo) {
            System.out.println("\nOpcion:");
            opcion = Main.scan.nextInt();

            try {
                if (opcion > opciones.length) {
                    Mensaje mensaje = new Mensaje();
                    mensaje.mostrarError("Esa opcion no existe usa un numero");
                } else {
                    seguirPidiendo = false;
                }
            } catch (Exception e) {
                System.out.println("¡Introduzca una opcion usa un numero!");
                seguirPidiendo = true;
            }
        }
        return opcion;
    }

    /**
     * Este metodo sirve para mostrar un menu de nombres de campeon
     * @param c recibe la conexion
     * @return devuelve el nombre que elegiste
     *//*
    public String NomMenu(Connection c){
        EdicionesController campeonController = new EdicionesController(c);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("\n" + "Ediciones Coleccionistas: ");
        for(;;){
            campeonController.showCampeonNom();
            try {
                opciones = br.readLine();
            } catch (NumberFormatException | IOException e) {
                System.out.println("valor no valido");
                e.printStackTrace();
            }
            return opciones;
        }
    }*/

    /**
     * Este metodo sirve para auntenticar
     * @param tries recibe la cantidad de intento
     * @return devuelve unos datos de tipo Identity
     * @throws IOException es un tipo de excepciones
     *//*
    public Identity authenticate(int tries) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("============================ACB=============================");
        System.out.println("============================================================");
        System.out.println("Aviso: tienes " + (3 - tries) + " intentos para iniciar sesion");
        System.out.println("============================================================");
        System.out.println("Inserta el nombre del usuario: ");
        String user = br.readLine();
        System.out.println("Inserta una contraseña: ");
        String password = br.readLine();

        Identity identity = new Identity(user, password);
        return identity;
    }*/
}
