package proyectoconstruccion.Controllers.colaboracion;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import proyectoconstruccion.Utils.Constantes;
import proyectoconstruccion.Utils.Sesion;
import proyectoconstruccion.Utils.Utils;
import proyectoconstruccion.modelo.DAO.ColaboracionDAO;
import proyectoconstruccion.modelo.POJO.colaboracion.Colaboracion;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorExterno;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorUV;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class FXMLDetallesColaboracionPendienteController implements Initializable {
    public Button btnDenegar;
    public Button btnAutorizar;

    Colaboracion colaboracion;

    public Label lbNumeroColaboracion;
    public Label lbTitulo;
    public Label lbNombreProfesor;
    public Label lbNombreColaborador;
    public Label lbIdioma;
    public Label lbPeriodo;
    public Label lbFechas;
    public Label lbExperienciaEducativa;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void inicializarValores(Colaboracion colaboracion) {

        if (Sesion.getInstancia().getRol().equals(Constantes.PROFESOR)){
            btnDenegar.setVisible(false);
            btnAutorizar.setVisible(false);
        }

        this.colaboracion = colaboracion;
        System.out.println(colaboracion.getPeriodo());
        lbNumeroColaboracion.setText(String.valueOf(colaboracion.getColaboracionId()));
        lbTitulo.setText(colaboracion.getTitulo());


        ProfesorUV profesorUV = colaboracion.getProfesorUv();
        ProfesorExterno profesorExterno = colaboracion.getProfesorExterno();

        if (profesorUV != null) {
            String nombreProfesor = profesorUV.getNombre() + " " + profesorUV.getApellidoMaterno() + " " + profesorUV.getApellidoPaterno();
            lbNombreProfesor.setText(nombreProfesor);
        }

        if (profesorExterno != null) {
            String nombreProfesorExterno = profesorExterno.getNombre() + " " + profesorExterno.getApellidoMaterno() + " " + profesorExterno.getApellidoPaterno();
            lbNombreColaborador.setText(nombreProfesorExterno);
        }
        lbIdioma.setText(colaboracion.getIdioma().getIdioma());
        lbPeriodo.setText(colaboracion.getPeriodo());


        lbFechas.setText(colaboracion.getFechaInicio().toString() + " - " + colaboracion.getFechaCierre().toString());

        lbExperienciaEducativa.setText(colaboracion.getExperienciaEducativa().getNombreExperienciaEducativa());

        lbPeriodo.setText(colaboracion.getPeriodo());



    }

    public void btnAutorizar(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Deseas Autorizar esta Colaboracion?");

        ButtonType buttonYes = new ButtonType("Sí");
        ButtonType buttonNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonYes) {
            ColaboracionDAO.actualizarEstadoColaboracion(this.colaboracion.getColaboracionId(),"Activa");
            this.colaboracion.setEstado("Activa");
            Utils.cerrarVentana(actionEvent);
        } else if (result.isPresent() && result.get() == buttonNo) {
            System.out.println("No se cambio de estado");
        } else {
            System.out.println("El usuario cerró el cuadro de diálogo.");
        }

    }

    public void btnDenegar(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText(null);
        alert.setContentText("¿Deseas Denegar esta Colaboracion?");

        ButtonType buttonYes = new ButtonType("Sí");
        ButtonType buttonNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonYes) {
            ColaboracionDAO.borrarColaboracion(this.colaboracion.getColaboracionId());
            Utils.cerrarVentana(actionEvent);
        } else if (result.isPresent() && result.get() == buttonNo) {
            System.out.println("No se borro la colaboracion");
        } else {
            System.out.println("El usuario cerró el cuadro de diálogo.");
        }
    }

}
