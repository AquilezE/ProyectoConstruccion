package proyectoconstruccion.Controllers.colaboracion;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import proyectoconstruccion.modelo.POJO.colaboracion.Colaboracion;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLAutorizarColaboraciónController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void inicializarValores(Colaboracion colaboracion){

    }

    public void btnSíAutorizar(ActionEvent actionEvent) {
        System.out.println("Boton autorizado");
    }

    public void btnNoAutorizar(ActionEvent actionEvent) {
        System.out.println("Boton no autorizado");
    }

}
