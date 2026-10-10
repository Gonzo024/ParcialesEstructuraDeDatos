package co.edu.uniquindio.poo.servicio;

/**
 * Historial de paquetes entregados.
 *
 * Usa la lista simplemente enlazada propia (ListaSimplementeEnlazada) para
 * conservar el orden exacto en que se entregaron los paquetes: el primero
 * entregado queda de primero y el último, al final.
 *
 * La lista es genérica y no sabe nada de paquetes; esta clase es la que la
 * usa con el tipo Paquete.
 */

import co.edu.uniquindio.poo.estructuras.ListaSimplementeEnlazada;
import co.edu.uniquindio.poo.modelo.Paquete;

public class HistorialEntregas {

    private ListaSimplementeEnlazada<Paquete> historial = new ListaSimplementeEnlazada<>();

    public void registrarEntrega(Paquete paquete) {
        historial.agregar(paquete);          // queda al final: orden real de entrega
    }

    public int cantidadEntregas() {
        return historial.tamanio();
    }

    public void mostrar() {
        for (Paquete p : historial) {        // recorre sin ver los nodos
            System.out.println(p);
        }
    }
}


