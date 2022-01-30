public class Mensaje {
    Util util = new Util();
    void mostrarError(String texto){
        util.saltoLineaX(1);
        System.out.println("\033[31m" + texto + "\033[0m");
        util.saltoLineaX(1);
    }

    void mostrarWarn(String texto){
        util.saltoLineaX(1);
        System.out.println("\033[33m" + texto + "\033[0m");
        util.saltoLineaX(1);
    }

    void mostrarInfo(String texto){
        util.saltoLineaX(1);
        System.out.println("\033[36m" + texto + "\033[0m");
        util.saltoLineaX(1);
    }
}
