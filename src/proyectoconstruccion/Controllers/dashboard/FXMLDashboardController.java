package proyectoconstruccion.Controllers.dashboard;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import proyectoconstruccion.Controllers.colaboracion.FXMLContenedorColaboracionesController;
import proyectoconstruccion.Controllers.oferta.FXMLContenedorOfertasController;
import proyectoconstruccion.Utils.Constantes;
import proyectoconstruccion.Utils.FilterData;
import proyectoconstruccion.Utils.Sesion;
import proyectoconstruccion.Utils.Utils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import proyectoconstruccion.Controllers.colaboracion.FXMLRegistrarColaboracionSinOfertaController;
import proyectoconstruccion.Controllers.oferta.FXMLRegistrarOfertaExternaController;
import proyectoconstruccion.Controllers.oferta.FXMLRegistrarOfertaUVController;
import proyectoconstruccion.modelo.DAO.ExperienciaEducativaDAO;
import proyectoconstruccion.modelo.DAO.NumeraliaDAO;
import proyectoconstruccion.modelo.DAO.PeriodoDAO;
import proyectoconstruccion.modelo.POJO.NumeraliaAreaAcademica;
import proyectoconstruccion.modelo.POJO.NumeraliaCampus;
import proyectoconstruccion.modelo.POJO.Periodo;
import proyectoconstruccion.modelo.POJO.academia.ExperienciaEducativa;

public class FXMLDashboardController implements Initializable {

    private ObservableList<ExperienciaEducativa> experienciasEducativas;
    private ObservableList<Periodo> periodos;
    private ObservableList<String> estados;

    public HBox hBoxFiltros;

    public DatePicker dpFechaCierre;
    public DatePicker dpFechaInicio;

    public TextField tfTituloColab;

    public ComboBox<String> cbEstado;
    public ComboBox<Periodo> cbPeriodo;
    public ComboBox<ExperienciaEducativa> cbExperienciaEducativa;



    public Button btnRegistrarOfertaExterna;
    public Button btnRegistrarOfertaUV;
    public Button btnRegistrarNuevaColab;

    public HBox hbSolicitudesHeader;
    public HBox hbFiltros;

    public Tab tabSocilicitudes;
    public Tab tabNumeralia;
    public TabPane tabPane;
    public BorderPane bdPaneColaboraciones;
    public BorderPane bdPaneOfertasColab;
    public BorderPane bdPaneNumerialia;
    public BorderPane bdPaneSolicitudes;

    public Label lbPeriodo;

    @FXML
    private TableView <NumeraliaCampus>tvNumeraliaCampus;
    @FXML
    private TableColumn <NumeraliaCampus, String> colCampus;
    @FXML
    private TableColumn <NumeraliaCampus, String> colNumAlumnosCampus;
    @FXML
    private TableColumn <NumeraliaCampus, String> colNumProfesoresCampus;
    @FXML
    private TableView <NumeraliaAreaAcademica> tvNumeraliaAreaAcademica;
    @FXML
    private TableColumn <NumeraliaAreaAcademica,String> colAreaAcademica;
    @FXML
    private TableColumn <NumeraliaAreaAcademica,String> colNumAlumnosAreaAcademica;
    @FXML
    private TableColumn <NumeraliaAreaAcademica,String> colNumProfesoresAreaAcademica;
    @FXML
    public ComboBox cbSeleccionPeriodo;

    public void initialize(URL url, ResourceBundle rb) {

        if (Sesion.getInstancia().getRol().equals(Constantes.PROFESOR)) {
            tabPane.getTabs().remove(3);
            tabPane.getTabs().remove(2);
            btnRegistrarOfertaExterna.setVisible(false);
            btnRegistrarOfertaUV.setVisible(true);
            btnRegistrarNuevaColab.setVisible(true);
        } else {
            btnRegistrarOfertaExterna.setVisible(true);
            btnRegistrarOfertaUV.setVisible(false);
        }



    }

