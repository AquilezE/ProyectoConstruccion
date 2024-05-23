package proyectoconstruccion.Controllers.colaboracion;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import proyectoconstruccion.modelo.POJO.colaboracion.Colaboracion;
import proyectoconstruccion.modelo.POJO.profesor.Profesor;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorExterno;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorUV;

public class FXMLColaboracionComponente {

    ProfesorUV profesorUV = new ProfesorUV();
    ProfesorExterno profesorExterno = new ProfesorExterno();


    public Label lbDatosProfesorUV;
    public Label lbDatosProfesorExt;
    public Label lbTituloColaboracion;
    public Label lbFechaInicio;
    public Label lbIdiomaColaboracion;
    public Label lbStatusColaboracion;
    Colaboracion colaboracion;


    //Aqui se van a meter los datos de las colabs que saquemos si es el admin ova el profesor, aparte de todas las consultas putas
    public void InicializarComponentes(Colaboracion colaboracion){
        this.colaboracion=colaboracion;
        lbTituloColaboracion.setText(colaboracion.getTitulo());
        lbFechaInicio.setText(colaboracion.getFechaInicio().toString());
        lbIdiomaColaboracion.setText(colaboracion.getIdioma());
        this.profesorExterno=colaboracion.getProfesorExterno();
        this.profesorUV=colaboracion.getProfesorUv();

        lbDatosProfesorExt.setText(profesorExterno.getNombre()+" "+profesorExterno.getApellidoPaterno());
        lbDatosProfesorUV.setText(profesorUV.getNombre()+" "+profesorUV.getApellidoPaterno());
    }

    public void btnDetalles(ActionEvent actionEvent) {
        System.out.println("Ir a detalles de Colaboracion");
    }
}
