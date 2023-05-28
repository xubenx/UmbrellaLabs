package gui;

import dao.AnalisisDAO;
import dao.AnalisisEstudioDAO;
import dao.AnalisisDAO;
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
import model.AnalisisEstudio;
import model.Estudio;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RelacionesView extends Application {
    public TableView tableAnalisis;
    public ComboBox comboEstudio;
    public Button btnAdd;
    public Button btnDelete;
    public TableColumn columnAnalisiss;
    AnalisisEstudio analisisEstudio = new AnalisisEstudio();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RelacionesView.class.getResource("/com/example/umbrellalabs/RelacionAnalisisEstudioGUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setScene(scene);
        primaryStage.show();

        try{
            AnalisisEstudioDAO dao = new AnalisisEstudioDAO(Conexion.conectar());

        }catch (Exception ex){
            System.err.println("q onda");
            ex.printStackTrace();
        }


    }
    public void informacion() throws SQLException {
        AnalisisDAO dao = new AnalisisDAO(Conexion.conectar());

        List<Analisis> analisis = dao.obtenerTodosAnalisis();
        ObservableList<Analisis> data = FXCollections.observableArrayList(analisis);

        // Configura las columnas para mostrar los datos de los objetos Cliente
        columnAnalisiss.setCellValueFactory(new PropertyValueFactory<>("nombre"));



        // Carga los datos en el TableView
        tableAnalisis.setItems(data);
    }
    public void initialize() throws SQLException {
        relenarcombos();
        informacion();
        tableAnalisis.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


    }


    public void relenarcombos() throws SQLException {
        EstudioDAO daoo = new EstudioDAO(Conexion.conectar());
        List<Estudio> Estudio = daoo.obtenerTodosEstudios();
        List<String> nombres = new ArrayList<>();
        ObservableList<String> names = FXCollections.observableArrayList(nombres);

        for(Estudio a: Estudio){

            names.add(a.getNombre());
        }
        comboEstudio.setItems(names);

    }
        public void onAdd(ActionEvent actionEvent) {
            AnalisisEstudioDAO dao = new AnalisisEstudioDAO(Conexion.conectar());


            ObservableList<Analisis> filasSeleccionadas = tableAnalisis.getSelectionModel().getSelectedItems();

                if(filasSeleccionadas.isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Selecciona al menos un analisis para crear la relación.");
                    alert.showAndWait();

                } else {



                    try {
                        for (Analisis fila: filasSeleccionadas){
                            int analisis = fila.getIdAnalisis();
                            EstudioDAO estudioDAO = new EstudioDAO(Conexion.conectar());
                            Estudio estudio = estudioDAO.obtenerEstudioPorNombre(comboEstudio.getValue().toString());

                            AnalisisEstudio orden = new AnalisisEstudio(analisis,(estudio.getIdEstudio()));
                            dao.insertarAnalisisEstudio(orden);
                        }


                        System.out.printf("Orden agregado correctamente.");
                        informacion();
                        Conexion.desconectar();

                    } catch (Exception ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Mensaje");
                        alert.setHeaderText(null);
                        alert.setContentText("No se puede agregar,su estudio ya cuenta con este analisis");

                        alert.showAndWait();
                    }
                }
    }

    public void onDelete(ActionEvent actionEvent) {
        AnalisisEstudioDAO dao = new AnalisisEstudioDAO(Conexion.conectar());

        ObservableList<Analisis> filasSeleccionadas = tableAnalisis.getSelectionModel().getSelectedItems();

        if (filasSeleccionadas.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Selecciona al menos un análisis para eliminar la relación.");
            alert.showAndWait();
        } else {
            try {
                for (Analisis fila: filasSeleccionadas) {

                    int analisis = fila.getIdAnalisis();
                    EstudioDAO estudioDAO = new EstudioDAO(Conexion.conectar());
                    Estudio estudio = estudioDAO.obtenerEstudioPorNombre(comboEstudio.getValue().toString());

                    dao.eliminarAnalisisEstudio(analisis, estudio.getIdEstudio());
                }


                informacion();
                Conexion.desconectar();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Mensaje");
                alert.setHeaderText(null);
                alert.setContentText("No se puede eliminar la relación de Análisis-Estudio.");

                alert.showAndWait();
            }
        }
    }


    public void obtenerAnalisis(MouseEvent mouseEvent) {
        ObservableList<Analisis> seleccionados = tableAnalisis.getSelectionModel().getSelectedItems();


        if (seleccionados != null){
        for (Analisis seleccionado : seleccionados) {
            // Realiza acones con cada elemento seleccionado
            System.out.printf("" + seleccionado.toString());
        }
        }

    }
}
