package proyectoconstruccion.Controllers.colaboracion;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import proyectoconstruccion.modelo.POJO.colaboracion.Colaboracion;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorExterno;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorUV;

public class FXMLColaboracionItem {

    public Label lbTitulo;
    public Label lbNombreProfesorExt;
    public Label lbNombreProfesorUV;
    public Label lbFechaInicio;
    public Label lbFechaCierre;
    public Label lbIdiomaColaboracion;
    public Label lbExperienciaEducativa;

    ProfesorUV profesorUV;
    ProfesorExterno profesorExterno;


    Colaboracion colaboracion;



    //Aqui se van a meter los datos de las colabs que saquemos si es el admin ova el profesor, aparte de todas las consultas putas
    public void InicializarComponentes(Colaboracion colaboracion){
        this.colaboracion=colaboracion;
        lbTitulo.setText(colaboracion.getTitulo());
        lbFechaInicio.setText(colaboracion.getFechaInicio().toString());
        lbFechaCierre.setText(colaboracion.getFechaCierre().toString());
       // lbIdiomaColaboracion.setText(colaboracion.getIdioma());
        this.profesorExterno=colaboracion.getProfesorExterno();
        this.profesorUV=colaboracion.getProfesorUv();

        lbNombreProfesorExt.setText(profesorExterno.getNombre()+" "+profesorExterno.getApellidoPaterno());
        lbNombreProfesorUV.setText(profesorUV.getNombre()+" "+profesorUV.getApellidoPaterno());
    }

    public void btnDetalles(ActionEvent actionEvent) {
        System.out.println("Ir a detalles de Colaboracion");
    }
}
