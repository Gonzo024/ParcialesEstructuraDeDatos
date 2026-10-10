
package co.edu.uniquindio.poo.servicio;

import co.edu.uniquindio.poo.modelo.Paquete;
import co.edu.uniquindio.poo.modelo.Repartidor;

public class DatosIniciales {

    private DatosIniciales() {
        // Evita crear objetos de esta clase de utilidad.
    }

    public static void cargar(GestorDistribucion gestor) {

        if (gestor == null) {
            throw new IllegalArgumentException(
                "El gestor no puede ser nulo");
        }

        // ========== PAQUETES ==========

        // Armenia
        gestor.registrarPaquete(new Paquete("PQ731", "Armenia", 4.5, 3, 40));
        gestor.registrarPaquete(new Paquete("PQ105", "Armenia", 2.0, 5, 25));
        gestor.registrarPaquete(new Paquete("PQ942", "Armenia", 8.2, 2, 35));
        gestor.registrarPaquete(new Paquete("PQ318", "Armenia", 1.5, 4, 20));
        gestor.registrarPaquete(new Paquete("PQ567", "Armenia", 6.0, 0, 60));

        // Calarcá
        gestor.registrarPaquete(new Paquete("PQ829", "Calarcá", 3.2, 4, 30));
        gestor.registrarPaquete(new Paquete("PQ214", "Calarcá", 7.8, 2, 45));
        gestor.registrarPaquete(new Paquete("PQ653", "Calarcá", 2.6, 5, 25));
        gestor.registrarPaquete(new Paquete("PQ401", "Calarcá", 9.1, 1, 55));
        gestor.registrarPaquete(new Paquete("PQ786", "Calarcá", 4.0, 3, 35));

        // Salento
        gestor.registrarPaquete(new Paquete("PQ123", "Salento", 1.8, 5, 50));
        gestor.registrarPaquete(new Paquete("PQ895", "Salento", 5.4, 3, 65));
        gestor.registrarPaquete(new Paquete("PQ346", "Salento", 3.7, 2, 40));
        gestor.registrarPaquete(new Paquete("PQ672", "Salento", 6.9, 4, 55));
        gestor.registrarPaquete(new Paquete("PQ519", "Salento", 2.3, 1, 30));

        // Montenegro
        gestor.registrarPaquete(new Paquete("PQ284", "Montenegro", 10.5, 2, 70));
        gestor.registrarPaquete(new Paquete("PQ917", "Montenegro", 3.1, 5, 35));
        gestor.registrarPaquete(new Paquete("PQ438", "Montenegro", 5.6, 3, 45));
        gestor.registrarPaquete(new Paquete("PQ761", "Montenegro", 1.2, 4, 20));
        gestor.registrarPaquete(new Paquete("PQ592", "Montenegro", 8.4, 0, 60));

        // Quimbaya
        gestor.registrarPaquete(new Paquete("PQ157", "Quimbaya", 4.8, 3, 55));
        gestor.registrarPaquete(new Paquete("PQ823", "Quimbaya", 2.2, 5, 30));
        gestor.registrarPaquete(new Paquete("PQ469", "Quimbaya", 7.3, 1, 65));
        gestor.registrarPaquete(new Paquete("PQ735", "Quimbaya", 3.6, 4, 40));
        gestor.registrarPaquete(new Paquete("PQ296", "Quimbaya", 9.0, 2, 75));

        // Circasia
        gestor.registrarPaquete(new Paquete("PQ648", "Circasia", 2.9, 5, 25));
        gestor.registrarPaquete(new Paquete("PQ371", "Circasia", 6.2, 3, 50));
        gestor.registrarPaquete(new Paquete("PQ984", "Circasia", 1.4, 4, 20));
        gestor.registrarPaquete(new Paquete("PQ526", "Circasia", 5.7, 2, 45));
        gestor.registrarPaquete(new Paquete("PQ813", "Circasia", 3.3, 0, 35));

        // ========== REPARTIDORES ==========

        gestor.registrarRepartidor(
            new Repartidor("R001", "Carlos Pérez", "Armenia"));

        gestor.registrarRepartidor(
            new Repartidor("R002", "Ana Gómez", "Calarcá"));

        gestor.registrarRepartidor(
            new Repartidor("R003", "Luis Ramírez", "Salento"));

        gestor.registrarRepartidor(
            new Repartidor("R004", "María Torres", "Montenegro"));

        gestor.registrarRepartidor(
            new Repartidor("R005", "Pedro Sánchez", "Quimbaya"));

        gestor.registrarRepartidor(
            new Repartidor("R006", "Laura Martínez", "Circasia"));
    }
}
