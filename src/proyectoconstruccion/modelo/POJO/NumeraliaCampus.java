package proyectoconstruccion.modelo.POJO;

public class NumeraliaCampus {
    private final String campus;
    private final Integer numeroProfesores;
    private final Integer numeroEstudiantes;

    public NumeraliaCampus(String campus, Integer numeroProfesores, Integer numeroEstudiantes) {
        this.campus = campus;
        this.numeroProfesores = numeroProfesores;
        this.numeroEstudiantes = numeroEstudiantes;
    }

    public String getCampus() {
        return campus;
    }

    public Integer getNumeroProfesores() {
        return numeroProfesores;
    }

    public Integer getNumeroEstudiantes() {
        return numeroEstudiantes;
    }
}
