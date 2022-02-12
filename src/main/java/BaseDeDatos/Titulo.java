package BaseDeDatos;

public class Titulo {
    void mostrar(String titulo){

        char[] charArray = titulo.toCharArray();
        int letras = charArray.length;

        //System.out.println("+-----------------+");
        for (int i = 1; i <= letras; i++) {
            if (i == 1){
                System.out.print("*--");
            } else if (i == letras) {
                System.out.println("--*");
            } else{
                System.out.print("-");
            }
        }

        //System.out.println("|  " + titulo +"  |");
        for (int i = 1; i <= letras; i++) {
            if (i == 1){
                System.out.print("│");
                System.out.print(" " + titulo + " ");
            } else if (i == letras) {
                System.out.println("│");
            }
        }

        //System.out.println("+-----------------+");
        for (int i = 1; i <= letras; i++) {
            if (i == 1){
                System.out.print("*--");
            } else if (i == letras) {
                System.out.println("--*");
            } else{
                System.out.print("-");
            }
        }
    }
}
