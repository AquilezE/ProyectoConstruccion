package proyectoconstruccion.Controllers.colaboracion;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLRegistrarColaboracionSinOfertaController implements Initializable {

    @FXML
    private Label lbNombreProfesorUV;
    @FXML
    private Label lbCorreoElectronicoProfesorUV;
    @FXML
    private Label lbNumPersonal;
    @FXML
    private ComboBox<?> cbIdioma;
    @FXML
    private ComboBox<?> cbProfesorExterno;
    @FXML
    private Label lbNombreProfesorExterno;
    @FXML
    private Label lbPais;
    @FXML
    private Label lbCorreoElectronicoProfeExterno;
    @FXML
    private TextField tfTitulo;
    @FXML
    private ComboBox<?> cbExperienciaEducativa;
    @FXML
    private ComboBox<?> cbPeriodo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnClicRegresar(ActionEvent event) {
    }

    @FXML
    private void btnClicRegistrarColaboracion(ActionEvent event) {
    }

    @FXML
    private void btnClicRegistrarProfesorExterno(ActionEvent event) {
    }
    
}
