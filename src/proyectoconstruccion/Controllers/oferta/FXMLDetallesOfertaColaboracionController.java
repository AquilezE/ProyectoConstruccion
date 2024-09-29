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
                root.getStylesheets().add(proyectoconstruccion.AppStartup.class.getResource("Views/style.css").toExternalForm());

                controller.InicializarDatos(this,ofertaColaboracion);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    public void btnCrearColaboracion(ActionEvent actionEvent) {

        //Carga diferentes paneles dependiendo si la oferta es UV o es Externa
        if (ofertaColaboracion instanceof OfertaColaboracionUV){
            try {
                FXMLLoader loader = Utils.obtenerLoader("Views/colaboracion/FXMLRegistrarColaboracionConOfertaUV.fxml");
                Parent root = loader.load();
                FXMLRegistrarColaboracionConOfertaUVController controller = loader.getController();
                root.getStylesheets().add(proyectoconstruccion.AppStartup.class.getResource("Views/style.css").toExternalForm());
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
                FXMLLoader loader = Utils.obtenerLoader("Views/colaboracion/FXMLRegistrarColaboracionConOfertaExterna.fxml");
                Parent root = loader.load();
                FXMLRegistrarColaboracionConOfertaExternaController controller = loader.getController();
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

            this.idioma=IdiomaDAO.obtenerIdioma(ofertaColaboracion.getIdiomaId());

            lbIdioma.setText(idioma.getIdioma());
            lbNombreProfesor.setText(ofertaColaboracion.getProfesor().getNombre()+" "+ofertaColaboracion.getProfesor().getApellidoPaterno()+" "+ofertaColaboracion.getProfesor().getApellidoMaterno());
            lbCorreoProfesor.setText(ofertaColaboracion.getProfesor().getCorreoElectronico());

    }

    public void recargarDatos() {
        inicializarDetalles((OfertaColaboracion) OfertaColaboracionDAO.getOfertaColaboracionById(ofertaColaboracion.getOfertaColaboracionId()).get("oferta"));
    }


}
