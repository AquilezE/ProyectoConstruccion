package proyectoconstruccion.modelo.POJO;

public class Universidad {
    private Integer universidadId;
    private String nombre;
    private String pais;

    public Universidad(Integer universidadId, String nombre, String pais) {
        this.universidadId = universidadId;
        this.nombre = nombre;
        this.pais = pais;
    }

    public Integer getUniversidadId() {
        return universidadId;
    }

    public void setUniversidadId(Integer universidadId) {
        this.universidadId = universidadId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
