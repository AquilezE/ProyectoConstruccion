/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyectoconstruccion.Controllers.colaboracion;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import proyectoconstruccion.Controllers.evidencias.FXMLSubirEvidenciasController;
import proyectoconstruccion.Controllers.listaEstudiantes.FXMLSubirListaEstudiantesController;
import proyectoconstruccion.Utils.Utils;

/**
 * FXML Controller class
 *
 * @author unaay
 */
public class FXMLDetallesColaboracionController implements Initializable {

    @FXML
    private Label lbIdColaboracion;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbNombreProfesorUV;
    @FXML
    private Label lbNombreProfesorExterno;
    @FXML
    private Label lbIdioma;
    @FXML
    private Label lbPeriodo;
    @FXML
    private Label lbFechas;
    @FXML
    private Label lbExperienciaEdcativa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnClicCancelarColaboracion(ActionEvent event) {
        try {
            Stage escenario = new Stage();
            FXMLLoader loader = Utils.obtenerLoader("Views/colaboracion/FXMLCancelarColaboracion.fxml");
            Parent root = loader.load();
            FXMLCancelarColaboracionController controlador = loader.getController();
            //TODO pasarle como valor una colaboracion
            //controlador.inicializarValores();
            Scene escena = new Scene(root);
            escenario.setScene(escena);
            escenario.setTitle("Cancelar colaboración");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnClicSyllabus(ActionEvent event) {
    }

    @FXML
    public void btnClicEvidencias(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = Utils.obtenerLoader("Views/evidencias/FXMLSubirEvidencias.fxml");
            Parent root = loader.load();
            FXMLSubirEvidenciasController controller = loader.getController();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setTitle("Subir Evidencias");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void btnClicListaEstudiantes(ActionEvent event) {
        try {
            FXMLLoader loader = Utils.obtenerLoader("ruta/al/FXMLSubirListaEstudiantes.fxml"); // Reemplaza "ruta/al/FXMLSubirListaEstudiantes.fxml" con la ruta real a tu FXML
            Parent root = loader.load();
            FXMLSubirListaEstudiantesController controller = loader.getController();
            // Puedes pasar datos a la nueva ventana si es necesario

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setTitle("Subir Lista de Estudiantes");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void btnClicRegresar(ActionEvent event) {
        Stage escenarioDetallesColaboracion = (Stage) lbTitulo.getScene().getWindow();
        escenarioDetallesColaboracion.close();
    }

    public void btnAutorizar(ActionEvent actionEvent) {
        try {
            Stage escenario = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyectoconstruccion/Views/colaboracion/FXMLRechazarAceptarColaboracion.fxml"));
            Parent root = loader.load();
            Scene escena = new Scene(root);
            escenario.setScene(escena);
            escenario.setTitle("Ventana de Autorización");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void btnFinalizar(ActionEvent actionEvent) {
        System.out.println("Este boton cambia el estado a finalizada");
    }
}
