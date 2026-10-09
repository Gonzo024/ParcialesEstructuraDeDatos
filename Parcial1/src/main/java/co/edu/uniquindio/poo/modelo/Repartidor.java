package co.edu.uniquindio.poo.modelo;

public class Repartidor {

    // ATRIBUTOS
    private final String identificacion;
    private String nombre;
    private String zona;
    private boolean disponible;
    private int paquetesAsignados;

    // CONSTANTE
    private static final int MAX_PAQUETES = 5;

    // CONSTRUCTOR
    public Repartidor(String identificacion, String nombre,
                      String zona) {

        if (identificacion == null ||
                identificacion.isBlank()) {
            throw new IllegalArgumentException(
                    "La identificación no puede estar vacía");
        }

        this.identificacion = identificacion;
        setNombre(nombre);
        setZona(zona);

        this.disponible = true;
        this.paquetesAsignados = 0;
    }

    // GETTERS
    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getZona() {
        return zona;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public int getPaquetesAsignados() {
        return paquetesAsignados;
    }

    // SETTERS CON VALIDACIONES
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre no puede estar vacío");
        }
        this.nombre = nombre;
    }

    public void setZona(String zona) {
        if (zona == null || zona.isBlank()) {
            throw new IllegalArgumentException(
                    "La zona no puede estar vacía");
        }
        this.zona = zona;
    }

    public void setDisponible(boolean disponible) {
        if (disponible && paquetesAsignados >= MAX_PAQUETES) {
            throw new IllegalStateException(
                    "El repartidor alcanzó su capacidad máxima");
        }
        this.disponible = disponible;
    }

    // VERIFICAR CAPACIDAD
    public boolean puedeRecibirPaquete() {
        return disponible && paquetesAsignados < MAX_PAQUETES;
    }

    // REGISTRAR UNA ASIGNACIÓN
    public void asignarPaquete() {
        if (!puedeRecibirPaquete()) {
            throw new IllegalStateException(
                    "El repartidor no puede recibir más paquetes");
        }

        paquetesAsignados++;

        if (paquetesAsignados == MAX_PAQUETES) {
            disponible = false;
        }
    }

    // REGISTRAR UNA ENTREGA
    public void completarEntrega() {
        if (paquetesAsignados <= 0) {
            throw new IllegalStateException(
                    "El repartidor no tiene paquetes asignados");
        }

        paquetesAsignados--;
        disponible = true;
    }

    // REPRESENTACIÓN EN TEXTO
    @Override
    public String toString() {
        return "Repartidor{" +
                "identificacion='" + identificacion + '\'' +
                ", nombre='" + nombre + '\'' +
                ", zona='" + zona + '\'' +
                ", disponible=" + disponible +
                ", paquetesAsignados=" + paquetesAsignados +
                '}';
    }
}
