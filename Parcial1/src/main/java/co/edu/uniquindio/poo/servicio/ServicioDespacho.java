package co.edu.uniquindio.poo.servicio;

import co.edu.uniquindio.poo.modelo.Paquete;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Administra las dos modalidades de despacho solicitadas en el parcial:
 * orden de llegada (FIFO) y orden de prioridad.
 *
 * Los paquetes se obtienen del GestorDistribucion. Los códigos ya despachados
 * se registran para evitar que el mismo paquete salga dos veces, aunque se
 * consulten ambas modalidades.
 */
public class ServicioDespacho {

    private final GestorDistribucion gestor;
    private final Queue<Paquete> colaPorLlegada;
    private final PriorityQueue<Paquete> colaPorPrioridad;
    private final Set<String> codigosEncolados;
    private final Set<String> codigosDespachados;

    public ServicioDespacho(GestorDistribucion gestor) {
        if (gestor == null) {
            throw new IllegalArgumentException("El gestor no puede ser nulo");
        }

        this.gestor = gestor;
        this.colaPorLlegada = new java.util.LinkedList<>();
        this.colaPorPrioridad = new PriorityQueue<>(
                Comparator.comparingInt(Paquete::getPrioridad).reversed()
                        .thenComparingInt(Paquete::getTiempoEstimado)
                        .thenComparing(Paquete::getCodigo)
        );
        this.codigosEncolados = new HashSet<>();
        this.codigosDespachados = new HashSet<>();
        sincronizarPaquetes();
    }

    /**
     * Agrega a las colas los paquetes registrados desde la última sincronización.
     * La lista del gestor conserva el orden original de registro.
     */
    public void sincronizarPaquetes() {
        for (Paquete paquete : gestor.obtenerPaquetes()) {
            String codigo = paquete.getCodigo();
            if (!codigosEncolados.contains(codigo)
                    && !codigosDespachados.contains(codigo)) {
                colaPorLlegada.offer(paquete);
                colaPorPrioridad.offer(paquete);
                codigosEncolados.add(codigo);
            }
        }
    }

    /**
     * Retorna y marca como despachado el primer paquete pendiente por llegada.
     * Retorna null si no quedan paquetes disponibles.
     */
    public Paquete despacharSiguientePorLlegada() {
        sincronizarPaquetes();

        while (!colaPorLlegada.isEmpty()) {
            Paquete paquete = colaPorLlegada.poll();
            if (codigosDespachados.add(paquete.getCodigo())) {
                return paquete;
            }
        }
        return null;
    }

    /**
     * Retorna y marca como despachado el paquete de mayor prioridad.
     * Desempates: menor tiempo estimado y luego código ascendente.
     * Retorna null si no quedan paquetes disponibles.
     */
    public Paquete despacharSiguientePorPrioridad() {
        sincronizarPaquetes();

        while (!colaPorPrioridad.isEmpty()) {
            Paquete paquete = colaPorPrioridad.poll();
            if (codigosDespachados.add(paquete.getCodigo())) {
                return paquete;
            }
        }
        return null;
    }

    public int cantidadPendientePorLlegada() {
        sincronizarPaquetes();
        int cantidad = 0;
        for (Paquete paquete : colaPorLlegada) {
            if (!codigosDespachados.contains(paquete.getCodigo())) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public boolean estaDespachado(String codigo) {
        return codigosDespachados.contains(codigo);
    }


    /**
    * Consulta el siguiente paquete por orden de llegada
    * sin marcarlo como despachado.
    */
    public Paquete consultarSiguientePorLlegada() {
        sincronizarPaquetes();

        while (!colaPorLlegada.isEmpty()) {
            Paquete paquete = colaPorLlegada.peek();

         if (codigosDespachados.contains(paquete.getCodigo())) {
                colaPorLlegada.poll();
            } else {
                return paquete;
            }
        }

     return null;
    }

    /**
    * Consulta el siguiente paquete por prioridad
    * sin marcarlo como despachado.
    */
    public Paquete consultarSiguientePorPrioridad() {
     sincronizarPaquetes();

        while (!colaPorPrioridad.isEmpty()) {
            Paquete paquete = colaPorPrioridad.peek();

            if (codigosDespachados.contains(paquete.getCodigo())) {
                colaPorPrioridad.poll();
            } else {
                return paquete;
         }
     }

     return null;
    }

    /**
    * Confirma el despacho únicamente después
    * de una asignación exitosa.
    */
    public boolean confirmarDespacho(String codigo) {
        sincronizarPaquetes();

        if (!codigosEncolados.contains(codigo)
             || codigosDespachados.contains(codigo)) {
         return false;
        }

        codigosDespachados.add(codigo);
        return true;
    }

}
