package gui;

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
import model.Cliente;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ClienteView extends Application {

    public TextField textoRFC;
    public TextField textoCorreo;
    public TextField textoNombre;
    public ComboBox comboSexo;
    public Button botonIrPrimer;
    public Spinner selectorEdad;
    public Button botonIrAnterior;
    public Button botonIrSiguiente;
    public Button botonIrUltimo;
    public Button botonAgregar;
    public Button botonEditar;
    public Button botonBorrar;
    public Button botonAceptar;
    public Button botonCancelar;
    public TextField textoPaterno;
    public TextField textoMaterno;
    public TextField textoTelefono;
    public TableColumn columnRFC;
    public TableView tableCliente;
    public TableColumn columnNombre;
    public TableColumn columnPaterno;
    public TableColumn columnMaterno;
    public TableColumn columnTelefono;
    public TableColumn columnCorreo;
    public Button btnClean;


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClienteView.class.getResource("/com/example/umbrellalabs/ClienteGUI.fxml"));
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
    public void informacion() throws SQLException {
        ClienteDAO dao = new ClienteDAO(Conexion.conectar());

        List<Cliente> clientes = dao.obtenerTodosClientes();
        ObservableList<Cliente> data = FXCollections.observableArrayList(clientes);

        // Configura las columnas para mostrar los datos de los objetos Cliente
        columnRFC.setCellValueFactory(new PropertyValueFactory<>("RFC"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        columnPaterno.setCellValueFactory(new PropertyValueFactory<>("APaterno"));
        columnMaterno.setCellValueFactory(new PropertyValueFactory<>("AMaterno"));
        columnTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
        columnCorreo.setCellValueFactory(new PropertyValueFactory<>("Correo"));

        // Carga los datos en el TableView
        tableCliente.setItems(data);
    }
    public void initialize() throws SQLException {
        informacion();
    }

    public void clicAgregar(ActionEvent actionEvent) throws SQLException {

        try {
            Cliente cliente = new Cliente(
                    textoRFC.getText().toString()
            ,textoNombre.getText().toString()
            ,textoPaterno.getText().toString()
            ,textoMaterno.getText().toString()
            ,textoTelefono.getText().toString()
            ,textoCorreo.getText().toString());

            try{
                ClienteDAO dao = new ClienteDAO(Conexion.conectar());
                dao.insertarCliente(cliente);


                informacion();
                clean();
            }catch (Exception ex){
                System.err.println("q onda");
                ex.printStackTrace();
            }
        }catch (Exception ex){
            System.err.println("q onda");
            ex.printStackTrace();
        }

    }




    public void clicBorrar(ActionEvent actionEvent) {
        try{
            ClienteDAO dao = new ClienteDAO(Conexion.conectar());
            dao.eliminarCliente(textoRFC.getText().toString());

            clean();
            informacion();
        }catch (Exception ex){
            System.err.println("q onda");
            ex.printStackTrace();
        }
    }
    public void clicEditar(ActionEvent actionEvent) {
        try {
            Cliente cliente = new Cliente(
                    textoRFC.getText().toString()
                    ,textoNombre.getText().toString()
                    ,textoPaterno.getText().toString()
                    ,textoMaterno.getText().toString()
                    ,textoTelefono.getText().toString()
                    ,textoCorreo.getText().toString());

            try{
                ClienteDAO dao = new ClienteDAO(Conexion.conectar());
                dao.actualizarCliente(cliente);

                informacion();
                clean();
            }catch (Exception ex){
                System.err.println("q onda");
                ex.printStackTrace();
            }
        }catch (Exception ex){
            System.err.println("q onda");
            ex.printStackTrace();
        }
    }

    public void obtenerCliente(MouseEvent mouseEvent) {
        Cliente seleccionado = (Cliente) tableCliente.getSelectionModel().selectedItemProperty().get();
        if (seleccionado!=null) {
            textoNombre.setText(seleccionado.getNombre());
            textoRFC.setText(seleccionado.getRFC());
            textoCorreo.setText(seleccionado.getCorreo());
            textoMaterno.setText(seleccionado.getAMaterno());
            textoPaterno.setText(seleccionado.getAPaterno());
            textoTelefono.setText(seleccionado.getTelefono());
            System.out.printf("" + seleccionado.toString());
        }
    }

    public void clean(){
        textoNombre.clear();
        textoRFC.clear();
        textoCorreo.clear();
        textoMaterno.clear();
        textoPaterno.clear();
        textoTelefono.clear();
    }

    public void onClean(ActionEvent actionEvent) {
        clean();
    }
}
