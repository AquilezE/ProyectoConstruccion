package proyectoconstruccion.modelo.POJO.academia;

public class ExperienciaEducativa {

    private Integer experienciaEducativaId;
    private Integer creditos;
    private String descripcion;
    private String nombreExperienciaEducativa;

    private Integer programaEducativoId;

    public ExperienciaEducativa(Integer experienciaEducativaId, Integer creditos, String descripcion, String nombreExperienciaEducativa,Integer programaEducativoId) {
        this.experienciaEducativaId = experienciaEducativaId;
        this.creditos = creditos;
        this.descripcion = descripcion;
        this.nombreExperienciaEducativa = nombreExperienciaEducativa;
        this.programaEducativoId = programaEducativoId;
    }

    public ExperienciaEducativa() {}

    public Integer getExperienciaEducativaId() {
        return experienciaEducativaId;
    }

    public void setExperienciaEducativaId(Integer experienciaEducativaId) {
        this.experienciaEducativaId = experienciaEducativaId;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreExperienciaEducativa() {
        return nombreExperienciaEducativa;
    }

    public void setNombreExperienciaEducativa(String nombreExperienciaEducativa) {
        this.nombreExperienciaEducativa = nombreExperienciaEducativa;
    }

    public void setProgramaEducativoId(Integer programaEducativoId) {this.programaEducativoId = programaEducativoId;}

    public Integer getProgramaEducativoId() {return programaEducativoId;}

}
