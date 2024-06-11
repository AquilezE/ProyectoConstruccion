package proyectoconstruccion.modelo.DAO;


import proyectoconstruccion.modelo.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class NumeraliaDAO {

    public static ArrayList<Integer> obtenerNumeroProfesorCampus (){
        int contadorXalapa=0;
        int contadorCoatzacoalcosMinatitlan=0;
        int contadorVeracruz=0;
        int contadorPozaRicaTuxpan=0;
        int contadorOrizabaCordoba=0;
        int totalProfesorCampus;
        ArrayList<Integer> dependencias = new ArrayList<>();
        Connection conexionBD = ConexionBD.getConexion();

        if(conexionBD != null){
            String consulta = "select Campus from colaboracion as c" +
                    "join experienciaeducativa as ee on c.experiencia_educativa_id = ee.experiencia_educativa_id" +
                    "join programaeducativo as pe on ee.programa_educativo_id = pe.programa_educativo_id" +
                    "join dependencia as dp on pe.dependencia_id = dp.dependencia_id" +
                    "where c.Estado='Concluida'";
            try{
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();
                while(resultado.next()){
                    switch (resultado.getString("Campus")){

                        case "Xalapa":
                            contadorXalapa++;
                            break;
                        case "Coatzacoalcos-Minatitlán":
                            contadorCoatzacoalcosMinatitlan++;
                            break;
                        case "Veracruz":
                            contadorVeracruz++;
                            break;
                        case "Córdoba":
                            contadorOrizabaCordoba++;
                            break;
                        case "Poza Rica":
                            contadorPozaRicaTuxpan++;
                            break;
                        case "Tuxpan":
                            contadorPozaRicaTuxpan++;
                            break;
                        case "Orizaba":
                            contadorOrizabaCordoba++;
                            break;
                    }
                }
                conexionBD.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return obtenerTotal(contadorXalapa, contadorCoatzacoalcosMinatitlan, contadorVeracruz, contadorPozaRicaTuxpan, contadorOrizabaCordoba, dependencias);
    }

    private static ArrayList<Integer> obtenerTotal(int contadorXalapa, int contadorCoatzacoalcosMinatitlan, int contadorVeracruz, int contadorPozaRicaTuxpan, int contadorOrizabaCordoba, ArrayList<Integer> dependencias) {
        int totalProfesorCampus;
        totalProfesorCampus= (contadorXalapa+contadorVeracruz+contadorPozaRicaTuxpan+contadorOrizabaCordoba+contadorCoatzacoalcosMinatitlan);
        dependencias.add(contadorXalapa);
        dependencias.add(contadorCoatzacoalcosMinatitlan);
        dependencias.add(contadorVeracruz);
        dependencias.add(contadorPozaRicaTuxpan);
        dependencias.add(contadorOrizabaCordoba);
        dependencias.add(totalProfesorCampus);
        return dependencias;
    }

    public static ArrayList<Integer> obtenerNumeroProfesorAreaAcademica (){
        int contadorHumanidades=0;
        int contadorTecnica =0;
        int contadorEconomicoAdministrativo=0;
        int contadorCienciasSalud=0;
        int contadorBiologicoAgropecuarias=0;
        int contadorArtes=0;
        int contadorDGRI=0;
        int totalProfesorAreaAcademica;
        ArrayList<Integer> areaAcademica = new ArrayList<>();
        Connection conexionBD = ConexionBD.getConexion();

        if(conexionBD != null){
            String consulta = "select NombreAreaAcademica from colaboracion as c" +
                    "join experienciaeducativa as ee on c.experiencia_educativa_id = ee.experiencia_educativa_id" +
                    "join programaeducativo as pe on ee.programa_educativo_id = pe.programa_educativo_id" +
                    "join dependencia as dp on pe.dependencia_id = dp.dependencia_id" +
                    "join areaacademica as aa on dp.area_academica_id = aa.area_academica_id" +
                    "where c.colaboracion_id=? AND c.Estado='Concluida'";
            try{
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();
                while(resultado.next()){
                    switch (resultado.getString("NombreAreaAcademica")){

                        case "Área Académica de Humanidades":
                            contadorHumanidades++;
                            break;
                        case "Área Académica Técnica":
                            contadorTecnica++;
                            break;
                        case "Área Académica Económico-Administrativa":
                            contadorEconomicoAdministrativo++;
                            break;
                        case "Área Académica de Ciencias de la Salud":
                            contadorCienciasSalud++;
                            break;
                        case "Área Académica de Ciencias Biológicas y Agropecuarias":
                            contadorBiologicoAgropecuarias++;
                            break;
                        case "Área Académica de Artes":
                            contadorArtes++;
                            break;
                        case "Área Académica de DGRI":
                            contadorDGRI++;
                            break;
                    }
                }
                conexionBD.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return obtenerTotalAreaAcademica(contadorHumanidades, contadorTecnica, contadorEconomicoAdministrativo, contadorCienciasSalud, contadorBiologicoAgropecuarias, contadorArtes, contadorDGRI, areaAcademica);
    }
    public static ArrayList<Integer> obtenerNumeroEstudianteCampus (){
        int contadorXalapa=0;
        int contadorCoatzacoalcosMinatitlan=0;
        int contadorVeracruz=0;
        int contadorPozaRicaTuxpan=0;
        int contadorOrizabaCordoba=0;
        int totalEstudianteCampus;
        ArrayList<Integer> dependencias = new ArrayList<>();
        Connection conexionBD = ConexionBD.getConexion();

        if(conexionBD != null){
            String consulta = "select Campus, NumeroEstudiantes  from colaboracion as c" +
                    "join experienciaeducativa as ee on c.experiencia_educativa_id = ee.experiencia_educativa_id" +
                    "join programaeducativo as pe on ee.programa_educativo_id = pe.programa_educativo_id" +
                    "join dependencia as dp on pe.dependencia_id = dp.dependencia_id" +
                    "where c.Estado='Concluida'";
            try{
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();
                while(resultado.next()){
                    switch (resultado.getString("Campus")){

                        case "Xalapa":
                            contadorXalapa += resultado.getInt("NumeroEstudiantes");
                            break;
                        case "Coatzacoalcos-Minatitlán":
                            contadorCoatzacoalcosMinatitlan += resultado.getInt("NumeroEstudiantes");;
                            break;
                        case "Veracruz":
                            contadorVeracruz += resultado.getInt("NumeroEstudiantes");;
                            break;
                        case "Córdoba":
                            contadorOrizabaCordoba += resultado.getInt("NumeroEstudiantes");;
                            break;
                        case "Poza Rica":
                            contadorPozaRicaTuxpan += resultado.getInt("NumeroEstudiantes");;
                            break;
                        case "Tuxpan":
                            contadorPozaRicaTuxpan += resultado.getInt("NumeroEstudiantes");;
                            break;
                        case "Orizaba":
                            contadorOrizabaCordoba += resultado.getInt("NumeroEstudiantes");;
                            break;
                    }
                }
                conexionBD.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return obtenerTotal(contadorXalapa, contadorCoatzacoalcosMinatitlan, contadorVeracruz, contadorPozaRicaTuxpan, contadorOrizabaCordoba, dependencias);
    }
    public static ArrayList<Integer> obtenerNumeroEstudianteAreaAcademica (){
        int contadorHumanidades=0;
        int contadorTecnica =0;
        int contadorEconomicoAdministrativo=0;
        int contadorCienciasSalud=0;
        int contadorBiologicoAgropecuarias=0;
        int contadorArtes=0;
        int contadorDGRI=0;
        int totalProfesorAreaAcademica;
        ArrayList<Integer> areaAcademica = new ArrayList<>();
        Connection conexionBD = ConexionBD.getConexion();

        if(conexionBD != null){
            String consulta = "select NombreAreaAcademica, NumeroEstudiantes from colaboracion as c" +
                    "join experienciaeducativa as ee on c.experiencia_educativa_id = ee.experiencia_educativa_id" +
                    "join programaeducativo as pe on ee.programa_educativo_id = pe.programa_educativo_id" +
                    "join dependencia as dp on pe.dependencia_id = dp.dependencia_id" +
                    "join areaacademica as aa on dp.area_academica_id = aa.area_academica_id" +
                    "where c.colaboracion_id=? AND c.Estado='Concluida'";
            try{
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();
                while(resultado.next()){
                    switch (resultado.getString("NombreAreaAcademica")){

                        case "Área Académica de Humanidades":
                            contadorHumanidades += resultado.getInt("NumeroEstudiantes");
                            break;
                        case "Área Académica Técnica":
                            contadorTecnica += resultado.getInt("NumeroEstudiantes");
                            break;
                        case "Área Académica Económico-Administrativa":
                            contadorEconomicoAdministrativo += resultado.getInt("NumeroEstudiantes");
                            break;
                        case "Área Académica de Ciencias de la Salud":
                            contadorCienciasSalud += resultado.getInt("NumeroEstudiantes");
                            break;
                        case "Área Académica de Ciencias Biológicas y Agropecuarias":
                            contadorBiologicoAgropecuarias += resultado.getInt("NumeroEstudiantes");
                            break;
                        case "Área Académica de Artes":
                            contadorArtes += resultado.getInt("NumeroEstudiantes");
                            break;
                        case "Área Académica de DGRI":
                            contadorDGRI += resultado.getInt("NumeroEstudiantes");
                            break;
                    }
                }
                conexionBD.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return obtenerTotalAreaAcademica(contadorHumanidades, contadorTecnica, contadorEconomicoAdministrativo, contadorCienciasSalud, contadorBiologicoAgropecuarias, contadorArtes, contadorDGRI, areaAcademica);
    }

    private static ArrayList<Integer> obtenerTotalAreaAcademica(int contadorHumanidades, int contadorTecnica, int contadorEconomicoAdministrativo, int contadorCienciasSalud, int contadorBiologicoAgropecuarias, int contadorArtes, int contadorDGRI, ArrayList<Integer> areaAcademica) {
        int totalProfesorAreaAcademica;
        totalProfesorAreaAcademica= (contadorDGRI+contadorArtes+contadorBiologicoAgropecuarias+contadorCienciasSalud+contadorHumanidades+contadorEconomicoAdministrativo+contadorTecnica);
        areaAcademica.add(contadorHumanidades);
        areaAcademica.add(contadorTecnica);
        areaAcademica.add(contadorEconomicoAdministrativo);
        areaAcademica.add(contadorCienciasSalud);
        areaAcademica.add(contadorBiologicoAgropecuarias);
        areaAcademica.add(contadorArtes);
        areaAcademica.add(contadorDGRI);
        areaAcademica.add(totalProfesorAreaAcademica);
        return areaAcademica;
    }


}
