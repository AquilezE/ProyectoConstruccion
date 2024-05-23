package proyectoconstruccion.modelo.POJO.ofertacolaboracion;

public abstract class OfertaColaboracion {
    private Integer ofertaColaboracionId;
    private String periodo;
    private String titulo;
    private String duracion;

    public OfertaColaboracion(Integer ofertaColaboracionId, String periodo, String titulo, String duracion) {
        this.ofertaColaboracionId = ofertaColaboracionId;
        this.periodo = periodo;
        this.titulo = titulo;
        this.duracion = duracion;
    }

    public Integer getOfertaColaboracionId() {
        return ofertaColaboracionId;
    }

    public void setOfertaColaboracionId(Integer ofertaColaboracionId) {
        this.ofertaColaboracionId = ofertaColaboracionId;
    }

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
}
