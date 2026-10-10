package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.modelo.Paquete;
import co.edu.uniquindio.poo.modelo.Repartidor;
import co.edu.uniquindio.poo.servicio.DatosIniciales;
import co.edu.uniquindio.poo.servicio.GestorDistribucion;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        GestorDistribucion gestor = new GestorDistribucion();
        DatosIniciales.cargar(gestor);

        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            try {
                switch (opcion) {
                    case 1 -> registrarPaquete(gestor);
                    case 2 -> buscarPaquete(gestor);
                    case 3 -> registrarRepartidor(gestor);
                    case 4 -> mostrarRepartidores(gestor);
                    case 5 -> mostrarMunicipios(gestor);
                    case 6 -> mostrarPaquetesPorDestino(gestor);
                    case 7 -> despacharPorLlegada(gestor);
                    case 8 -> despacharPorPrioridad(gestor);
                    case 9 -> consultarAsignacion(gestor);
                    case 10 -> mostrarPendientes(gestor);
                    case 11 -> registrarEntrega(gestor);
                    case 12 -> mostrarHistorial(gestor);
                    case 0 -> System.out.println("¡Hasta pronto!");
                    default -> System.out.println("Opción no válida.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (opcion != 0);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n========== QUINDÍOEXPRESS ==========");
        System.out.println("1. Registrar paquete");
        System.out.println("2. Buscar paquete por código");
        System.out.println("3. Registrar repartidor");
        System.out.println("4. Mostrar repartidores");
        System.out.println("5. Mostrar municipios ordenados");
        System.out.println("6. Mostrar paquetes por destino");
        System.out.println("7. Despachar por orden de llegada");
        System.out.println("8. Despachar por prioridad");
        System.out.println("9. Consultar asignación");
        System.out.println("10. Ver cantidad de pendientes");
        System.out.println("11. Registrar entrega");
        System.out.println("12. Mostrar historial de entregas");
        System.out.println("0. Salir");
    }

    private static void registrarPaquete(GestorDistribucion gestor) {
        System.out.println("\n--- Registrar paquete ---");

        String codigo = leerTexto("Código: ");
        String destino = leerTexto("Destino: ");
        double peso = leerDouble("Peso (kg): ");
        int prioridad = leerEntero("Prioridad (0 a 5): ");
        int tiempo = leerEntero("Tiempo estimado (min): ");

        Paquete paquete = new Paquete(
                codigo, destino, peso, prioridad, tiempo);

        gestor.registrarPaquete(paquete);

        System.out.println("Paquete registrado correctamente.");
    }

    private static void buscarPaquete(GestorDistribucion gestor) {
        String codigo = leerTexto("Código del paquete: ");

        Paquete paquete = gestor.buscarPaquete(codigo);

        if (paquete == null) {
            System.out.println("Paquete no encontrado.");
        } else {
            System.out.println(paquete);
        }
    }

    private static void registrarRepartidor(GestorDistribucion gestor) {
        System.out.println("\n--- Registrar repartidor ---");

        String id = leerTexto("Identificación: ");
        String nombre = leerTexto("Nombre: ");
        String zona = leerTexto("Zona: ");

        gestor.registrarRepartidor(
                new Repartidor(id, nombre, zona));

        System.out.println("Repartidor registrado correctamente.");
    }

    private static void mostrarRepartidores(GestorDistribucion gestor) {
        System.out.println("\n--- Repartidores ---");

        for (Repartidor repartidor : gestor.obtenerRepartidores()) {
            System.out.println(repartidor);
        }
    }

    private static void mostrarMunicipios(GestorDistribucion gestor) {
        System.out.println("\n--- Municipios ordenados ---");

        for (String municipio : gestor.obtenerMunicipiosOrdenados()) {
            System.out.println("- " + municipio);
        }
    }

    private static void mostrarPaquetesPorDestino(
            GestorDistribucion gestor) {

        System.out.println("\n--- Paquetes por destino ---");

        Map<String, List<Paquete>> grupos =
                gestor.agruparPorDestino();

        for (String municipio : gestor.obtenerMunicipiosOrdenados()) {
            System.out.println("\n" + municipio + ":");

            for (Paquete paquete : grupos.get(municipio)) {
                System.out.println("  " + paquete);
            }
        }
    }

    private static void despacharPorLlegada(GestorDistribucion gestor) {
        Paquete paquete = gestor.despacharPorLlegada();
        mostrarResultadoDespacho(gestor, paquete);
    }

    private static void despacharPorPrioridad(GestorDistribucion gestor) {
        Paquete paquete = gestor.despacharPorPrioridad();
        mostrarResultadoDespacho(gestor, paquete);
    }

    private static void mostrarResultadoDespacho(
            GestorDistribucion gestor, Paquete paquete) {

        if (paquete == null) {
            System.out.println(
                    "No fue posible despachar el siguiente paquete. "
                    + "Puede que no haya repartidor disponible "
                    + "o que no queden pendientes.");
            return;
        }

        Repartidor repartidor =
                gestor.consultarAsignacion(paquete.getCodigo());

        System.out.println("Paquete despachado: " + paquete.getCodigo());
        System.out.println("Destino: " + paquete.getDestino());

        if (repartidor != null) {
            System.out.println(
                    "Repartidor asignado: " + repartidor.getNombre());
        }
    }

    private static void consultarAsignacion(GestorDistribucion gestor) {
        String codigo = leerTexto("Código del paquete: ");

        Repartidor repartidor = gestor.consultarAsignacion(codigo);

        if (repartidor == null) {
            System.out.println("El paquete no tiene asignación activa.");
        } else {
            System.out.println("Repartidor: " + repartidor);
        }
    }

    private static void mostrarPendientes(GestorDistribucion gestor) {
        System.out.println(
                "Paquetes pendientes de despacho: "
                + gestor.cantidadPendientes());
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(leerTexto(mensaje));
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número entero válido.");
            }
        }
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            try {
                return Double.parseDouble(
                        leerTexto(mensaje).replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número decimal válido.");
            }
        }
    }


    private static void registrarEntrega(GestorDistribucion gestor) {

        String codigo = leerTexto("Código del paquete entregado: ");

        if (gestor.registrarEntrega(codigo)) {
            System.out.println("Entrega registrada correctamente.");
            System.out.println("Cupo del repartidor liberado.");
        } else {
            System.out.println(
                "No se puede registrar la entrega. "
                + "Verifica que el paquete esté despachado, "
                + "tenga asignación activa y no se haya entregado antes.");
        }
    }

    private static void mostrarHistorial(GestorDistribucion gestor) {

        System.out.println("\n--- Historial de entregas ---");
        System.out.println("Total entregados: "
                + gestor.cantidadEntregas());

        gestor.mostrarHistorial();
    }

}
