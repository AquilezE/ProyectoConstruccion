package proyectoconstruccion.modelo.POJO.ofertacolaboracion;

import proyectoconstruccion.modelo.POJO.profesor.ProfesorUV;

public class OfertaColaboracionUV extends OfertaColaboracion {
    ProfesorUV profesorUV;

    public OfertaColaboracionUV(Integer ofertaColaboracionId, String periodo, String titulo, String duracion, ProfesorUV profesorUV) {
        super(ofertaColaboracionId, periodo, titulo, duracion);
        this.profesorUV = profesorUV;
    }

    public ProfesorUV getProfesorUV() {
        return profesorUV;
    }

    public void setProfesorUV(ProfesorUV profesorUV) {
        this.profesorUV = profesorUV;
    }
}
