
package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.estructuras.ListaSimplementeEnlazada;
import java.util.Iterator;

public class PruebaListaEnlazada {

    private static int aprobadas = 0;
    private static int fallidas = 0;

    public static void main(String[] args) {

        System.out.println("=== PRUEBAS DE LISTA SIMPLEMENTE ENLAZADA ===");

        // 1. Lista vacia
        ListaSimplementeEnlazada<String> lista =
                new ListaSimplementeEnlazada<>();

        verificar("Lista inicialmente vacia",
                lista.estaVacia() && lista.tamanio() == 0);

        // 2. Agregar elementos
        lista.agregar("A");
        lista.agregar("B");
        lista.agregar("C");
        lista.agregar("D");

        verificar("Agregar cuatro elementos",
                lista.tamanio() == 4 && !lista.estaVacia());

        // 3. Obtener por posicion
        verificar("Obtener por indice",
                "A".equals(lista.obtener(0))
                && "C".equals(lista.obtener(2))
                && "D".equals(lista.obtener(3)));

        // 4. Eliminar primer nodo
        verificar("Eliminar primer nodo",
                lista.eliminar("A")
                && lista.tamanio() == 3
                && "B".equals(lista.obtener(0)));

        // 5. Eliminar nodo intermedio
        verificar("Eliminar nodo intermedio",
                lista.eliminar("C")
                && lista.tamanio() == 2
                && "B".equals(lista.obtener(0))
                && "D".equals(lista.obtener(1)));

        // 6. Eliminar ultimo nodo
        verificar("Eliminar ultimo nodo",
                lista.eliminar("D")
                && lista.tamanio() == 1
                && "B".equals(lista.obtener(0)));

        // 7. Eliminar elemento inexistente
        verificar("Eliminar elemento inexistente",
                !lista.eliminar("Z")
                && lista.tamanio() == 1);

        // 8. Recorrido con Iterator
        Iterator<String> iterador = lista.iterator();

        verificar("Iterator recorre correctamente",
                iterador.hasNext()
                && "B".equals(iterador.next())
                && !iterador.hasNext());

        // 9. Eliminar el unico elemento
        verificar("Eliminar unico elemento",
                lista.eliminar("B")
                && lista.estaVacia()
                && lista.tamanio() == 0);

        // 10. Eliminar en una lista vacia
        verificar("Eliminar de lista vacia",
                !lista.eliminar("X")
                && lista.estaVacia());

        // 11. Agregar nuevamente despues de vaciar
        lista.agregar("NUEVO");

        verificar("Reutilizar lista despues de vaciar",
                lista.tamanio() == 1
                && "NUEVO".equals(lista.obtener(0)));

        // 12. Indice fuera de rango
        boolean excepcionCorrecta = false;

        try {
            lista.obtener(99);
        } catch (IndexOutOfBoundsException e) {
            excepcionCorrecta = true;
        }

        verificar("Rechazar indice fuera de rango",
                excepcionCorrecta);

        // Resultado final
        System.out.println("\n=== RESULTADO FINAL ===");
        System.out.println("Pruebas aprobadas: " + aprobadas);
        System.out.println("Pruebas fallidas: " + fallidas);

        if (fallidas == 0) {
            System.out.println("TODAS LAS PRUEBAS APROBADAS");
        } else {
            throw new AssertionError(
                    "Hay " + fallidas + " pruebas fallidas");
        }
    }

    private static void verificar(String nombre, boolean condicion) {
        if (condicion) {
            aprobadas++;
            System.out.println("[APROBADO] " + nombre);
        } else {
            fallidas++;
            System.out.println("[FALLO] " + nombre);
        }
    }
}
