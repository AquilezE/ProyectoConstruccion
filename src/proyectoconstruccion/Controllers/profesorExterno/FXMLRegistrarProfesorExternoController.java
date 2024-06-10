/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyectoconstruccion.Controllers.profesorExterno;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import proyectoconstruccion.Controllers.oferta.FXMLRegistrarOfertaExternaController;
import proyectoconstruccion.Utils.Utils;
import proyectoconstruccion.modelo.DAO.IdiomaDAO;
import proyectoconstruccion.modelo.DAO.ProfesorDAO;
import proyectoconstruccion.modelo.DAO.UniversidadDAO;
import proyectoconstruccion.modelo.POJO.Idioma;
import proyectoconstruccion.modelo.POJO.Universidad;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorExterno;

/**
 * FXML Controller class
 *
 * @author unaay
 */
public class FXMLRegistrarProfesorExternoController implements Initializable {

    private ObservableList<Idioma> idiomas;
    private ObservableList<Universidad> universidades;

    @FXML
    private ComboBox<Idioma> cbIdioma;
    @FXML
    private TextField tfNombre;
    @FXML
    public TextField tfApellidoPaterno;
    @FXML
    public TextField tfApellidoMaterno;
    @FXML
    private TextField tfCorreo;
    @FXML
    private ComboBox<Universidad> cbUniversidad;
    @FXML
    private TextField tfUniversidad;
    @FXML
    private TextField tfTelefono;

    private FXMLRegistrarOfertaExternaController ofertaExternaController;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void inicializarValores(FXMLRegistrarOfertaExternaController fxmlRegistrarOfertaExternaController) {
        this.ofertaExternaController = fxmlRegistrarOfertaExternaController;
        cargarIdiomas();
        cargarUniversidades();
    }


    public void cargarIdiomas() {
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



    public void cargarUniversidades() {
        universidades = FXCollections.observableArrayList();
        universidades.addAll(UniversidadDAO.getAllUniversidades());

        cbUniversidad.setItems(universidades);
        cbUniversidad.setConverter(new StringConverter<Universidad>() {
            @Override
            public String toString(Universidad object) {
                return object.getNombre();
            }

            @Override
            public Universidad fromString(String string) {
                return universidades.stream().filter(o -> o.getNombre().equals(string)).findFirst().orElse(null);
            }
        });
    }

    @FXML
    private void btnClicRegresar(ActionEvent event) {
        Stage escenarioRegistrarProfesorExterno = (Stage) tfNombre.getScene().getWindow();
        escenarioRegistrarProfesorExterno.close();
    }

    @FXML
    private void btnClicRegistrar(ActionEvent event) {

        if (validarCampos()) {
            // Assuming ProfesorExterno has a constructor with these arguments
            ProfesorExterno profesorExterno = new ProfesorExterno(
                    tfCorreo.getText(),
                    tfNombre.getText(),
                    tfApellidoMaterno.getText(),
                    tfApellidoPaterno.getText(),
                    tfTelefono.getText(),  // Assumes phone number is to be stored as Integer
                    cbIdioma.getValue().getIdiomaID(),
                    cbUniversidad.getValue().getUniversidadId()
                    );

            // Assuming ProfesorExternoDAO has a method to save ProfesorExterno
            // You will need to catch or throw any exceptions that might occur here
            boolean isRegistered = ProfesorDAO.addProfesorExterno(profesorExterno);

            if (isRegistered) {
                System.out.println("Profesor Externo registrado");
                ofertaExternaController.cargarProfesExternos();
                Utils.cerrarVentana(event);
            } else {
                System.out.println("Profesor Externo no registrado");
            }
        } else {
            System.out.println("Campos vacios");
        }
    }



    private boolean validarCampos () {
        System.out.println(cbIdioma.getValue().getIdiomaID());
        if (cbUniversidad.getValue() == null ||
                cbIdioma.getValue() == null ||
                tfNombre.getText() == null || tfNombre.getText().trim().isEmpty() ||
                tfApellidoPaterno.getText() == null || tfApellidoPaterno.getText().trim().isEmpty() ||
                tfCorreo.getText() == null || tfCorreo.getText().trim().isEmpty() ||
                tfTelefono.getText() == null || tfTelefono.getText().trim().isEmpty()) {
            return false;
        }
            return true;
        }
}
