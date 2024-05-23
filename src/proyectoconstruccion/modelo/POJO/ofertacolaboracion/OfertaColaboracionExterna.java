package proyectoconstruccion.modelo.POJO.ofertacolaboracion;

import proyectoconstruccion.modelo.POJO.profesor.ProfesorExterno;

public class OfertaColaboracionExterna extends OfertaColaboracion {
    private ProfesorExterno profesorExterno;

    public OfertaColaboracionExterna(Integer ofertaColaboracionId, String periodo, String titulo, String duracion, ProfesorExterno profesorExterno) {
        super(ofertaColaboracionId, periodo, titulo, duracion);
        this.profesorExterno = profesorExterno;
    }
}
