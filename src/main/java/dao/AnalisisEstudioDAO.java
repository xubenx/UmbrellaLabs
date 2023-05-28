package dao;

import data.Conexion;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Analisis;
import model.AnalisisEstudio;
import model.AnalisisEstudioNombre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnalisisEstudioDAO {

    private Connection conn = null;

    public AnalisisEstudioDAO(Connection connection) {
        this.conn = connection;
    }

    public void insertarAnalisisEstudio(AnalisisEstudio analisisEstudio) throws SQLException {
        String sql = "INSERT INTO AnalisisEstudio (Analisis, Estudio) VALUES (?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, analisisEstudio.getAnalisis());
        pstmt.setInt(2, analisisEstudio.getEstudio());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Deseas insertar el análisis para el estudio?");

        ButtonType buttonYes = new ButtonType("Sí");
        ButtonType buttonNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (((Optional<?>) result).isPresent() && result.get() == buttonYes) {
            pstmt.executeUpdate();

            Alert alertb = new Alert(Alert.AlertType.INFORMATION);
            alertb.setTitle("Mensaje");
            alertb.setHeaderText(null);
            alertb.setContentText("Se ha insertado correctamente el análisis para el estudio");

            alertb.showAndWait();
        } else {
            Alert alertb = new Alert(Alert.AlertType.INFORMATION);
            alertb.setTitle("Mensaje");
            alertb.setHeaderText(null);
            alertb.setContentText("No se realizó la inserción del análisis para el estudio");

            alertb.showAndWait();
        }
    }

    public List<AnalisisEstudioNombre> obtenerTodosAnalisisEstudios() throws SQLException {
        List<AnalisisEstudioNombre> listaAnalisisEstudio = new ArrayList<>();

        String sql = "SELECT * FROM AnalisisEstudio";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        Connection connection = Conexion.conectar();
        while (rs.next()) {

            String sqldos = "SELECT Nombre FROM Estudio WHERE idEstudio = ?";
            PreparedStatement stmtdos = connection.prepareStatement(sqldos);
            stmtdos.setInt(1, rs.getInt("Estudio"));
            ResultSet resultSetDos = stmtdos.executeQuery();

            String nombreEstudio = "";
            if (resultSetDos.next()) {
                nombreEstudio = resultSetDos.getString("Nombre");
            }

            String sqltres = "SELECT Nombre FROM Analisis WHERE idAnalisis = ?";
            PreparedStatement stmttres = connection.prepareStatement(sqltres);
            stmttres.setInt(1, rs.getInt("Analisis"));
            ResultSet resultSetTres = stmttres.executeQuery();
            String nombreAnalisis = "";
            if (resultSetTres.next()) {
                nombreAnalisis = resultSetTres.getString("Nombre");
            }

            AnalisisEstudioNombre analisisEstudio = new AnalisisEstudioNombre(nombreAnalisis,nombreEstudio
                    );
            listaAnalisisEstudio.add(analisisEstudio);
        }
        return listaAnalisisEstudio;
    }



    public List<AnalisisEstudio> obtenerAnalisisEstudioPorIds(int estudio) throws SQLException {
        String sql = "SELECT * FROM AnalisisEstudio WHERE Estudio = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, estudio);
        ResultSet rs = pstmt.executeQuery();

        List<AnalisisEstudio> analisisEstudiosList = new ArrayList<>();

        while (rs.next()) {
            AnalisisEstudio analisisEstudio = new AnalisisEstudio(
                    rs.getInt("Analisis"),
                    rs.getInt("Estudio"));
            analisisEstudiosList.add(analisisEstudio);
        }
        return analisisEstudiosList;
    }

    public List<String> obtenerAnalisisEstudioPorNombre(String estudio) throws SQLException {

        List<String> listaAnalisisEstudio = new ArrayList<>();


        int idEstudio = 0 ;
        String sqldos = "SELECT idEstudio FROM Estudio WHERE Nombre = ?";

        PreparedStatement stmtdos = conn.prepareStatement(sqldos);
        stmtdos.setString(1, estudio);
        ResultSet resultSetDos = stmtdos.executeQuery();

        if (resultSetDos.next()) {
            idEstudio = resultSetDos.getInt("idEstudio");
        }


        String sql = "SELECT Analisis.Nombre FROM Analisis INNER JOIN AnalisisEstudio ON  Analisis.idAnalisis = AnalisisEstudio.Analisis  INNER JOIN Estudio ON Estudio.idEstudio = AnalisisEstudio.Estudio WHERE AnalisisEstudio.Estudio = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        pstmt.setInt(1,idEstudio);
        String nombreAnalisis = " ";
        Connection connection = Conexion.conectar();
        while (rs.next()) {

            nombreAnalisis = rs.getString("Nombre");
            System.out.printf(""+nombreAnalisis);

            listaAnalisisEstudio.add(nombreAnalisis);
        }
        return listaAnalisisEstudio;
    }
    public void actualizarAnalisisEstudio(AnalisisEstudio analisisEstudio) throws SQLException {
    }

    public void eliminarAnalisisEstudio(int analisis, int estudio) throws SQLException {
        String sql = "DELETE FROM AnalisisEstudio WHERE Analisis = ? AND Estudio = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, analisis);
        pstmt.setInt(2, estudio);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Deseas eliminar el análisis del estudio?");

        ButtonType buttonYes = new ButtonType("Sí");
        ButtonType buttonNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonYes) {
            pstmt.executeUpdate();

            Alert alertb = new Alert(Alert.AlertType.INFORMATION);
            alertb.setTitle("Mensaje");
            alertb.setHeaderText(null);
            alertb.setContentText("Se ha eliminado correctamente el análisis del estudio");

            alertb.showAndWait();
        } else {
            Alert alertb = new Alert(Alert.AlertType.INFORMATION);
            alertb.setTitle("Mensaje");
            alertb.setHeaderText(null);
            alertb.setContentText("No se realizó la eliminación del análisis del estudio");

            alertb.showAndWait();
        }
    }
}
