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
            // Create a statement
            Statement stmt = conn.createStatement();

            // Execute a query
            ResultSet rs = stmt.executeQuery("SELECT * FROM Periodo");

            // Extract data from result set


            while (rs.next()) {

                // Retrieve by column name
                int id = rs.getInt("id");
                String descripcion = rs.getString("descripcion");

                // Add to the list of periodos
                periodos.add(new Periodo(id, descripcion));
            }
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle any other exceptions
            e.printStackTrace();
        }

        return periodos;

    }
}
