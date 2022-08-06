package org.reto5.view;

import org.reto5.controller.ReportesController;
import org.reto5.model.vo.ProyectosVo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ReporteProyectosGUI extends JFrame{

    private ReportesGUI reportesGUI;
    private ReportesController controller;
    private DefaultTableModel tableModel;
    private JComboBox cbClasificacion;
    private JComboBox cbCiudad1;
    private JComboBox cbCiudad2;
    private JComboBox cbCiudad3;
    private JTable tbProyectos;
    private JScrollPane scrollPane1;
    private JPanel mainPanel;
    private JLabel lbClasificacion;
    private JLabel lbCiudad1;
    private JLabel lbCiudad2;
    private JLabel lbCoidad3;
    private JButton btnMostrar;
    private JButton btnRegresar;

    public ReporteProyectosGUI() {
        controller = new ReportesController();
        setContentPane(mainPanel);
        setTitle("REPORTE PROYECTOS");
        setSize(600,550);
        setLocationByPlatform(false);
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        //Encabezados de la tabla
        String[] encabezados = {"ID Proyecto", "Constructora", "Número de Habitaciones", "Ciudad"};
        tableModel = new DefaultTableModel(null, encabezados);
        tbProyectos.setModel(tableModel);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] ciudades = {cbCiudad1.getSelectedItem().toString(),
                        cbCiudad2.getSelectedItem().toString(),
                        cbCiudad3.getSelectedItem().toString()};

                if (Arrays.equals(ciudades,
                        new String[]{cbCiudad1.getItemAt(0).toString(),
                                cbCiudad2.getItemAt(0).toString(),
                                cbCiudad3.getItemAt(0).toString()})) {
                    try {
                        mostrarDatos(cbClasificacion.getSelectedItem().toString());
                    } catch (SQLException ex) {
                        System.err.println("Error SQl " + ex.getMessage());;
                    }
                } else if (cbClasificacion.getSelectedIndex() == 0) {
                    try {
                        mostrarDatos(ciudades);
                    } catch (SQLException ex) {
                        System.err.println("Error SQl " + ex.getMessage());;
                    }
                } else {
                    try {
                        mostrarDatos(cbClasificacion.getSelectedItem().toString(), ciudades);
                    } catch (SQLException ex) {
                        System.err.println("Error SQl " + ex.getMessage());;
                    }
                }
            }
        };
        cbClasificacion.addActionListener(listener);
        cbCiudad1.addActionListener(listener);
        cbCiudad2.addActionListener(listener);
        cbCiudad3.addActionListener(listener);

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
                    cbClasificacion.setSelectedIndex(0);
                    cbCiudad1.setSelectedIndex(0);
                    cbCiudad2.setSelectedIndex(0);
                    cbCiudad3.setSelectedIndex(0);
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

    public void mostrarDatos(String clasificacion, String[] ciudades) throws SQLException {
        actualizarTabla();
        List<ProyectosVo> listaProyectos = controller.listarProyectos(clasificacion, ciudades);
        for (ProyectosVo proyecto : listaProyectos) {
            Object[] objeto = {proyecto.getIdProyecto(), proyecto.getConstructora(), proyecto.getNumeroHabitaciones(), proyecto.getCiudad()};
            tableModel.addRow(objeto);
        }
    }

    public void mostrarDatos(String clasificacion) throws SQLException {
        actualizarTabla();
        List<ProyectosVo> listaProyectos = controller.listarProyectos(clasificacion);
        for (ProyectosVo proyecto : listaProyectos) {
            Object[] objeto = {proyecto.getIdProyecto(), proyecto.getConstructora(), proyecto.getNumeroHabitaciones(), proyecto.getCiudad()};
            tableModel.addRow(objeto);
        }
    }

    public void mostrarDatos(String[] ciudades) throws SQLException {
        actualizarTabla();
        List<ProyectosVo> listaProyectos = controller.listarProyectos(ciudades);
        for (ProyectosVo proyecto : listaProyectos) {
            Object[] objeto = {proyecto.getIdProyecto(), proyecto.getConstructora(), proyecto.getNumeroHabitaciones(), proyecto.getCiudad()};
            tableModel.addRow(objeto);
        }
    }

    public void mostrarDatos() throws SQLException {
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
        List<ProyectosVo> listaProyectos = controller.listarProyectos();
        for (ProyectosVo proyecto : listaProyectos) {
            Object[] objeto = {proyecto.getIdProyecto(), proyecto.getConstructora(), proyecto.getNumeroHabitaciones(), proyecto.getCiudad()};
            tableModel.addRow(objeto);
        }
    }

    public static void main(String[] args) {
        ReporteProyectosGUI myProyectosGUI = new ReporteProyectosGUI();
    }
}
