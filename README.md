# tarea2_2PSP
Tarea 2_2 del modulo Programación de Servicios y Procesos.

## Descripción
Programación de Servicios y Procesos

Práctica 2.

Tenemos un presentador que genera caracteres de manera aleatoria entre la a y la z, y tenemos 3
concursantes que se pelean por recoger las letras. Cada concursante tiene que usar las letras que va
recolectando para construir una palabra. Gana aquel concursante que consigua primero todas las letras de la
palabra. Ejemplo de salida del programa:

Palabra a conseguir: ‘de’


Presentador genera letra 'd'.
 Concursante 1 recibe letra 'd'. Tiene ‘d_’ 

Presentador genera letra 'h'.
 
Concursante 2 recibe letra 'h'. 
Tiene ‘__’
Presentador genera letra 'i'.
 
Recolector 1 recibe letra 'i'. 
Tiene ‘d_’
Presentador genera letra 'e'.
 
Concursante 3 recibe la letra 'e'. 
Tiene ‘_e’
Presentador genera letra 'g'
 
Concursante 3 recibe la letra 'g'. 
Tiene ‘_e’
Presentador genera la letra 'f'.
 
Concursante 2 recibe la letra 'f'. Tiene ‘__’

Presentador genera la letra 'e'.
 
Concursante 1 recibe la letra 'e'.
Tiene ‘de’
Concursante 1 ha ganado el concurso.

Concursante 3 no ha conseguido todas las letras.

Concursante 2 no ha conseguido todas las letras.

Para este ejercicio tenemos que trabajar con 4 hilos, 1 que genera las letras de manera aleatoria (El Presentador)
y 3 Concursantes.
La parte del Presentador sería de esta manera:
```
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
```
Creamos la clase Cola para ir poniendo las letras que generamos aleatoriamente, y despues se pide las letras desde esa clase, en esta clase le decimos a los hilos que espere y notifique cuando tenga su letra y le toca al siguiente:
```
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
```
En la parte de los concursantes he optado por usar StringBuilder, porque así podemos ir añadiendo las letras
obtenidas aleatoriamente en el lugar correcto de la palabra y cuando conseguimos la palabra salimos del bucle while:
```
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
```
Ejecutamos y vemos que funciona correctamente:

<img src="http://i66.tinypic.com/34rf5gy.jpg">

En el codigo está todo comentado para entender que hacemos.
    
