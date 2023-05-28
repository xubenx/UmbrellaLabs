package dao;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Analisis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnalisisDAO {

    private Connection conn;

    public AnalisisDAO(Connection connection) {
        this.conn = connection;
    }
    public Integer contar(Integer id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Analisis";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.executeUpdate();
        return id;
    }
    public void insertarAnalisis(Analisis analisis) throws SQLException {
        String sql = "INSERT INTO Analisis (Nombre, LimInferior, LimSuperior) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, analisis.getNombre());
        pstmt.setDouble(2, analisis.getLimInferior());
        pstmt.setDouble(3, analisis.getLimSuperior());

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
            alertb.setContentText("Se ha agregado correctamente el análisis");

            alertb.showAndWait();

        } else {
            Alert alertb = new Alert(Alert.AlertType.INFORMATION);
            alertb.setTitle("Mensaje");
            alertb.setHeaderText(null);
            alertb.setContentText("No se pudo capturar el analisis. ");

            alertb.showAndWait();
        }



    }

    public List<Analisis> obtenerTodosAnalisis() throws SQLException {
        List<Analisis> listaAnalisis = new ArrayList<>();

        String sql = "SELECT * FROM Analisis";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Analisis analisis = new Analisis(rs.getInt("idAnalisis"),
                    rs.getString("Nombre"),
                    rs.getDouble("LimInferior"),
                    rs.getDouble("LimSuperior"));
            listaAnalisis.add(analisis);
        }
        return listaAnalisis;
    }

    public Analisis obtenerAnalisisPorId(int idAnalisis) throws SQLException {
        String sql = "SELECT * FROM Analisis WHERE idAnalisis = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, idAnalisis);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            Analisis analisis = new Analisis(rs.getInt("idAnalisis"),
                    rs.getString("Nombre"),
                    rs.getDouble("LimInferior"),
                    rs.getDouble("LimSuperior"));
            return analisis;
        }
        return null;
    }

    public List<Analisis> obtenerAnalisisPorNombreEstudio(String idAnalisis) throws SQLException {
        String sql = "SELECT * FROM Analisis INNER JOIN AnalisisEstudio ON  Analisis.idAnalisis = AnalisisEstudio.Analisis  INNER JOIN Estudio ON Estudio.idEstudio = AnalisisEstudio.Estudio WHERE Estudio.Nombre = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, idAnalisis);
        ResultSet rs = pstmt.executeQuery();
        List<Analisis> listAnalisis = new ArrayList<>();
        while (rs.next()) {
            Analisis analisis = new Analisis(rs.getInt("idAnalisis"),
                    rs.getString("Nombre"),
                    rs.getDouble("LimInferior"),
                    rs.getDouble("LimSuperior"));
            listAnalisis.add(analisis);
        }
        return listAnalisis;
    }

    public Analisis obtenerAnalisisPorNombre(String idAnalisis) throws SQLException {
        String sql = "SELECT * FROM Analisis WHERE Nombre = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, idAnalisis);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            Analisis analisis = new Analisis(rs.getInt("idAnalisis"),
                    rs.getString("Nombre"),
                    rs.getDouble("LimInferior"),
                    rs.getDouble("LimSuperior"));
            return analisis;
        }
        return null;
    }

    public void actualizarAnalisis(Analisis analisis) throws SQLException {
        String sql = "UPDATE Analisis SET Nombre = ?, LimInferior = ?, LimSuperior = ? WHERE idAnalisis = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, analisis.getNombre());
        pstmt.setDouble(2, analisis.getLimInferior());
        pstmt.setDouble(3, analisis.getLimSuperior());
        pstmt.setInt(4, analisis.getIdAnalisis());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Deseas actualizar el análisis?");

        ButtonType buttonYes = new ButtonType("Sí");
        ButtonType buttonNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonYes) {

            pstmt.executeUpdate();

            Alert alertb = new Alert(Alert.AlertType.INFORMATION);
            alertb.setTitle("Mensaje");
            alertb.setHeaderText(null);
            alertb.setContentText("Se ha actualizado correctamente el análisis");

            alertb.showAndWait();

        } else {
            Alert alertb = new Alert(Alert.AlertType.INFORMATION);
            alertb.setTitle("Mensaje");
            alertb.setHeaderText(null);
            alertb.setContentText("No se realizó la actualización del análisis");

            alertb.showAndWait();
        }
    }

    public void eliminarAnalisis(int idAnalisis) throws SQLException {
        String sql = "DELETE FROM Analisis WHERE idAnalisis = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, idAnalisis);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Deseas eliminar el análisis?");

        ButtonType buttonYes = new ButtonType("Sí");
        ButtonType buttonNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonYes) {

            pstmt.executeUpdate();

            Alert alertb = new Alert(Alert.AlertType.INFORMATION);
            alertb.setTitle("Mensaje");
            alertb.setHeaderText(null);
            alertb.setContentText("Se ha eliminado correctamente el análisis");

            alertb.showAndWait();

        } else {
            Alert alertb = new Alert(Alert.AlertType.INFORMATION);
            alertb.setTitle("Mensaje");
            alertb.setHeaderText(null);
            alertb.setContentText("No se realizó la eliminación del análisis");

            alertb.showAndWait();
        }
    }

}
