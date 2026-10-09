
package co.edu.uniquindio.poo.modelo;

public class Paquete implements Comparable<Paquete> {

    // ATRIBUTOS
    private final String codigo;
    private String destino;
    private double peso;
    private int prioridad;
    private int tiempoEstimado;

    // CONSTRUCTOR
    public Paquete(String codigo, String destino, double peso,
                   int prioridad, int tiempoEstimado) {

        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException(
                "El código no puede estar vacío");
        }

        this.codigo = codigo;
        setDestino(destino);
        setPeso(peso);
        setPrioridad(prioridad);
        setTiempoEstimado(tiempoEstimado);
    }

    // GETTERS
    public String getCodigo() {
        return codigo;
    }

    public String getDestino() {
        return destino;
    }

    public double getPeso() {
        return peso;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    // SETTERS CON VALIDACIONES
    public void setDestino(String destino) {
        if (destino == null || destino.isBlank()) {
            throw new IllegalArgumentException(
                "El destino no puede estar vacío");
        }
        this.destino = destino;
    }

    public void setPeso(double peso) {
        if (!Double.isFinite(peso) || peso <= 0) {
            throw new IllegalArgumentException(
                "El peso debe ser mayor que cero");
        }
        this.peso = peso;
    }

    public void setPrioridad(int prioridad) {
        if (prioridad < 0 || prioridad > 5) {
            throw new IllegalArgumentException(
                "La prioridad debe estar entre 0 y 5");
        }
        this.prioridad = prioridad;
    }

    public void setTiempoEstimado(int tiempoEstimado) {
        if (tiempoEstimado <= 0) {
            throw new IllegalArgumentException(
                "El tiempo estimado debe ser positivo");
        }
        this.tiempoEstimado = tiempoEstimado;
    }

    // ORDEN NATURAL: CÓDIGO ASCENDENTE
    @Override
    public int compareTo(Paquete otro) {
        return this.codigo.compareTo(otro.codigo);
    }

    // REPRESENTACIÓN EN TEXTO
    @Override
    public String toString() {
        return "Paquete{" +
                "codigo='" + codigo + '\'' +
                ", destino='" + destino + '\'' +
                ", peso=" + peso +
                ", prioridad=" + prioridad +
                ", tiempoEstimado=" + tiempoEstimado +
                '}';
    }
}
