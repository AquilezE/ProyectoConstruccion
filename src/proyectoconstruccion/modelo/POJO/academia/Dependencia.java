package proyectoconstruccion.modelo.POJO.academia;

public class Dependencia {
    private Integer dependenciaId;

    private String dependenciaNombre;
    private String Campus;
    private String Municipio;

    private Integer areaAcademicaId;


    public Dependencia(Integer dependenciaId, String dependenciaNombre, String campus, String municipio, Integer areaAcademicaId) {
        this.dependenciaId = dependenciaId;
        this.dependenciaNombre = dependenciaNombre;
        Campus = campus;
        Municipio = municipio;
        this.areaAcademicaId = areaAcademicaId;
    }

    public Dependencia() {}

    public Integer getDependenciaId() {
        return dependenciaId;
    }

    public void setDependenciaId(Integer dependenciaId) {
        this.dependenciaId = dependenciaId;
    }

    public String getDependenciaNombre() {
        return dependenciaNombre;
    }

    public void setDependenciaNombre(String dependenciaNombre) {
        this.dependenciaNombre = dependenciaNombre;
    }

    public String getCampus() {
        return Campus;
    }

    public void setCampus(String campus) {
        Campus = campus;
    }

    public String getMunicipio() {
        return Municipio;
    }

    public void setMunicipio(String municipio) {
        Municipio = municipio;
    }

    public Integer getAreaAcademicaId() {
        return areaAcademicaId;
    }

    public void setAreaAcademicaId(Integer areaAcademicaId) {
        this.areaAcademicaId = areaAcademicaId;
    }
}
