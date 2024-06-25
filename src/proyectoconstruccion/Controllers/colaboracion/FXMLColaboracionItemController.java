package proyectoconstruccion.Controllers.colaboracion;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import proyectoconstruccion.Utils.Utils;
import proyectoconstruccion.modelo.DAO.IdiomaDAO;
import proyectoconstruccion.modelo.POJO.colaboracion.Colaboracion;
import proyectoconstruccion.modelo.POJO.Idioma;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorExterno;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorUV;

public class FXMLColaboracionItemController {

    public Label lbTitulo;
    public Label lbNombreProfesorExt;
    public Label lbNombreProfesorUV;
    public Label lbFechaInicio;
    public Label lbFechaCierre;
    public Label lbIdiomaColaboracion;
    public Label lbExperienciaEducativa;
    public Label lbEstado;

    ProfesorUV profesorUV;
    ProfesorExterno profesorExterno;

    Colaboracion colaboracion;

    // Este método se utiliza para inicializar los componentes de la interfaz y asignarle los valores
    // de la colaboración que se le pasa como parámetro.
    public void InicializarComponentes(Colaboracion colaboracion) {
        this.colaboracion = colaboracion;
        lbTitulo.setText(colaboracion.getTitulo());
        lbFechaInicio.setText(colaboracion.getFechaInicio().toString());
        lbFechaCierre.setText(colaboracion.getFechaCierre().toString());
        lbIdiomaColaboracion.setText(colaboracion.getIdioma().getIdioma());
        lbExperienciaEducativa.setText(colaboracion.getExperienciaEducativa().toString());
        this.profesorExterno = colaboracion.getProfesorExterno();
        this.profesorUV = colaboracion.getProfesorUv();
        this.lbEstado.setText(colaboracion.getEstado());

        lbExperienciaEducativa.setText(colaboracion.getExperienciaEducativa().getNombreExperienciaEducativa());
        lbNombreProfesorExt.setText(profesorExterno.getNombre() + " " + profesorExterno.getApellidoPaterno());
        lbNombreProfesorUV.setText(profesorUV.getNombre() + " " + profesorUV.getApellidoPaterno());
    }

    // Dependiendo del estado de la colaboración, se muestra una ventana de detalles diferente.
    public void btnDetalles(ActionEvent actionEvent) {
        if (this.colaboracion.getEstado().equals("Pendiente")) {
            mostrarDetallesPendiente();
        } else if (this.colaboracion.getEstado().equals("Activa")) {
            mostrarDetallesActiva();
        } else if (this.colaboracion.getEstado().equals("Concluida")) {
            mostrarDetallesConcluida();
        } else if (this.colaboracion.getEstado().equals("Cancelada")) {
            Utils.mostrarAlertaSimple("Error", "Esta colaboracion esta cancelada!\n" + this.colaboracion.getAnotaciones(), Alert.AlertType.ERROR);
        } else if (this.colaboracion.getEstado().equals("Clausurada")) {
            Utils.mostrarAlertaSimple("Error", "Esta colaboracion esta clausurada!", Alert.AlertType.ERROR);
        }
    }

    public void mostrarDetallesPendiente() {
        try {
            Stage escenario = new Stage();
            FXMLLoader loader = Utils.obtenerLoader("Views/colaboracion/FXMLDetallesColaboracionPendiente.fxml");
            Parent root = loader.load();
            FXMLDetallesColaboracionPendienteController controlador = loader.getController();
            root.getStylesheets().add(proyectoconstruccion.AppStartup.class.getResource("Views/style.css").toExternalForm());
            controlador.inicializarValores(this.colaboracion);
            Scene escena = new Scene(root);
            escenario.setScene(escena);
            escenario.setTitle("Detalles colaboración");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void mostrarDetallesActiva() {
        try {
            Stage escenario = new Stage();
            FXMLLoader loader = Utils.obtenerLoader("Views/colaboracion/FXMLDetallesColaboracion.fxml");
            Parent root = loader.load();
            FXMLDetallesColaboracionController controlador = loader.getController();
            root.getStylesheets().add(proyectoconstruccion.AppStartup.class.getResource("Views/style.css").toExternalForm());
            controlador.inicializarValores(this.colaboracion);
            Scene escena = new Scene(root);
            escenario.setScene(escena);
            escenario.setTitle("Detalles colaboración");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void mostrarDetallesConcluida() {
        try {
            Stage escenario = new Stage();
            FXMLLoader loader = Utils.obtenerLoader("Views/colaboracion/FXMLDetallesColaboracionTerminado.fxml");
            Parent root = loader.load();
            FXMLDetallesColaboracionTerminadoController controlador = loader.getController();
            root.getStylesheets().add(proyectoconstruccion.AppStartup.class.getResource("Views/style.css").toExternalForm());
            controlador.inicializarValores(this.colaboracion);
            Scene escena = new Scene(root);
            escenario.setScene(escena);
            escenario.setTitle("Detalles colaboración");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
