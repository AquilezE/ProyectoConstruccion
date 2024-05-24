package proyectoconstruccion.modelo.POJO.ofertacolaboracion;

import proyectoconstruccion.modelo.POJO.profesor.ProfesorExterno;

public class OfertaColaboracionExterna extends OfertaColaboracion {


    public OfertaColaboracionExterna(Integer ofertaColaboracionId, Integer idiomaId, String periodo, String titulo, String duracion, ProfesorExterno profesorExterno) {
        super(ofertaColaboracionId, idiomaId, periodo, titulo, duracion,profesorExterno);

    }



}
