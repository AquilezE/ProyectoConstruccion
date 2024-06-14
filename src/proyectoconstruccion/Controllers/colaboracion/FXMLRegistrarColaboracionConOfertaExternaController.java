package proyectoconstruccion.Controllers.colaboracion;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import proyectoconstruccion.Utils.Sesion;
import proyectoconstruccion.Utils.Utils;
import proyectoconstruccion.modelo.DAO.*;
import proyectoconstruccion.modelo.POJO.Idioma;
import proyectoconstruccion.modelo.POJO.Periodo;
import proyectoconstruccion.modelo.POJO.academia.ExperienciaEducativa;
import proyectoconstruccion.modelo.POJO.colaboracion.Colaboracion;
import proyectoconstruccion.modelo.POJO.evidencia.Evidencia;
import proyectoconstruccion.modelo.POJO.ofertacolaboracion.OfertaColaboracionExterna;
import proyectoconstruccion.modelo.POJO.ofertacolaboracion.OfertaColaboracionUV;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorExterno;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorUV;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FXMLRegistrarColaboracionConOfertaExternaController implements Initializable {


    private ObservableList<Periodo> periodos;
    private ObservableList<ProfesorExterno> profesores;
    private ObservableList<ExperienciaEducativa> experiencias;
    private ObservableList<Idioma> idiomas;


    OfertaColaboracionExterna ofertaColaboracionExterna;

    public DatePicker dpFechaInicio;

    public DatePicker dpFechaCierre;
    @FXML
    private Label lbNombreProfesorExterno;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbCorreoElectronicoProfeExterno;
    @FXML
    private Label lbNombreProfesorUV;
    @FXML
    private Label lbNumPersonal;
    @FXML
    private Label lbCorreoElectronicoProfeUV;
    @FXML
    private ComboBox<ExperienciaEducativa> cbExperienciaEducativa;
    @FXML
    public ComboBox<String> cbTipoColab;
    @FXML
    private ComboBox<Idioma> cbIdioma;
    @FXML
    private ComboBox<Periodo> cbPeriodo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }


        public void inicializarValores(OfertaColaboracionExterna ofertaColaboracionExterna) {


        ObservableList<String> tipoColabOptions = FXCollections.observableArrayList();
        tipoColabOptions.add("Clase espejo");
        tipoColabOptions.add("Taller COIL-VIC");
        cbTipoColab.setItems(tipoColabOptions);

        this.ofertaColaboracionExterna=ofertaColaboracionExterna;
        lbTitulo.setText(ofertaColaboracionExterna.getTitulo());

        lbNombreProfesorExterno.setText(ofertaColaboracionExterna.getProfesor().getNombre() + " " + ofertaColaboracionExterna.getProfesor().getApellidoPaterno() + " " + ofertaColaboracionExterna.getProfesor().getApellidoMaterno());
        lbCorreoElectronicoProfeExterno.setText(ofertaColaboracionExterna.getProfesor().getCorreoElectronico());


        ProfesorUV profesorUV = Sesion.getInstancia().getProfesorUsuario();
        lbCorreoElectronicoProfeUV.setText(profesorUV.getCorreoElectronico());
        lbNumPersonal.setText(profesorUV.getNumeroPersonal());
        lbNombreProfesorUV.setText(profesorUV.getNombre()+" "+profesorUV.getApellidoPaterno()+" "+profesorUV.getApellidoMaterno());


        cargarPeriodos();
        cargarExperienciasEducativas();
        cargarIdiomas();
    }


    @FXML
    private void btnClicRegresar(ActionEvent event) {
        Utils.cerrarVentana(event);
    }

    @FXML
    private void btnClicRegistrarColaboracion(ActionEvent event) {
        if(validarCampos()){
            Colaboracion colaboracion = new Colaboracion();
        
            String duracion = ofertaColaboracionExterna.getDuracion();

            String titulo = ofertaColaboracionExterna.getTitulo();

            String periodo = cbPeriodo.getSelectionModel().getSelectedItem().getDescripcion();

            Idioma idioma = cbIdioma.getSelectionModel().getSelectedItem();

            LocalDate fechaInicio = dpFechaInicio.getValue();

            LocalDate fechaCierre = dpFechaCierre.getValue();

            String tipo = cbTipoColab.getSelectionModel().getSelectedItem();

            String estado = "Pendiente";

            Integer numeroEstudiantes = null;
            ProfesorUV profesorUv = Sesion.getInstancia().getProfesorUsuario();
            ProfesorExterno profesorExterno = (ProfesorExterno) ofertaColaboracionExterna.getProfesor();
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

            if(ColaboracionDAO.registrarColaboracion(colaboracion)){
                OfertaColaboracionDAO.cambiarEstadoOfertaColaboracion(ofertaColaboracionExterna.getOfertaColaboracionId(),1);
                Utils.cerrarVentana(event);

            }
        } else {
            Utils.mostrarAlertaSimple("Error", "Hay campos que no han sido completados", Alert.AlertType.ERROR);
        }
    }



    public boolean validarCampos(){
        if (cbPeriodo.getSelectionModel().getSelectedItem() == null ||
                cbIdioma.getSelectionModel().getSelectedItem() == null ||
                cbExperienciaEducativa.getSelectionModel().getSelectedItem() == null ||
                cbTipoColab.getSelectionModel().getSelectedItem() == null ||
                dpFechaInicio.getValue() == null || dpFechaCierre.getValue() == null) {
            return false;
        }
            return true;
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

        cbExperienciaEducativa.setItems(experiencias);
    }


}
