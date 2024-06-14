package proyectoconstruccion.Controllers.colaboracion;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import proyectoconstruccion.Utils.FilterData;
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


    public void InicializarComponentesSolicitudes(){


        HashMap<String,Object> seleccion =  ColaboracionDAO.getColaboraciones(null, "Concluida",null,null,null,null,Sesion.getInstancia().getRol());
        if(seleccion==null){
            System.out.println("da null");
        }


        ArrayList<Colaboracion> colaboraciones= (ArrayList<Colaboracion>) seleccion.get("colaboraciones");
        for (Colaboracion colaboracion:colaboraciones){
            System.out.println("colaboracionAñadida");
            añadirItem(colaboracion);
        }

    }

    public void InicializarComponentes(FilterData filtros){
        String titulo = (filtros.getTituloColab().trim().isEmpty()) ? null : filtros.getTituloColab();
        String estado = (filtros.getEstado() != null) ? filtros.getEstado() : null;
        String periodo;
        if (filtros.getPeriodo() != null) {
            periodo = filtros.getPeriodo().getDescripcion();
            System.out.println("BBADBABDAB: " + periodo);
        } else {
            periodo = null;
        }
        LocalDate fechaInicio = (filtros.getFechaInicio() != null) ? filtros.getFechaInicio() : null;
        LocalDate fechaFin = (filtros.getFechaCierre() != null) ? filtros.getFechaCierre() : null;

        Integer experienciaEducativaId;
        if (filtros.getExperienciaEducativa() != null) {
            experienciaEducativaId = filtros.getExperienciaEducativa().getExperienciaEducativaId();
        }else {
            experienciaEducativaId = null;
        }

        System.out.println("Parametros de búsqueda:");
        System.out.println("Titulo: " + titulo);
        System.out.println("Estado: " + estado);
        System.out.println("Periodo: " + periodo);
        System.out.println("FechaInicio: " + fechaInicio);
        System.out.println("FechaFin: " + fechaFin);
        System.out.println("ExperienciaEducativa: " + experienciaEducativaId);

        HashMap<String, Object> seleccion = ColaboracionDAO.getColaboraciones(titulo, estado, periodo, fechaInicio, fechaFin, experienciaEducativaId, Sesion.getInstancia().getRol());

        if (seleccion.get("colaboraciones") == null) {
            System.out.println("da null");
        }

        ArrayList<Colaboracion> colaboraciones = (ArrayList<Colaboracion>) seleccion.get("colaboraciones");

        System.out.println("Cantidad de colaboraciones: " + colaboraciones.size());
        for (Colaboracion colaboracion : colaboraciones) {
            System.out.println("colaboracionAñadida");
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
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
