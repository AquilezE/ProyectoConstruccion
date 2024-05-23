package proyectoconstruccion.modelo.POJO.evidencia;

//Esta madre se puede cambiar de nombre a Syllabus
public class EvidenciaPDF {
    private Integer evidenciaPDFId;
    byte[] archivoPDF;

    public EvidenciaPDF(Integer evidenciaPDFId, byte[] archivoPDF) {
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

