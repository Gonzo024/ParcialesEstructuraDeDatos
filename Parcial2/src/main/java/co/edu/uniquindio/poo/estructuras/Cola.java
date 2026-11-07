package co.edu.uniquindio.poo.estructuras;

public class Cola<T> {

    private Nodo<T> frente;
    private Nodo<T> fin;
    private int tamanio;

    public Cola() {
        frente = null;
        fin = null;
        tamanio = 0;
    }

    public void encolar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (estaVacia()) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
        tamanio++;
    }

    public T desencolar() {
        if (estaVacia()) {
            return null;
        }
        T dato = frente.getDato();
        frente = frente.getSiguiente();
        if (frente == null) {
            fin = null;
        }
        tamanio--;
        return dato;
    }

    public T verFrente() {
        if (estaVacia()) {
            return null;
        }
        return frente.getDato();
    }

    public boolean estaVacia() { return frente == null; }

    public int tamanio() { return tamanio; }

    @Override
    public String toString() {
        String texto = "[";
        Nodo<T> actual = frente;
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
