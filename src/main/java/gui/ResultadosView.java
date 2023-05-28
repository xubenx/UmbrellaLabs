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

public class ResultadosView extends Application {


    public Button botonAgregar;
    public Button botonEditar;
    public Button botonBorrar;
    public Button btnLimpiar;
    public TableView tableResultado;
    public TableColumn columnEstudio;
   
    public TextField textoID;
    public ComboBox textoAnalisis;
    public ComboBox textoOrden;
    public ComboBox textoEstudio;
    public TextField textoResultado;
    public TableColumn columAnalisis;
    public TableColumn columnOrden;
    public TableColumn columnResultado;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ResultadosView.class.getResource("/com/example/umbrellalabs/ResultadoGUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setScene(scene);
        primaryStage.show();


        try {
            ResultadosDAO dao = new ResultadosDAO(Conexion.conectar());

        } catch (Exception ex) {
            System.err.println("q onda");
            ex.printStackTrace();
        }
    }

    public void initialize() throws SQLException, ParseException {
        informacion();
        relenarcombos();

    }

    public void informacion() throws SQLException {
        ResultadosDAO dao = new ResultadosDAO(Conexion.conectar());

        List<ResultadosNombre> analisis = dao.obtenerTodosResultados();
        ObservableList<ResultadosNombre> data = FXCollections.observableArrayList(analisis);

        columnEstudio.setCellValueFactory(new PropertyValueFactory<>("Estudio"));
        columnOrden.setCellValueFactory(new PropertyValueFactory<>("Orden"));
        columnResultado.setCellValueFactory(new PropertyValueFactory<>("Resultado"));
        columAnalisis.setCellValueFactory(new PropertyValueFactory<>("Analisis"));

        // Carga los datos en el TableView
        tableResultado.setItems(data);
    }



    public void clicEditar(ActionEvent actionEvent) {
        try {
            ResultadosDAO dao = new ResultadosDAO(Conexion.conectar());

            EstudioDAO estudioDAO = new EstudioDAO(Conexion.conectar());
            Estudio estudio = estudioDAO.obtenerEstudioPorNombre(textoEstudio.getValue().toString());

            AnalisisDAO analisisDAO = new AnalisisDAO(Conexion.conectar());
            Analisis analisis = analisisDAO.obtenerAnalisisPorNombre(textoAnalisis.getValue().toString());



            int orden = Integer.parseInt(textoOrden.getValue().toString());
            double resultado = Double.parseDouble(textoResultado.getText().toString());
            Resultados resultados = new Resultados(orden, estudio.getIdEstudio(), analisis.getIdAnalisis(), resultado);
            dao.actualizarResultado(resultados);

            tableResultado.getItems().clear();
            int idOrden = Integer.parseInt(textoOrden.getValue().toString());
            ResultadosDAO daoo = new ResultadosDAO(Conexion.conectar());

            List<ResultadosNombre> analisiss = daoo.obtenerResultadosPorIdOrden(idOrden);
            ObservableList<ResultadosNombre> data = FXCollections.observableArrayList(analisiss);

            columnEstudio.setCellValueFactory(new PropertyValueFactory<>("Estudio"));
            columnOrden.setCellValueFactory(new PropertyValueFactory<>("Orden"));
            columnResultado.setCellValueFactory(new PropertyValueFactory<>("Resultado"));
            columAnalisis.setCellValueFactory(new PropertyValueFactory<>("Analisis"));

            // Carga los datos en el TableView
            tableResultado.setItems(data);
            clean();
        } catch (Exception ex) {
            System.err.println("Error al editar resultado");
            ex.printStackTrace();
        }
    }

    public void obtenerResultados(MouseEvent mouseEvent) {
        ResultadosNombre seleccionado = (ResultadosNombre) tableResultado.getSelectionModel().selectedItemProperty().get();

        if(seleccionado != null){
            textoEstudio.setValue((seleccionado.getEstudio()));
            textoAnalisis.setValue((seleccionado.getAnalisis()));
            textoResultado.setText(Double.toString(seleccionado.getResultado()));

            System.out.printf("" + seleccionado.toString());
        }

    }

    public void contar() throws SQLException {
    }

    public void clean() {

    }

    public void clicClean(ActionEvent actionEvent) {
        clean();
    }
    public void relenarcombos() throws SQLException, ParseException {
        EstudioDAO dao = new EstudioDAO(Conexion.conectar());
        List<Estudio> analisis = dao.obtenerTodosEstudios();
        ObservableList<String> analisisList = FXCollections.observableArrayList();
        for(Estudio a: analisis){
            analisisList.add(String.valueOf(a.getIdEstudio()));

        }
        textoEstudio.setItems((analisisList));


        OrdenDAO daoo = new OrdenDAO(Conexion.conectar());
        List<Orden> Estudio = daoo.obtenerTodasOrdenes();
        ObservableList<String> EstudioList = FXCollections.observableArrayList();
        for(Orden a: Estudio){
            EstudioList.add(String.valueOf(a.getIdOrden()));

        }
        textoOrden.setItems((EstudioList));

        AnalisisDAO daooo = new AnalisisDAO(Conexion.conectar());
        List<Analisis> analises = daooo.obtenerTodosAnalisis();
        ObservableList<String> listaanalisis = FXCollections.observableArrayList();
        for(Analisis a: analises){
            listaanalisis.add(String.valueOf(a.getIdAnalisis()));

        }
        textoAnalisis.setItems(listaanalisis);
    }
    public void obtenerAnalisis(MouseEvent mouseEvent) {
    }

    public void filtrarAnalisis(ActionEvent actionEvent) {

        tableResultado.getItems().clear();
        int idOrden = Integer.parseInt(textoOrden.getValue().toString());
        ResultadosDAO dao = new ResultadosDAO(Conexion.conectar());

        List<ResultadosNombre> analisis = dao.obtenerResultadosPorIdOrden(idOrden);
        ObservableList<ResultadosNombre> data = FXCollections.observableArrayList(analisis);

        columnEstudio.setCellValueFactory(new PropertyValueFactory<>("Estudio"));
        columnOrden.setCellValueFactory(new PropertyValueFactory<>("Orden"));
        columnResultado.setCellValueFactory(new PropertyValueFactory<>("Resultado"));
        columAnalisis.setCellValueFactory(new PropertyValueFactory<>("Analisis"));

        // Carga los datos en el TableView
        tableResultado.setItems(data);

    }

    public void onRefresh(ActionEvent actionEvent) throws SQLException {
        tableResultado.getItems().clear();
        informacion();
    }
}
