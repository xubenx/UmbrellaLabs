package dao;

import data.Conexion;
import model.Estudio;
import model.EstudioOrden;
import model.EstudioOrdenNombre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstudioOrdenDAO {
    private Connection connection;

    public EstudioOrdenDAO(Connection connection) {
        this.connection = connection;
    }

    // Crear
    public void agregarEstudioOrden(EstudioOrden estudioOrden) {
        String sql = "INSERT INTO EstudioOrden (Orden, Estudio, Precio) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, estudioOrden.getOrden());
            stmt.setInt(2, estudioOrden.getEstudio());
            stmt.setDouble(3, estudioOrden.getPrecio());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Obtener por ID
    public List<EstudioOrdenNombre> obtenerTodosEstudiosOrdenporNombre(int orden) {
        String sql = "SELECT * " +
                "FROM EstudioOrden WHERE Orden = ? ";

        List<EstudioOrdenNombre> estudioOrdenNombreList = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1,orden);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                EstudioOrdenNombre estudioOrdenNombre = new EstudioOrdenNombre();

                EstudioDAO estudioDAO = new EstudioDAO(connection);

                int id = resultSet.getInt("Estudio");

                System.out.printf("\n"+ id);
                try {
                    estudioOrdenNombre.setNombreEstudio(estudioDAO.obtenerEstudioPorId(id).getNombre());

                }catch (Exception ex){}



                estudioOrdenNombre.setIdOrden(resultSet.getInt("Orden"));

                estudioOrdenNombre.setPrecio(resultSet.getDouble("Precio"));

                estudioOrdenNombreList.add(estudioOrdenNombre);
            }

            resultSet.close();

            return estudioOrdenNombreList;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return estudioOrdenNombreList;
    }



    // Obtener todos
    public List<EstudioOrdenNombre> obtenerTodosEstudiosOrden() {
        String sql = "SELECT * " +
                "FROM EstudioOrden ";

        List<EstudioOrdenNombre> estudioOrdenNombreList = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                EstudioOrdenNombre estudioOrdenNombre = new EstudioOrdenNombre();

                EstudioDAO estudioDAO = new EstudioDAO(connection);

                int id = resultSet.getInt("Estudio");

                System.out.printf("\n"+ id);
                try {
                    estudioOrdenNombre.setNombreEstudio(estudioDAO.obtenerEstudioPorId(id).getNombre());

                }catch (Exception ex){}



                estudioOrdenNombre.setIdOrden(resultSet.getInt("Orden"));

                estudioOrdenNombre.setPrecio(resultSet.getDouble("Precio"));

                estudioOrdenNombreList.add(estudioOrdenNombre);
            }

            resultSet.close();

            return estudioOrdenNombreList;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return estudioOrdenNombreList;
    }



    // Actualizar
    public void actualizarEstudioOrden(EstudioOrden estudioOrden) {
        String sql = "UPDATE EstudioOrden SET Precio = ? WHERE Estudio = ? AND Orden = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, estudioOrden.getPrecio());
            stmt.setInt(2, estudioOrden.getEstudio());
            stmt.setInt(3, estudioOrden.getOrden());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Eliminar
    public void eliminarEstudioOrden(int estudio, int orden) {
        String sql = "DELETE FROM EstudioOrden WHERE Estudio = ? AND Orden = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, estudio);
            stmt.setInt(2, orden);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
