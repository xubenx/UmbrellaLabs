package dao;

import data.Conexion;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Estudio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EstudioDAO {

    private Connection conn;

    public EstudioDAO(Connection connection) {
        this.conn = connection;
    }

    public void insertarEstudio(Estudio estudio) throws SQLException {
        String sql = "INSERT INTO Estudio (Nombre, Precio) VALUES (?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, estudio.getNombre());
        pstmt.setDouble(2, estudio.getPrecio());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Deseas realizar la acción?");

        ButtonType buttonYes = new ButtonType("Sí");
        ButtonType buttonNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonYes) {

            pstmt.executeUpdate();

            Alert alertb = new Alert(Alert.AlertType.INFORMATION);
            alertb.setTitle("Mensaje");
            alertb.setHeaderText(null);
            alertb.setContentText("Se ha agregado correctamente el estudio");

            alertb.showAndWait();

        } else {
            Alert alertb = new Alert(Alert.AlertType.INFORMATION);
            alertb.setTitle("Mensaje");
            alertb.setHeaderText(null);
            alertb.setContentText("No se pudo capturar el estudio");

            alertb.showAndWait();
        }
    }


    public List<Estudio> obtenerTodosEstudios() throws SQLException {
        List<Estudio> listaEstudios = new ArrayList<>();

        String sql = "SELECT * FROM Estudio";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Estudio estudio = new Estudio(rs.getInt("idEstudio"),
                    rs.getString("Nombre"),
                    rs.getDouble("Precio"));
            listaEstudios.add(estudio);
        }
        return listaEstudios;
    }

    public Estudio obtenerEstudioPorNombre(String idEstudio) throws SQLException {
        String sql = "SELECT * FROM Estudio WHERE Nombre= ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, idEstudio);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            Estudio estudio = new Estudio(rs.getInt("idEstudio"),
                    rs.getString("Nombre"),
                    rs.getDouble("Precio"));
            return estudio;
        }
        return null;
    }

    public Estudio obtenerEstudioPorId(int idEstudio) throws SQLException {
        String sql = "SELECT * FROM Estudio WHERE idEstudio = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, String.valueOf(idEstudio));
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            Estudio estudio = new Estudio(rs.getInt("idEstudio"),
                    rs.getString("Nombre"),
                    rs.getDouble("Precio"));
            return estudio;
        }
        return null;
    }

    public void actualizarEstudio(Estudio estudio) throws SQLException {
        String sql = "UPDATE Estudio SET Nombre = ?, Precio = ? WHERE idEstudio = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, estudio.getNombre());
        pstmt.setDouble(2, estudio.getPrecio());
        pstmt.setInt(3, estudio.getIdEstudio());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Deseas actualizar el estudio?");

        ButtonType buttonYes = new ButtonType("Sí");
        ButtonType buttonNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonYes) {

            pstmt.executeUpdate();

            Alert alertb = new Alert(Alert.AlertType.INFORMATION);
            alertb.setTitle("Mensaje");
            alertb.setHeaderText(null);
            alertb.setContentText("Se ha actualizado correctamente el estudio");

            alertb.showAndWait();

        } else {
            Alert alertb = new Alert(Alert.AlertType.INFORMATION);
            alertb.setTitle("Mensaje");
            alertb.setHeaderText(null);
            alertb.setContentText("No se realizó la actualización del estudio");

            alertb.showAndWait();
        }
    }

    public void eliminarEstudio(int idEstudio) throws SQLException {
        String sql = "DELETE FROM Estudio WHERE idEstudio = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, idEstudio);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Deseas eliminar el estudio?");

        ButtonType buttonYes = new ButtonType("Sí");
        ButtonType buttonNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonYes) {

            pstmt.executeUpdate();

            Alert alertb = new Alert(Alert.AlertType.INFORMATION);
            alertb.setTitle("Mensaje");
            alertb.setHeaderText(null);
            alertb.setContentText("Se ha eliminado correctamente el estudio");

            alertb.showAndWait();

        } else {
            Alert alertb = new Alert(Alert.AlertType.INFORMATION);
            alertb.setTitle("Mensaje");
            alertb.setHeaderText(null);
            alertb.setContentText("No se realizó la eliminación del estudio");

            alertb.showAndWait();
        }
    }
}
