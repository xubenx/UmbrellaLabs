package gui;

import dao.AnalisisDAO;
import dao.EstudioDAO;
import dao.OrdenDAO;
import dao.generarReporteDAO;
import data.Conexion;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.Analisis;
import model.Estudio;
import model.Orden;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class ReporteView extends Application {

    public ComboBox comboOrden;
    public Button btnGenerate;

    public static void main(String[] args) {
        launch(args);
    }
    public void relenarcombos() throws SQLException, ParseException {



        OrdenDAO daoo = new OrdenDAO(Conexion.conectar());
        List<Orden> Estudio = daoo.obtenerTodasOrdenes();
        ObservableList<String> EstudioList = FXCollections.observableArrayList();
        for(Orden a: Estudio){
            EstudioList.add(String.valueOf(a.getIdOrden()));

        }
        comboOrden.setItems((EstudioList));


    }

    public void initialize() throws SQLException, ParseException {
        relenarcombos();
    }
    @Override
    public void start(Stage primaryStage) {

    }

    public void generateReport(ActionEvent actionEvent) {
        if(comboOrden.getValue() != null){
            generarReporteDAO generarReporteDAO = new generarReporteDAO();
            generarReporteDAO.generateJasperReport(Integer.parseInt(comboOrden.getValue().toString()),Conexion.conectar());

        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Ingresa la orden a generar");
            alert.showAndWait();
        }

    }
}
