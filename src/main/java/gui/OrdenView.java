package gui;

import dao.ClienteDAO;
import dao.OrdenDAO;
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
import model.Orden;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class OrdenView extends Application {

    public Button botonAgregar;
    public Button botonEditar;
    public Button botonBorrar;
    public TextField textoID;
    public TextField textoSubtotal;
    public TextField textoIVA;
    public Label textoTotal;
    public TableView tableOrden;
    public TableColumn columnID;
    public TableColumn columnCliente;
    public TableColumn columnFecha;
    public TableColumn columnSubtotal;
    public TableColumn columnIVA;
    public TableColumn columnTotal;
    public Button btnClean;
    public ComboBox textoRFC;
    public DatePicker textFecha;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(OrdenView.class.getResource("/com/example/umbrellalabs/OrdenGUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setScene(scene);
        primaryStage.show();


        try{
            OrdenDAO dao = new OrdenDAO(Conexion.conectar());

        }catch (Exception ex){
            System.err.println("q onda");
            ex.printStackTrace();
        }
    }
    public void informacion() throws SQLException {
        OrdenDAO dao = new OrdenDAO(Conexion.conectar());

        List<Orden> orden = dao.obtenerTodasOrdenes();
        ObservableList<Orden> data = FXCollections.observableArrayList(orden);

        // Configura las columnas para mostrar los datos de los objetos Cliente
        columnID.setCellValueFactory(new PropertyValueFactory<>("idOrden"));
        columnCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        columnFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        columnSubtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
        columnIVA.setCellValueFactory(new PropertyValueFactory<>("iva"));
        columnTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        // Carga los datos en el TableView
        tableOrden.setItems(data);
    }
    public void initialize() throws SQLException {
        informacion();
        relenarcombos();
        textoRFC.setValue("1");
        textoID.setText("0");
        textoIVA.setText("1.16");
    }
    public void relenarcombos() throws SQLException {
        ClienteDAO dao = new ClienteDAO(Conexion.conectar());

        List<Cliente> clientes = dao.obtenerTodosClientes();
        ObservableList<String> rfcList = FXCollections.observableArrayList();
        for(Cliente list: clientes){
            rfcList.add(list.getRFC());
        }
        textoRFC.setItems((rfcList));

    }

    public void clicAgregar(ActionEvent actionEvent) throws SQLException {

        try {
            Double total = Double.parseDouble(textoSubtotal.getText()) * Double.parseDouble(textoIVA.getText());
            Orden orden = new Orden(
                    Integer.parseInt(textoID.getText()),
                    textoRFC.getValue().toString()
                    ,textFecha.getValue().toString()
                    ,Double.parseDouble(textoSubtotal.getText())
                    ,Double.parseDouble(textoIVA.getText())
                    ,Double.parseDouble(String.format("%.2f",total)));

            try{
                OrdenDAO dao = new OrdenDAO(Conexion.conectar());
                dao.agregarOrden(orden);


                System.out.printf("Orden agregado correctamente.");
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
            OrdenDAO dao = new OrdenDAO(Conexion.conectar());
            dao.eliminarOrden(Integer.parseInt(textoID.getText()));

            clean();
            informacion();
        }catch (Exception ex){
            System.err.println("q onda");
            ex.printStackTrace();
        }
    }
    public void clicEditar(ActionEvent actionEvent) {
        try {
            Double total = Double.parseDouble(textoSubtotal.getText()) * Double.parseDouble(textoIVA.getText());
            Orden orden = new Orden(
                    Integer.parseInt(textoID.getText()),
                    textoRFC.getValue().toString()
                    ,textFecha.getValue().toString()
                    ,Double.parseDouble(textoSubtotal.getText())
                    ,Double.parseDouble(textoIVA.getText())
                    ,total);


            try{
                OrdenDAO dao = new OrdenDAO(Conexion.conectar());
                dao.actualizarOrden(orden);


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

    public void obtenerOrden(MouseEvent mouseEvent) {
        Orden seleccionado = (Orden) tableOrden.getSelectionModel().selectedItemProperty().get();
        if (seleccionado!=null){
            textoRFC.setValue(seleccionado.getCliente());
            textFecha.setValue(LocalDate.parse(seleccionado.getFecha()));
            textoID.setText(String.valueOf(seleccionado.getIdOrden()));
            textoSubtotal.setText(String.valueOf(seleccionado.getSubtotal()));
            textoIVA.setText(String.valueOf(seleccionado.getIva()));
            textoTotal.setText(String.valueOf(seleccionado.getTotal()));
            System.out.printf(""+seleccionado.toString());
        }

    }

    public void clean(){
        textoRFC.setValue("1");
        textoID.setText("0");
        textoIVA.setText("1.16");
        textoTotal.setText("$");
        textoSubtotal.clear();

    }

    public void onClean(ActionEvent actionEvent) {
        clean();
    }
}
