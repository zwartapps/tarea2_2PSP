package es.formacion.cip;

public class Concursante extends Thread {

    private Cola cola;
    private char letra;
    private int numero;

    //constructor
    public Concursante(Cola cola, int numero) {
        this.cola = cola;
        this.numero = numero;
    }

    //Creamos este Stringbuilder que tenemos que llenar con las letras "de"
    public StringBuilder sb = new StringBuilder("__");
    private volatile boolean exit = false;

    /*Le decimos que mientras que no consigan ambas letras, es decir que el StringBuilder sea igual a "de",
    vaya añadiendo letras a cada concursante (hilo), la "d" en la primera posicion y la "e" en la segunda*/
    public void run() {

        while (!exit) {
            letra = cola.get();
            System.out.println("<-- Concursante " + numero + " recibe la letra " + letra);
            System.out.println("*** Concursante " + numero + " tiene '" + sb + "'");

            if (letra == 'd') {
                sb.replace(0, 1, String.valueOf(letra));
                System.out.println("*** Concursante " + numero + " tiene '" + sb + "'");

            } else if (letra == 'e') {
                sb.replace(1, 2, String.valueOf(letra));
                System.out.println("*** Concursante " + numero + " tiene '" + sb + "'");
            }
            if (sb.toString().equals("de")) {
                System.out.println("Concursante " + numero + " ha ganado el Concurso!");
                exit = true;
                System.exit(0);
            }
        }
    }
}










