package es.formacion.cip;

public class Cola {

    private char letra;
    private boolean disponible = false;

    public synchronized char get() {
        while (disponible == false) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        disponible = false;
        notifyAll();
        return letra;
    }

    public synchronized void put(char c) {
        while (disponible == true) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        disponible = true;
        letra = c;
        notifyAll();
    }
}
