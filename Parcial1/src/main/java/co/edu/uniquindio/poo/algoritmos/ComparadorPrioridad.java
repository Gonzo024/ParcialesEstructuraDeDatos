
package co.edu.uniquindio.poo.algoritmos;

import co.edu.uniquindio.poo.modelo.Paquete;
import java.util.Comparator;

public class ComparadorPrioridad implements Comparator<Paquete> {

    @Override
    public int compare(Paquete a, Paquete b) {
        int resultado = Integer.compare(
                b.getPrioridad(), a.getPrioridad());

        if (resultado != 0) {
            return resultado;
        }

        resultado = Integer.compare(
                a.getTiempoEstimado(), b.getTiempoEstimado());

        if (resultado != 0) {
            return resultado;
        }

        return a.getCodigo().compareTo(b.getCodigo());
    }
}
