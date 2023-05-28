package gui;

import dao.*;
import data.Conexion;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class EstudioOrdenView extends Application {


    public Button botonBorrar;

    public TableView tableEstudio;
    public TableColumn columnID;
    public TableColumn columnNombre;
    public TableColumn columnPrecio;
    public ComboBox idEstudio;

    Estudio buffEstudio = new Estudio();
     Orden buffOrden = new Orden();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EstudioOrdenView.class.getResource("/com/example/umbrellalabs/EstudioOrdenGUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setScene(scene);
        primaryStage.show();


        try{
            EstudioOrdenDAO dao = new EstudioOrdenDAO(Conexion.conectar());

        }catch (Exception ex){
            System.err.println("q onda");
            ex.printStackTrace();
        }
    }

    public void relenarcombos() throws SQLException, ParseException {



        OrdenDAO dao = new OrdenDAO(Conexion.conectar());
        List<Orden> analisis = dao.obtenerTodasOrdenes();
        ObservableList<String> analisisList = FXCollections.observableArrayList();
        for(Orden a: analisis){
            analisisList.add(String.valueOf(a.getIdOrden()));


        }
        idEstudio.setItems((analisisList));


    }

    public void informacion() throws SQLException {
        EstudioOrdenDAO dao = new EstudioOrdenDAO(Conexion.conectar());

        List<EstudioOrdenNombre> estudios = dao.obtenerTodosEstudiosOrden();
        ObservableList<EstudioOrdenNombre> data = FXCollections.observableArrayList(estudios);

        // Configura las columnas para mostrar los datos de los objetos Cliente
        columnID.setCellValueFactory(new PropertyValueFactory<>("idOrden"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombreEstudio"));
        columnPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));


        // Carga los datos en el TableView
        tableEstudio.setItems(data);
    }




    public void initialize() throws SQLException, ParseException {
        informacion();
        relenarcombos();
    }


    public void cambioBox(ActionEvent actionEvent) {

        tableEstudio.getItems().clear();

        OrdenDAO ordenDAO = new OrdenDAO(Conexion.conectar());
        int idstudio = Integer.parseInt(idEstudio.getValue().toString());
        Orden orden = ordenDAO.obtenerOrdenPorId(idstudio);

        EstudioOrdenDAO dao = new EstudioOrdenDAO(Conexion.conectar());

        List<EstudioOrdenNombre> estudios = dao.obtenerTodosEstudiosOrdenporNombre(orden.getIdOrden());
        ObservableList<EstudioOrdenNombre> data = FXCollections.observableArrayList(estudios);

        // Configura las columnas para mostrar los datos de los objetos Cliente
        columnID.setCellValueFactory(new PropertyValueFactory<>("idOrden"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombreEstudio"));
        columnPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));


        // Carga los datos en el TableView
        tableEstudio.setItems(data);

    }
}
