
package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.ejercicios.EjerciciosPilas;
import co.edu.uniquindio.poo.estructuras.Pila;
import co.edu.uniquindio.poo.modelo.Operacion;
import co.edu.uniquindio.poo.modelo.Operacion.Estado;

public class PruebaPunto1 {

    public static void main(String[] args) {

        Pila<Operacion> historial = new Pila<>();

        historial.apilar(new Operacion("O1", Estado.ANULADA));
        historial.apilar(new Operacion("O2", Estado.CONFIRMADA));
        historial.apilar(new Operacion("O3", Estado.CONFIRMADA));
        historial.apilar(new Operacion("O4", Estado.ANULADA));
        historial.apilar(new Operacion("O5", Estado.CONFIRMADA));

        System.out.println("PUNTO 1 - HISTORIAL");

        System.out.println("Historial original:");
        System.out.println(historial);

        Pila<Operacion> resultado =
                EjerciciosPilas.obtenerUltimasConfirmadas(historial, 2);

        System.out.println("Ultimas 2 confirmadas:");
        System.out.println(resultado);

        System.out.println("Historial despues de ejecutar:");
        System.out.println(historial);

        System.out.println("Prueba con n = 0:");
        System.out.println(
                EjerciciosPilas.obtenerUltimasConfirmadas(historial, 0)
        );

        System.out.println("Prueba con n = 10:");
        System.out.println(
                EjerciciosPilas.obtenerUltimasConfirmadas(historial, 10)
        );
    }
}
