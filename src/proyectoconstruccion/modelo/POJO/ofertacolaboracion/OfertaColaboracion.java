package proyectoconstruccion.modelo.POJO.ofertacolaboracion;

import proyectoconstruccion.modelo.POJO.profesor.Profesor;

public abstract class OfertaColaboracion {
    private Integer ofertaColaboracionId;
    private Integer idiomaID;
    private String periodo;
    private String titulo;
    private String duracion;
    private Profesor profesor;

    public OfertaColaboracion(Integer ofertaColaboracionId, Integer idiomaID, String periodo, String titulo, String duracion,Profesor profesor) {
        this.ofertaColaboracionId = ofertaColaboracionId;
        this.idiomaID = idiomaID;
        this.periodo = periodo;
        this.titulo = titulo;
        this.duracion = duracion;
        this.profesor = profesor;
    }

    public Integer getOfertaColaboracionId() {
        return ofertaColaboracionId;
    }

    public void setOfertaColaboracionId(Integer ofertaColaboracionId) {
        this.ofertaColaboracionId = ofertaColaboracionId;
    }

    public Integer getIdiomaID() {return idiomaID;}

    public void setIdiomaID(Integer idiomaID) {this.idiomaID = idiomaID;}

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public Profesor getProfesor() {return profesor;}

    public void setProfesor(Profesor profesor) {this.profesor = profesor;}

}
