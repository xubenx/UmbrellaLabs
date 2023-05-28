package gui;

import dao.AnalisisDAO;
import dao.ClienteDAO;
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
import model.Analisis;
import model.Cliente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AnalisisView extends Application {

    public TextField textoLimInferior;
    public TextField textoLimSuperior;
    public Button botonAgregar;
    public Button botonEditar;
    public Button botonBorrar;
    public Button btnLimpiar;
    public TableView tableAnalisis;
    public TableColumn columnID;
    public TableColumn columnNombre;
    public TableColumn columnLimInferior;
    public TableColumn columnLimSuperior;
    public TextField textoID;
    public TextField textoNombre;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AnalisisView.class.getResource("/com/example/umbrellalabs/AnalisisGUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setScene(scene);
        primaryStage.show();


        try{
            ClienteDAO dao = new ClienteDAO(Conexion.conectar());

        }catch (Exception ex){
            System.err.println("q onda");
            ex.printStackTrace();
        }
    }
    public void initialize() throws SQLException {
        informacion();
        textoID.setText(Integer.toString(Integer.parseInt("0")));

    }

    public void informacion() throws SQLException {
        AnalisisDAO dao = new AnalisisDAO(Conexion.conectar());

        List<Analisis> analisis = dao.obtenerTodosAnalisis();
        ObservableList<Analisis> data = FXCollections.observableArrayList(analisis);

        // Configura las columnas para mostrar los datos de los objetos Cliente
        columnID.setCellValueFactory(new PropertyValueFactory<>("idAnalisis"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnLimInferior.setCellValueFactory(new PropertyValueFactory<>("limInferior"));
        columnLimSuperior.setCellValueFactory(new PropertyValueFactory<>("limSuperior"));

        // Carga los datos en el TableView
        tableAnalisis.setItems(data);
    }

    public void clicAgregar(ActionEvent actionEvent) throws SQLException {
        try {
            Analisis analisis = new Analisis(
                    Integer.parseInt(textoID.getText().toString()),
                    textoNombre.getText().toString(),
                    Double.parseDouble(textoLimInferior.getText().toString()),
                    Double.parseDouble(textoLimSuperior.getText().toString()));

            try {
                AnalisisDAO dao = new AnalisisDAO(Conexion.conectar());
                dao.insertarAnalisis(analisis);


                System.out.printf("Análisis agregado correctamente.");
                informacion();
                clean();
            } catch (Exception ex) {
                System.err.println("q onda");
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            System.err.println("q onda");
            ex.printStackTrace();
        }
    }

    public void clicBorrar(ActionEvent actionEvent) {
        try {
            AnalisisDAO dao = new AnalisisDAO(Conexion.conectar());
            dao.eliminarAnalisis(Integer.parseInt(textoID.getText().toString()));

            clean();
            informacion();
        } catch (Exception ex) {
            System.err.println("q onda");
            ex.printStackTrace();
        }
    }

    public void clicEditar(ActionEvent actionEvent) {
        try {
            Analisis analisis = new Analisis(
                    Integer.parseInt(textoID.getText().toString()),
                    textoNombre.getText().toString(),
                    Double.parseDouble(textoLimInferior.getText().toString()),
                    Double.parseDouble(textoLimSuperior.getText().toString()));

            try {
                AnalisisDAO dao = new AnalisisDAO(Conexion.conectar());
                dao.actualizarAnalisis(analisis);


                informacion();
                clean();
            } catch (Exception ex) {
                System.err.println("q onda");
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            System.err.println("q onda");
            ex.printStackTrace();
        }
    }

    public void obtenerAnalisis(MouseEvent mouseEvent) {
        Analisis seleccionado = (Analisis) tableAnalisis.getSelectionModel().selectedItemProperty().get();
        if (seleccionado!=null){
        textoID.setText(Integer.toString(seleccionado.getIdAnalisis()));
        textoNombre.setText(seleccionado.getNombre());
        textoLimInferior.setText(Double.toString(seleccionado.getLimInferior()));
        textoLimSuperior.setText(Double.toString(seleccionado.getLimSuperior()));

        System.out.printf("" + seleccionado.toString());}
    }

    public void contar() throws SQLException {
        int total = 0;
        AnalisisDAO dao = new AnalisisDAO(Conexion.conectar());
        dao.contar(total);

    }

    public void clean() {
        textoID.setText(Integer.toString(Integer.parseInt("0")));

        textoNombre.clear();
        textoLimInferior.clear();
        textoLimSuperior.clear();
    }

    public void clicClean(ActionEvent actionEvent) {
        clean();
    }
}
