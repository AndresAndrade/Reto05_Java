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

public class InformeComprasGUI extends JFrame {

    private DefaultTableModel tableModel;
    private ReportesController controller;
    private MenuGUI menuGUI;
    private InformeLideresGUI informeLideresGUI;
    private InformeProyectosGUI informeProyectosGUI;
    private JTableHeader header;
    private TableUtil tableUtil;
    private JMenuBar menuBar;
    private JMenu mInformes;
    private JMenuItem mInicio;
    private JMenuItem mProyectos;
    private JMenuItem mLideres;
    private JTable tbCompras;
    private JComboBox cbProveedor;
    private JComboBox cbCiudad;
    private JPanel mainPanel;
    private JLabel lbProveedor;
    private JLabel lbCiudad;
    private JButton btnMostrar;
    private JButton btnRegresar;
    private JScrollPane scroll;
    private JButton btnProyectos;
    private JButton btnLideres;

    public InformeComprasGUI() {
        controller = new ReportesController();
        setContentPane(mainPanel);
        setTitle("INFORME COMPRAS");
        setSize(new Dimension(450, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        //Encabezados de la tabla
        tbCompras.setFillsViewportHeight(true);
        String[] encabezados = new String[]{"ID Compra", "Constructora", "Banco Vinculado"};
        tableModel = new DefaultTableModel(null, encabezados);
        tbCompras.setModel(tableModel);
        header = tbCompras.getTableHeader();
        Color myColor = new Color(182,194,183);
        header.setBackground(myColor);
        Font myFont = new Font("Roboto",Font.BOLD, 16);
        header.setFont(myFont);

        //Colores intercalados en filas
        tableUtil = new TableUtil();
        tableUtil.setColorUno(new Color(255,255,255));
        tableUtil.setColorDos(new Color(232,245,233));
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
                menuGUI = new MenuGUI();
                menuGUI.setVisible(true);
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

        btnProyectos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                informeProyectosGUI = new InformeProyectosGUI();
                dispose();
            }
        });

        btnLideres.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                informeLideresGUI = new InformeLideresGUI();
                dispose();
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

}
