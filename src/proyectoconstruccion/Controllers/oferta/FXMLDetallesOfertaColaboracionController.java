package proyectoconstruccion.Controllers.oferta;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import proyectoconstruccion.Controllers.colaboracion.FXMLRegistrarColaboracionConOfertaExternaController;
import proyectoconstruccion.Utils.Constantes;
import proyectoconstruccion.Utils.Sesion;
import proyectoconstruccion.Utils.Utils;
import proyectoconstruccion.modelo.DAO.IdiomaDAO;
import proyectoconstruccion.modelo.DAO.OfertaColaboracionDAO;
import proyectoconstruccion.modelo.POJO.Idioma;
import proyectoconstruccion.modelo.POJO.ofertacolaboracion.OfertaColaboracion;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import proyectoconstruccion.Controllers.colaboracion.FXMLRegistrarColaboracionConOfertaUVController;
import proyectoconstruccion.modelo.POJO.ofertacolaboracion.OfertaColaboracionExterna;
import proyectoconstruccion.modelo.POJO.ofertacolaboracion.OfertaColaboracionUV;

public class FXMLDetallesOfertaColaboracionController implements Initializable {
    public Label lbDuracion;
    public Label lbIdioma;
    public Label lbPeriodo;
    public Label lbTitulo;
    public Label lbNombreProfesor;
    public Label lbCorreoProfesor;

    public Button btnEditar;
    public Button btnCrearColab;

    OfertaColaboracion ofertaColaboracion;
    Idioma idioma;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void btnEditar(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = Utils.obtenerLoader("Views/oferta/FXMLEdicionOfertaColaboracion.fxml");
            Parent root = loader.load();
            FXMLEdicionOfertaColaboracionController controller = loader.getController();
            controller.InicializarDatos(this,ofertaColaboracion);

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


        if (ofertaColaboracion instanceof OfertaColaboracionUV){
            try {
                System.out.println("ES COLABORACION UV");
                FXMLLoader loader = Utils.obtenerLoader("Views/colaboracion/FXMLRegistrarColaboracionConOfertaUV.fxml");
                Parent root = loader.load();
                FXMLRegistrarColaboracionConOfertaUVController controller = loader.getController();
                controller.inicializarValores((OfertaColaboracionUV) ofertaColaboracion);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (ofertaColaboracion instanceof OfertaColaboracionExterna){
            try {
                System.out.println("ES COLABORACION EXTERNA");
                FXMLLoader loader = Utils.obtenerLoader("Views/colaboracion/FXMLRegistrarColaboracionConOfertaExterna.fxml");
                Parent root = loader.load();
                FXMLRegistrarColaboracionConOfertaExternaController controller = loader.getController();
                // Puedes pasar datos a la nueva ventana si es necesario
                controller.inicializarValores((OfertaColaboracionExterna) ofertaColaboracion);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void inicializarDetalles(OfertaColaboracion ofertaColaboracion) {

            if (ofertaColaboracion instanceof OfertaColaboracionUV){
                if(Sesion.getInstancia().getRol().equals(Constantes.PROFESOR)){
                    btnEditar.setVisible(true);
                }
            }else if (ofertaColaboracion instanceof OfertaColaboracionExterna){
                if(Sesion.getInstancia().getRol().equals(Constantes.ADMIN)){
                    btnEditar.setVisible(true);
                }
            }

            if (Sesion.getInstancia().getRol().equals(Constantes.PROFESOR)){
                btnCrearColab.setVisible(true);
            }


            this.ofertaColaboracion = ofertaColaboracion;

            lbDuracion.setText(ofertaColaboracion.getDuracion());
            lbPeriodo.setText(ofertaColaboracion.getPeriodo());
            lbTitulo.setText(ofertaColaboracion.getTitulo());

            this.idioma=IdiomaDAO.obtenerIdioma(ofertaColaboracion.getIdiomaID());

            lbIdioma.setText(idioma.getIdioma());
            lbNombreProfesor.setText(ofertaColaboracion.getProfesor().getNombre()+" "+ofertaColaboracion.getProfesor().getApellidoPaterno()+" "+ofertaColaboracion.getProfesor().getApellidoMaterno());
            lbCorreoProfesor.setText(ofertaColaboracion.getProfesor().getCorreoElectronico());

    }

    public void refreshData() {
            inicializarDetalles((OfertaColaboracion) OfertaColaboracionDAO.getOfertaColaboracionById(ofertaColaboracion.getOfertaColaboracionId()).get("oferta"));
    }


}