    public void iniciarComponentes() {

                cargarExperienciaEducativa();
                cargarEstados();
                cargarPeriodos();


        tabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            if (verificarTabSeleccionado(tabNumeralia)) {
                cargarPeriodos();
                configurarTablaCampus();
                configurarTablaAreaAcademica();
                obtenerDatosTablaCampus();
                obtenerDatosTablaAreaAcademica();
            }

        });


    }

    public FilterData getDatosFiltro() {
        FilterData filterData = new FilterData();
        filterData.setFechaCierre(dpFechaCierre.getValue());
        filterData.setFechaInicio(dpFechaInicio.getValue());
        filterData.setEstado(cbEstado.getValue());
        filterData.setTituloColab(tfTituloColab.getText().toLowerCase());
        filterData.setPeriodo(cbPeriodo.getValue());
        filterData.setExperienciaEducativa(cbExperienciaEducativa.getValue());

        if (cbPeriodo.getValue() == null){
            System.out.println("No se arreglo");
        }else{
            System.out.println(cbPeriodo.getValue().getDescripcion());
        }
        if (cbExperienciaEducativa.getValue() == null){
            System.out.println("No se arreglo");
        }else{
            System.out.println(cbExperienciaEducativa.getValue().getExperienciaEducativaId());
        }
        return filterData;
    }

    public void cargarEstados() {
        this.estados = FXCollections.observableArrayList("Pendiente", "Activa", "Concluida");
        cbEstado.setItems(this.estados);
    }

    private boolean verificarTabSeleccionado(Tab tab) {
        return tabPane.getSelectionModel().getSelectedItem().equals(tab);
    }
    public void configurarTablaCampus(){
        colCampus.setCellValueFactory(new PropertyValueFactory<>("campus"));
        colNumProfesoresCampus.setCellValueFactory(new PropertyValueFactory<>("numeroProfesores"));
        colNumAlumnosCampus.setCellValueFactory(new PropertyValueFactory<>("numeroEstudiantes"));
    }
    public void obtenerDatosTablaCampus(){
        ArrayList<NumeraliaCampus> listaNumeralia = NumeraliaDAO.obtenerNumeraliaCampus();
        ObservableList<NumeraliaCampus> datos = FXCollections.observableArrayList(listaNumeralia);
        tvNumeraliaCampus.setItems(datos);

    }
    public void configurarTablaAreaAcademica(){
        colAreaAcademica.setCellValueFactory(new PropertyValueFactory<>("AreaAcademica"));
        colNumProfesoresAreaAcademica.setCellValueFactory(new PropertyValueFactory<>("numeroProfesores"));
        colNumAlumnosAreaAcademica.setCellValueFactory(new PropertyValueFactory<>("numeroEstudiantes"));
    }
    public void obtenerDatosTablaAreaAcademica(){
        ArrayList<NumeraliaAreaAcademica> listaNumeralia = NumeraliaDAO.obtenerNumeraliaAreaAcademica();
        ObservableList<NumeraliaAreaAcademica> datos = FXCollections.observableArrayList(listaNumeralia);
        tvNumeraliaAreaAcademica.setItems(datos);

    }


    @FXML
    public void btnVerColabs(ActionEvent actionEvent) {
        cargarColaboraciones();
    }
    
    public void cargarColaboraciones(){
        try {
            FilterData filterData = getDatosFiltro();

            System.out.println("Datos del filtro:");
            System.out.println("Fecha Cierre: " + filterData.getFechaCierre());
            System.out.println("Fecha Inicio: " + filterData.getFechaInicio());
            System.out.println("Título: " + filterData.getTituloColab());
            System.out.println("Periodo: " + (filterData.getPeriodo() != null ? filterData.getPeriodo().getDescripcion() : "null"));
            System.out.println("Estado: " + filterData.getEstado());
            System.out.println("Experiencia Educativa: " + (filterData.getExperienciaEducativa() != null ? filterData.getExperienciaEducativa().getNombreExperienciaEducativa() : "null"));

            FXMLLoader loader = Utils.obtenerLoader("Views/colaboracion/FXMLContenedorColaboraciones.fxml");
            AnchorPane contenedorColaboraciones = loader.load();
            FXMLContenedorColaboracionesController controller = loader.getController();
            controller.InicializarComponentes(filterData);

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

    @FXML
    private void btnClicVerNumeralia(ActionEvent event) {
        Periodo seleccion = (Periodo) cbSeleccionPeriodo.getSelectionModel().getSelectedItem();
        obtenerDatosTablaCampusPorPeriodo(seleccion.getDescripcion());
        obtenerDatosTablaAreaAcademicaPorPeriodo(seleccion.getDescripcion());
    }

    public void obtenerDatosTablaCampusPorPeriodo(String periodo){
        ArrayList<NumeraliaCampus> listaNumeralia = NumeraliaDAO.obtenerNumeraliaCampusPorPeriodo(periodo);
        ObservableList<NumeraliaCampus> datos = FXCollections.observableArrayList(listaNumeralia);
        tvNumeraliaCampus.setItems(datos);
    }

    public void obtenerDatosTablaAreaAcademicaPorPeriodo(String periodo){
        ArrayList<NumeraliaAreaAcademica> listaNumeralia = NumeraliaDAO.obtenerNumeraliaAreaAcademicaPorPeriodo(periodo);
        ObservableList<NumeraliaAreaAcademica> datos = FXCollections.observableArrayList(listaNumeralia);
        tvNumeraliaAreaAcademica.setItems(datos);
    }


    @FXML
    private void btnClicRegistrarColaboracion(ActionEvent event) {
        try {
            Stage escenario = new Stage();
            FXMLLoader loader = Utils.obtenerLoader("Views/colaboracion/FXMLRegistrarColaboracionSinOferta.fxml");
            Parent root = loader.load();
            FXMLRegistrarColaboracionSinOfertaController controlador = loader.getController();
            controlador.inicializarValores();
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

        cbPeriodo.setItems(periodos);

        cbPeriodo.setCellFactory(new Callback<ListView<Periodo>, ListCell<Periodo>>() {
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

        cbPeriodo.setButtonCell(new ListCell<Periodo>() {
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

    @FXML
    private void btnClicRegistrarOfertaUV(ActionEvent event) {
        try {
            Stage escenario = new Stage();
            FXMLLoader loader = Utils.obtenerLoader("Views/oferta/FXMLRegistrarOfertaUV.fxml");
            Parent root = loader.load();
            FXMLRegistrarOfertaUVController controlador = loader.getController();
            controlador.inicializarValores();
            Scene escena = new Scene(root);
            escenario.setScene(escena);
            escenario.setTitle("Registrar oferta de colaboración UV");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public void btnVerColabsSolicitudes(ActionEvent actionEvent) {
        try {

            FXMLLoader loader = Utils.obtenerLoader("Views/colaboracion/FXMLContenedorColaboraciones.fxml");
            AnchorPane contenedorColaboraciones = loader.load();
            FXMLContenedorColaboracionesController controller = loader.getController();

            controller.InicializarComponentesSolicitudes();

            bdPaneSolicitudes.setCenter(contenedorColaboraciones);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void cargarExperienciaEducativa() {
        experienciasEducativas = FXCollections.observableArrayList();
        experienciasEducativas.addAll(ExperienciaEducativaDAO.obtenerTodasExperienciasEducativas());
        cbExperienciaEducativa.setItems(experienciasEducativas);

        cbExperienciaEducativa.setCellFactory(new Callback<ListView<ExperienciaEducativa>, ListCell<ExperienciaEducativa>>() {
            @Override
            public ListCell<ExperienciaEducativa> call(ListView<ExperienciaEducativa> param) {
                return new ListCell<ExperienciaEducativa>() {
                    @Override
                    protected void updateItem(ExperienciaEducativa item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(item.getNombreExperienciaEducativa());
                        }
                    }
                };
            }
        });

        cbExperienciaEducativa.setButtonCell(new ListCell<ExperienciaEducativa>() {
            @Override
            protected void updateItem(ExperienciaEducativa item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getDescripcion());
                }
            }
        });
    }

    public void btnBorrarFiltros(ActionEvent actionEvent) {

        tfTituloColab.clear();
        dpFechaInicio.setValue(null);
        dpFechaCierre.setValue(null);
        cbExperienciaEducativa.getSelectionModel().clearSelection();
        cbPeriodo.getSelectionModel().clearSelection();
        cbEstado.getSelectionModel().clearSelection();
        cargarColaboraciones();
    }
}



