package org.reto5.view;

import org.reto5.controller.ReportesController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportesGUI extends JFrame{

    private ReporteLideresGUI reporteLideresGUI;
    private ReporteProyectosGUI reporteProyectosGUI;
    private ReporteComprasGUI reporteComprasGUI;
    private JButton btnProyectos;
    private JButton btnLideres;
    private JButton btnCompras;
    private JPanel mainPanel;
    private JLabel lbOpcion;

    public ReportesGUI() {
        setContentPane(mainPanel);
        setTitle("REPORTES");
        setSize(new Dimension(250, 300));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        btnProyectos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reporteProyectosGUI = new ReporteProyectosGUI();
                dispose(); //Con esto elimino la ventana previa
            }
        });
        btnLideres.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reporteLideresGUI = new ReporteLideresGUI();
                dispose();
            }
        });
        btnCompras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reporteComprasGUI = new ReporteComprasGUI();
                dispose();
            }
        });
    }

}
