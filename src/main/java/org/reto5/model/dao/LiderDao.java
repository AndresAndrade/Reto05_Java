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
            "FROM Lider ORDER BY Ciudad_Residencia;";

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
