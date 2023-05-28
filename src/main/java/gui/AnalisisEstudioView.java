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
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AnalisisEstudioView extends Application {


    public TableView tableEstudio;

    public TableColumn columnAnalisis;
    public ComboBox idEstudio;
    public Button btnCrearAnalisisEstudio;


    AnalisisEstudio analisisEstudio = new AnalisisEstudio();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AnalisisEstudioView.class.getResource("/com/example/umbrellalabs/AnalisisEstudioGUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setScene(scene);
        primaryStage.show();


        try{
            AnalisisEstudioDAO dao = new AnalisisEstudioDAO(Conexion.conectar());

        }catch (Exception ex){
            System.err.println("q onda");
            ex.printStackTrace();
        }

        Conexion.desconectar();

    }
    public void relenarcombos() throws SQLException {



        EstudioDAO daoo = new EstudioDAO(Conexion.conectar());
        List<Estudio> Estudio = daoo.obtenerTodosEstudios();
        ObservableList<String> EstudioList = FXCollections.observableArrayList();
        for(Estudio a: Estudio){
            EstudioList.add(a.getNombre());
            analisisEstudio.setEstudio(a.getIdEstudio());
        }
            idEstudio.setItems((EstudioList));

        Conexion.desconectar();

    }
    public void clicAgregar(ActionEvent actionEvent) throws SQLException {

        try {
            AnalisisEstudio orden = new AnalisisEstudio(
                    analisisEstudio.getEstudio(),
                    analisisEstudio.getAnalisis());
            try{
                AnalisisEstudioDAO dao = new AnalisisEstudioDAO(Conexion.conectar());
                dao.insertarAnalisisEstudio(orden);


                informacion();

            }catch (Exception ex){
                System.err.println("q onda");
                ex.printStackTrace();
            }
        }catch (Exception ex){
            System.err.println("q onda");
            ex.printStackTrace();
        }
        Conexion.desconectar();


    }


    public void informacion() throws SQLException {
        AnalisisDAO dao = new AnalisisDAO(Conexion.conectar());

        List<Analisis> analisis = dao.obtenerTodosAnalisis();
        ObservableList<Analisis> data = FXCollections.observableArrayList(analisis);

        // Configura las columnas para mostrar los datos de los objetos Cliente
        columnAnalisis.setCellValueFactory(new PropertyValueFactory<>("nombre"));



        // Carga los datos en el TableView
        tableEstudio.setItems(data);
    }
    public void clicEditar(ActionEvent actionEvent) {

    }

    public void obtenerEstudio(MouseEvent mouseEvent) {


    }

    public void initialize() throws SQLException {
        informacion();
        relenarcombos();
        tableEstudio.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


    }
    public void clean() {
    }

    public void clicClean(ActionEvent actionEvent) {
        clean();
    }



    public void onAnalisisEstudio(ActionEvent actionEvent) {
        Conexion.desconectar();
        try {
            FXMLLoader fxmlLoader = new
                    FXMLLoader(RelacionesView.class.getResource(
                    "/com/example/umbrellalabs/RelacionAnalisisEstudioGUI.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Relación de estudio por analisis");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }catch (Exception ex){

        }
    }

    public void obtenerAnalisis(ActionEvent actionEvent) throws SQLException {



        tableEstudio.getItems().clear();


        AnalisisDAO dao = new AnalisisDAO(Conexion.conectar());

        List<Analisis> analisis = dao.obtenerAnalisisPorNombreEstudio(idEstudio.getValue().toString());
        ObservableList<Analisis> data = FXCollections.observableArrayList(analisis);

        // Configura las columnas para mostrar los datos de los objetos Cliente
        columnAnalisis.setCellValueFactory(new PropertyValueFactory<>("nombre"));



        // Carga los datos en el TableView
        tableEstudio.setItems(data);



    }
}
