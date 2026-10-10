package co.edu.uniquindio.poo.algoritmos;

import co.edu.uniquindio.poo.modelo.Paquete;

import java.util.ArrayList;
import java.util.List;

/**
 * Algoritmos requeridos en el parcial: recursividad, búsqueda binaria
 * sobre paquetes ordenados por código y divide y vencerás.
 */
public class AlgoritmosPaquetes {

    private AlgoritmosPaquetes() {
        // Clase de utilidad: no requiere instancias.
    }

    /**
     * Suma recursivamente el peso de los paquetes que tienen el destino indicado.
     * Complejidad: O(n) tiempo y O(n) espacio de pila.
     */
    public static double pesoTotalPorDestino(List<Paquete> paquetes, String destino) {
        if (paquetes == null) {
            throw new IllegalArgumentException("La lista de paquetes no puede ser nula");
        }
        if (destino == null || destino.isBlank()) {
            throw new IllegalArgumentException("El destino no puede estar vacío");
        }
        return pesoTotalPorDestinoRecursivo(paquetes, destino, 0);
    }

    private static double pesoTotalPorDestinoRecursivo(
            List<Paquete> paquetes, String destino, int indice) {
        if (indice >= paquetes.size()) {
            return 0.0;
        }

        Paquete actual = paquetes.get(indice);
        double pesoActual = actual.getDestino().equalsIgnoreCase(destino)
                ? actual.getPeso() : 0.0;

        return pesoActual
                + pesoTotalPorDestinoRecursivo(paquetes, destino, indice + 1);
    }

    /**
     * Cuenta recursivamente los paquetes con prioridad mayor o igual al mínimo.
     * Complejidad: O(n) tiempo y O(n) espacio de pila.
     */
    public static int contarPrioridadMinima(List<Paquete> paquetes, int prioridadMinima) {
        if (paquetes == null) {
            throw new IllegalArgumentException("La lista de paquetes no puede ser nula");
        }
        if (prioridadMinima < 0 || prioridadMinima > 5) {
            throw new IllegalArgumentException("La prioridad mínima debe estar entre 0 y 5");
        }
        return contarPrioridadRecursivo(paquetes, prioridadMinima, 0);
    }

    private static int contarPrioridadRecursivo(
            List<Paquete> paquetes, int prioridadMinima, int indice) {
        if (indice >= paquetes.size()) {
            return 0;
        }

        int cuentaActual = paquetes.get(indice).getPrioridad() >= prioridadMinima ? 1 : 0;
        return cuentaActual
                + contarPrioridadRecursivo(paquetes, prioridadMinima, indice + 1);
    }

    /**
     * Búsqueda binaria por código. PRECONDICIÓN: la lista debe estar ordenada
     * ascendentemente por código (orden natural de Paquete).
     * Retorna el paquete encontrado o null si no existe.
     * Complejidad: O(log n) tiempo y O(1) espacio adicional.
     */
    public static Paquete busquedaBinariaPorCodigo(List<Paquete> paquetes, String codigo) {
        if (paquetes == null) {
            throw new IllegalArgumentException("La lista de paquetes no puede ser nula");
        }
        if (codigo == null) {
            return null;
        }

        int izquierda = 0;
        int derecha = paquetes.size() - 1;

        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;
            int comparacion = paquetes.get(medio).getCodigo().compareTo(codigo);

            if (comparacion == 0) {
                return paquetes.get(medio);
            } else if (comparacion < 0) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }
        return null;
    }

    /**
     * Retorna una copia ordenada por código para usarla con búsqueda binaria.
     * No modifica el orden de registro del gestor.
     */
    public static List<Paquete> ordenarPorCodigo(List<Paquete> paquetes) {
        if (paquetes == null) {
            throw new IllegalArgumentException("La lista de paquetes no puede ser nula");
        }
        List<Paquete> ordenados = new ArrayList<>(paquetes);
        ordenados.sort(null); // Usa Comparable<Paquete>, que ordena por código.
        return ordenados;
    }

    /**
     * Divide y vencerás: encuentra el paquete de mayor peso.
     * Caso base: un solo paquete. Divide: dos mitades. Combina: el más pesado.
     * Retorna null si la lista está vacía.
     * Complejidad: O(n) tiempo y O(log n) espacio de pila.
     */
    public static Paquete paqueteMasPesado(List<Paquete> paquetes) {
        if (paquetes == null) {
            throw new IllegalArgumentException("La lista de paquetes no puede ser nula");
        }
        if (paquetes.isEmpty()) {
            return null;
        }
        return paqueteMasPesadoRecursivo(paquetes, 0, paquetes.size() - 1);
    }

    private static Paquete paqueteMasPesadoRecursivo(
            List<Paquete> paquetes, int inicio, int fin) {
        if (inicio == fin) {
            return paquetes.get(inicio);
        }

        int medio = inicio + (fin - inicio) / 2;
        Paquete masPesadoIzquierda =
                paqueteMasPesadoRecursivo(paquetes, inicio, medio);
        Paquete masPesadoDerecha =
                paqueteMasPesadoRecursivo(paquetes, medio + 1, fin);

        return masPesadoIzquierda.getPeso() >= masPesadoDerecha.getPeso()
                ? masPesadoIzquierda : masPesadoDerecha;
    }
}
