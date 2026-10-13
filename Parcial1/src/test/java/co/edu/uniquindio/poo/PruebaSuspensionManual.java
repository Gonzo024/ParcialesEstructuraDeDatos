
package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.modelo.Repartidor;

public class PruebaSuspensionManual {

    public static void main(String[] args) {

        int aprobadas = 0;
        int fallidas = 0;

        // Prueba 1: la suspensión manual se mantiene
        // después de completar una entrega.
        Repartidor repartidor =
                new Repartidor("R001", "Ana", "Armenia");

        repartidor.asignarPaquete();
        repartidor.setDisponible(false);
        repartidor.completarEntrega();

        if (!repartidor.isDisponible()
                && repartidor.getPaquetesAsignados() == 0
                && !repartidor.puedeRecibirPaquete()) {

            System.out.println(
                    "[APROBADO] Se mantiene la suspensión manual");
            aprobadas++;
        } else {
            System.out.println(
                    "[FALLO] Se perdió la suspensión manual");
            fallidas++;
        }

        // Prueba 2: al reactivar manualmente,
        // puede volver a recibir paquetes.
        repartidor.setDisponible(true);

        if (repartidor.isDisponible()
                && repartidor.puedeRecibirPaquete()) {

            System.out.println(
                    "[APROBADO] Reactivación manual correcta");
            aprobadas++;
        } else {
            System.out.println(
                    "[FALLO] No se pudo reactivar el repartidor");
            fallidas++;
        }

        // Prueba 3: completar una entrega sin
        // suspensión manual libera el cupo.
        Repartidor repartidor2 =
                new Repartidor("R002", "Luis", "Armenia");

        repartidor2.asignarPaquete();
        repartidor2.completarEntrega();

        if (repartidor2.isDisponible()
                && repartidor2.getPaquetesAsignados() == 0) {

            System.out.println(
                    "[APROBADO] Se libera el cupo normalmente");
            aprobadas++;
        } else {
            System.out.println(
                    "[FALLO] No se liberó el cupo correctamente");
            fallidas++;
        }

        System.out.println("\n=== RESULTADO FINAL ===");
        System.out.println("Pruebas aprobadas: " + aprobadas);
        System.out.println("Pruebas fallidas: " + fallidas);

        if (fallidas > 0) {
            throw new AssertionError(
                    "Hay pruebas fallidas");
        }
    }
}
