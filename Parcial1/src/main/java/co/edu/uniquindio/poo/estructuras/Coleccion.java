package co.edu.uniquindio.poo.estructuras;

/**
 * Define QUÉ operaciones debe ofrecer una colección (agregar, eliminar,
 * obtener, conocer el tamaño y saber si está vacía).
 */

public interface Coleccion<T> {
    void agregar(T elemento);
    boolean eliminar(T elemento);
    T obtener(int posicion);
    int tamanio();
    boolean estaVacia();
}
