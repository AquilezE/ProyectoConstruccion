package proyectoconstruccion.modelo.DAO;

import proyectoconstruccion.modelo.ConexionBD;
import proyectoconstruccion.modelo.POJO.Periodo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PeriodoDAO {

    public static List<Periodo> getPeriodos() {
        List<Periodo> periodos = new ArrayList<>();


        try (Connection conn = ConexionBD.getConexion()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Periodo");

            while (rs.next()) {
                int id = rs.getInt("id");
                String descripcion = rs.getString("descripcion");

                periodos.add(new Periodo(id, descripcion));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return periodos;

    }
}
