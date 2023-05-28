package gui;

import dao.AnalisisDAO;
import dao.ClienteDAO;
import dao.EstudioDAO;
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
import model.Estudio;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EstudioView extends Application {

    public TextField textoID;
    public TextField textNombre;
    public TextField textoPrecio;
    public Button botonAgregar;
    public Button botonEditar;
    public Button botonBorrar;
    public Button btnLimpiar;
    public TableView tableEstudio;
    public TableColumn columnID;
    public TableColumn columnNombre;
    public TableColumn columnPrecio;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(OrdenView.class.getResource("/com/example/umbrellalabs/EstudioGUI.fxml"));
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

    public void clicAgregar(ActionEvent actionEvent) throws SQLException {
        try {
            Estudio estudio = new Estudio(
                    Integer.parseInt(textoID.getText().toString()),
                    textNombre.getText().toString(),
                    Double.parseDouble(textoPrecio.getText().toString()));

            try {
                EstudioDAO dao = new EstudioDAO(Conexion.conectar());
                dao.insertarEstudio(estudio);


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
            EstudioDAO dao = new EstudioDAO(Conexion.conectar());
            dao.eliminarEstudio(Integer.parseInt(textoID.getText().toString()));


            clean();
            informacion();
        } catch (Exception ex) {
            System.err.println("q onda");
            ex.printStackTrace();
        }
    }
    public void informacion() throws SQLException {
        EstudioDAO dao = new EstudioDAO(Conexion.conectar());

        List<Estudio> estudios = dao.obtenerTodosEstudios();
        ObservableList<Estudio> data = FXCollections.observableArrayList(estudios);

        // Configura las columnas para mostrar los datos de los objetos Cliente
        columnID.setCellValueFactory(new PropertyValueFactory<>("idEstudio"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));


        // Carga los datos en el TableView
        tableEstudio.setItems(data);
    }
    public void clicEditar(ActionEvent actionEvent) {
        try {
            Estudio estudio = new Estudio(
                    Integer.parseInt(textoID.getText().toString()),
                    textNombre.getText().toString(),
                    Double.parseDouble(textoPrecio.getText().toString()));

            try {
                EstudioDAO dao = new EstudioDAO(Conexion.conectar());
                dao.actualizarEstudio(estudio);

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

    public void obtenerEstudio(MouseEvent mouseEvent) {
        Estudio seleccionado = (Estudio) tableEstudio.getSelectionModel().selectedItemProperty().get();
        if (seleccionado!=null) {
            textoID.setText(Integer.toString(seleccionado.getIdEstudio()));
            textNombre.setText(seleccionado.getNombre());
            textoPrecio.setText(Double.toString(seleccionado.getPrecio()));

            System.out.printf("" + seleccionado.toString());
        }
    }

    public void initialize() throws SQLException {
        textoID.setText(Integer.toString(Integer.parseInt("0")));
        informacion();
    }
    public void clean() {
        textoID.setText(Integer.toString(Integer.parseInt("0")));
        textNombre.clear();
        textoPrecio.clear();
    }

    public void clicClean(ActionEvent actionEvent) {
        clean();
    }
}
