
package co.edu.uniquindio.poo.estructuras;

public class Pila<T> {

    private Nodo<T> cima;
    private int tamanio;

    public Pila() {
        cima = null;
        tamanio = 0;
    }

    public void apilar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.setSiguiente(cima);
        cima = nuevo;
        tamanio++;
    }

    public T desapilar() {
        if (estaVacia()) {
            return null;
        }

        T dato = cima.getDato();
        cima = cima.getSiguiente();
        tamanio--;

        return dato;
    }

    public T verCima() {
        if (estaVacia()) {
            return null;
        }

        return cima.getDato();
    }

    public boolean estaVacia() {
        return cima == null;
    }

    public int tamanio() {
        return tamanio;
    }

    @Override
    public String toString() {
        String texto = "[";
        Nodo<T> actual = cima;

        while (actual != null) {
            texto += actual.getDato();

            if (actual.getSiguiente() != null) {
                texto += ", ";
            }

            actual = actual.getSiguiente();
        }

        return texto + "]";
    }
}
