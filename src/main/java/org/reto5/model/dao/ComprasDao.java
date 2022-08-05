package org.reto5.model.dao;



import org.reto5.model.vo.ComprasVo;
import org.reto5.util.JDBCUtilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComprasDao {
    private final String queryCompras = "SELECT c.ID_Compra, p.Constructora, p.Banco_Vinculado " +
            "FROM Compra c JOIN Proyecto p ON c.ID_Proyecto = p.ID_Proyecto " +
            "WHERE c.Proveedor = ? AND p.Ciudad = ?";

    public List<ComprasVo> buscarCompras(String proveedor, String ciudad) throws SQLException {
        List<ComprasVo> listaCompras = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            connection = JDBCUtilities.getConnection();
            pstm = connection.prepareStatement(queryCompras);
            pstm.setString(1, proveedor);
            pstm.setString(2, ciudad);
            rs = pstm.executeQuery();

            while (rs.next()) {
                ComprasVo compras = new ComprasVo();
                compras.setIdCompra(rs.getInt("ID_Compra"));
                compras.setConstructora(rs.getString("Constructora"));
                compras.setBancoVinculado(rs.getString("Banco_Vinculado"));
                listaCompras.add(compras);
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
        return listaCompras;
    }
}

