package proyectoconstruccion.modelo.DAO;

import proyectoconstruccion.modelo.ConexionBD;
import proyectoconstruccion.modelo.POJO.evidencia.Evidencia;

import java.io.*;
import java.sql.*;

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

    public static File getSyllabus(Integer evidenciaId) {

        try {
            Connection con = ConexionBD.getConexion();
            String sql = "SELECT syllabus from Evidencia WHERE evidencia_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, evidenciaId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                byte[] syllabusData = rs.getBytes("syllabus");
                File syllabusFile = File.createTempFile("syllabus", ".tmp");
                FileOutputStream fos = new FileOutputStream(syllabusFile);
                fos.write(syllabusData);
                fos.close();
                return syllabusFile;
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static InputStream getEvidencia (Integer evidenciaId){
            InputStream evidenciaStream = null;
            try {
                Connection con = ConexionBD.getConexion();
                String sql = "SELECT EvidenciaZip from Evidencia WHERE evidencia_id = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, evidenciaId);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    evidenciaStream = rs.getBinaryStream("EvidenciaZip");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return evidenciaStream;
        }

    public static InputStream getListaDeEstudiantes(Integer evidenciaId) {

        InputStream listaEstudiantesStream = null;
        try {
            Connection con = ConexionBD.getConexion();
            String sql = "SELECT listaEstudiantes from Evidencia WHERE evidencia_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, evidenciaId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                listaEstudiantesStream = rs.getBinaryStream("listaEstudiantes");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaEstudiantesStream;
    }

    public static Evidencia crearRegistroEvidencia() {
        try {
            Connection con = ConexionBD.getConexion();
            String sql = "INSERT INTO Evidencia (Syllabus, EvidenciaZip, listaEstudiantes) VALUES (NULL, NULL, NULL)";
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                return new Evidencia(id);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static boolean borrarEvidencias(Integer colaboracionId) {

        try {
            Connection con = ConexionBD.getConexion();
            String sql = "DELETE FROM Evidencia WHERE evidencia_id IN (SELECT evidencia_id FROM Colaboracion WHERE colaboracion_id = ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, colaboracionId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
