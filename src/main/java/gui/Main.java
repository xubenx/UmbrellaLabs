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
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

public class Main extends Application {

    public Button btnCliente;

    public Button Analisis;
    public Button btnResultados;
    public Button btnOrden;
    public Button btnEstudio;
    public Button btnReporte;
    public Button btnAnalisisEstudio;
    public Button btnEstudioOrden;
    public ComboBox comboCliente;
    public ComboBox comboEstudio;
    public Button btnAddEstudio;
    public TableView tableMain;
    public TableColumn columnName;
    public TableColumn columnPrice;
    public Label labelSubtotal;
    public Label labelTotal;
    public Button btnGenerate;
    public TextField txtIVA;
    public Button btnRefresh;
    int id = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/example/umbrellalabs/MainGUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Programa de Analisis Clinico Umbrella");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public void onCliente(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new
                    FXMLLoader(ClienteView.class.getResource(
                            "/com/example/umbrellalabs/ClienteGUI.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Clientes");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }catch (Exception ex){

        }
    }

    public void onAnalisis(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new
                    FXMLLoader(AnalisisView.class.getResource(
                    "/com/example/umbrellalabs/AnalisisGUI.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Analisis");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }catch (Exception ex){

        }
    }
    public void initialize() throws SQLException {
        columnName.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("precio"));
        relenarcombos();
    }
    public void relenarcombos() throws SQLException {
        ClienteDAO dao = new ClienteDAO(Conexion.conectar());

        List<Cliente> clientes = dao.obtenerTodosClientes();
        ObservableList<String> rfcList = FXCollections.observableArrayList();
        for(Cliente list: clientes){
            rfcList.add(list.getRFC());
        }
        comboCliente.setItems((rfcList));


        EstudioDAO daoo = new EstudioDAO(Conexion.conectar());
        List<Estudio> Estudio = daoo.obtenerTodosEstudios();
        ObservableList<String> EstudioList = FXCollections.observableArrayList();
        for(Estudio a: Estudio){
            EstudioList.add(a.getNombre());
        }
        comboEstudio.setItems((EstudioList));
    }
    public void onResultados(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new
                    FXMLLoader(ResultadosView.class.getResource(
                    "/com/example/umbrellalabs/ResultadoGUI.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Resultados");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }catch (Exception ex){

        }
    }

    public void onOrden(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new
                    FXMLLoader(OrdenView.class.getResource(
                    "/com/example/umbrellalabs/OrdenGUI.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Ordenes");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }catch (Exception ex){

        }
    }

    public void onEstudio(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new
                    FXMLLoader(EstudioView.class.getResource(
                    "/com/example/umbrellalabs/EstudioGUI.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Estudios");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }catch (Exception ex){

        }
    }

    public void onReporte(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new
                    FXMLLoader(ReporteView.class.getResource(
                    "/com/example/umbrellalabs/ReporteGUI.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Reportes");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }catch (Exception ex){

        }
    }

    public void onAnalisisEstudio(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new
                    FXMLLoader(AnalisisEstudioView.class.getResource(
                    "/com/example/umbrellalabs/AnalisisEstudioGUI.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Analisis de estudio");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }catch (Exception ex){

        }
    }

    public void onEstudioOrden(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new
                    FXMLLoader(EstudioOrdenView.class.getResource(
                    "/com/example/umbrellalabs/EstudioOrdenGUI.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Estudios de orden");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }catch (Exception ex){

        }
    }
    private void calcularSumaPrecios() {
        ObservableList<PrecioNombre> data = tableMain.getItems();
        float total = 0;
        for (PrecioNombre item : data) {
            total += item.getPrecio();
        }
        labelSubtotal.setText(String.valueOf(total));

        float iva = Float.parseFloat(txtIVA.getText());
        float totals =  iva * total;
        labelTotal.setText(String.valueOf(totals));
    }

    public int obtenerSiguienteIndice(Connection conn) {
        String query = "SELECT MAX(idOrden) as max_id FROM Orden";
        int siguienteIndice = 1;

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                int max_id = rs.getInt("max_id");
                siguienteIndice = max_id + 1;
            }
        } catch (SQLException e) {
        }

        return siguienteIndice;
    }
    public void onGenerate(ActionEvent actionEvent) {


            EstudioOrdenDAO dao = new EstudioOrdenDAO(Conexion.conectar());

            ObservableList<PrecioNombre> filasSeleccionadas = tableMain.getItems();

            if(filasSeleccionadas.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Ingresa al menos un Estudio a la orden");
                alert.showAndWait();

            } else {

                try {
                    for (PrecioNombre fila: filasSeleccionadas){
                        String nombre = fila.getNombre();


                        EstudioDAO estudioDao = new EstudioDAO(Conexion.conectar());
                        Estudio buff = estudioDao.obtenerEstudioPorNombre(nombre);


                        id = obtenerSiguienteIndice(Conexion.conectar());

                        int orden = id;
                        int estudio = Integer.parseInt(String.valueOf(buff.getIdEstudio()));
                        double precio = Double.parseDouble(String.valueOf(fila.getPrecio()));

                        // Crear un objeto EstudioOrden con los valores obtenidos
                        EstudioOrden estudioOrden = new EstudioOrden( orden,estudio, precio);

                        // Insertar el EstudioOrden en la base de datos
                        EstudioOrdenDAO estudioOrdenDAO = new EstudioOrdenDAO(Conexion.conectar());
                        estudioOrdenDAO.agregarEstudioOrden(estudioOrden);





                        AnalisisEstudioDAO analisisEstudioDAO = new AnalisisEstudioDAO(Conexion.conectar());

                        List<AnalisisEstudio> analisis = analisisEstudioDAO.obtenerAnalisisEstudioPorIds(buff.getIdEstudio());

                        for (AnalisisEstudio filados: analisis){

                            mostrarAlertaConTextField(buff.getIdEstudio(),filados.getAnalisis());

                            System.out.printf(""+filados.toString());
                        }


                    }

                        try {
                            Double total = Double.parseDouble(labelSubtotal.getText()) * Double.parseDouble(txtIVA.getText());
                            Orden orden = new Orden(
                                    id,
                                    comboCliente.getValue().toString(),
                                     LocalDate.now().toString()
                                    ,Double.parseDouble(labelSubtotal.getText())
                                    ,Double.parseDouble(txtIVA.getText())
                                    ,Double.parseDouble(String.format("%.2f",total)));

                            try{
                                OrdenDAO ordendao = new OrdenDAO(Conexion.conectar());
                                ordendao.agregarOrden(orden);
                                AnalisisEstudioDAO analisisEstudioDAO = new AnalisisEstudioDAO(Conexion.conectar());

                                List<AnalisisEstudioNombre> analisis = analisisEstudioDAO.obtenerTodosAnalisisEstudios();



                            }catch (Exception ex){
                                System.err.println("q onda");
                                ex.printStackTrace();
                            }
                        }catch (Exception ex){
                            System.err.println("q onda");
                            ex.printStackTrace();
                        }

                } catch (Exception ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Mensaje");
                    alert.setHeaderText(null);
                    alert.setContentText("No se puede agregar uno de sus Estudios ya que la Orden ya cuenta con uno existente.");

                    alert.showAndWait();
                }
            }

            clean();
            Conexion.desconectar();

    }


    public void mostrarAlertaConTextField(int idEstudio, int idAnalisis) throws SQLException {
        EstudioDAO estudioDAO = new EstudioDAO(Conexion.conectar());
        AnalisisDAO analisisDAO = new AnalisisDAO(Conexion.conectar());

        Estudio estudio = estudioDAO.obtenerEstudioPorId(idEstudio);
        Analisis analisis = analisisDAO.obtenerAnalisisPorId(idAnalisis);



        String inputText = "0";
                try {
                    double resultado = Double.parseDouble(inputText);

                        Resultados resultados = new Resultados(id, idEstudio, idAnalisis, resultado);
                        try {
                            ResultadosDAO dao = new ResultadosDAO(Conexion.conectar());
                            dao.agregarResultado(resultados);
                        } catch (Exception ex) {
                            System.err.println("Error al agregar el resultado: ");
                            ex.printStackTrace();
                        }

                } catch (Exception ex) {

                }
        }



    public void onAddEstudio(ActionEvent actionEvent) {
        String nombreEstudio = comboEstudio.getValue().toString();
        Double precioEstudio = getPrecioEstudio(nombreEstudio); // Obtén el precio del estudio seleccionado

        PrecioNombre model = new PrecioNombre(nombreEstudio,
                (float)precioEstudio.doubleValue());
        ObservableList<PrecioNombre> data = tableMain.getItems();
        data.add(model);

        tableMain.setItems(data);

        calcularSumaPrecios();
    }

    private Double getPrecioEstudio(String nombreEstudio) {
        EstudioDAO estudioDAO = new EstudioDAO(Conexion.conectar());
        Float precio = 0.0F;
        try {
            Estudio estudio = estudioDAO.obtenerEstudioPorNombre(nombreEstudio);
            if (estudio != null) {
                precio = (float) estudio.getPrecio().doubleValue();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Double.valueOf(precio);
    }


    public void clean(){
        comboCliente.setPromptText("Ingresar cliente");
        comboEstudio.setPromptText("Agregar estudios");
        labelSubtotal.setText("$0");
        labelTotal.setText("0");

        txtIVA.setText("1.16");
        tableMain.getItems().clear();
    }

    public void onRefresh(ActionEvent actionEvent) throws SQLException {
        clean();
        relenarcombos();
    }
}
