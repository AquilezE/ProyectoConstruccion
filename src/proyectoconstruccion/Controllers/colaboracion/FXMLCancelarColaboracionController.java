package proyectoconstruccion.Controllers.colaboracion;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import proyectoconstruccion.Utils.Utils;
import proyectoconstruccion.modelo.DAO.ColaboracionDAO;
import proyectoconstruccion.modelo.POJO.colaboracion.Colaboracion;

public class FXMLCancelarColaboracionController implements Initializable {

    @FXML
    private TextField tfMotivo;

    Integer idColaboracion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void inicializarValores(Colaboracion colaboracion){
            this.idColaboracion = colaboracion.getColaboracionId();
    }

    @FXML
    private void btnClicRegresar(ActionEvent event) {
        Stage escenarioCancelarColaboracion = (Stage) tfMotivo.getScene().getWindow();
        escenarioCancelarColaboracion.close();
    }

    @FXML
    private void btnClicCancelarColaboracion(ActionEvent event) {

        String texto = tfMotivo.getText();
        if (texto != null && !texto.trim().isEmpty()) {
            if (texto.length() > 254) {
                // Manejo para cuando el texto supera los 254 caracteres
                Utils.mostrarAlertaSimple("Error", "El texto no puede superar los 254 caracteres", Alert.AlertType.ERROR);
            } else {
                // Manejo para cuando el texto es válido
                ColaboracionDAO.aniadirAnotaciones(this.idColaboracion, texto);
                ColaboracionDAO.actualizarEstadoColaboracion(this.idColaboracion,"Cancelada");
                Utils.mostrarAlertaSimple("","La colaboración ha sido cancelada",Alert.AlertType.CONFIRMATION);
                Utils.cerrarVentana(event);
            }
        } else {
            // Manejo para cuando el texto es nulo o vacío
            Utils.mostrarAlertaSimple("Error", "El campo no puede estar vacío", Alert.AlertType.ERROR);
        }

    }
    
}
