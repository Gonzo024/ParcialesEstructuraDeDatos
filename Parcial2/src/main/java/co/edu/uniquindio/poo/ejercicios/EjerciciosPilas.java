
package co.edu.uniquindio.poo.ejercicios;

import co.edu.uniquindio.poo.estructuras.Pila;
import co.edu.uniquindio.poo.modelo.Operacion;

public class EjerciciosPilas {

    // PUNTO 1: Obtener las ultimas operaciones confirmadas

    public static Pila<Operacion> obtenerUltimasConfirmadas(
            Pila<Operacion> historial, int n) {

        Pila<Operacion> resultado = new Pila<>();

        // Caso 1: no hay operaciones que buscar
        if (historial == null || n <= 0) {
            return resultado;
        }

        Pila<Operacion> auxiliar = new Pila<>();
        Pila<Operacion> confirmadas = new Pila<>();

        int contador = 0;

        // Revisamos desde la operacion mas reciente
        while (!historial.estaVacia()) {

            Operacion actual = historial.desapilar();

            // Guardamos cada operacion para restaurarla
            auxiliar.apilar(actual);

            // Solo seleccionamos las primeras n confirmadas
            if (actual.estaConfirmada() && contador < n) {
                confirmadas.apilar(actual);
                contador++;
            }
        }

        // Restauramos el historial original
        while (!auxiliar.estaVacia()) {
            historial.apilar(auxiliar.desapilar());
        }

        // Organizamos las confirmadas en el orden correcto
        while (!confirmadas.estaVacia()) {
            resultado.apilar(confirmadas.desapilar());
        }

        return resultado;
    }


    // PUNTO 2: Obtener un segmento del historial

    public static <T> Pila<T> obtenerSegmento(
            Pila<T> original, int inicio, int fin) {

        Pila<T> resultado = new Pila<>();

        // Caso 1: validar las posiciones
        if (original == null || inicio < 1 ||
                inicio > fin || fin > original.tamanio()) {
            return resultado;
        }

        Pila<T> auxiliar = new Pila<>();
        Pila<T> segmento = new Pila<>();

        int posicion = 1;

        // Recorremos la pila desde la cima
        while (!original.estaVacia()) {

            T actual = original.desapilar();

            // Guardamos para restaurar la pila original
            auxiliar.apilar(actual);

            // Seleccionamos las posiciones solicitadas
            if (posicion >= inicio && posicion <= fin) {
                segmento.apilar(actual);
            }

            posicion++;
        }

        // Restauramos la pila original
        while (!auxiliar.estaVacia()) {
            original.apilar(auxiliar.desapilar());
        }

        // Organizamos el segmento en el orden correcto
        while (!segmento.estaVacia()) {
            resultado.apilar(segmento.desapilar());
        }

        return resultado;
    }

}
