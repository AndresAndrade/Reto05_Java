package org.reto5.view;

import org.reto5.controller.ReportesController;
import org.reto5.model.vo.ComprasVo;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class ReporteComprasGUI extends JFrame{

    DefaultTableModel tableModel;
    ReportesController controller;
    private JTable tbCompras;
    private JComboBox cbProveedor;
    private JComboBox cbCiudad;
    private JPanel mainPanel;
    private JLabel lbProveedor;
    private JLabel lbCiudad;

    public ReporteComprasGUI() {
        controller = new ReportesController();
        setContentPane(mainPanel);
        setTitle("SISTEMA");
        setSize(550,550);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        //Encabezados de la tabla
        String[] encabezados = {"ID Compra", "Constructora", "Banco Vinculado"};
        tableModel = new DefaultTableModel(null, encabezados);
        tbCompras.setModel(tableModel);


        cbProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mostrarDatos(cbProveedor.getSelectedItem().toString(), cbCiudad.getSelectedItem().toString());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        cbCiudad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mostrarDatos(cbProveedor.getSelectedItem().toString(), cbCiudad.getSelectedItem().toString());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void mostrarDatos(String proveedor, String ciudad) throws SQLException {
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
        List<ComprasVo> listaCompras = controller.listarCompras(proveedor, ciudad);
        for (ComprasVo compra : listaCompras) {
            Object[] objeto = {compra.getIdCompra(), compra.getConstructora(), compra.getBancoVinculado()};
            tableModel.addRow(objeto);
        }
    }

    public static void main(String[] args) throws SQLException {
        ReporteComprasGUI myReportesComprasGUI = new ReporteComprasGUI();
    }
}
