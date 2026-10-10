package co.edu.uniquindio.poo.estructuras;

/**
 * Representa un nodo  de la lista enlazada.
 *
 * Cada nodo guarda dos cosas:
 *  - dato: el valor que se almacena (un String, un número, un Paquete, etc.).
 *  - siguiente: una referencia al nodo que viene después. Si es null,
 *    significa que este es el último nodo.
 */


public class Nodo<T> {
    T dato;
    Nodo<T> siguiente;

    public Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}
