package dao;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Resultados;
import model.ResultadosNombre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ResultadosDAO {
    private Connection connection;

    public ResultadosDAO(Connection connection) {
        this.connection = connection;
    }

    // Crear
    public void agregarResultado(Resultados resultados) {
        String sql = "INSERT INTO Resultados (Orden, Estudio, Analisis, Resultado) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, resultados.getOrden());
            stmt.setInt(2, resultados.getEstudio());
            stmt.setInt(3, resultados.getAnalisis());
            stmt.setDouble(4, resultados.getResultado());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Obtener por ID
    public Resultados obtenerResultadoPorId(int orden, int estudio, int analisis) {
        String sql = "SELECT * FROM Resultados WHERE Orden = ? AND Estudio = ? AND Analisis = ?";
        Resultados resultados = null;
        ResultadosNombre resultNombre = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orden);
            stmt.setInt(2, estudio);
            stmt.setInt(3, analisis);

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {


                    resultados = new Resultados(resultSet.getInt("Orden"), resultSet.getInt("Estudio"),
                        resultSet.getInt("Analisis"), resultSet.getDouble("Resultado"));


            }
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultados;
    }

    public List<ResultadosNombre> obtenerResultadosPorIdOrden(int idOrden) {
        String sql = "SELECT * FROM Resultados WHERE Orden = ?";
        List<ResultadosNombre> resultadosList = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idOrden);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int idEstudio = resultSet.getInt("Estudio");
                int idAnalisis = resultSet.getInt("Analisis");
                double resultado = resultSet.getDouble("Resultado");

                String sqldos = "SELECT Nombre FROM Estudio WHERE idEstudio = ?";
                PreparedStatement stmtdos = connection.prepareStatement(sqldos);
                stmtdos.setInt(1, idEstudio);
                ResultSet resultSetDos = stmtdos.executeQuery();

                String nombreEstudio = "";
                if (resultSetDos.next()) {
                    nombreEstudio = resultSetDos.getString("Nombre");
                }

                String sqltres = "SELECT Nombre FROM Analisis WHERE idAnalisis = ?";
                PreparedStatement stmttres = connection.prepareStatement(sqltres);
                stmttres.setInt(1, idAnalisis);
                ResultSet resultSetTres = stmttres.executeQuery();

                String nombreAnalisis = "";
                if (resultSetTres.next()) {
                    nombreAnalisis = resultSetTres.getString("Nombre");
                }

                resultadosList.add(new ResultadosNombre(idOrden, nombreEstudio, nombreAnalisis, resultado));
            }

            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultadosList;
    }
    public List<ResultadosNombre> obtenerTodosResultados() {
        String sql = "SELECT * FROM Resultados";
        List<Resultados> resultadosList = new ArrayList<>();
        List<ResultadosNombre> nombresList = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int idEstudio = resultSet.getInt("Estudio");
                int idAnalisis = resultSet.getInt("Analisis");
                double resultado = resultSet.getDouble("Resultado");

                String sqldos = "SELECT Nombre FROM Estudio WHERE idEstudio = ?";
                PreparedStatement stmtdos = connection.prepareStatement(sqldos);
                stmtdos.setInt(1, idEstudio);
                ResultSet resultSetDos = stmtdos.executeQuery();

                String nombreEstudio = "";
                if (resultSetDos.next()) {
                    nombreEstudio = resultSetDos.getString("Nombre");
                }

                String sqltres = "SELECT Nombre FROM Analisis WHERE idAnalisis = ?";
                PreparedStatement stmttres = connection.prepareStatement(sqltres);
                stmttres.setInt(1, idAnalisis);
                ResultSet resultSetTres = stmttres.executeQuery();

                String nombreAnalisis = "";
                if (resultSetTres.next()) {
                    nombreAnalisis = resultSetTres.getString("Nombre");
                }

                resultadosList.add(new Resultados(resultSet.getInt("Orden"), idEstudio, idAnalisis, resultado));
                nombresList.add(new ResultadosNombre(resultSet.getInt("Orden"), nombreEstudio, nombreAnalisis, resultado));
            }

            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return nombresList;
    }


    // Actualizar
    public void actualizarResultado(Resultados resultados) {
        String sql = "UPDATE Resultados SET Resultado = ? WHERE Orden = ? AND Estudio = ? AND Analisis = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, resultados.getResultado());
            stmt.setInt(2, resultados.getOrden());
            stmt.setInt(3, resultados.getEstudio());
            stmt.setInt(4, resultados.getAnalisis());
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
                alertb.setContentText("Se ha editado correctamente el Resultado");

                alertb.showAndWait();

            } else {
                Alert alertb = new Alert(Alert.AlertType.INFORMATION);
                alertb.setTitle("Mensaje");
                alertb.setHeaderText(null);
                alertb.setContentText("No se pudo editar el Resultado");

                alertb.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Eliminar
    public void eliminarResultado(int orden, int estudio, int analisis) {
        String sql = "DELETE FROM Resultados WHERE Orden = ? AND Estudio = ? AND Analisis = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orden);
            stmt.setInt(2, estudio);
            stmt.setInt(3, analisis);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
