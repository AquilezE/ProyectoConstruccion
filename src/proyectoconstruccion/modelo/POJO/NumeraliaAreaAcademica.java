package proyectoconstruccion.modelo.POJO;

public class NumeraliaAreaAcademica {
    private final String areaAcademica;
    private final int numeroProfesores;
    private final int numeroEstudiantes;

    public NumeraliaAreaAcademica(String areaAcademica, Integer numeroProfesores, Integer numeroEstudiantes) {
        this.areaAcademica = areaAcademica;
        this.numeroProfesores = numeroProfesores;
        this.numeroEstudiantes = numeroEstudiantes;
    }

    public String getAreaAcademica() {
        return areaAcademica;
    }

    public Integer getNumeroProfesores() {
        return numeroProfesores;
    }

    public Integer getNumeroEstudiantes() {
        return numeroEstudiantes;
    }
}
