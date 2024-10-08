package proyectoconstruccion.Controllers.panelPrincipal;

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
import proyectoconstruccion.Controllers.RefreshserUtils;
import proyectoconstruccion.Controllers.colaboracion.FXMLContenedorColaboracionesController;
import proyectoconstruccion.Controllers.oferta.FXMLContenedorOfertasController;
import proyectoconstruccion.Utils.*;

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

public class FXMLPanelPrincipalController implements Initializable {


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

    public ComboBox<String> cbFiltroOfertas;


    public Button btnRegistrarOfertaExterna;
    public Button btnRegistrarOfertaUV;
    public Button btnRegistrarNuevaColab;

    public HBox hbSolicitudesHeader;
    public HBox hbFiltrosColabs;

    public Tab tabSocilicitudes;

    //Numeralia
    public Tab tabNumeralia;
    //Numeralia

    public TabPane tabPane;

    public BorderPane bdPaneColaboraciones;
    public BorderPane bdPaneOfertasColab;
    public BorderPane bdPaneNumerialia;
    public BorderPane bdPaneSolicitudes;
    public BorderPane bdPaneUsuario;


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

    //Ver que pedo con este y el de arriba
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

        //Numeralia
        tabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            if (verificarTabSeleccionado(tabNumeralia)) {
                cargarPeriodos();
                configurarTablaCampus();
                configurarTablaAreaAcademica();
                obtenerDatosTablaCampus();
                obtenerDatosTablaAreaAcademica();
            }

        });
        //Numeralia

        cargarOpcionesDeFiltroOferta();
        cargarTabUsuario();
    }

    public void cargarTabUsuario(){
        try {
            FXMLLoader loader = Utils.obtenerLoader("Views/login/FXMLPanelUsuario.fxml");
            AnchorPane root = loader.load();
            bdPaneUsuario.setCenter(root);
        }catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public DatosFiltroColaboracion getDatosFiltro() {
        DatosFiltroColaboracion filterData = new DatosFiltroColaboracion();
        filterData.setFechaCierre(dpFechaCierre.getValue());
        filterData.setFechaInicio(dpFechaInicio.getValue());
        filterData.setEstado(cbEstado.getValue());
        filterData.setTituloColab(tfTituloColab.getText().toLowerCase());
        filterData.setPeriodo(cbPeriodo.getValue());
        filterData.setExperienciaEducativa(cbExperienciaEducativa.getValue());

        return filterData;
    }

    public DatosFiltroOferta getDatosFiltroOferta(){
        DatosFiltroOferta filterData;
        filterData= new DatosFiltroOferta();
        if (cbFiltroOfertas.getValue()!=null){
            System.out.println(cbFiltroOfertas.getValue());
            filterData.setOpcionFiltro(cbFiltroOfertas.getValue());
        }{
            filterData.setOpcionFiltro("");
        }

        return filterData;
    }


    public void cargarEstados() {
        this.estados = FXCollections.observableArrayList("Pendiente", "Activa", "Concluida","Clausurada","Cancelada");
        cbEstado.setItems(this.estados);
    }

    //Numeralia
    private boolean verificarTabSeleccionado(Tab tab) {
        return tabPane.getSelectionModel().getSelectedItem().equals(tab);
    }
    //Numeralia

    @FXML
    public void btnVerColabs(ActionEvent actionEvent) {
        cargarColaboraciones();
    }
    
    public void cargarColaboraciones(){
        try {
            DatosFiltroColaboracion filterData = getDatosFiltro();

            FXMLLoader loader = Utils.obtenerLoader("Views/colaboracion/FXMLContenedorColaboraciones.fxml");
            AnchorPane contenedorColaboraciones = loader.load();
            FXMLContenedorColaboracionesController controller = loader.getController();
            RefreshserUtils.setColaboracionesController(controller);
            RefreshserUtils.setColaboracionesBusquedaCache(filterData);
            controller.InicializarComponentes(filterData);

            bdPaneColaboraciones.setCenter(contenedorColaboraciones);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void btnVerOfertas(ActionEvent actionEvent) {
        cargarOfertas();
    }

    public void cargarOfertas(){
        DatosFiltroOferta filterData = getDatosFiltroOferta();

        try {
            FXMLLoader loader = Utils.obtenerLoader("Views/oferta/FXMLContenedorOfertas.fxml");
            AnchorPane contenedorColaboraciones = loader.load();
            FXMLContenedorOfertasController controller = loader.getController();
            filterData.setOpcionFiltro(cbFiltroOfertas.getValue());

            RefreshserUtils.setOfertasBusquedaCache(filterData);
            RefreshserUtils.setOfertasController(controller);
            controller.InicializarComponentes(filterData);
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
            root.getStylesheets().add(proyectoconstruccion.AppStartup.class.getResource("Views/style.css").toExternalForm());

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
    private void btnClicRegistrarColaboracion(ActionEvent event) {
        try {
            Stage escenario = new Stage();
            FXMLLoader loader = Utils.obtenerLoader("Views/colaboracion/FXMLRegistrarColaboracionSinOferta.fxml");
            Parent root = loader.load();
            FXMLRegistrarColaboracionSinOfertaController controlador = loader.getController();
            root.getStylesheets().add(proyectoconstruccion.AppStartup.class.getResource("Views/style.css").toExternalForm());

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

        //Se usa para numeralia
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


        //Se usa para los filtros
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
            root.getStylesheets().add(proyectoconstruccion.AppStartup.class.getResource("Views/style.css").toExternalForm());
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
                    setText(item.getNombreExperienciaEducativa());
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

    public void cargarOpcionesDeFiltroOferta(){
        cbFiltroOfertas.setItems(FXCollections.observableArrayList("Periodo", "Idioma","Tipo"));
    }


    //NUMERALIA
    @FXML
    private void btnClicVerNumeralia(ActionEvent event) {
        verNumeralia();
    }
    public void verNumeralia(){
        if(cbSeleccionPeriodo.getSelectionModel().getSelectedItem() != null){
            Periodo seleccion = (Periodo) cbSeleccionPeriodo.getSelectionModel().getSelectedItem();
            obtenerDatosTablaCampusPorPeriodo(seleccion.getDescripcion());
            obtenerDatosTablaAreaAcademicaPorPeriodo(seleccion.getDescripcion());
        }else{
            Utils.mostrarAlertaSimple("Error", "Debe seleccionar un periodo", Alert.AlertType.ERROR);
        }
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
}