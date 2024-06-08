package proyectoconstruccion.Controllers.colaboracion;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLCancelarColaboracionController implements Initializable {

    @FXML
    private TextField tfMotivo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnClicRegresar(ActionEvent event) {
        Stage escenarioCancelarColaboracion = (Stage) tfMotivo.getScene().getWindow();
        escenarioCancelarColaboracion.close();
    }

    @FXML
    private void btnClicCancelarColaboracion(ActionEvent event) {
    }
    
}