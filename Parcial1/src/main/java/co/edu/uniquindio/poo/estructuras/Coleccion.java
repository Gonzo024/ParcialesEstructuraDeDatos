package co.edu.uniquindio.poo.estructuras;

/**
 * Contrato genérico de las colecciones propias del sistema.
 *
 * Define QUÉ operaciones debe ofrecer una colección (agregar, eliminar,
 * obtener, conocer el tamaño y saber si está vacía), pero no dice CÓMO se
 * hacen. Cada clase que la implemente decide su forma de hacerlo; en este
 * proyecto la implementa ListaSimplementeEnlazada.
 */

public interface Coleccion<T> {
    void agregar(T elemento);
    boolean eliminar(T elemento);
    T obtener(int posicion);
    int tamanio();
    boolean estaVacia();
}
