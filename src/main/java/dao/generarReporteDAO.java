package dao;

import java.sql.SQLException;

import data.Conexion;
import gui.Main;
import model.Orden;
import model.ResultadosReporte;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class generarReporteDAO {


    public void generateJasperReport(int id,Connection connection) {
        connection = Conexion.conectar();

        try {

            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("noOrden", id);

            Main main = new Main();

            OrdenDAO ordenDAO = new OrdenDAO(connection);
            Orden orden = ordenDAO.obtenerOrdenPorId(id);

            String jrxmlFile = "Reporte.jrxml";
            String jasperFile = "reporte.jasper";




            String sql = "SELECT Analisis.idAnalisis, Analisis.Nombre,Analisis.LimInferior,Analisis.LimSuperior FROM Analisis INNER JOIN Resultados ON Analisis.idAnalisis = Resultados.Analisis WHERE Resultados.Orden = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);

            LinkedList<ResultadosReporte> resultados = new LinkedList<>();
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                ResultadosReporte result = new ResultadosReporte();


                int idAnalisis = rs.getInt("idAnalisis");
                String nombre = rs.getString("Nombre");
                double inferior = rs.getFloat("LimInferior");
                double superior = rs.getFloat("LimSuperior");
                result.setNombre(nombre);
                result.setLimInferior(inferior);
                result.setLimSuperior(superior);


                String sqldos = "SELECT Resultado FROM Resultados INNER JOIN Analisis ON Resultados.Analisis = Analisis.idAnalisis WHERE Resultados.Analisis = ? AND Resultados.Orden = ?";
                PreparedStatement pstmtdos = connection.prepareStatement(sqldos);
                pstmtdos.setInt(1, idAnalisis);
                pstmtdos.setInt(2, id);


                ResultSet rss = pstmtdos.executeQuery();
                double resultado = rss.getFloat("Resultado");

                result.setResultado(resultado);

                resultados.add(result);

            }

            try {


                JasperCompileManager.compileReportToFile(jrxmlFile, jasperFile);
                JasperPrint jpReport = JasperFillManager.fillReport(jasperFile, parametros, connection);
                JasperViewer.viewReport(jpReport, false);

            }catch (Exception ex){
                System.out.printf("SE PUDO");
            }
        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            if (connection != null) {
                try {
                    Conexion.desconectar();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        }

    }
}
