package proyectoconstruccion.Controllers.colaboracion;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import proyectoconstruccion.Utils.Constantes;
import proyectoconstruccion.Utils.Sesion;
import proyectoconstruccion.modelo.POJO.colaboracion.Colaboracion;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorExterno;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorUV;

import java.net.URL;
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
        System.out.println("Boton autorizar");
    }

    public void btnDenegar(ActionEvent actionEvent) {
        System.out.println("Boton denegar");
    }

}
