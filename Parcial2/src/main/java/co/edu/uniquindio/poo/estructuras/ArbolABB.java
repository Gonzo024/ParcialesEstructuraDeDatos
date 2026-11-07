
package co.edu.uniquindio.poo.estructuras;

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
}
