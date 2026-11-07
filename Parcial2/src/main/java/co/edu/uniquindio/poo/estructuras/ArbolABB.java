
package co.edu.uniquindio.poo.estructuras;

import java.util.ArrayList;

public class ArbolABB<T extends Comparable<T>> {

    private NodoArbol<T> raiz;

    public ArbolABB() {
        raiz = null;
    }

    public NodoArbol<T> getRaiz() {
        return raiz;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public void insertar(T dato) {
        raiz = insertarRecursivo(raiz, dato);
    }

    private NodoArbol<T> insertarRecursivo(NodoArbol<T> actual, T dato) {

        // Caso 1: encontramos un espacio vacío
        if (actual == null) {
            return new NodoArbol<>(dato);
        }

        // Caso 2: el dato es menor, va a la izquierda
        if (dato.compareTo(actual.getDato()) < 0) {
            actual.setIzquierdo(
                    insertarRecursivo(actual.getIzquierdo(), dato)
            );
        }

        // Caso 3: el dato es mayor, va a la derecha
        else if (dato.compareTo(actual.getDato()) > 0) {
            actual.setDerecho(
                    insertarRecursivo(actual.getDerecho(), dato)
            );
        }

        return actual;
    }


    // ==================== PREORDEN ====================

    public ArrayList<T> preorden() {
        ArrayList<T> resultado = new ArrayList<>();
        recorrerPreorden(raiz, resultado);
        return resultado;
    }

    private void recorrerPreorden(NodoArbol<T> actual, ArrayList<T> resultado) {
        if (actual != null) {
            resultado.add(actual.getDato());
            recorrerPreorden(actual.getIzquierdo(), resultado);
            recorrerPreorden(actual.getDerecho(), resultado);
        }
    }

    // ==================== INORDEN ====================

    public ArrayList<T> inorden() {
        ArrayList<T> resultado = new ArrayList<>();
        recorrerInorden(raiz, resultado);
        return resultado;
    }

    private void recorrerInorden(NodoArbol<T> actual, ArrayList<T> resultado) {
        if (actual != null) {
            recorrerInorden(actual.getIzquierdo(), resultado);
            resultado.add(actual.getDato());
            recorrerInorden(actual.getDerecho(), resultado);
        }
    }

    // ==================== POSTORDEN ====================

    public ArrayList<T> postorden() {
        ArrayList<T> resultado = new ArrayList<>();
        recorrerPostorden(raiz, resultado);
        return resultado;
    }

    private void recorrerPostorden(NodoArbol<T> actual, ArrayList<T> resultado) {
        if (actual != null) {
            recorrerPostorden(actual.getIzquierdo(), resultado);
            recorrerPostorden(actual.getDerecho(), resultado);
            resultado.add(actual.getDato());
        }
    }


    // ==================== AMPLITUD (BFS) ====================

    public ArrayList<T> amplitud() {
        ArrayList<T> resultado = new ArrayList<>();

        if (raiz == null) {
            return resultado;
        }

        Cola<NodoArbol<T>> cola = new Cola<>();
        cola.encolar(raiz);

        while (!cola.estaVacia()) {
            NodoArbol<T> actual = cola.desencolar();

            resultado.add(actual.getDato());

            if (actual.getIzquierdo() != null) {
                cola.encolar(actual.getIzquierdo());
            }

            if (actual.getDerecho() != null) {
                cola.encolar(actual.getDerecho());
            }
        }

        return resultado;
    }


}
