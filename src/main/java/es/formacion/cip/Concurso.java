package es.formacion.cip;

public class Concurso {

    public static void main(String[] args) {

        System.out.println("Palabra a conseguir: ‘de’");
        Cola cola = new Cola();
        Presentador p = new Presentador(cola);
        Concursante c = new Concursante(cola, 1);
        Concursante d = new Concursante(cola, 2);
        Concursante e = new Concursante(cola, 3);

        p.start();
        c.start();
        d.start();
        e.start();
    }
}



