package org.reto5.model.dao;



import org.reto5.model.vo.ProyectosVo;
import org.reto5.util.JDBCUtilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProyectosDao {

    private final String queryPyClasificacionCiudad = "SELECT ID_Proyecto, Constructora, Numero_Habitaciones, Ciudad " +
            "FROM Proyecto " +
            "WHERE Clasificacion = ? " +
            "AND Ciudad IN (?, ?, ?) " +
            "ORDER By Ciudad;";

    private final String queryProyectosCl = "SELECT ID_Proyecto, Constructora, Numero_Habitaciones, Ciudad " +
            "FROM Proyecto " +
            "WHERE Clasificacion = ? " +
            "ORDER By Ciudad;";

    private final String queryProyectosCiudad = "SELECT ID_Proyecto, Constructora, Numero_Habitaciones, Ciudad " +
            "FROM Proyecto " +
            "WHERE Ciudad IN (?, ?, ?) " +
            "ORDER By Ciudad;";

    private final String queryProyectos = "SELECT ID_Proyecto, Constructora, Numero_Habitaciones, Ciudad " +
            "FROM Proyecto ORDER By Ciudad;";

    public List<ProyectosVo> buscarProyectos(String clasificacion, String[] ciudades) throws SQLException {
        List<ProyectosVo> listaProyectos = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            connection = JDBCUtilities.getConnection();
            pstm = connection.prepareStatement(queryPyClasificacionCiudad);
            pstm.setString(1, clasificacion);
            pstm.setString(2, ciudades[0]);
            pstm.setString(3, ciudades[1]);
            pstm.setString(4, ciudades[2]);
            rs = pstm.executeQuery();

            while (rs.next()) {
                ProyectosVo proyecto = new ProyectosVo();
                proyecto.setIdProyecto(rs.getInt("ID_Proyecto"));
                proyecto.setConstructora(rs.getString("Constructora"));
                proyecto.setNumeroHabitaciones(rs.getInt("Numero_Habitaciones"));
                proyecto.setCiudad(rs.getString("Ciudad"));
                listaProyectos.add(proyecto);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
        return listaProyectos;
    }

    public List<ProyectosVo> buscarProyectos(String clasificacion) throws SQLException {
        List<ProyectosVo> listaProyectos = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            connection = JDBCUtilities.getConnection();
            pstm = connection.prepareStatement(queryProyectosCl);
            pstm.setString(1, clasificacion);
            rs = pstm.executeQuery();

            while (rs.next()) {
                ProyectosVo proyecto = new ProyectosVo();
                proyecto.setIdProyecto(rs.getInt("ID_Proyecto"));
                proyecto.setConstructora(rs.getString("Constructora"));
                proyecto.setNumeroHabitaciones(rs.getInt("Numero_Habitaciones"));
                proyecto.setCiudad(rs.getString("Ciudad"));
                listaProyectos.add(proyecto);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
        return listaProyectos;
    }

    public List<ProyectosVo> buscarProyectos(String[] ciudades) throws SQLException {
        List<ProyectosVo> listaProyectos = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            connection = JDBCUtilities.getConnection();
            pstm = connection.prepareStatement(queryProyectosCiudad);
            pstm.setString(1, ciudades[0]);
            pstm.setString(2, ciudades[1]);
            pstm.setString(3, ciudades[2]);
            rs = pstm.executeQuery();

            while (rs.next()) {
                ProyectosVo proyecto = new ProyectosVo();
                proyecto.setIdProyecto(rs.getInt("ID_Proyecto"));
                proyecto.setConstructora(rs.getString("Constructora"));
                proyecto.setNumeroHabitaciones(rs.getInt("Numero_Habitaciones"));
                proyecto.setCiudad(rs.getString("Ciudad"));
                listaProyectos.add(proyecto);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
        return listaProyectos;
    }

    public List<ProyectosVo> buscarProyectos() throws SQLException {
        List<ProyectosVo> listaProyectos = new ArrayList<>();

        try (Connection connection = JDBCUtilities.getConnection();
             PreparedStatement pstm = connection.prepareStatement(queryProyectos);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                ProyectosVo proyecto = new ProyectosVo();
                proyecto.setIdProyecto(rs.getInt("ID_Proyecto"));
                proyecto.setConstructora(rs.getString("Constructora"));
                proyecto.setNumeroHabitaciones(rs.getInt("Numero_Habitaciones"));
                proyecto.setCiudad(rs.getString("Ciudad"));
                listaProyectos.add(proyecto);
            }
        }
        return listaProyectos;
    }

}
