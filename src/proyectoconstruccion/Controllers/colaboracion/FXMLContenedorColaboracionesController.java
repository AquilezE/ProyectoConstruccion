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

    // Inicializa los componentes de las solicitudes
    public void InicializarComponentesSolicitudes() {

        // Busca las colaboraciones que han sido concluidas
        HashMap<String, Object> seleccion = ColaboracionDAO.getColaboraciones(null, "Concluida", null, null, null, null, Sesion.getInstancia().getRol());

        // Si no hay colaboraciones, devuelve "null" en la consola
        if (seleccion == null) {
            System.out.println("da null");
        }

        // Crea una lista de las colaboraciones obtenidas de la base de datos
        ArrayList<Colaboracion> colaboraciones = (ArrayList<Colaboracion>) seleccion.get("colaboraciones");

        // Añade cada colaboración a la vista
        for (Colaboracion colaboracion : colaboraciones) {
            System.out.println("colaboracionAñadida");
            añadirItem(colaboracion);
        }
    }


    public void InicializarComponentes(DatosFiltroColaboracion filtros) {

        eliminarItems();

        // Operaciones para filtrar las búsquedas
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

        // Recupera y muestra los parámetros de búsqueda
        System.out.println("Parametros de búsqueda:");
        System.out.println("Titulo: " + titulo);
        System.out.println("Estado: " + estado);
        System.out.println("Periodo: " + periodo);
        System.out.println("FechaInicio: " + fechaInicio);
        System.out.println("FechaFin: " + fechaFin);
        System.out.println("ExperienciaEducativa: " + experienciaEducativaId);

        HashMap<String, Object> seleccion = ColaboracionDAO.getColaboraciones(titulo, estado, periodo, fechaInicio, fechaFin, experienciaEducativaId, Sesion.getInstancia().getRol());

        ArrayList<Colaboracion> colaboraciones = (ArrayList<Colaboracion>) seleccion.get("colaboraciones");

        for (Colaboracion colaboracion : colaboraciones) {
            System.out.println("colaboracionAñadida");
            // Añade cada colaboración filtrada a la vista
            añadirItem(colaboracion);
        }
    }

    // Método para añadir un item de colaboración a la vista
    private void añadirItem(Colaboracion colaboracion) {
        try {
            FXMLLoader loader = Utils.obtenerLoader("Views/colaboracion/FXMLColaboracionItem.fxml");
            AnchorPane colaboracionItem = loader.load();

            // Inicializa los componentes para cada colaboración
            FXMLColaboracionItemController controler = loader.getController();
            controler.InicializarComponentes(colaboracion);

            // Añade el item al contenedor principal
            vBoxBucket.getChildren().add(colaboracionItem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar todos los items del contenedor principal
    private void eliminarItems() {
        vBoxBucket.getChildren().clear();
    }

}
