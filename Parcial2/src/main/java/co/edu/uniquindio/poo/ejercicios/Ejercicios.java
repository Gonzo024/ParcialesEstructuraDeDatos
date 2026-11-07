package co.edu.uniquindio.poo.ejercicios;

import co.edu.uniquindio.poo.estructuras.Cola;
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
}
