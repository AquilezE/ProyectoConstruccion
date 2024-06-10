package proyectoconstruccion.Controllers.dashboard;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import proyectoconstruccion.Controllers.colaboracion.FXMLContenedorColaboracionesController;
import proyectoconstruccion.Controllers.oferta.FXMLContenedorOfertasController;
import proyectoconstruccion.Utils.Sesion;
import proyectoconstruccion.Utils.Utils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import proyectoconstruccion.Controllers.colaboracion.FXMLRegistrarColaboracionSinOfertaController;
import proyectoconstruccion.Controllers.oferta.FXMLRegistrarOfertaExternaController;
import proyectoconstruccion.modelo.DAO.PeriodoDAO;
import proyectoconstruccion.modelo.POJO.Periodo;

public class FXMLDashboardController implements Initializable {

    private ObservableList<Periodo> periodos;

    public Tab tabSocilicitudes;
    public Tab tabNumeralia;
    public TabPane tabPane;
    public BorderPane bdPaneColaboraciones;
    public BorderPane bdPaneOfertasColab;
    public BorderPane bdPaneSolicitudesConstancias;
    public BorderPane bdPaneNumerialia;
    public Label lbNumeroColaboracion;
    public TableView tvEstudiantes;
    public Label lbFechas;
    public Label lbPeriodoSC;
    public Label lbExperienciaEducativa;
    public Label lbIdioma;
    public Label lbNombreColaborador;
    public Label lbNombreProfesor;
    public Label lbPeriodo;
    public Label lbTituloMateria;
    public ComboBox cbSeleccionPeriodo;
    @FXML
    private Button btnEliminarrFiltros;
    @FXML
    private TableView<?> tvNumeralia;
    @FXML
    private TableColumn<?, ?> colNumProfesores;
    @FXML
    private TableColumn<?, ?> colNumAlumnos;


    public void initialize(URL url, ResourceBundle rb) {

        if (Sesion.getInstancia().getRol() == "profesor") {
            //quita Solicitud de constancias
            tabPane.getTabs().remove(3);
            //quita Solicitud de
            tabPane.getTabs().remove(2);
        } else {
            //cargarPeriodosNumeralia();
        }
        iniciarComponentes();


    }

    public void iniciarComponentes() {
        tabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            if (verificarTabSeleccionado(tabNumeralia)) {
                cargarPeriodos();
            }
        });
    }

    private boolean verificarTabSeleccionado(Tab tab) {
        return tabPane.getSelectionModel().getSelectedItem().equals(tab);
    }

    @FXML
    public void btnVerColabs(ActionEvent actionEvent) {
        try {

            FXMLLoader loader = Utils.obtenerLoader("Views/colaboracion/FXMLContenedorColaboraciones.fxml");
            AnchorPane contenedorColaboraciones = loader.load();
            FXMLContenedorColaboracionesController controller = loader.getController();

            controller.InicializarComponentes();

            bdPaneColaboraciones.setCenter(contenedorColaboraciones);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void btnVerOfertas(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = Utils.obtenerLoader("Views/oferta/FXMLContenedorOfertas.fxml");
            AnchorPane contenedorColaboraciones = loader.load();
            FXMLContenedorOfertasController controller = loader.getController();

            controller.InicializarComponentes();
            bdPaneOfertasColab.setCenter(contenedorColaboraciones);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void btnClicRegistrarOfertaExterna(ActionEvent event) {
        try {
            Stage escenario = new Stage();
            FXMLLoader loader = Utils.obtenerLoader("Views/oferta/FXMLRegistrarOfertaExterna.fxml");
            Parent root = loader.load();
            FXMLRegistrarOfertaExternaController controlador = loader.getController();
            controlador.inicializarValores();
            Scene escena = new Scene(root);
            escenario.setScene(escena);
            escenario.setTitle("Registrar oferta de colaboración externa");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void cargarPeriodosNumeralia() {
        //TODO
    }

    @FXML
    private void btnClicVerNumeralia(ActionEvent event) {

    }

    @FXML
    public void btnDescargarListaEstudiantes(ActionEvent actionEvent) {
    }

    @FXML
    public void btnSyllabus(ActionEvent actionEvent) {
    }

    @FXML
    public void btnEvidencia(ActionEvent actionEvent) {
    }

    @FXML
    public void btnAprobarConstancia(ActionEvent actionEvent) {
    }

    @FXML
    public void btnRegresar(ActionEvent actionEvent) {
    }

    @FXML
    private void btnClicRegistrarColaboracion(ActionEvent event) {
        try {
            Stage escenario = new Stage();
            FXMLLoader loader = Utils.obtenerLoader("Views/colaboracion/FXMLRegistrarColaboracionSinOferta.fxml");
            Parent root = loader.load();
            FXMLRegistrarColaboracionSinOfertaController controlador = loader.getController();
            //controlador.inicializarValores();
            Scene escena = new Scene(root);
            escenario.setScene(escena);
            escenario.setTitle("Registrar colaboración sin oferta");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void cargarPeriodos(){
        periodos = FXCollections.observableArrayList();
        periodos.addAll(PeriodoDAO.getPeriodos());
        cbSeleccionPeriodo.setItems(periodos);

        cbSeleccionPeriodo.setCellFactory(new Callback<ListView<Periodo>, ListCell<Periodo>>() {
            @Override
            public ListCell<Periodo> call(ListView<Periodo> param) {
                return new ListCell<Periodo>() {
                    @Override
                    protected void updateItem(Periodo item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(item.getDescripcion());
                        }
                    }
                };
            }
        });

        cbSeleccionPeriodo.setButtonCell(new ListCell<Periodo>() {
            @Override
            protected void updateItem(Periodo item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getDescripcion());
                }
            }
        });
    }


}