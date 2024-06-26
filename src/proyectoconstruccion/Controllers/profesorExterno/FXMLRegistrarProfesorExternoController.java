package proyectoconstruccion.Controllers.profesorExterno;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import proyectoconstruccion.Controllers.colaboracion.FXMLRegistrarColaboracionConOfertaUVController;
import proyectoconstruccion.Controllers.oferta.FXMLRegistrarOfertaExternaController;
import proyectoconstruccion.Utils.Utils;
import proyectoconstruccion.modelo.DAO.IdiomaDAO;
import proyectoconstruccion.modelo.DAO.ProfesorDAO;
import proyectoconstruccion.modelo.DAO.UniversidadDAO;
import proyectoconstruccion.modelo.POJO.Idioma;
import proyectoconstruccion.modelo.POJO.Universidad;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorExterno;

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
    private FXMLRegistrarColaboracionConOfertaUVController colaboracionConOfertaUVController;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void inicializarValores(FXMLRegistrarOfertaExternaController fxmlRegistrarOfertaExternaController) {
        this.ofertaExternaController = fxmlRegistrarOfertaExternaController;
        cargarIdiomas();
        cargarUniversidades();
    }

    public void inicializarValores(FXMLRegistrarColaboracionConOfertaUVController colaboracionConOfertaUVController) {
        this.colaboracionConOfertaUVController = colaboracionConOfertaUVController;
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
        Utils.cerrarVentana(event);
    }

    @FXML
    private void btnClicRegistrar(ActionEvent event) {

        if (validarCampos()) {
            ProfesorExterno profesorExterno = new ProfesorExterno(
                    tfCorreo.getText(),
                    tfNombre.getText(),
                    tfApellidoMaterno.getText(),
                    tfApellidoPaterno.getText(),
                    tfTelefono.getText(),
                    cbIdioma.getValue().getIdiomaID(),
                    cbUniversidad.getValue().getUniversidadId()
                    );

            boolean isRegistered = ProfesorDAO.addProfesorExterno(profesorExterno);

            if (isRegistered) {
                System.out.println("Profesor Externo registrado");
                ofertaExternaController.cargarProfesExternos();
            } else {
                System.out.println("Profesor Externo no registrado");
            }
            Utils.cerrarVentana(event);
        } else {
            Utils.mostrarAlertaSimple("Error", "No pueden quedar campos vacíos", Alert.AlertType.ERROR);
        }
    }



    private boolean validarCampos () {
        if (!validarFormatoCorreo(tfCorreo.getText())) {
            Utils.mostrarAlertaSimple("Error","El correo electrónico no es válido.",Alert.AlertType.ERROR );
        }
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
    
    private boolean validarFormatoCorreo(String correo) {
            if (!correo.contains("@")) {
                return false;
            }
            int ultimoArrobaIndex = correo.lastIndexOf("@");
            String localPart = correo.substring(0, ultimoArrobaIndex);
            String domainPart = correo.substring(ultimoArrobaIndex + 1);

            if (localPart.isEmpty()) {
                return false;
            }

            if (!domainPart.contains(".")) {
                return false;
            }

            return true;
    }
}
