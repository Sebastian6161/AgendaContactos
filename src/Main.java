import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Agenda agenda = new Agenda();
        agenda.cargarContactos();

        int opcion;

        do {
            System.out.println("\n=== AGENDA DE CONTACTOS ===");
            System.out.println("1. Agregar contacto");
            System.out.println("2. Listar contactos");
            System.out.println("3. Buscar contacto por nombre");
            System.out.println("4. Eliminar contacto por nombre");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();

                    if (agenda.existeContacto(nombre)) {
                        System.out.println("Ya existe un contacto con ese nombre.");
                        break;
                    }

                    System.out.print("Teléfono: ");
                    String telefono = scanner.nextLine();

                    if (!agenda.validarTelefono(telefono)) {
                        System.out.println("El teléfono debe contener solo números.");
                        break;
                    }

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    if (!agenda.validarEmail(email)) {
                        System.out.println("El email no tiene un formato válido.");
                        break;
                    }

                    Contacto nuevoContacto = new Contacto(nombre, telefono, email);
                    agenda.agregarContacto(nuevoContacto);
                    agenda.guardarContactos();
                    break;

                case 2:
                    agenda.listarTodos();
                    break;

                case 3:
                    System.out.print("Ingrese el nombre a buscar: ");
                    String nombreBuscar = scanner.nextLine();

                    Contacto encontrado = agenda.buscarPorNombre(nombreBuscar);

                    if (encontrado != null) {
                        System.out.println("Contacto encontrado:");
                        System.out.println(encontrado);
                    } else {
                        System.out.println("No se encontró el contacto.");
                    }
                    break;

                case 4:
                    System.out.print("Ingrese el nombre a eliminar: ");
                    String nombreEliminar = scanner.nextLine();

                    boolean eliminado = agenda.eliminarPorNombre(nombreEliminar);

                    if (eliminado) {
                        agenda.guardarContactos();
                        System.out.println("Contacto eliminado correctamente.");
                    } else {
                        System.out.println("No se encontró el contacto.");
                    }
                    break;

                case 5:
                    System.out.println("Saliendo de la agenda...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 5);

        scanner.close();
    }
}