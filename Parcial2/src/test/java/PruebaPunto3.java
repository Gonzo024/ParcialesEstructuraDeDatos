package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.ejercicios.Ejercicios;
import co.edu.uniquindio.poo.estructuras.Cola;
import co.edu.uniquindio.poo.modelo.Prioridad;
import co.edu.uniquindio.poo.modelo.Solicitud;

/**
 * Pruebas del punto 3: Cola propia y seleccionarSolicitudes.
 * Se ejecuta con el boton verde junto al main.
 */
public class PruebaPunto3 {

    private static int aprobadas = 0;
    private static int fallidas = 0;

    public static void main(String[] args) {
        System.out.println("=== PRUEBAS DE LA COLA ===");
        probarCola();

        System.out.println();
        System.out.println("=== PRUEBAS DE seleccionarSolicitudes ===");
        probarSeleccion();

        System.out.println();
        System.out.println("Aprobadas: " + aprobadas + "  Fallidas: " + fallidas);
    }

    private static void probarCola() {
        Cola<String> cola = new Cola<>();
        verificar("Cola nueva vacia", cola.estaVacia() && cola.tamanio() == 0);
        verificar("Desencolar en cola vacia retorna null", cola.desencolar() == null);

        cola.encolar("A");
        cola.encolar("B");
        cola.encolar("C");
        verificar("Encolar tres elementos", cola.tamanio() == 3 && cola.toString().equals("[A, B, C]"));
        verificar("verFrente no quita el elemento", "A".equals(cola.verFrente()) && cola.tamanio() == 3);
        verificar("Desencolar saca el primero (FIFO)", "A".equals(cola.desencolar()) && cola.toString().equals("[B, C]"));

        cola.desencolar();
        cola.desencolar();
        verificar("Cola vacia despues de sacar todos", cola.estaVacia() && cola.tamanio() == 0);

        cola.encolar("D");
        verificar("Encolar despues de vaciarla", cola.toString().equals("[D]") && "D".equals(cola.verFrente()));
    }

    private static void probarSeleccion() {
        // 1. Ejemplo del PDF
        Cola<Solicitud> pendientes = crearEjemploPdf();
        Cola<Solicitud> seleccionadas = Ejercicios.seleccionarSolicitudes(pendientes, 25);
        verificar("Ejemplo PDF (25 min): seleccionadas S3, S1", seleccionadas.toString().equals("[S3, S1]"));
        verificar("Ejemplo PDF (25 min): pendientes S2, S4", pendientes.toString().equals("[S2, S4]"));

        // 2. Con 40 minutos se alcanza a atender S2
        pendientes = crearEjemploPdf();
        seleccionadas = Ejercicios.seleccionarSolicitudes(pendientes, 40);
        verificar("40 min: seleccionadas S2, S1", seleccionadas.toString().equals("[S2, S1]"));
        verificar("40 min: pendientes S3, S4", pendientes.toString().equals("[S3, S4]"));

        // 3. Tiempo cero: cola vacia y pendientes intactas
        pendientes = crearEjemploPdf();
        seleccionadas = Ejercicios.seleccionarSolicitudes(pendientes, 0);
        verificar("0 min: seleccionadas vacia", seleccionadas.estaVacia());
        verificar("0 min: pendientes sin cambios", pendientes.toString().equals("[S1, S2, S3, S4]"));

        // 4. Tiempo negativo
        pendientes = crearEjemploPdf();
        seleccionadas = Ejercicios.seleccionarSolicitudes(pendientes, -5);
        verificar("-5 min: seleccionadas vacia y pendientes sin cambios",
                seleccionadas.estaVacia() && pendientes.toString().equals("[S1, S2, S3, S4]"));

        // 5. Caben todas: primero las ALTA y despues las NORMAL
        pendientes = crearEjemploPdf();
        seleccionadas = Ejercicios.seleccionarSolicitudes(pendientes, 100);
        verificar("100 min: seleccionadas S2, S3, S1, S4", seleccionadas.toString().equals("[S2, S3, S1, S4]"));
        verificar("100 min: pendientes vacia", pendientes.estaVacia());

        // 6. No cabe ninguna
        pendientes = crearEjemploPdf();
        seleccionadas = Ejercicios.seleccionarSolicitudes(pendientes, 3);
        verificar("3 min: ninguna cabe y se conserva el orden",
                seleccionadas.estaVacia() && pendientes.toString().equals("[S1, S2, S3, S4]"));

        // 7. Cola de pendientes vacia
        pendientes = new Cola<>();
        seleccionadas = Ejercicios.seleccionarSolicitudes(pendientes, 30);
        verificar("Pendientes vacia: retorna cola vacia", seleccionadas.estaVacia());

        // 8. Cola null
        seleccionadas = Ejercicios.seleccionarSolicitudes(null, 30);
        verificar("Pendientes null: retorna cola vacia", seleccionadas != null && seleccionadas.estaVacia());

        // 9. Duracion invalida
        boolean lanzoError = false;
        try {
            new Solicitud("SX", Prioridad.NORMAL, 0);
        } catch (IllegalArgumentException e) {
            lanzoError = true;
        }
        verificar("Duracion 0 no se permite", lanzoError);
    }

    /** Crea la cola del ejemplo del PDF: S1 normal 10, S2 alta 30, S3 alta 15, S4 normal 5. */
    private static Cola<Solicitud> crearEjemploPdf() {
        Cola<Solicitud> cola = new Cola<>();
        cola.encolar(new Solicitud("S1", Prioridad.NORMAL, 10));
        cola.encolar(new Solicitud("S2", Prioridad.ALTA, 30));
        cola.encolar(new Solicitud("S3", Prioridad.ALTA, 15));
        cola.encolar(new Solicitud("S4", Prioridad.NORMAL, 5));
        return cola;
    }

    private static void verificar(String nombre, boolean condicion) {
        if (condicion) {
            System.out.println("[OK]    " + nombre);
            aprobadas++;
        } else {
            System.out.println("[FALLA] " + nombre);
            fallidas++;
        }
    }
}
