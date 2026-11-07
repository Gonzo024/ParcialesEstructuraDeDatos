package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.ejercicios.Ejercicios;
import co.edu.uniquindio.poo.estructuras.Cola;
import co.edu.uniquindio.poo.modelo.Prioridad;
import co.edu.uniquindio.poo.modelo.Solicitud;

public class Main {
    public static void main(String[] args) {
        Cola<Solicitud> pendientes = new Cola<>();
        pendientes.encolar(new Solicitud("S1", Prioridad.NORMAL, 10));
        pendientes.encolar(new Solicitud("S2", Prioridad.ALTA, 30));
        pendientes.encolar(new Solicitud("S3", Prioridad.ALTA, 15));
        pendientes.encolar(new Solicitud("S4", Prioridad.NORMAL, 5));

        Cola<Solicitud> seleccionadas = Ejercicios.seleccionarSolicitudes(pendientes, 25);
        System.out.println("Seleccionadas: " + seleccionadas);
        System.out.println("Pendientes: " + pendientes);
    }
}