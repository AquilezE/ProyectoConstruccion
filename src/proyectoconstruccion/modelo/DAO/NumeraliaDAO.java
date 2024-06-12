package proyectoconstruccion.modelo.DAO;


import proyectoconstruccion.modelo.ConexionBD;
import proyectoconstruccion.modelo.POJO.NumeraliaAreaAcademica;
import proyectoconstruccion.modelo.POJO.NumeraliaCampus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class NumeraliaDAO {

    public static ArrayList<NumeraliaCampus> obtenerNumeraliaCampus() {
        ArrayList<NumeraliaCampus> numeraliaCampusLista = new ArrayList<>();
        Connection conexionBD = ConexionBD.getConexion();

        if (conexionBD != null) {
            String consulta = "SELECT Campus, COUNT(DISTINCT profesor_uv_id) as NumeroProfesores, SUM(NumeroEstudiantes) as NumeroEstudiantes FROM colaboracion " +
                    "JOIN experienciaeducativa ON colaboracion.experiencia_educativa_id = experienciaeducativa.experiencia_educativa_id " +
                    "JOIN programaeducativo ON experienciaeducativa.programa_educativo_id = programaeducativo.programa_educativo_id " +
                    "JOIN dependencia ON programaeducativo.dependencia_id = dependencia.dependencia_id " +
                    "WHERE colaboracion.Estado = 'Concluida' " +
                    "GROUP BY Campus";
            try {
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();
                while (resultado.next()) {
                    String campus = resultado.getString("Campus");
                    int numeroProfesores = resultado.getInt("NumeroProfesores");
                    int numeroEstudiantes = resultado.getInt("NumeroEstudiantes");
                    numeraliaCampusLista.add(new NumeraliaCampus(campus, numeroProfesores, numeroEstudiantes));
                }
                conexionBD.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return numeraliaCampusLista;
    }


public static ArrayList<NumeraliaAreaAcademica> obtenerNumeraliaAreaAcademica() {
    ArrayList<NumeraliaAreaAcademica> numeraliaAreaAcademicaLista = new ArrayList<>();
    Connection conexionBD = ConexionBD.getConexion();

    if (conexionBD != null) {
        String consulta = "SELECT NombreAreaAcademica, COUNT(DISTINCT profesor_uv_id) as NumeroProfesores, SUM(NumeroEstudiantes) as NumeroEstudiantes FROM colaboracion " +
            "JOIN experienciaeducativa ON colaboracion.experiencia_educativa_id = experienciaeducativa.experiencia_educativa_id " +
            "JOIN programaeducativo ON experienciaeducativa.programa_educativo_id = programaeducativo.programa_educativo_id " +
            "JOIN dependencia ON programaeducativo.dependencia_id = dependencia.dependencia_id " +
            "JOIN areaacademica ON dependencia.area_academica_id = areaacademica.area_academica_id " +
            "WHERE colaboracion.Estado= 'Concluida' " +
            "GROUP BY NombreAreaAcademica";
        try {
            PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
            ResultSet resultado = prepararSentencia.executeQuery();
            while (resultado.next()) {
                String areaAcademica = resultado.getString("NombreAreaAcademica");
                int numeroProfesores = resultado.getInt("NumeroProfesores");
                int numeroEstudiantes = resultado.getInt("NumeroEstudiantes");
                numeraliaAreaAcademicaLista.add(new NumeraliaAreaAcademica(areaAcademica,numeroProfesores,numeroEstudiantes));
            }
            conexionBD.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return numeraliaAreaAcademicaLista;
}
    public static ArrayList<NumeraliaCampus> obtenerNumeraliaCampusPorPeriodo(String periodo) {
        ArrayList<NumeraliaCampus> numeraliaCampusListaPorPeriodo = new ArrayList<>();
        Connection conexionBD = ConexionBD.getConexion();

        if (conexionBD != null) {
            String consulta = "SELECT Campus, COUNT(DISTINCT profesor_uv_id) as NumeroProfesores, SUM(NumeroEstudiantes) as NumeroEstudiantes FROM colaboracion " +
                    "JOIN experienciaeducativa ON colaboracion.experiencia_educativa_id = experienciaeducativa.experiencia_educativa_id " +
                    "JOIN programaeducativo ON experienciaeducativa.programa_educativo_id = programaeducativo.programa_educativo_id " +
                    "JOIN dependencia ON programaeducativo.dependencia_id = dependencia.dependencia_id " +
                    "WHERE colaboracion.Estado = 'Concluida' AND colaboracion.periodo = ? " +
                    "GROUP BY Campus";
            try {
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setString(1, periodo);
                ResultSet resultado = prepararSentencia.executeQuery();
                while (resultado.next()) {
                    String campus = resultado.getString("Campus");
                    int numeroProfesores = resultado.getInt("NumeroProfesores");
                    int numeroEstudiantes = resultado.getInt("NumeroEstudiantes");
                    numeraliaCampusListaPorPeriodo.add(new NumeraliaCampus(campus, numeroProfesores, numeroEstudiantes));
                }
                conexionBD.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return numeraliaCampusListaPorPeriodo;
    }


    public static ArrayList<NumeraliaAreaAcademica> obtenerNumeraliaAreaAcademicaPorPeriodo(String periodo) {
        ArrayList<NumeraliaAreaAcademica> numeraliaAreaAcademicaListaPorPeriodo = new ArrayList<>();
        Connection conexionBD = ConexionBD.getConexion();

        if (conexionBD != null) {
            String consulta = "SELECT NombreAreaAcademica, COUNT(DISTINCT profesor_uv_id) as NumeroProfesores, SUM(NumeroEstudiantes) as NumeroEstudiantes FROM colaboracion " +
                    "JOIN experienciaeducativa ON colaboracion.experiencia_educativa_id = experienciaeducativa.experiencia_educativa_id " +
                    "JOIN programaeducativo ON experienciaeducativa.programa_educativo_id = programaeducativo.programa_educativo_id " +
                    "JOIN dependencia ON programaeducativo.dependencia_id = dependencia.dependencia_id " +
                    "JOIN areaacademica ON dependencia.area_academica_id = areaacademica.area_academica_id " +
                    "WHERE colaboracion.Estado= 'Concluida' AND colaboracion.periodo = ? " +
                    "GROUP BY NombreAreaAcademica";
            try {
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setString(1, periodo);
                ResultSet resultado = prepararSentencia.executeQuery();
                while (resultado.next()) {
                    String areaAcademica = resultado.getString("NombreAreaAcademica");
                    int numeroProfesores = resultado.getInt("NumeroProfesores");
                    int numeroEstudiantes = resultado.getInt("NumeroEstudiantes");
                    numeraliaAreaAcademicaListaPorPeriodo.add(new NumeraliaAreaAcademica(areaAcademica,numeroProfesores,numeroEstudiantes));
                }
                conexionBD.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return numeraliaAreaAcademicaListaPorPeriodo;
    }

}