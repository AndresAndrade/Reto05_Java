package org.reto5.view;

import org.reto5.controller.ReportesController;
import org.reto5.model.vo.LiderVo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class ReporteLideresGUI extends JFrame{
    private ReportesGUI reportesGUI;
    private DefaultTableModel tableModel;
    private ReportesController controller;
    private JComboBox cbCargo;
    private JComboBox cbCiudad;
    private JTable tbLideres;
    private JPanel mainPanel;
    private JLabel lbCargo;
    private JLabel lbCiudad;
    private JButton btnMostrar;
    private JButton btnRegresar;

    public ReporteLideresGUI() {

        controller = new ReportesController();
        setContentPane(mainPanel);
        setTitle("REPORTE LIDERES");
        setSize(550, 550);
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        //Encabezados de la tabla
        String[] encabezados = {"ID Lider", "Nombre", "Apellido", "Ciudad de Residencia"};
        tableModel = new DefaultTableModel(null, encabezados);
        tbLideres.setModel(tableModel);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbCargo.getSelectedIndex() == 0) {
                    try {
                        mostrarDatosCiudad(cbCiudad.getSelectedItem().toString());
                    } catch (SQLException ex) {
                        System.err.println("Error SQl " + ex.getMessage());;
                    }
                } else if (cbCiudad.getSelectedIndex() == 0) {
                    try {
                        mostrarDatos(cbCargo.getSelectedItem().toString());
                    } catch (SQLException ex) {
                        System.err.println("Error SQl " + ex.getMessage());;
                    }
                } else {
                    try {
                        mostrarDatos(cbCargo.getSelectedItem().toString(), cbCiudad.getSelectedItem().toString());
                    } catch (SQLException ex) {
                        System.err.println("Error SQl " + ex.getMessage());;
                    }
                }
            }
        };
        cbCargo.addActionListener(listener);
        cbCiudad.addActionListener(listener);

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
                cbCiudad.setSelectedIndex(0);
                cbCargo.setSelectedIndex(0);
                try {
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

    public void mostrarDatos(String cargo) throws SQLException {
        actualizarTabla();
        List<LiderVo> listaLideres = controller.listarlideres(cargo);
        for (LiderVo lider : listaLideres) {
            Object[] objeto = {lider.getIdLider(), lider.getNombre(), lider.getPrimerApellido(), lider.getCiudadResidencia()};
            tableModel.addRow(objeto);
        }
    }

    public void mostrarDatos(String cargo, String ciudad) throws SQLException {
        actualizarTabla();
        List<LiderVo> listaLideres = controller.listarlideres(cargo, ciudad);
        for (LiderVo lider : listaLideres) {
            Object[] objeto = {lider.getIdLider(), lider.getNombre(), lider.getPrimerApellido(), lider.getCiudadResidencia()};
            tableModel.addRow(objeto);
        }
    }

    public void mostrarDatosCiudad(String ciudad) throws SQLException {
        actualizarTabla();
        List<LiderVo> listaLideres = controller.listarLideresCiudad(ciudad);
        for (LiderVo lider : listaLideres) {
            Object[] objeto = {lider.getIdLider(), lider.getNombre(), lider.getPrimerApellido(), lider.getCiudadResidencia()};
            tableModel.addRow(objeto);
        }
    }

    public void mostrarDatos() throws SQLException {
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
        List<LiderVo> listaLideres = controller.listarlideres();
        for (LiderVo lider : listaLideres) {
            Object[] objeto = {lider.getIdLider(), lider.getNombre(), lider.getPrimerApellido(), lider.getCiudadResidencia()};
            tableModel.addRow(objeto);
        }
    }

    public static void main(String[] args) {
        ReporteLideresGUI myReporteLideres = new ReporteLideresGUI();
    }
}
