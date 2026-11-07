
package co.edu.uniquindio.poo.modelo;

public class Operacion {

    public enum Estado {
        CONFIRMADA,
        ANULADA
    }

    private String codigo;
    private Estado estado;

    public Operacion(String codigo, Estado estado) {
        this.codigo = codigo;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public Estado getEstado() {
        return estado;
    }

    public boolean estaConfirmada() {
        return estado == Estado.CONFIRMADA;
    }

    @Override
    public String toString() {
        return codigo + " (" + estado + ")";
    }
}
