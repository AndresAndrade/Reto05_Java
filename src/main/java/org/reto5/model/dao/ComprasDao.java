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
    private final String queryComprasProveedorCiudad = "SELECT c.ID_Compra, p.Constructora, p.Banco_Vinculado " +
            "FROM Compra c JOIN Proyecto p ON c.ID_Proyecto = p.ID_Proyecto " +
            "WHERE c.Proveedor = ? AND p.Ciudad = ?";

    private final String queryComprasProveedor = "SELECT c.ID_Compra, p.Constructora, p.Banco_Vinculado " +
            "FROM Compra c JOIN Proyecto p ON c.ID_Proyecto = p.ID_Proyecto " +
            "WHERE c.Proveedor = ?";

    private final String queryComprasCiudad = "SELECT c.ID_Compra, p.Constructora, p.Banco_Vinculado " +
            "FROM Compra c JOIN Proyecto p ON c.ID_Proyecto = p.ID_Proyecto " +
            "WHERE p.Ciudad = ?";

    private final String queryCompras = "SELECT c.ID_Compra, p.Constructora, p.Banco_Vinculado " +
            "FROM Compra c JOIN Proyecto p ON c.ID_Proyecto = p.ID_Proyecto";

    public List<ComprasVo> buscarCompras(String proveedor, String ciudad) throws SQLException {
        List<ComprasVo> listaCompras = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            connection = JDBCUtilities.getConnection();
            pstm = connection.prepareStatement(queryComprasProveedorCiudad);
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

    public List<ComprasVo> buscarCompras(String proveedor) throws SQLException {
        List<ComprasVo> listaCompras = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            connection = JDBCUtilities.getConnection();
            pstm = connection.prepareStatement(queryComprasProveedor);
            pstm.setString(1, proveedor);
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

    public List<ComprasVo> buscarComprasCiudad(String ciudad) throws SQLException {
        List<ComprasVo> listaCompras = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            connection = JDBCUtilities.getConnection();
            pstm = connection.prepareStatement(queryComprasCiudad);
            pstm.setString(1, ciudad);
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

    public List<ComprasVo> buscarCompras() throws SQLException {
        List<ComprasVo> listaCompras = new ArrayList<>();

        try (Connection connection = JDBCUtilities.getConnection();
             PreparedStatement pstm = connection.prepareStatement(queryCompras);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                ComprasVo compras = new ComprasVo();
                compras.setIdCompra(rs.getInt("ID_Compra"));
                compras.setConstructora(rs.getString("Constructora"));
                compras.setBancoVinculado(rs.getString("Banco_Vinculado"));
                listaCompras.add(compras);
            }
        }
        return listaCompras;
    }

}

