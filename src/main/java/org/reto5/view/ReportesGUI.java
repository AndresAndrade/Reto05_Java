package org.reto5.view;

import org.reto5.controller.ReportesController;

import javax.swing.*;
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
        /*this.reporteComprasGUI = new ReporteComprasGUI();
        this.reporteLideresGUI = new ReporteLideresGUI();*/

        setContentPane(mainPanel);
        setTitle("REPORTES");
        setSize(550, 550);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        btnProyectos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reporteProyectosGUI = new ReporteProyectosGUI();
                reporteProyectosGUI.setVisible(true);
                dispose(); //Con esto elimino la ventana previa
            }
        });
        btnLideres.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reporteLideresGUI = new ReporteLideresGUI();
                reporteLideresGUI.setVisible(true);
                dispose();
            }
        });
        btnCompras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reporteComprasGUI = new ReporteComprasGUI();
                reporteComprasGUI.setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        ReportesGUI myReportesGUI = new ReportesGUI();
    }
}
