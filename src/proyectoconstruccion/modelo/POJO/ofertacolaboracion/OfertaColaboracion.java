package proyectoconstruccion.modelo.POJO.ofertacolaboracion;

import proyectoconstruccion.modelo.POJO.profesor.Profesor;

public abstract class OfertaColaboracion {
    private Integer ofertaColaboracionId;
    private Integer idIdioma;
    private String periodo;
    private String titulo;
    private String duracion;
    private Integer tipo;
    private Profesor profesor;

    public OfertaColaboracion(Integer ofertaColaboracionId, Integer idIdioma, String periodo, String titulo,Integer tipo, String duracion,Profesor profesor) {
        this.ofertaColaboracionId = ofertaColaboracionId;
        this.idIdioma = idIdioma;
        this.periodo = periodo;
        this.titulo = titulo;
        this.duracion = duracion;
        this.tipo = tipo;
        this.profesor = profesor;
    }

    public OfertaColaboracion() {

    }

    public Integer getTipo(){
        return tipo;
    }
    public void setTipo(Integer tipo){
        this.tipo = tipo;
    }

    public Integer getOfertaColaboracionId() {
        return ofertaColaboracionId;
    }

    public void setOfertaColaboracionId(Integer ofertaColaboracionId) {
        this.ofertaColaboracionId = ofertaColaboracionId;
    }

    public Integer getIdiomaId() {return idIdioma;}

    public void setIdiomaId(Integer idIdioma) {this.idIdioma = idIdioma;}

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