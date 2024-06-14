package proyectoconstruccion.Utils;

import proyectoconstruccion.modelo.POJO.Periodo;
import proyectoconstruccion.modelo.POJO.academia.ExperienciaEducativa;

import java.time.LocalDate;

public class FilterData {
    private LocalDate fechaCierre;
    private LocalDate fechaInicio;
    private String estado;
    private String tituloColab;
    private Periodo periodo;
    private ExperienciaEducativa experienciaEducativa;

    public LocalDate getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(LocalDate fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTituloColab() {
        return tituloColab;
    }

    public void setTituloColab(String tituloColab) {
        this.tituloColab = tituloColab;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public ExperienciaEducativa getExperienciaEducativa() {
        return experienciaEducativa;
    }

    public void setExperienciaEducativa(ExperienciaEducativa experienciaEducativa) {
        this.experienciaEducativa = experienciaEducativa;
    }

    @Override
    public String toString() {
        return "FilterData{" +
                "fechaCierre=" + fechaCierre +
                ", fechaInicio=" + fechaInicio +
                ", tituloColab='" + tituloColab + '\'' +
                ", periodo='" + periodo + '\'' +
                ", experienciaEducativa='" + experienciaEducativa + '\'' +
                '}';
    }
}