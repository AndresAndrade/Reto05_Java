package org.reto5.view;

import org.reto5.controller.ReportesController;
import org.reto5.model.vo.ComprasVo;
import org.reto5.util.TableUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class ReporteComprasGUI extends JFrame {

    private DefaultTableModel tableModel;
    private ReportesController controller;
    private ReportesGUI reportesGUI;
    private JTableHeader header;
    private TableUtil tableUtil;
    private JTable tbCompras;
    private JComboBox cbProveedor;
    private JComboBox cbCiudad;
    private JPanel mainPanel;
    private JLabel lbProveedor;
    private JLabel lbCiudad;
    private JButton btnMostrar;
    private JButton btnRegresar;
    private JScrollPane scroll;

    public ReporteComprasGUI() {
        controller = new ReportesController();
        setContentPane(mainPanel);
        setTitle("REPORTE COMPRAS");
        setSize(new Dimension(550, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        //Encabezados de la tabla
        tbCompras.setFillsViewportHeight(true);
        String[] encabezados = new String[]{"ID Compra", "Constructora", "Banco Vinculado"};
        tableModel = new DefaultTableModel(null, encabezados);
        tbCompras.setModel(tableModel);
        header = tbCompras.getTableHeader();
        Color myColor = new Color(117, 164, 120);
        header.setBackground(myColor);
        Font myFont = new Font("Roboto",Font.BOLD, 16);
        header.setFont(myFont);

        //Colores intercalados en filas
        tableUtil = new TableUtil();
        tableUtil.setColorUno(new Color(215,255,217));
        tableUtil.setColorDos(new Color(165,214,167));
        for (int i = 0; i < tbCompras.getColumnCount(); i++){
            tbCompras.getColumnModel().getColumn(i).setCellRenderer(tableUtil);
        }

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (cbProveedor.getSelectedIndex() == 0) {
                    try {
                        mostrarDatosCiudad(cbCiudad.getSelectedItem().toString());
                    } catch (SQLException ex) {
                        System.out.println("Error SQl " + ex.getMessage());;
                    }
                } else if (cbCiudad.getSelectedIndex() == 0) {
                    try {
                        mostrarDatos(cbProveedor.getSelectedItem().toString());
                    } catch (SQLException ex) {
                        System.out.println("Error SQL " + ex.getMessage());;
                    }
                } else {
                    try {
                        mostrarDatos(cbProveedor.getSelectedItem().toString(), cbCiudad.getSelectedItem().toString());
                    } catch (SQLException ex) {
                        System.out.println("Error SQL " + ex.getMessage());;
                    }
                }

            }
        };
        cbCiudad.addActionListener(listener);
        cbProveedor.addActionListener(listener);

        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reportesGUI = new ReportesGUI();
                reportesGUI.setVisible(true);
                dispose();
            }
        });

        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cbCiudad.setSelectedIndex(0);
                    cbProveedor.setSelectedIndex(0);
                    mostrarDatos();
                } catch (SQLException ex) {
                    System.err.println("Error SQl " + ex.getMessage());;
                }
            }
        });

        try {
            mostrarDatos();
        } catch (SQLException ex) {
            System.err.println("Error SQl " + ex.getMessage());;
        }
    }

    public void actualizarTabla() {
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
    }

    public void mostrarDatos(String proveedor, String ciudad) throws SQLException {
        actualizarTabla();
        List<ComprasVo> listaCompras = controller.listarCompras(proveedor, ciudad);
        for (ComprasVo compra : listaCompras) {
            Object[] objeto = {compra.getIdCompra(), compra.getConstructora(), compra.getBancoVinculado()};
            tableModel.addRow(objeto);
        }
    }

    public void mostrarDatos(String proveedor) throws SQLException {
        actualizarTabla();
        List<ComprasVo> listaCompras = controller.listarCompras(proveedor);
        for (ComprasVo compra : listaCompras) {
            Object[] objeto = {compra.getIdCompra(), compra.getConstructora(), compra.getBancoVinculado()};
            tableModel.addRow(objeto);
        }
    }

    public void mostrarDatosCiudad(String ciudad) throws SQLException {
        actualizarTabla();
        List<ComprasVo> listaCompras = controller.listarComprasCiudad(ciudad);
        for (ComprasVo compra : listaCompras) {
            Object[] objeto = {compra.getIdCompra(), compra.getConstructora(), compra.getBancoVinculado()};
            tableModel.addRow(objeto);
        }
    }

    public void mostrarDatos() throws SQLException {
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
        List<ComprasVo> listaCompras = controller.listarCompras();
        for (ComprasVo compra : listaCompras) {
            Object[] objeto = {compra.getIdCompra(), compra.getConstructora(), compra.getBancoVinculado()};
            tableModel.addRow(objeto);
        }
    }



    public static void main(String[] args) throws SQLException {
        ReporteComprasGUI myReportesComprasGUI = new ReporteComprasGUI();
    }

}
