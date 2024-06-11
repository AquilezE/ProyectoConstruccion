/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyectoconstruccion.Controllers.colaboracion;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import proyectoconstruccion.Controllers.evidencias.FXMLSubirEvidenciasController;
import proyectoconstruccion.Controllers.listaEstudiantes.FXMLSubirListaEstudiantesController;
import proyectoconstruccion.Utils.Constantes;
import proyectoconstruccion.Utils.Sesion;
import proyectoconstruccion.Utils.Utils;
import proyectoconstruccion.modelo.DAO.EvidenciaDAO;
import proyectoconstruccion.modelo.POJO.colaboracion.Colaboracion;
import proyectoconstruccion.modelo.POJO.evidencia.Evidencia;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorExterno;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorUV;

/**
 * FXML Controller class
 *
 * @author unaay
 */
public class FXMLDetallesColaboracionController implements Initializable {

    public Button btnSyllabus;
    public Button btnEvidencias;
    public Button btnLista;


    private Colaboracion colaboracion;
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

    public void inicializarValores(Colaboracion colaboracion){

        if (Sesion.getInstancia().getRol().equals(Constantes.ADMIN)){
            btnSyllabus.setVisible(false);
            btnEvidencias.setVisible(false);
            btnLista.setVisible(false);
        }

        this.colaboracion = colaboracion;
        System.out.println(colaboracion.getPeriodo());
        lbIdColaboracion.setText(String.valueOf(colaboracion.getColaboracionId()));
        lbTitulo.setText(colaboracion.getTitulo());


        ProfesorUV profesorUV = colaboracion.getProfesorUv();
        ProfesorExterno profesorExterno = colaboracion.getProfesorExterno();

        if (profesorUV != null) {
            String nombreProfesor = profesorUV.getNombre() + " " + profesorUV.getApellidoMaterno() + " " + profesorUV.getApellidoPaterno();
            lbNombreProfesorUV.setText(nombreProfesor);
        }

        if (profesorExterno != null) {
            String nombreProfesorExterno = profesorExterno.getNombre() + " " + profesorExterno.getApellidoMaterno() + " " + profesorExterno.getApellidoPaterno();
            lbNombreProfesorExterno.setText(nombreProfesorExterno);
        }
        lbIdioma.setText(colaboracion.getIdioma().getIdioma());
        lbPeriodo.setText(colaboracion.getPeriodo());


        lbFechas.setText(colaboracion.getFechaInicio().toString() + " - " + colaboracion.getFechaCierre().toString());

        lbExperienciaEdcativa.setText(colaboracion.getExperienciaEducativa().getNombreExperienciaEducativa());

        lbPeriodo.setText(colaboracion.getPeriodo());

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
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Syllabus");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File selectedFile = fileChooser.showOpenDialog(lbTitulo.getScene().getWindow());
        if (selectedFile != null) {

            if(EvidenciaDAO.guardarSyllabus(this.colaboracion.getEvidencia().getEvidenciaId(), selectedFile)){
                System.out.println("Se Logro Syllabus");
            }
        }
    }

    @FXML
    public void btnClicEvidencias(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar el zip con tus Evidencias");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Zip Files", "*.zip"));
        File selectedFile = fileChooser.showOpenDialog(lbTitulo.getScene().getWindow());
        if (selectedFile != null) {
            if (EvidenciaDAO.guardarEvidenciaZip(this.colaboracion.getEvidencia().getEvidenciaId(), selectedFile)){
                System.out.println("Se Logro Evidencias");
            }
        }
    }

    @FXML
    private void btnClicListaEstudiantes(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar el CSV con la lista de estudiantes");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
                if (EvidenciaDAO.guardarListaEstudiantes(this.colaboracion.getEvidencia().getEvidenciaId(), selectedFile)){
                    System.out.println("Se Logro Lista");
                }
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyectoconstruccion/Views/colaboracion/FXMLDetallesColaboracionPendiente.fxml"));
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
