    package proyectoconstruccion.modelo.POJO;

    public class Estudiante {
        private Integer estudianteId;

        private String nombre;
        private String apellidoMaterno;
        private String apellidoPaterno;
        private String Matricula;

        public Estudiante(Integer estudianteId, String nombre, String apellidoMaterno, String apellidoPaterno, String matricula) {
            this.estudianteId = estudianteId;
            this.nombre = nombre;
            this.apellidoMaterno = apellidoMaterno;
            this.apellidoPaterno = apellidoPaterno;
            Matricula = matricula;
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
            return Matricula;
        }

        public void setMatricula(String matricula) {
            Matricula = matricula;
        }
    }
