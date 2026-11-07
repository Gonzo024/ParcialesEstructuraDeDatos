package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.ejercicios.Ejercicios;
import co.edu.uniquindio.poo.estructuras.ArbolABB;
import co.edu.uniquindio.poo.estructuras.NodoArbol;

/**
 * Pruebas del punto 4.a: contarEquiposEnRango.
 * Usa el ArbolABB del proyecto.
 */
public class PruebaPunto4 {

    private static int aprobadas = 0;
    private static int fallidas = 0;

    public static void main(String[] args) {
        ArbolABB<Integer> arbol = crearArbolEjemplo();
        NodoArbol<Integer> raiz = arbol.getRaiz();

        System.out.println("=== PRUEBAS DE contarEquiposEnRango ===");
        // Arbol: 50, 30, 70, 20, 40, 60, 80, 55, 65
        verificar("Rango [35, 62] -> 40, 50, 55, 60 = 4",
                Ejercicios.contarEquiposEnRango(raiz, 35, 62) == 4);
        verificar("Rango [60, 80] -> 60, 65, 70, 80 = 4",
                Ejercicios.contarEquiposEnRango(raiz, 60, 80) == 4);
        verificar("Rango [45, 50] -> 50 = 1",
                Ejercicios.contarEquiposEnRango(raiz, 45, 50) == 1);
        verificar("Rango que cubre todo [0, 100] = 9",
                Ejercicios.contarEquiposEnRango(raiz, 0, 100) == 9);
        verificar("Extremos incluidos [20, 80] = 9",
                Ejercicios.contarEquiposEnRango(raiz, 20, 80) == 9);
        verificar("Limites iguales y el codigo existe [55, 55] = 1",
                Ejercicios.contarEquiposEnRango(raiz, 55, 55) == 1);
        verificar("Limites iguales y el codigo no existe [56, 56] = 0",
                Ejercicios.contarEquiposEnRango(raiz, 56, 56) == 0);
        verificar("Rango sin codigos [41, 49] = 0",
                Ejercicios.contarEquiposEnRango(raiz, 41, 49) == 0);
        verificar("Rango por debajo de todos [1, 10] = 0",
                Ejercicios.contarEquiposEnRango(raiz, 1, 10) == 0);
        verificar("Rango por encima de todos [90, 99] = 0",
                Ejercicios.contarEquiposEnRango(raiz, 90, 99) == 0);
        verificar("Inferior mayor que superior [62, 35] = 0",
                Ejercicios.contarEquiposEnRango(raiz, 62, 35) == 0);

        ArbolABB<Integer> vacio = new ArbolABB<>();
        verificar("Arbol vacio = 0",
                Ejercicios.contarEquiposEnRango(vacio.getRaiz(), 0, 100) == 0);

        arbol.insertar(40);
        verificar("Un codigo repetido no se cuenta dos veces",
                Ejercicios.contarEquiposEnRango(arbol.getRaiz(), 0, 100) == 9);

        System.out.println();
        System.out.println("Aprobadas: " + aprobadas + "  Fallidas: " + fallidas);
    }

    /** Crea el ABB del punto 4.b: 50, 30, 70, 20, 40, 60, 80, 55, 65. */
    private static ArbolABB<Integer> crearArbolEjemplo() {
        ArbolABB<Integer> arbol = new ArbolABB<>();
        arbol.insertar(50);
        arbol.insertar(30);
        arbol.insertar(70);
        arbol.insertar(20);
        arbol.insertar(40);
        arbol.insertar(60);
        arbol.insertar(80);
        arbol.insertar(55);
        arbol.insertar(65);
        return arbol;
    }

    private static void verificar(String nombre, boolean condicion) {
        if (condicion) {
            System.out.println("[OK]    " + nombre);
            aprobadas++;
        } else {
            System.out.println("[FALLA] " + nombre);
            fallidas++;
        }
    }
}
