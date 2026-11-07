
package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.ejercicios.EjerciciosPilas;
import co.edu.uniquindio.poo.estructuras.Pila;

public class PruebaPunto2 {

    public static void main(String[] args) {

        Pila<String> historial = new Pila<>();

        historial.apilar("A1");
        historial.apilar("A2");
        historial.apilar("A3");
        historial.apilar("A4");
        historial.apilar("A5");
        historial.apilar("A6");

        System.out.println("PUNTO 2 - SEGMENTO DEL HISTORIAL");

        System.out.println("Pila original:");
        System.out.println(historial);

        Pila<String> resultado =
                EjerciciosPilas.obtenerSegmento(historial, 2, 4);

        System.out.println("Segmento de 2 a 4:");
        System.out.println(resultado);

        System.out.println("Pila original despues:");
        System.out.println(historial);

        System.out.println("Segmento de 3 a 3:");
        System.out.println(
                EjerciciosPilas.obtenerSegmento(historial, 3, 3)
        );

        System.out.println("Segmento invalido de 0 a 3:");
        System.out.println(
                EjerciciosPilas.obtenerSegmento(historial, 0, 3)
        );

        System.out.println("Segmento invalido de 4 a 2:");
        System.out.println(
                EjerciciosPilas.obtenerSegmento(historial, 4, 2)
        );

        System.out.println("Segmento invalido de 2 a 10:");
        System.out.println(
                EjerciciosPilas.obtenerSegmento(historial, 2, 10)
        );
    }
}
