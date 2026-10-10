
package co.edu.uniquindio.poo.servicio;

import co.edu.uniquindio.poo.modelo.Paquete;
import co.edu.uniquindio.poo.modelo.Repartidor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class GestorDistribucion {

    // Registro oficial de paquetes
    private final List<Paquete> paquetes;
    private final Map<String, Paquete> paquetesPorCodigo;

    // Registro oficial de repartidores
    private final Map<String, Repartidor> repartidoresPorId;
    private final ServicioDespacho servicioDespacho;
    private final ServicioAsignacion servicioAsignacion;

    public GestorDistribucion() {
        paquetes = new ArrayList<>();
        paquetesPorCodigo = new HashMap<>();
        repartidoresPorId = new HashMap<>();

        servicioDespacho = new ServicioDespacho(this);
        servicioAsignacion = new ServicioAsignacion(this);
    }

    // ========== PAQUETES ==========

    public void registrarPaquete(Paquete paquete) {
        if (paquete == null) {
            throw new IllegalArgumentException(
                "El paquete no puede ser nulo");
        }

        String codigo = paquete.getCodigo();

        if (paquetesPorCodigo.containsKey(codigo)) {
            throw new IllegalArgumentException(
                "Ya existe un paquete con código: " + codigo);
        }

        paquetes.add(paquete);
        paquetesPorCodigo.put(codigo, paquete);
    }

    public Paquete buscarPaquete(String codigo) {
        return paquetesPorCodigo.get(codigo);
    }

    public boolean existePaquete(String codigo) {
        return paquetesPorCodigo.containsKey(codigo);
    }

    public List<Paquete> obtenerPaquetes() {
        return new ArrayList<>(paquetes);
    }

    public int cantidadPaquetes() {
        return paquetes.size();
    }

    // ========== REPARTIDORES ==========

    public void registrarRepartidor(Repartidor repartidor) {
        if (repartidor == null) {
            throw new IllegalArgumentException(
                "El repartidor no puede ser nulo");
        }

        String id = repartidor.getIdentificacion();

        if (repartidoresPorId.containsKey(id)) {
            throw new IllegalArgumentException(
                "Ya existe un repartidor con ID: " + id);
        }

        repartidoresPorId.put(id, repartidor);
    }

    public Repartidor buscarRepartidor(String id) {
        return repartidoresPorId.get(id);
    }

    public boolean existeRepartidor(String id) {
        return repartidoresPorId.containsKey(id);
    }

    public List<Repartidor> obtenerRepartidores() {
        return new ArrayList<>(repartidoresPorId.values());
    }

    public int cantidadRepartidores() {
        return repartidoresPorId.size();
    }

    // ========== MUNICIPIOS ==========

    public Set<String> obtenerMunicipios() {
        Set<String> municipios = new HashSet<>();

        for (Paquete paquete : paquetes) {
            municipios.add(paquete.getDestino());
        }

        return municipios;
    }

    public Set<String> obtenerMunicipiosOrdenados() {
        Set<String> municipios = new TreeSet<>();

        for (Paquete paquete : paquetes) {
            municipios.add(paquete.getDestino());
        }

        return municipios;
    }

    // ========== AGRUPACIÓN POR DESTINO ==========

    public Map<String, List<Paquete>> agruparPorDestino() {
        Map<String, List<Paquete>> grupos = new HashMap<>();

        for (Paquete paquete : paquetes) {
            String destino = paquete.getDestino();

            grupos.putIfAbsent(destino, new ArrayList<>());
            grupos.get(destino).add(paquete);
        }

        return grupos;
    }


// ========== DESPACHO Y ASIGNACIÓN ==========

/**
 * Despacha el siguiente paquete por orden de llegada,
 * siempre que exista un repartidor compatible.
 */
public Paquete despacharPorLlegada() {

    Paquete paquete =
        servicioDespacho.consultarSiguientePorLlegada();

    return asignarYConfirmarDespacho(paquete);
}

/**
 * Despacha el siguiente paquete según su prioridad.
 */
public Paquete despacharPorPrioridad() {

    Paquete paquete =
        servicioDespacho.consultarSiguientePorPrioridad();

    return asignarYConfirmarDespacho(paquete);
}

/**
 * Coordina la asignación y la confirmación del despacho.
 */
private Paquete asignarYConfirmarDespacho(Paquete paquete) {

    if (paquete == null) {
        return null;
    }

    Repartidor repartidor =
        servicioAsignacion.asignarPaquete(paquete);

    // Si no hay repartidor, el paquete sigue pendiente.
    if (repartidor == null) {
        return null;
    }

    boolean confirmado =
        servicioDespacho.confirmarDespacho(paquete.getCodigo());

    if (!confirmado) {
        throw new IllegalStateException(
            "No fue posible confirmar el despacho del paquete "
            + paquete.getCodigo());
    }

    return paquete;
}

    /**
    * Consulta qué repartidor tiene asignado un paquete.
     */
    public Repartidor consultarAsignacion(String codigoPaquete) {
        return servicioAsignacion.consultarAsignacion(codigoPaquete);
    }

    /**
    * Consulta cuántos paquetes siguen pendientes
    * de despacho por orden de llegada.
    */
    public int cantidadPendientes() {
     return servicioDespacho.cantidadPendientePorLlegada();
    }

    /**
    * Consulta si un paquete ya fue despachado.
    */
    public boolean estaDespachado(String codigoPaquete) {
        return servicioDespacho.estaDespachado(codigoPaquete);
    }

}
