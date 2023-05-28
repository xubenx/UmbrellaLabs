package dao;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Cliente;
import model.Orden;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrdenDAO {
    private Connection conn;

    public OrdenDAO(Connection connection) {
        this.conn = connection;
    }

    // Crear
    public void agregarOrden(Orden orden) {
        String sql = "INSERT INTO Orden (Cliente, Fecha, Subtotal, IVA, Total) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, orden.getCliente());
            stmt.setString(2, orden.getFecha());
            stmt.setDouble(3, orden.getSubtotal());
            stmt.setDouble(4, orden.getIva());
            stmt.setDouble(5, orden.getTotal());

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("¿Deseas realizar la acción?");

            ButtonType buttonYes = new ButtonType("Sí");
            ButtonType buttonNo = new ButtonType("No");

            alert.getButtonTypes().setAll(buttonYes, buttonNo);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonYes) {

                stmt.executeUpdate();

                Alert alertb = new Alert(Alert.AlertType.INFORMATION);
                alertb.setTitle("Mensaje");
                alertb.setHeaderText(null);
                alertb.setContentText("Se ha agregado correctamente la orden");

                alertb.showAndWait();

            } else {
                Alert alertb = new Alert(Alert.AlertType.INFORMATION);
                alertb.setTitle("Mensaje");
                alertb.setHeaderText(null);
                alertb.setContentText("No se pudo capturar la orden");

                alertb.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Orden obtenerOrdenPorId(int idOrden) {
        String sql = "SELECT * FROM Orden WHERE idOrden = ?";
        Orden orden = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idOrden);

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                orden = new Orden(resultSet.getInt("idOrden"), resultSet.getString("Cliente"),
                        resultSet.getString("Fecha"), resultSet.getDouble("Subtotal"),
                        resultSet.getDouble("IVA"), resultSet.getDouble("Total"));
            }
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orden;
    }

    // Obtener todos
    public List<Orden> obtenerTodasOrdenes() {
        String sql = "SELECT * FROM Orden";
        List<Orden> ordenList = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                ordenList.add(new Orden(resultSet.getInt("idOrden"), resultSet.getString("Cliente"),
                        resultSet.getString("Fecha"), resultSet.getDouble("Subtotal"),
                        resultSet.getDouble("IVA"), resultSet.getDouble("Total")));
            }
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ordenList;
    }

    public List<Cliente> ClientesCombo() throws SQLException {
        List<Cliente> listaClientes = new ArrayList<>();

        String sql = "SELECT * FROM Cliente";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Cliente cliente = new Cliente(rs.getString("RFC"),
                    rs.getString("Nombre"),
                    rs.getString("APaterno"),
                    rs.getString("AMaterno"),
                    rs.getString("Telefono"),
                    rs.getString("Correo"));
            listaClientes.add(cliente);
        }
        return listaClientes;
    }

    // Actualizar
    public void actualizarOrden(Orden orden) {
        String sql = "UPDATE Orden SET Cliente = ?, Fecha = ?, Subtotal = ?, IVA = ?, Total = ? WHERE idOrden = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, orden.getCliente());
            stmt.setString(2, orden.getFecha());
            stmt.setDouble(3, orden.getSubtotal());
            stmt.setDouble(4, orden.getIva());
            stmt.setDouble(5, orden.getTotal());
            stmt.setInt(6, orden.getIdOrden());

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("¿Deseas realizar la acción?");

            ButtonType buttonYes = new ButtonType("Sí");
            ButtonType buttonNo = new ButtonType("No");

            alert.getButtonTypes().setAll(buttonYes, buttonNo);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonYes) {

                stmt.executeUpdate();

                Alert alertb = new Alert(Alert.AlertType.INFORMATION);
                alertb.setTitle("Mensaje");
                alertb.setHeaderText(null);
                alertb.setContentText("Se ha editado correctamente la orden");

                alertb.showAndWait();

            } else {
                Alert alertb = new Alert(Alert.AlertType.INFORMATION);
                alertb.setTitle("Mensaje");
                alertb.setHeaderText(null);
                alertb.setContentText("No se pudo editar la orden");

                alertb.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Eliminar
    public void eliminarOrden(int idOrden) {
        String sql = "DELETE FROM Orden WHERE idOrden = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idOrden);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("¿Deseas realizar la acción?");

            ButtonType buttonYes = new ButtonType("Sí");
            ButtonType buttonNo = new ButtonType("No");

            alert.getButtonTypes().setAll(buttonYes, buttonNo);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonYes) {

                stmt.executeUpdate();

                Alert alertb = new Alert(Alert.AlertType.INFORMATION);
                alertb.setTitle("Mensaje");
                alertb.setHeaderText(null);
                alertb.setContentText("Se ha borrado correctamente la orden");

                alertb.showAndWait();

            } else {
                Alert alertb = new Alert(Alert.AlertType.INFORMATION);
                alertb.setTitle("Mensaje");
                alertb.setHeaderText(null);
                alertb.setContentText("No se pudo borrar la orden");

                alertb.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
