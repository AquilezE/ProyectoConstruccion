package proyectoconstruccion.modelo.POJO.academia;

public class AreaAcademica {
    private Integer areaAcademicaId;
    private String nombre;

    public AreaAcademica(Integer areaAcademicaId, String nombre) {
        this.areaAcademicaId = areaAcademicaId;
        this.nombre = nombre;
    }

    public AreaAcademica(){}

    public Integer getAreaAcademicaId() {
        return areaAcademicaId;
    }

    public void setAreaAcademicaId(Integer areaAcademicaId) {
        this.areaAcademicaId = areaAcademicaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
