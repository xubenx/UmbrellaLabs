package dao;

import data.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static data.Conexion.*;

public class ClienteDAO {


    private Connection conn;

    public ClienteDAO(Connection connection) {
        this.conn = connection;
    }

    public void initialize(){

    }
    public void insertarCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO Cliente (RFC, Nombre, APaterno, AMaterno, Telefono, Correo) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, cliente.getRFC());
        pstmt.setString(2, cliente.getNombre());
        pstmt.setString(3, cliente.getAPaterno());
        pstmt.setString(4, cliente.getAMaterno());
        pstmt.setString(5, cliente.getTelefono());
        pstmt.setString(6, cliente.getCorreo());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Deseas insertar el cliente?");

        ButtonType buttonYes = new ButtonType("Sí");
        ButtonType buttonNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonYes) {
            pstmt.executeUpdate();

            Alert alertb = new Alert(Alert.AlertType.INFORMATION);
            alertb.setTitle("Mensaje");
            alertb.setHeaderText(null);
            alertb.setContentText("Se ha insertado correctamente el cliente");

            alertb.showAndWait();
        } else {
            Alert alertb = new Alert(Alert.AlertType.INFORMATION);
            alertb.setTitle("Mensaje");
            alertb.setHeaderText(null);
            alertb.setContentText("No se realizó la inserción del cliente");

            alertb.showAndWait();
        }
    }

    private ObservableList<Cliente> dataview() throws SQLException {


        String sql = "SELECT * FROM Cliente";
        ObservableList<Cliente> lista = FXCollections.observableArrayList();


        try{
            Conexion.conectar();
            Statement st = conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while ((rs.next())){
                String rfc = rs.getString("RFC");
                String nombre = rs.getString("nombre");
                String paterno = rs.getString("APaterno");
                String materno = rs.getString("AMaterno");
                String telefono = rs.getString("telefono");
                String correo = rs.getString("correo");


                lista.add(new Cliente(rfc,nombre,paterno,materno,telefono,correo));
            }
        }catch (SQLException ex){
            System.err.println("Failed to fetch data from the database.");
            ex.printStackTrace();
        }

        return  lista;

    }

    public void configurarTabla(){

    }

    public List<Cliente> obtenerTodosClientes() throws SQLException {
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

    public Cliente obtenerClientePorRFC(String RFC) throws SQLException {
        String sql = "SELECT * FROM Cliente WHERE RFC = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, RFC);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            Cliente cliente = new Cliente(rs.getString("RFC"),
                    rs.getString("Nombre"),
                    rs.getString("APaterno"),
                    rs.getString("AMaterno"),
                    rs.getString("Telefono"),
                    rs.getString("Correo"));
            return cliente;
        }
        return null;
    }

    public void actualizarCliente(Cliente cliente) throws SQLException {
        String sql = "UPDATE Cliente SET Nombre = ?, APaterno = ?, AMaterno = ?, Telefono = ?, Correo = ? WHERE RFC = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, cliente.getNombre());
        pstmt.setString(2, cliente.getAPaterno());
        pstmt.setString(3, cliente.getAMaterno());
        pstmt.setString(4, cliente.getTelefono());
        pstmt.setString(5, cliente.getCorreo());
        pstmt.setString(6, cliente.getRFC());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Deseas actualizar el cliente?");

        ButtonType buttonYes = new ButtonType("Sí");
        ButtonType buttonNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonYes) {
            pstmt.executeUpdate();

            Alert alertb = new Alert(Alert.AlertType.INFORMATION);
            alertb.setTitle("Mensaje");
            alertb.setHeaderText(null);
            alertb.setContentText("Se ha actualizado correctamente el cliente");

            alertb.showAndWait();
        } else {
            Alert alertb = new Alert(Alert.AlertType.INFORMATION);
            alertb.setTitle("Mensaje");
            alertb.setHeaderText(null);
            alertb.setContentText("No se realizó la actualización del cliente");

            alertb.showAndWait();
        }
    }

    public void eliminarCliente(String RFC) throws SQLException {
        String sql = "DELETE FROM Cliente WHERE RFC = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, RFC);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Deseas eliminar el cliente?");

        ButtonType buttonYes = new ButtonType("Sí");
        ButtonType buttonNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonYes) {
            pstmt.executeUpdate();

            Alert alertb = new Alert(Alert.AlertType.INFORMATION);
            alertb.setTitle("Mensaje");
            alertb.setHeaderText(null);
            alertb.setContentText("Se ha eliminado correctamente el cliente");

            alertb.showAndWait();
        } else {
            Alert alertb = new Alert(Alert.AlertType.INFORMATION);
            alertb.setTitle("Mensaje");
            alertb.setHeaderText(null);
            alertb.setContentText("No se realizó la eliminación del cliente");

            alertb.showAndWait();
        }
    }



}
