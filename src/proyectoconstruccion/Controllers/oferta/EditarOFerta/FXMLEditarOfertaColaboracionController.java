package proyectoconstruccion.Controllers.oferta.EditarOFerta;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLEditarOfertaColaboracionController implements Initializable {


    public Label idTitulo;
    public Label lbIdioma;
    public Label lbPeriodo;
    public Label lbDuracion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void btnCancelar(ActionEvent actionEvent) {
        System.out.println("Cancelar");
    }

    public void btnEditar(ActionEvent actionEvent) {
        System.out.println("Editar");
    }
}


