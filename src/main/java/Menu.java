public class Menu {
    String elegirOpcion(String[] opciones){

        boolean seguirPidiendo = true;
        String opcion = " ";


        for (int i = 0; i < opciones.length; i++) {
            System.out.println((i + 1) + ". " + opciones[i]);
        }
        while(seguirPidiendo) {
            System.out.println("\nOpcion:");
            opcion = Main.scan.nextLine();

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