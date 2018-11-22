package es.formacion.cip;

//Clase para sacar y meter las letras que recibimos de manera aleatoria y que espera a cada hilo
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
