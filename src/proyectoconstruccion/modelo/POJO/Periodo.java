package proyectoconstruccion.modelo.POJO;

public class Periodo {
    Integer id;
    String descripcion;

    public Periodo(){
    }

    public Periodo(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Integer getPeriodoId() {
        return id;
    }

    public void setPeriodoId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return getDescripcion();
    }
}