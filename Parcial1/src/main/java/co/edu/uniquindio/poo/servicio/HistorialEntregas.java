package co.edu.uniquindio.poo.servicio;

/*+
 * Usa la lista simplemente enlazada propia para
 * conservar el orden en que se entregaron los paquetes: el primero
 * entregado queda de primero y el último, al final.
 */

import co.edu.uniquindio.poo.estructuras.ListaSimplementeEnlazada;
import co.edu.uniquindio.poo.modelo.Paquete;

public class HistorialEntregas {

    private ListaSimplementeEnlazada<Paquete> historial = new ListaSimplementeEnlazada<>();

    public void registrarEntrega(Paquete paquete) {
        historial.agregar(paquete);
    }

    public int cantidadEntregas() {
        return historial.tamanio();
    }

    public void mostrar() {
        for (Paquete p : historial) {
        System.out.println(p);
        }
    }
}


