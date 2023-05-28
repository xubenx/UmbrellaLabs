package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static Connection conn = null;

    public static Connection conectar() {
        if (conn == null) {
            String url = "jdbc:sqlite:UmbrellaLabs.db";
            try {
                conn = DriverManager.getConnection(url);
                System.out.println("Conexión establecida.");
            } catch (SQLException e) {
                System.out.println("Error al conectar: ");
            }
        }
        return conn;
    }

    public static void desconectar() {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.out.println("Error al desconectar: ");
            }
        }
    }

}
