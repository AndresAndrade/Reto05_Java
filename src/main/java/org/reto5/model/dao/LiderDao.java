package org.reto5.model.dao;


import org.reto5.model.vo.LiderVo;
import org.reto5.util.JDBCUtilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LiderDao {

    private final String queryLideres = "SELECT ID_Lider, Nombre, Primer_Apellido, Ciudad_Residencia " +
            "FROM Lider ORDER BY Ciudad_Residencia";

    private final String queryLideresCiudad = "SELECT ID_Lider, Nombre, Primer_Apellido, Ciudad_Residencia " +
            "FROM Lider WHERE Ciudad_Residencia = ? ORDER BY Ciudad_Residencia";

    private final String queryLideresCargo = "SELECT ID_Lider, Nombre, Primer_Apellido, Ciudad_Residencia " +
            "FROM Lider WHERE Cargo = ? ORDER BY Ciudad_Residencia";

    private final String queryLideresCargoCiudad = "SELECT ID_Lider, Nombre, Primer_Apellido, Ciudad_Residencia " +
            "FROM Lider WHERE Cargo = ? AND Ciudad_Residencia = ? ORDER BY Ciudad_Residencia";

    public List<LiderVo> listarLideres(String cargo, String ciudad) throws SQLException {
        List<LiderVo> listaLideres = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            connection = JDBCUtilities.getConnection();
            pstm = connection.prepareStatement(queryLideresCargoCiudad);
            pstm.setString(1, cargo);
            pstm.setString(2, ciudad);
            rs = pstm.executeQuery();

            while (rs.next()) {
                LiderVo liderVo = new LiderVo();
                liderVo.setIdLider(rs.getInt("ID_lider"));
                liderVo.setNombre(rs.getString("Nombre"));
                liderVo.setPrimerApellido(rs.getString("Primer_Apellido"));
                liderVo.setCiudadResidencia(rs.getString("Ciudad_Residencia"));
                listaLideres.add(liderVo);
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
        return listaLideres;
    }

    public List<LiderVo> listarLideresCiudad(String ciudad) throws SQLException {
        List<LiderVo> listaLideres = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            connection = JDBCUtilities.getConnection();
            pstm = connection.prepareStatement(queryLideresCiudad);
            pstm.setString(1, ciudad);
            rs = pstm.executeQuery();

            while (rs.next()) {
                LiderVo liderVo = new LiderVo();
                liderVo.setIdLider(rs.getInt("ID_lider"));
                liderVo.setNombre(rs.getString("Nombre"));
                liderVo.setPrimerApellido(rs.getString("Primer_Apellido"));
                liderVo.setCiudadResidencia(rs.getString("Ciudad_Residencia"));
                listaLideres.add(liderVo);
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
        return listaLideres;
    }

    public List<LiderVo> listarLideres(String cargo) throws SQLException {
        List<LiderVo> listaLideres = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            connection = JDBCUtilities.getConnection();
            pstm = connection.prepareStatement(queryLideresCargo);
            pstm.setString(1, cargo);
            rs = pstm.executeQuery();

            while (rs.next()) {
                LiderVo liderVo = new LiderVo();
                liderVo.setIdLider(rs.getInt("ID_lider"));
                liderVo.setNombre(rs.getString("Nombre"));
                liderVo.setPrimerApellido(rs.getString("Primer_Apellido"));
                liderVo.setCiudadResidencia(rs.getString("Ciudad_Residencia"));
                listaLideres.add(liderVo);
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
        return listaLideres;
    }

    public List<LiderVo> listarLideres() throws SQLException {
        List<LiderVo> listaLideres = new ArrayList<>();

        try (Connection connection = JDBCUtilities.getConnection();
             PreparedStatement pstm = connection.prepareStatement(queryLideres);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                LiderVo liderVo = new LiderVo();
                liderVo.setIdLider(rs.getInt("ID_lider"));
                liderVo.setNombre(rs.getString("Nombre"));
                liderVo.setPrimerApellido(rs.getString("Primer_Apellido"));
                liderVo.setCiudadResidencia(rs.getString("Ciudad_Residencia"));
                listaLideres.add(liderVo);
            }
        }
        return listaLideres;
    }

}
