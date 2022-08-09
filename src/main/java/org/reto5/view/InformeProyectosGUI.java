package org.reto5.view;

import org.reto5.controller.ReportesController;
import org.reto5.model.vo.ProyectosVo;
import org.reto5.util.TableUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class InformeProyectosGUI extends JFrame{

    private MenuGUI menuGUI;
    private InformeComprasGUI informeComprasGUI;
    private InformeLideresGUI informeLideresGUI;
    private ReportesController controller;
    private DefaultTableModel tableModel;
    private JTableHeader header;
    private TableUtil tableUtil;
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
    private JButton btnCompras;
    private JButton btnLideres;

    public InformeProyectosGUI() {
        controller = new ReportesController();
        setContentPane(mainPanel);
        setTitle("INFORME PROYECTOS");
        setSize(new Dimension(550, 600));
        setLocationRelativeTo(null);
        setLocationByPlatform(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        //Encabezados de la tabla
        tbProyectos.setFillsViewportHeight(true);
        String[] encabezados = new String[]{"ID Proyecto", "Constructora", "Habitaciones", "Ciudad"};
        tableModel = new DefaultTableModel(null, encabezados);
        tbProyectos.setModel(tableModel);
        header = tbProyectos.getTableHeader();
        header.setBackground(new Color(175,194,203));
        Font myFont = new Font("Roboto",Font.BOLD, 16);
        header.setFont(myFont);

        //Colores intercalados en filas
        tableUtil = new TableUtil();
        tableUtil.setColorUno(new Color(255,255,255));
        tableUtil.setColorDos(new Color(225,245,254));
        for (int i = 0; i < tbProyectos.getColumnCount(); i++){
            tbProyectos.getColumnModel().getColumn(i).setCellRenderer(tableUtil);
        }

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
                menuGUI = new MenuGUI();
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

        btnCompras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                informeComprasGUI = new InformeComprasGUI();
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

}
