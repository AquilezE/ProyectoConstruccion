package proyectoconstruccion.Controllers.oferta;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import proyectoconstruccion.Utils.Utils;
import proyectoconstruccion.modelo.DAO.IdiomaDAO;
import proyectoconstruccion.modelo.POJO.ofertacolaboracion.OfertaColaboracion;
import proyectoconstruccion.modelo.POJO.ofertacolaboracion.OfertaColaboracionExterna;
import proyectoconstruccion.modelo.POJO.profesor.Profesor;

import java.io.IOException;

public class FXMLOfertaItemController {

    public Pane paneColor;

    public Label lbTitulo;
    public Label lbNombreProfesorOfertante;
    public Label lbPeriodo;
    public Label lbIdiomaOferta;
    public Label lbDuracion;

    OfertaColaboracion ofertaColaboracion;
    Profesor profesor;

    public void InicializarComponentes(OfertaColaboracion ofertaColaboracion){
        this.ofertaColaboracion = ofertaColaboracion;
        profesor = ofertaColaboracion.getProfesor();

        lbTitulo.setText(ofertaColaboracion.getTitulo());
        lbNombreProfesorOfertante.setText(profesor.getNombre()+" "+profesor.getApellidoPaterno());
        lbPeriodo.setText(ofertaColaboracion.getPeriodo());
        lbIdiomaOferta.setText(IdiomaDAO.obtenerIdioma(ofertaColaboracion.getIdiomaId()).getIdioma());
        lbDuracion.setText(ofertaColaboracion.getDuracion());

        if(ofertaColaboracion instanceof OfertaColaboracionExterna){
            paneColor.setStyle("-fx-background-color: #FFBF00; -fx-background-radius: 15;");
        }else{
            paneColor.setStyle("-fx-background-color: #28AD56; -fx-background-radius: 15;");
        }

    }

    public void btnDetalles(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = Utils.obtenerLoader("Views/oferta/FXMLDetallesOfertaColaboracion.fxml");
            Parent root = loader.load();
            FXMLDetallesOfertaColaboracionController controller = loader.getController();
            root.getStylesheets().add(proyectoconstruccion.AppStartup.class.getResource("Views/style.css").toExternalForm());
            controller.inicializarDetalles(ofertaColaboracion);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
