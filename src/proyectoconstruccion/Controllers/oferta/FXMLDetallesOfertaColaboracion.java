package proyectoconstruccion.Controllers.oferta;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import proyectoconstruccion.Controllers.oferta.EditarOFerta.FXMLEdicionOfertaColaboracionController;
import proyectoconstruccion.Utils.Utils;
import proyectoconstruccion.modelo.POJO.ofertacolaboracion.OfertaColaboracion;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import proyectoconstruccion.Controllers.colaboracion.FXMLRegistrarColaboracionConOfertaUVController;

public class FXMLDetallesOfertaColaboracion implements Initializable {
    public Label lbDuracion;
    public Label lbIdioma;
    public Label lbPeriodo;
    public Label lbTitulo;
    public Label lbNombreProfesor;
    public Label lbCorreoProfesor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void btnEditar(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = Utils.obtenerLoader("Views/oferta/EditarOferta/FXMLEdicionOfertaColaboracion.fxml");
            Parent root = loader.load();
            FXMLEdicionOfertaColaboracionController controller = loader.getController();
            // Puedes pasar datos a la nueva ventana si es necesario

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void btnCrearColaboracion(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = Utils.obtenerLoader("Views/colaboracion/FXMLRegistrarColaboracionConOfertaUV.fxml");
            Parent root = loader.load();
            FXMLRegistrarColaboracionConOfertaUVController controller = loader.getController();
            // Puedes pasar datos a la nueva ventana si es necesario
            //controller.inicializarValores();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void inicializarDetalles(OfertaColaboracion ofertaColaboracion) {
    }
}
