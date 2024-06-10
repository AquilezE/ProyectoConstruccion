package proyectoconstruccion.modelo.DAO;
import proyectoconstruccion.modelo.ConexionBD;
import proyectoconstruccion.modelo.POJO.Universidad;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public  class UniversidadDAO {

    public static Universidad getUniversidadById(int id) {
        Universidad universidad = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Universidad WHERE universidad_id = ?";

        try {
            con = ConexionBD.getConexion();
                    ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                universidad = new Universidad();
                universidad.setUniversidadId(rs.getInt("universidad_id"));
                universidad.setNombre(rs.getString("nombre"));
                universidad.setPais(rs.getString("pais"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // close database resources
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return universidad;
    }


    public static List<Universidad> getAllUniversidades() {
        List<Universidad> universidades = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Universidad";

        try {
            con = ConexionBD.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Universidad universidad = new Universidad();
                universidad.setUniversidadId(rs.getInt("universidad_id"));
                universidad.setNombre(rs.getString("nombre"));
                universidad.setPais(rs.getString("pais"));
                universidades.add(universidad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // close database resources
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return universidades;
    }



}
