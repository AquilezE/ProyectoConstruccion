package proyectoconstruccion.modelo.POJO.evidencia;

//Esta madre se puede cambiar de
public class EvidenciaZip {
    private Integer evidenciaPDFId;
    byte[] archivoPDF;

    public EvidenciaZip(Integer evidenciaPDFId, byte[] archivoPDF) {
        this.evidenciaPDFId = evidenciaPDFId;
        this.archivoPDF = archivoPDF;
    }

    public Integer getEvidenciaPDFId() {
        return evidenciaPDFId;
    }

    public void setEvidenciaPDFId(Integer evidenciaPDFId) {
        this.evidenciaPDFId = evidenciaPDFId;
    }

    public byte[] getArchivoPDF() {
        return archivoPDF;
    }

    public void setArchivoPDF(byte[] archivoPDF) {
        this.archivoPDF = archivoPDF;
    }
}

