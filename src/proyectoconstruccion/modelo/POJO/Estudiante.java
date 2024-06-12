    package proyectoconstruccion.modelo.POJO;
    public class Estudiante {
        private Integer estudianteId;
        private String nombre;
        private String apellidoMaterno;
        private String apellidoPaterno;
        private String matricula;
        private Integer calificacion;
        private Integer faltas;

        public Estudiante(Integer estudianteId, String nombre, String apellidoMaterno, String apellidoPaterno, String matricula, Integer calificacion, Integer faltas) {
            this.estudianteId = estudianteId;
            this.nombre = nombre;
            this.apellidoMaterno = apellidoMaterno;
            this.apellidoPaterno = apellidoPaterno;
            this.matricula = matricula;
            this.calificacion = calificacion;
            this.faltas = faltas;
        }

        public Estudiante() {}

        public Integer getEstudianteId() {
            return estudianteId;
        }

        public void setEstudianteId(Integer estudianteId) {
            this.estudianteId = estudianteId;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApellidoMaterno() {
            return apellidoMaterno;
        }

        public void setApellidoMaterno(String apellidoMaterno) {
            this.apellidoMaterno = apellidoMaterno;
        }

        public String getApellidoPaterno() {
            return apellidoPaterno;
        }

        public void setApellidoPaterno(String apellidoPaterno) {
            this.apellidoPaterno = apellidoPaterno;
        }

        public String getMatricula() {
            return matricula;
        }

        public void setMatricula(String matricula) {
            this.matricula = matricula;
        }

        public Integer getCalificacion() {
            return calificacion;
        }

        public void setCalificacion(Integer calificacion) {
            this.calificacion = calificacion;
        }

        public Integer getFaltas() {
            return faltas;
        }

        public void setFaltas(Integer faltas) {
            this.faltas = faltas;
        }
    }