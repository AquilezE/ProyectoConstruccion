package proyectoconstruccion.Controllers.colaboracion;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import proyectoconstruccion.Controllers.RefreshserUtils;
import proyectoconstruccion.Controllers.profesorExterno.FXMLRegistrarProfesorExternoController;
import proyectoconstruccion.Utils.Sesion;
import proyectoconstruccion.Utils.Utils;
import proyectoconstruccion.modelo.DAO.*;
import proyectoconstruccion.modelo.POJO.Idioma;
import proyectoconstruccion.modelo.POJO.Periodo;
import proyectoconstruccion.modelo.POJO.Universidad;
import proyectoconstruccion.modelo.POJO.academia.ExperienciaEducativa;
import proyectoconstruccion.modelo.POJO.colaboracion.Colaboracion;
import proyectoconstruccion.modelo.POJO.evidencia.Evidencia;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorExterno;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorUV;

public class FXMLRegistrarColaboracionSinOfertaController implements Initializable {

    private ObservableList<Periodo> periodos;
    private ObservableList<ProfesorExterno> profesores;
    private ObservableList<ExperienciaEducativa> experiencias;
    private ObservableList<Idioma> idiomas;


    @FXML
    private Label lbNombreProfesorUV;
    @FXML
    private Label lbCorreoElectronicoProfesorUV;
    @FXML
    private Label lbNumPersonal;
    @FXML
    private ComboBox<Idioma> cbIdioma;
    @FXML
    private ComboBox<ProfesorExterno> cbProfesorExterno;
    @FXML
    private Label lbNombreProfesorExterno;
    @FXML
    private Label lbPais;
    @FXML
    private Label lbUniversidad;
    @FXML
    private Label lbCorreoElectronicoProfeExterno;
    @FXML
    private TextField tfTitulo;
    @FXML
    public TextField tfDuracion;
    @FXML
    private ComboBox<ExperienciaEducativa> cbExperienciaEducativa;
    @FXML
    private ComboBox<Periodo> cbPeriodo;
    @FXML
    private ComboBox<String> cbTipoColaboracion;
    @FXML
    private DatePicker dpFechaInicio;
    @FXML
    private DatePicker dpFechaCierre;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void inicializarValores(){
        ObservableList<String> tipoColabOptions = FXCollections.observableArrayList();
        tipoColabOptions.add("Clase espejo");
        tipoColabOptions.add("Taller COIL-VIC");
        cbTipoColaboracion.setItems(tipoColabOptions);


        ProfesorUV profesorUV = Sesion.getInstancia().getProfesorUsuario();
        lbNombreProfesorUV.setText(profesorUV.getNombre()+" "+profesorUV.getApellidoPaterno()+" "+profesorUV.getApellidoMaterno());
        lbNumPersonal.setText(profesorUV.getNumeroPersonal());
        lbCorreoElectronicoProfesorUV.setText(profesorUV.getCorreoElectronico());



        cargarPeriodos();
        cargarExperienciasEducativas();
        cargarProfesExternos();
        cargarIdiomas();

    }


    @FXML
    private void btnClicRegresar(ActionEvent event) {
        Utils.cerrarVentana(event);
    }

