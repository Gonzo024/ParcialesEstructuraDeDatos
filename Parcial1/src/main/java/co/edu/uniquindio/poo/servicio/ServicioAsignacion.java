package co.edu.uniquindio.poo.servicio;

import co.edu.uniquindio.poo.modelo.Paquete;
import co.edu.uniquindio.poo.modelo.Repartidor;

import java.util.HashMap;
import java.util.Map;

/**
 * Asigna paquetes a repartidores que estén disponibles, tengan capacidad
 * y trabajen en la zona de destino del paquete.
 */
public class ServicioAsignacion {

    private final GestorDistribucion gestor;
    private final Map<String, Repartidor> asignacionesPorCodigo;

    public ServicioAsignacion(GestorDistribucion gestor) {
        if (gestor == null) {
            throw new IllegalArgumentException("El gestor no puede ser nulo");
        }
        this.gestor = gestor;
        this.asignacionesPorCodigo = new HashMap<>();
    }

    /**
     * Busca un repartidor compatible y registra la asignación.
     * Retorna el repartidor asignado o null si no existe uno compatible.
     */
    public Repartidor asignarPaquete(Paquete paquete) {
        if (paquete == null) {
            throw new IllegalArgumentException("El paquete no puede ser nulo");
        }

        String codigo = paquete.getCodigo();
        if (asignacionesPorCodigo.containsKey(codigo)) {
            return asignacionesPorCodigo.get(codigo);
        }

        for (Repartidor repartidor : gestor.obtenerRepartidores()) {
            boolean mismaZona = repartidor.getZona().equalsIgnoreCase(paquete.getDestino());
            if (mismaZona && repartidor.puedeRecibirPaquete()) {
                repartidor.asignarPaquete();
                asignacionesPorCodigo.put(codigo, repartidor);
                return repartidor;
            }
        }
        return null;
    }

    public Repartidor consultarAsignacion(String codigoPaquete) {
        return asignacionesPorCodigo.get(codigoPaquete);
    }

    /**
     * Registra que la entrega fue completada y libera capacidad del repartidor.
     * Retorna false si el paquete no tenía una asignación registrada.
     */
    public boolean completarEntrega(String codigoPaquete) {
        Repartidor repartidor = asignacionesPorCodigo.remove(codigoPaquete);
        if (repartidor == null) {
            return false;
        }
        repartidor.completarEntrega();
        return true;
    }

    public int cantidadAsignacionesActivas() {
        return asignacionesPorCodigo.size();
    }
}
