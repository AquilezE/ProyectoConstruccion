package proyectoconstruccion.Controllers.colaboracion;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import proyectoconstruccion.Utils.DatosFiltroColaboracion;
import proyectoconstruccion.Utils.Sesion;
import proyectoconstruccion.Utils.Utils;
import proyectoconstruccion.modelo.DAO.ColaboracionDAO;
import proyectoconstruccion.modelo.POJO.colaboracion.Colaboracion;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class FXMLContenedorColaboracionesController {

    public VBox vBoxBucket;

    public void InicializarComponentesSolicitudes() {

        HashMap<String, Object> seleccion = ColaboracionDAO.getColaboraciones(null, "Concluida", null, null, null, null, Sesion.getInstancia().getRol());

        ArrayList<Colaboracion> colaboraciones = (ArrayList<Colaboracion>) seleccion.get("colaboraciones");

        for (Colaboracion colaboracion : colaboraciones) {
            añadirItem(colaboracion);
        }
    }


    public void InicializarComponentes(DatosFiltroColaboracion filtros) {

        eliminarItems();

        String titulo = (filtros.getTituloColab().trim().isEmpty()) ? null : filtros.getTituloColab();
        String estado = (filtros.getEstado() != null) ? filtros.getEstado() : null;
        String periodo;
        if (filtros.getPeriodo() != null) {
            periodo = filtros.getPeriodo().getDescripcion();
        } else {
            periodo = null;
        }
        LocalDate fechaInicio = (filtros.getFechaInicio() != null) ? filtros.getFechaInicio() : null;
        LocalDate fechaFin = (filtros.getFechaCierre() != null) ? filtros.getFechaCierre() : null;

        Integer experienciaEducativaId;
        if (filtros.getExperienciaEducativa() != null) {
            experienciaEducativaId = filtros.getExperienciaEducativa().getExperienciaEducativaId();
        } else {
            experienciaEducativaId = null;
        }


        HashMap<String, Object> seleccion = ColaboracionDAO.getColaboraciones(titulo, estado, periodo, fechaInicio, fechaFin, experienciaEducativaId, Sesion.getInstancia().getRol());

        ArrayList<Colaboracion> colaboraciones = (ArrayList<Colaboracion>) seleccion.get("colaboraciones");

        for (Colaboracion colaboracion : colaboraciones) {
            añadirItem(colaboracion);
        }
    }

    private void añadirItem(Colaboracion colaboracion) {
        try {
            FXMLLoader loader = Utils.obtenerLoader("Views/colaboracion/FXMLColaboracionItem.fxml");
            AnchorPane colaboracionItem = loader.load();

            FXMLColaboracionItemController controler = loader.getController();
            controler.InicializarComponentes(colaboracion);

            vBoxBucket.getChildren().add(colaboracionItem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void eliminarItems() {
        vBoxBucket.getChildren().clear();
    }

}
