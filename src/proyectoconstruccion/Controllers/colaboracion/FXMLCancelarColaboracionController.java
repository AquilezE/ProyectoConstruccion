package proyectoconstruccion.Controllers.colaboracion;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import proyectoconstruccion.Controllers.RefreshserUtils;
import proyectoconstruccion.Utils.Utils;
import proyectoconstruccion.modelo.DAO.ColaboracionDAO;
import proyectoconstruccion.modelo.POJO.colaboracion.Colaboracion;

public class FXMLCancelarColaboracionController implements Initializable {

    @FXML
    private TextField tfMotivo;

    Colaboracion colaboracion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void inicializarValores(Colaboracion colaboracion){
            this.colaboracion = colaboracion;
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
                Utils.mostrarAlertaSimple("Error", "El texto no puede superar los 254 caracteres", Alert.AlertType.ERROR);
            } else {
                ColaboracionDAO.aniadirAnotaciones(this.colaboracion.getColaboracionId(), texto);
                ColaboracionDAO.actualizarEstadoColaboracion(this.colaboracion.getColaboracionId(), "Cancelada");
                this.colaboracion.setEstado("Cancelada");

                Utils.mostrarAlertaSimple("","La colaboración ha sido cancelada",Alert.AlertType.CONFIRMATION);

                //ESTO SIRVE PARA RECARGAR LA LISTA SI CANCELAS UNA COLABORACION
                RefreshserUtils.getColaboracionesController().InicializarComponentes(RefreshserUtils.getColaboracionesBusquedaCache());
                RefreshserUtils.getColaboracionesController().InicializarComponentesSolicitudes();

                Utils.cerrarVentana(event);
            }
        } else {
            Utils.mostrarAlertaSimple("Error", "El campo no puede estar vacío", Alert.AlertType.ERROR);
        }

    }
    
}
