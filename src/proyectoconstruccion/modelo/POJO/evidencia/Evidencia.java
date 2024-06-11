package proyectoconstruccion.modelo.POJO.evidencia;

//hay que ver como hacer esa madre de poder meter multiples evidencias
public class Evidencia {
    private Integer evidenciaId;

    public Evidencia(Integer evidenciaId) {
        this.evidenciaId = evidenciaId;
    }



    public Integer getEvidenciaId() {
        return evidenciaId;
    }

    public void setEvidenciaId(Integer evidenciaId) {
        this.evidenciaId = evidenciaId;
    }

}
