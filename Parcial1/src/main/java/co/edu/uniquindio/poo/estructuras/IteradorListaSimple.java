package co.edu.uniquindio.poo.estructuras;

/**
 * Iterador que recorre la lista simplemente enlazada de principio a fin,
 * entregando un elemento a la vez.
 *
 * Guarda en "actual" el nodo al que le toca entregar su dato. Recibe el
 * primer nodo por el constructor y no tiene "public" a propósito: así solo
 * las clases del paquete estructuras pueden crearlo, y el código de afuera
 * únicamente lo obtiene llamando a iterator() en la lista.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;


class IteradorListaSimple<T> implements Iterator<T> {

    private Nodo<T> actual;

    IteradorListaSimple(Nodo<T> primero) {
        this.actual = primero;
    }

    @Override
    public boolean hasNext() {
        return actual != null;
    }

    @Override
    public T next() {
        if (actual == null) {
            throw new NoSuchElementException();
        }
        T dato = actual.dato;
        actual = actual.siguiente;
        return dato;
    }
}


