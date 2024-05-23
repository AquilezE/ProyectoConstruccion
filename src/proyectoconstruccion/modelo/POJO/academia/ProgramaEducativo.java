package proyectoconstruccion.modelo.POJO.academia;

public class ProgramaEducativo {

    private Integer programaEducativoid;

    private String nombre;
    private String anioInicio;

    private Integer dependenciaId;

    public ProgramaEducativo(Integer programaEducativoid, String nombre, String anioInicio, Integer dependenciaId) {
        this.programaEducativoid = programaEducativoid;
        this.nombre = nombre;
        this.anioInicio = anioInicio;
        this.dependenciaId = dependenciaId;
    }

    public ProgramaEducativo() {}

    public Integer getProgramaEducativoid() {
        return programaEducativoid;
    }

    public void setProgramaEducativoid(Integer programaEducativoid) {
        this.programaEducativoid = programaEducativoid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAnioInicio() {
        return anioInicio;
    }

    public void setAnioInicio(String anioInicio) {
        this.anioInicio = anioInicio;
    }

    public Integer getDependenciaId() {
        return dependenciaId;
    }

    public void setDependenciaId(Integer dependenciaId) {
        this.dependenciaId = dependenciaId;
    }
}
