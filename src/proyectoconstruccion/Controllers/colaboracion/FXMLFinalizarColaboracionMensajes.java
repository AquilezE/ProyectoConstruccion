package proyectoconstruccion.Controllers.colaboracion;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLFinalizarColaboracionMensajes implements Initializable {
    public Label lbMensaje;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void btnFinalizar(ActionEvent actionEvent) {
        System.out.println("este boton hace el cambio de mensaje al de constancias");
    }
}
