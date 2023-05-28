package gui;

import dao.ClienteDAO;
import dao.EstudioOrdenDAO;
import dao.OrdenDAO;
import dao.ResultadosDAO;
import data.Conexion;
import model.Cliente;
import model.Orden;
import java.util.List;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class Menu {

    private ClienteDAO clienteDAO;
    public Menu(Connection connection) {
        this.clienteDAO = new ClienteDAO(connection);
  }
    private void menuCliente(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\nOperaciones de Cliente:");
            System.out.println("1. Agregar Cliente");
            System.out.println("2. Obtener Cliente por RFC");
            System.out.println("3. Obtener todos los Clientes");
            System.out.println("4. Actualizar Cliente");
            System.out.println("5. Eliminar Cliente");
            System.out.println("6. Regresar");
            System.out.print("Introduce una opción (1-6): ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    agregarCliente(scanner);
                    break;
                case 2:
                    obtenerClientePorRFC(scanner);
                    break;
                case 3:
                    obtenerTodosClientes(scanner);
                    break;
                case 4:
                    actualizarCliente(scanner);
                    break;
                case 5:
                    eliminarCliente(scanner);
                    break;
                case 6:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, introduce una opción válida.");
            }
        } while (opcion != 6);
    }

    private void agregarCliente(Scanner scanner) {
        System.out.print("Introduce el RFC del cliente: ");
        String rfc = scanner.next();
        System.out.print("Introduce el nombre del cliente: ");
        String nombre = scanner.next();
        System.out.print("Introduce el apellido paterno del cliente: ");
        String apellidoPaterno = scanner.next();
        System.out.print("Introduce el apellido materno del cliente (presiona Enter si no tiene): ");
        scanner.nextLine(); // Consumir el salto de línea pendiente
        String apellidoMaterno = scanner.nextLine();
        System.out.print("Introduce el teléfono del cliente: ");
        String telefono = scanner.next();
        System.out.print("Introduce el correo del cliente: ");
        String correo = scanner.next();

        Cliente cliente = new Cliente(rfc, nombre, apellidoPaterno, apellidoMaterno, telefono, correo);
        try {
            clienteDAO.insertarCliente(cliente);
            System.out.println("Cliente agregado con éxito.");
        } catch (SQLException e) {
            System.out.println("Error al agregar el cliente: " + e.getMessage());
        }
    }

    private void obtenerClientePorRFC(Scanner scanner) {
        System.out.print("Introduce el RFC del cliente: ");
        String rfc = scanner.next();

        try {
            Cliente cliente = clienteDAO.obtenerClientePorRFC(rfc);
            if (cliente != null) {
                System.out.println(cliente);
            } else {
                System.out.println("No se encontró ningún cliente con ese RFC.");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el cliente: " + e.getMessage());
        }
    }

    private void obtenerTodosClientes(Scanner scanner) {
        try {
            List<Cliente> listaClientes = clienteDAO.obtenerTodosClientes();
            for (Cliente cliente : listaClientes) {
                System.out.println(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los clientes: " + e.getMessage());
        }
    }

    private void actualizarCliente(Scanner scanner) {
        System.out.print("Introduce el RFC del cliente a actualizar: ");
        String rfc = scanner.next();
        System.out.print("Introduce el nuevo nombre del cliente: ");
        String nombre = scanner.next();
        System.out.print("Introduce el nuevo apellido paterno del cliente: ");
        String apellidoPaterno = scanner.next();
        System.out.print("Introduce el nuevo apellido materno del cliente (presiona Enter si no tiene): ");
        scanner.nextLine(); // Consumir el salto de línea pendiente
        String apellidoMaterno = scanner.nextLine();
        System.out.print("Introduce el nuevo teléfono del cliente: ");
        String telefono = scanner.next();
        System.out.print("Introduce el nuevo correo del cliente: ");
        String correo = scanner.next();

        Cliente cliente = new Cliente(rfc, nombre, apellidoPaterno, apellidoMaterno, telefono, correo);
        try {
            clienteDAO.actualizarCliente(cliente);
            System.out.println("Cliente actualizado con éxito.");
        } catch (SQLException e) {
            System.out.println("Error al actualizar el cliente: " + e.getMessage());
        }
    }

    private void eliminarCliente(Scanner scanner) {
        System.out.print("Introduce el RFC del cliente a eliminar: ");
        String rfc = scanner.next();

        try {
            clienteDAO.eliminarCliente(rfc);
            System.out.println("Cliente eliminado con éxito.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar el cliente: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Connection connection = Conexion.conectar();
        Menu menu = new Menu(connection);
        Scanner scanner = new Scanner(System.in);
        menu.menuCliente(scanner);
    }

}
