package proyectoconstruccion.modelo.POJO.evidencia;

//hay que ver como hacer esa madre de poder meter multiples evidencias
public class Evidencia {
    private Integer evidenciaId;

    private Integer evidenciaPDFId;
    private Integer evidenciaZip;

    public Evidencia(Integer evidenciaId, Integer evidenciaPDFId, Integer evidenciaZip) {
        this.evidenciaId = evidenciaId;
        this.evidenciaPDFId = evidenciaPDFId;
        this.evidenciaZip = evidenciaZip;
    }



    public Integer getEvidenciaId() {
        return evidenciaId;
    }

    public void setEvidenciaId(Integer evidenciaId) {
        this.evidenciaId = evidenciaId;
    }

    public Integer getEvidenciaPDFId() {
        return evidenciaPDFId;
    }

    public void setEvidenciaPDFId(Integer evidenciaPDFId) {
        this.evidenciaPDFId = evidenciaPDFId;
    }

    public Integer getEvidenciaZip() {
        return evidenciaZip;
    }

    public void setEvidenciaZip(Integer evidenciaZip) {
        this.evidenciaZip = evidenciaZip;
    }

}
