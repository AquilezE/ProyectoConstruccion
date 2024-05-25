package proyectoconstruccion.Controllers.oferta;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import proyectoconstruccion.modelo.DAO.ProfesorDAO;
import proyectoconstruccion.modelo.POJO.ofertacolaboracion.OfertaColaboracion;
import proyectoconstruccion.modelo.POJO.ofertacolaboracion.OfertaColaboracionExterna;
import proyectoconstruccion.modelo.POJO.profesor.Profesor;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorExterno;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorUV;

public class FXMLOfertaItem {

    public Pane paneColor;

    public Label lbTitulo;
    public Label lbNombreProfesorOfertante;
    public Label lbPeriodo;
    public Label lbIdiomaOferta;
    public Label lbDuracion;

    OfertaColaboracion ofertaColaboracion;
    Profesor profesor;

    //Aqui esta chido por que tienes que ver de que instancia es la ofertaColaboracion
    public void InicializarComponentes(OfertaColaboracion ofertaColaboracion){
        this.ofertaColaboracion = ofertaColaboracion;
        profesor = ofertaColaboracion.getProfesor();

        lbTitulo.setText(ofertaColaboracion.getTitulo());
        lbNombreProfesorOfertante.setText(profesor.getNombre()+" "+profesor.getApellidoPaterno());
        lbPeriodo.setText(ofertaColaboracion.getPeriodo());
        lbIdiomaOferta.setText(String.valueOf(ofertaColaboracion.getIdiomaID()));

        if(ofertaColaboracion instanceof OfertaColaboracionExterna){
            paneColor.setStyle("-fx-background-color: #FFBF00; -fx-background-radius: 15;");
        }else{
            paneColor.setStyle("-fx-background-color: #28AD56; -fx-background-radius: 15;");
        }
    }

    public void btnDetalles(ActionEvent actionEvent) {
        System.out.println("Se muestran los detalles");

        System.out.println(ProfesorDAO.getTipoProfesor(profesor.getProfesorId()));

    }
}
