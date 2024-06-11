package proyectoconstruccion.modelo.DAO;

import proyectoconstruccion.modelo.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class EvidenciaDAO {

    public static boolean guardarSyllabus(Integer idEvidencia, File syllabus) {
        try {
            byte[] syllabusData = new byte[(int) syllabus.length()];
            FileInputStream fis = new FileInputStream(syllabus);
            fis.read(syllabusData);
            fis.close();

            Connection con = ConexionBD.getConexion();
            String sql = "UPDATE Evidencia SET syllabus = ? WHERE evidencia_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setBytes(1, syllabusData);
            stmt.setInt(2, idEvidencia);
            return stmt.executeUpdate() > 0;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean guardarEvidenciaZip(Integer idEvidencia, File evidencia) {
        try {
            byte[] evidenciaData = new byte[(int) evidencia.length()];
            FileInputStream fis = new FileInputStream(evidencia);
            fis.read(evidenciaData);
            fis.close();

            Connection con = ConexionBD.getConexion();
            String sql = "UPDATE Evidencia SET EvidenciaZip = ? WHERE evidencia_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setBytes(1, evidenciaData);
            stmt.setInt(2, idEvidencia);
            return stmt.executeUpdate() > 0;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean guardarListaEstudiantes(Integer idEvidencia, File listaEstudiantes) {
        try {
            byte[] listaEstudiantesData = new byte[(int) listaEstudiantes.length()];
            FileInputStream fis = new FileInputStream(listaEstudiantes);
            fis.read(listaEstudiantesData);
            fis.close();

            Connection con = ConexionBD.getConexion();
            String sql = "UPDATE Evidencia SET listaEstudiantes = ? WHERE evidencia_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setBytes(1, listaEstudiantesData);
            stmt.setInt(2, idEvidencia);
            return stmt.executeUpdate() > 0;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return false;
    }

}
