package co.edu.uniquindio.poo.estructuras;

/**
 * Lista simplemente enlazada genérica, implementada desde cero (sin usar
 * ArrayList, LinkedList ni otras colecciones de Java para guardar datos).
 *
 * Funcionamiento:
 *  - Los elementos se guardan en nodos conectados uno detrás de otro.
 *  - nodoPrimero apunta al inicio de la lista y nodoUltimo al final.
 *  - tamanio lleva la cuenta de cuántos elementos hay.
 *
 * Se guarda nodoUltimo para que agregar al final no
 * tenga que recorrer la lista (cuesta O(1)). El historial de entregas solo
 * agrega al final, por eso conviene.
 *
 * Complejidad: agregar O(1), obtener O(n), eliminar O(n),
 * tamanio y estaVacia O(1).
 *
 * Implementa Coleccion<T> (el contrato de operaciones) e Iterable<T> para
 * poder recorrerla con for-each sin que el código externo conozca los nodos.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaSimplementeEnlazada<T> implements Coleccion<T>, Iterable<T> {

    private Nodo<T> nodoPrimero;
    private Nodo<T> nodoUltimo;
    private int tamanio;

    public ListaSimplementeEnlazada() {
        nodoPrimero = null;
        nodoUltimo = null;
        tamanio = 0;
    }

    @Override
    public void agregar(T elemento) {
        Nodo<T> nuevo = new Nodo<>(elemento);
        if (estaVacia()) {
            nodoPrimero = nuevo;
            nodoUltimo = nuevo;
        } else {
            nodoUltimo.siguiente = nuevo;
            nodoUltimo = nuevo;
        }
        tamanio++;
    }

    @Override
    public boolean eliminar(T elemento) {
        if (estaVacia()) {
            return false;
        }

        if (nodoPrimero.dato.equals(elemento)) {
            nodoPrimero = nodoPrimero.siguiente;
            if (nodoPrimero == null) {      // la lista quedó vacía
                nodoUltimo = null;
            }
            tamanio--;
            return true;
        }

        Nodo<T> anterior = nodoPrimero;
        Nodo<T> actual = nodoPrimero.siguiente;
        while (actual != null) {
            if (actual.dato.equals(elemento)) {
                anterior.siguiente = actual.siguiente;
                if (actual == nodoUltimo) {  // borré el último
                    nodoUltimo = anterior;
                }
                tamanio--;
                return true;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
        return false;
    }

    @Override
    public T obtener(int posicion) {
        if (posicion < 0 || posicion >= tamanio) {
            throw new IndexOutOfBoundsException("Posición inválida: " + posicion);
        }
        Nodo<T> actual = nodoPrimero;
        for (int i = 0; i < posicion; i++) {
            actual = actual.siguiente;
        }
        return actual.dato;
    }

    @Override
    public int tamanio() {
        return tamanio;
    }

    @Override
    public boolean estaVacia() {
        return tamanio == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new IteradorListaSimple<>(nodoPrimero);
    }



}