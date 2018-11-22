package es.formacion.cip;
import java.util.Random;

public class Presentador extends Thread {

    private Cola cola;
    private char c;

    public Presentador(Cola cola) {
        this.cola = cola;
        // this.letra = letra;
    }

    public void run() {

        while (true) {
            Random r = new Random();
            c = (char) (r.nextInt(26) + 'a');
            cola.put(c);
            System.out.println("=> Presentador genera la letra => " + c);
            try {
                sleep(500);
            } catch (InterruptedException e) {
            }

        }
    }
}


