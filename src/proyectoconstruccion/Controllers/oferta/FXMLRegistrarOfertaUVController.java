/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyectoconstruccion.Controllers.oferta;

import java.net.URL;
import java.util.ResourceBundle;

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
import proyectoconstruccion.modelo.DAO.IdiomaDAO;
import proyectoconstruccion.modelo.DAO.OfertaColaboracionDAO;
import proyectoconstruccion.modelo.DAO.PeriodoDAO;
import proyectoconstruccion.modelo.DAO.ExperienciaEducativaDAO;
import proyectoconstruccion.modelo.POJO.Idioma;
import proyectoconstruccion.modelo.POJO.Periodo;
import proyectoconstruccion.modelo.POJO.academia.ExperienciaEducativa;
import proyectoconstruccion.modelo.POJO.ofertacolaboracion.OfertaColaboracionUV;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorExterno;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorUV;

/**
 * FXML Controller class
 *
 * @author unaay
 */
public class FXMLRegistrarOfertaUVController implements Initializable {

    Integer idProfesor;
    Integer tipoOferta;

    private ObservableList<Periodo> periodos;
    private ObservableList<ExperienciaEducativa> experienciasEducativas;
    private ObservableList<Idioma> idiomas;


    @FXML
    private TextField tfTitulo;
    @FXML
    private TextField tfDuracion;
    @FXML
    private ComboBox<Periodo> cbPeriodo;
    @FXML
    private ComboBox<ExperienciaEducativa> cbExperienciaEducativa;
    @FXML
    private ComboBox<Idioma> cbIdioma;
    @FXML
    private Label lbNombre;
    @FXML
    private Label lbCorreo;
    @FXML
    private Label lbNumPersonal;
    @FXML
    private Label lbCelular;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void inicializarValores(){

        if(Sesion.getInstancia().getProfesorUsuario() == null){
            System.out.println("profesor es null");
        }
        ProfesorUV profesorUV = Sesion.getInstancia().getProfesorUsuario();
        idProfesor = profesorUV.getProfesorId();
        lbNombre.setText(profesorUV.getNombre());
        lbCorreo.setText(profesorUV.getCorreoElectronico());
        lbNumPersonal.setText(profesorUV.getNumeroPersonal());
        lbCelular.setText(profesorUV.getTelefono());
        tipoOferta=0;

        cargarPeriodos();
        cargarExperienciaEducativa();
        cargarIdiomas();

    }

    @FXML
    private void btnClicRegresar(ActionEvent event) {
        Stage escenarioRegistrarOfertaUV = (Stage) tfTitulo.getScene().getWindow();
        escenarioRegistrarOfertaUV.close();
    }

    @FXML
    private void btnClicRegistrarOferta(ActionEvent event) {

        if(validateFields()){
            Periodo selectedPeriodo = cbPeriodo.getSelectionModel().getSelectedItem();
            ProfesorUV profesorUV = Sesion.getInstancia().getProfesorUsuario();
            String title = tfTitulo.getText();
            String duration = tfDuracion.getText();
            Integer idIdioma = cbIdioma.getValue().getIdiomaID();

            OfertaColaboracionUV oferta = new OfertaColaboracionUV();
            oferta.setPeriodo(selectedPeriodo.getDescripcion());
            oferta.setProfesor(profesorUV);
            oferta.setIdiomaID(idIdioma);
            oferta.setTitulo(title);
            oferta.setDuracion(duration);

            OfertaColaboracionDAO.guardarOfertaUV(oferta);
            Utils.cerrarVentana(event);
        }else{
            System.out.println("Campos invalidos");
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


    private boolean validateFields () {
        boolean valid = true;

        // Check for selected values in combo boxes
        if (cbIdioma.getSelectionModel().getSelectedItem() == null) {
            valid = false;
        }
        if (cbPeriodo.getSelectionModel().getSelectedItem() == null) {
            valid = false;
        }

        // Check for empty fields in text fields, disallow spaces and newlines
        if (tfTitulo.getText().trim().isEmpty() || tfTitulo.getText().trim().isEmpty()) {
            valid = false;
        }
        if (tfDuracion.getText().trim().isEmpty() || tfDuracion.getText().trim().isEmpty()) {
            valid = false;
        }

        return valid;
    }
}
