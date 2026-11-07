package co.edu.uniquindio.poo.ejercicios;

import co.edu.uniquindio.poo.estructuras.Cola;
import co.edu.uniquindio.poo.estructuras.NodoArbol;
import co.edu.uniquindio.poo.modelo.Prioridad;
import co.edu.uniquindio.poo.modelo.Solicitud;

public class Ejercicios {

    // ===================== PUNTO 3 =====================

    public static Cola<Solicitud> seleccionarSolicitudes(Cola<Solicitud> pendientes, int minutosDisponibles) {
        Cola<Solicitud> seleccionadas = new Cola<>();

        if (pendientes == null || minutosDisponibles <= 0) {
            return seleccionadas;
        }

        int restante = minutosDisponibles;
        restante = procesarPrioridad(pendientes, seleccionadas, Prioridad.ALTA, restante);
        restante = procesarPrioridad(pendientes, seleccionadas, Prioridad.NORMAL, restante);

        return seleccionadas;
    }

    private static int procesarPrioridad(Cola<Solicitud> pendientes, Cola<Solicitud> seleccionadas,
                                         Prioridad prioridad, int restante) {
        int cantidad = pendientes.tamanio();

        for (int i = 0; i < cantidad; i++) {
            Solicitud actual = pendientes.desencolar();

            if (actual.getPrioridad() == prioridad && actual.getDuracion() <= restante) {
                seleccionadas.encolar(actual);
                restante = restante - actual.getDuracion();
            } else {
                pendientes.encolar(actual);
            }
        }
        return restante;
    }

    // ===================== PUNTO 4.a =====================

    /**
     * Cuenta recursivamente cuantos codigos del ABB estan entre limiteInferior y
     * limiteSuperior (incluidos). Usa el orden del ABB para no visitar
     * subarboles que no pueden tener codigos dentro del rango.
     */
    public static int contarEquiposEnRango(NodoArbol<Integer> raiz, int limiteInferior, int limiteSuperior) {
        // Caso base: arbol vacio o rango invalido
        if (raiz == null || limiteInferior > limiteSuperior) {
            return 0;
        }

        int codigo = raiz.getDato();

        // El nodo es menor que el rango: a la izquierda todo es aun menor
        if (codigo < limiteInferior) {
            return contarEquiposEnRango(raiz.getDerecho(), limiteInferior, limiteSuperior);
        }

        // El nodo es mayor que el rango: a la derecha todo es aun mayor
        if (codigo > limiteSuperior) {
            return contarEquiposEnRango(raiz.getIzquierdo(), limiteInferior, limiteSuperior);
        }

        // El nodo esta dentro del rango: se cuenta y se busca a ambos lados
        return 1 + contarEquiposEnRango(raiz.getIzquierdo(), limiteInferior, limiteSuperior)
                + contarEquiposEnRango(raiz.getDerecho(), limiteInferior, limiteSuperior);
    }
}