    @FXML
    private void btnClicRegistrarColaboracion(ActionEvent event) {
        if(areFieldsValid()){
            Colaboracion colaboracion = new Colaboracion();

            String duracion = tfDuracion.getText();

            String periodo = cbPeriodo.getSelectionModel().getSelectedItem().getDescripcion();

            String titulo = tfTitulo.getText();

            Idioma idioma = cbIdioma.getSelectionModel().getSelectedItem();

            LocalDate fechaInicio = dpFechaInicio.getValue();

            LocalDate fechaCierre = dpFechaCierre.getValue();

            String tipo = cbTipoColaboracion.getSelectionModel().getSelectedItem();

            String estado = "Pendiente";

            Integer numeroEstudiantes = null;
            ProfesorUV profesorUv = Sesion.getInstancia().getProfesorUsuario();
            ProfesorExterno profesorExterno = cbProfesorExterno.getSelectionModel().getSelectedItem();
            ExperienciaEducativa experienciaEducativa = cbExperienciaEducativa.getSelectionModel().getSelectedItem();
            Evidencia evidencia = EvidenciaDAO.crearRegistroEvidencia();

            colaboracion.setIdioma(idioma);
            colaboracion.setFechaInicio(fechaInicio);
            colaboracion.setFechaCierre(fechaCierre);
            colaboracion.setTipo(tipo);
            colaboracion.setEstado(estado);
            colaboracion.setNumeroEstudiantes(numeroEstudiantes);
            colaboracion.setDuracion(duracion);
            colaboracion.setPeriodo(periodo);
            colaboracion.setTitulo(titulo);
            colaboracion.setProfesorExterno(profesorExterno);
            colaboracion.setProfesorUv(profesorUv);
            colaboracion.setExperienciaEducativa(experienciaEducativa);
            colaboracion.setEvidencia(evidencia);
            colaboracion.setIdioma(idioma);

            if (ColaboracionDAO.registrarColaboracion(colaboracion)) {
                RefreshserUtils.getOfertasController().InicializarComponentes(RefreshserUtils.getOfertasBusquedaCache());
                Utils.cerrarVentana(event);
            } else {
                System.out.println("NOOOO");
            }
        }else{
            Utils.mostrarAlertaSimple("Alerta","Hay algun campo vacio", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    private void btnClicRegistrarProfesorExterno(ActionEvent event) {
        try {
            Stage escenario = new Stage();
            FXMLLoader loader = Utils.obtenerLoader("Views/profesorExterno/FXMLRegistrarProfesorExterno.fxml");
            Parent root = loader.load();
            FXMLRegistrarProfesorExternoController controlador = loader.getController();
            root.getStylesheets().add(proyectoconstruccion.AppStartup.class.getResource("Views/style.css").toExternalForm());
            Scene escena = new Scene(root);
            escenario.setScene(escena);
            escenario.setTitle("Registrar profesor externo");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
            cargarProfesExternos();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    private void cargarPeriodos(){
        periodos = FXCollections.observableArrayList();
        periodos.addAll(PeriodoDAO.getPeriodos());
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

    public void cargarProfesExternos(){
        profesores = FXCollections.observableArrayList();
        profesores.addAll(ProfesorDAO.obtenerTodosProfesoresExternos());
        cbProfesorExterno.setItems(profesores);

        cbProfesorExterno.setCellFactory(new Callback<ListView<ProfesorExterno>, ListCell<ProfesorExterno>>() {
            @Override
            public ListCell<ProfesorExterno> call(ListView<ProfesorExterno> l) {
                return new ListCell<ProfesorExterno>() {
                    @Override
                    protected void updateItem(ProfesorExterno item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.getNombre());
                        }
                    }
                };
            }
        });

        cbProfesorExterno.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProfesorExterno>() {
            @Override
            public void changed(ObservableValue<? extends ProfesorExterno> observable, ProfesorExterno oldValue, ProfesorExterno newValue) {
                if (newValue != null) {
                    lbNombreProfesorExterno.setText(newValue.getNombre()+" "+newValue.getApellidoPaterno()+" "+ newValue.getApellidoMaterno());
                    lbCorreoElectronicoProfeExterno.setText(newValue.getCorreoElectronico());

                    Universidad universidadSeleccionada = UniversidadDAO.getUniversidadById(newValue.getUniversidadID());

                    lbPais.setText(universidadSeleccionada.getPais());
                    lbUniversidad.setText(universidadSeleccionada.getNombre());

                } else {
                    lbNombreProfesorExterno.setText("");
                    lbCorreoElectronicoProfeExterno.setText("");
                    lbPais.setText("");
                }
            }
        });
        cbProfesorExterno.setConverter(new StringConverter<ProfesorExterno>() {
            @Override
            public String toString(ProfesorExterno profesor) {
                return profesor != null ? profesor.getNombre() : "";
            }

            @Override
            public ProfesorExterno fromString(String id) {
                return cbProfesorExterno.getItems().stream().filter(profesor ->
                        profesor.getNombre().equals(id)).findFirst().orElse(null);
            }
        });
    }

    private void cargarIdiomas() {
        idiomas = FXCollections.observableArrayList();
        idiomas.addAll(IdiomaDAO.obtenerIdiomas());

        cbIdioma.setItems(idiomas);
        cbIdioma.setConverter(new StringConverter<Idioma>() {
            @Override
            public String toString(Idioma object) {
                return object.getIdioma();
            }

            @Override
            public Idioma fromString(String string) {
                return idiomas.stream().filter(o -> o.getIdioma().equals(string)).findFirst().orElse(null);
            }
        });
    }

    private void cargarExperienciasEducativas(){
        experiencias = FXCollections.observableArrayList();
        experiencias.addAll(ExperienciaEducativaDAO.obtenerTodasExperienciasEducativas());

        // Configurar el renderizador de celda
        cbExperienciaEducativa.setCellFactory(new Callback<ListView<ExperienciaEducativa>, ListCell<ExperienciaEducativa>>() {
            @Override
            public ListCell<ExperienciaEducativa> call(ListView<ExperienciaEducativa> l) {
                return new ListCell<ExperienciaEducativa>() {
                    @Override
                    protected void updateItem(ExperienciaEducativa item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
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

        cbExperienciaEducativa.setConverter(new StringConverter<ExperienciaEducativa>() {
            @Override
            public String toString(ExperienciaEducativa object) {
                return object != null ? object.getNombreExperienciaEducativa() : "";
            }

            @Override
            public ExperienciaEducativa fromString(String string) {
                return experiencias.stream().filter(o -> o.getNombreExperienciaEducativa().equals(string)).findFirst().orElse(null);
            }
        });

        cbExperienciaEducativa.setItems(experiencias);
    }

    private boolean areFieldsValid() {

        if (tfDuracion.getText().trim().isEmpty() || cbPeriodo.getValue() == null ||
                tfTitulo.getText().trim().isEmpty() || cbIdioma.getValue() == null ||
                dpFechaInicio.getValue() == null || dpFechaCierre.getValue() == null ||
                cbTipoColaboracion.getValue() == null ||
                cbProfesorExterno.getValue() == null ||
                cbExperienciaEducativa.getValue() == null) {
            return false;
        }

        return true;
    }

}
