package proyectoconstruccion.Controllers.colaboracion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FXMLRegistrarColaboracionConOfertaExternaController {

    @FXML
    private Label lbNombreProfesorExterno;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbCorreoElectronicoProfeExterno;
    @FXML
    private Label lbPeriodo;
    @FXML
    private Label lbExperienciaEducativa;
    @FXML
    private ComboBox<?> cbProfesorUV;
    @FXML
    private Label lbNombreProfesorUV;
    @FXML
    private Label lbNumPersonal;
    @FXML
    private Label lbCorreoElectronicoProfeUV;
    @FXML
    private Label lbIdioma;

    @FXML
    private void btnClicRegresar(ActionEvent event) {
        Stage escenarioRegistrarColaboracionConOfertaExterna = (Stage) lbTitulo.getScene().getWindow();
        escenarioRegistrarColaboracionConOfertaExterna.close();
    }

    @FXML
    private void btnClicRegistrarColaboracion(ActionEvent event) {
        System.out.println("Colaboracion registrada exitosamente");
    }
    
}
