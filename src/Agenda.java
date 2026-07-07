import java.util.ArrayList;
import java.io.*;

public class Agenda {

    private ArrayList<Contacto> contactos;
    private final String archivo = "contactos.txt";

    public Agenda() {
        contactos = new ArrayList<>();
    }

    public void agregarContacto(Contacto contacto) {
        contactos.add(contacto);
        System.out.println("Contacto agregado correctamente.");
    }

    public void listarTodos() {
        if (contactos.isEmpty()) {
            System.out.println("No hay contactos registrados.");
        } else {
            for (Contacto contacto : contactos) {
                System.out.println(contacto);
            }
        }
    }

    public Contacto buscarPorNombre(String nombre) {
        for (Contacto contacto : contactos) {
            if (contacto.getNombre().equalsIgnoreCase(nombre)) {
                return contacto;
            }
        }
        return null;
    }

    public boolean eliminarPorNombre(String nombre) {
        Contacto contacto = buscarPorNombre(nombre);

        if (contacto != null) {
            contactos.remove(contacto);
            return true;
        }

        return false;
    }

    public boolean validarTelefono(String telefono) {
        return telefono.matches("[0-9]+");
    }

    public boolean validarEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    public boolean existeContacto(String nombre) {
        return buscarPorNombre(nombre) != null;
    }

    public void guardarContactos() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
            for (Contacto contacto : contactos) {
                writer.println(contacto.formatoArchivo());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar contactos.");
        }
    }

    public void cargarContactos() {
        File file = new File(archivo);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(";");

                if (datos.length == 3) {
                    Contacto contacto = new Contacto(datos[0], datos[1], datos[2]);
                    contactos.add(contacto);
                }
            }

        } catch (IOException e) {
            System.out.println("Error al cargar contactos.");
        }
    }
}