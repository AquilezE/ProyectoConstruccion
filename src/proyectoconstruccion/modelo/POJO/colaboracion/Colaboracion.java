package proyectoconstruccion.modelo.POJO.colaboracion;

import proyectoconstruccion.modelo.POJO.Idioma;
import proyectoconstruccion.modelo.POJO.academia.ExperienciaEducativa;
import proyectoconstruccion.modelo.POJO.evidencia.Evidencia;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorExterno;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorUV;

import java.time.LocalDate;
import java.util.Date;

public class Colaboracion {

    private Integer colaboracionId;

    private String Duracion;
    private String Periodo;
    private String Titulo;
    private LocalDate fechaInicio;
    private LocalDate fechaCierre;
    private String tipo;
    private String estado;
    private Integer numeroEstudiantes;

    private ProfesorUV profesorUv;
    private ProfesorExterno profesorExterno;
    private ExperienciaEducativa experienciaEducativa;
    private Evidencia evidencia;
    private Idioma Idioma;

    public Colaboracion(Integer colaboracionId, String duracion, String periodo, String titulo, Idioma idioma, LocalDate fechaInicio, LocalDate fechaCierre, String tipo, String estado, Integer numeroEstudiantes, ProfesorUV profesorUv, ProfesorExterno profesorExterno, ExperienciaEducativa experienciaEducativa, Evidencia evidencia) {
        this.colaboracionId = colaboracionId;
        Duracion = duracion;
        Periodo = periodo;
        Titulo = titulo;
        Idioma = idioma;
        this.fechaInicio = fechaInicio;
        this.fechaCierre = fechaCierre;
        this.tipo = tipo;
        this.estado = estado;
        this.numeroEstudiantes=numeroEstudiantes;
        this.profesorUv = profesorUv;
        this.profesorExterno = profesorExterno;
        this.experienciaEducativa = experienciaEducativa;
        this.evidencia = evidencia;
    }


    public ExperienciaEducativa getExperienciaEducativa() {
        return experienciaEducativa;
    }

    public void setExperienciaEducativa(ExperienciaEducativa experienciaEducativa) {
        this.experienciaEducativa = experienciaEducativa;
    }

    public Evidencia getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(Evidencia evidencia) {
        this.evidencia = evidencia;
    }

    //Falta saber la implementacion de como crear Colaboracion para poder hacer su creador



    public Integer getColaboracionId() {
        return colaboracionId;
    }

    public void setColaboracionId(Integer colaboracionId) {
        this.colaboracionId = colaboracionId;
    }

    public String getDuracion() {
        return Duracion;
    }

    public void setDuracion(String duracion) {
        Duracion = duracion;
    }

    public String getPeriodo() {
        return Periodo;
    }

    public void setPeriodo(String periodo) {
        Periodo = periodo;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }


    public Idioma getIdioma() {
        return Idioma;
    }

    public void setIdioma(Idioma idioma) {
        Idioma = idioma;
    }


    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(LocalDate fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getNumeroEstudiantes() {
        return numeroEstudiantes;
    }

    public void setNumeroEstudiantes(Integer numeroEstudiantes) {
        this.numeroEstudiantes=numeroEstudiantes;
    }

    public ProfesorUV getProfesorUv() {
        return profesorUv;
    }

    public void setProfesorUv(ProfesorUV profesorUv) {
        this.profesorUv = profesorUv;
    }

    public ProfesorExterno getProfesorExterno() {
        return profesorExterno;
    }

    public void setProfesorExterno(ProfesorExterno profesorExterno) {
        this.profesorExterno = profesorExterno;
    }

}
