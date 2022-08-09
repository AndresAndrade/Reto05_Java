package org.reto5.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGUI extends JFrame{

    private InformeLideresGUI informeLideresGUI;
    private InformeProyectosGUI informeProyectosGUI;
    private InformeComprasGUI informeComprasGUI;
    private JButton btnProyectos;
    private JButton btnLideres;
    private JButton btnCompras;
    private JPanel mainPanel;
    private JLabel lbOpcion;

    public MenuGUI() {
        setContentPane(mainPanel);
        setTitle("MENU INFORMES");
        setSize(new Dimension(250, 300));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        btnProyectos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                informeProyectosGUI = new InformeProyectosGUI();
                dispose(); //Con esto elimino la ventana previa
            }
        });
        btnLideres.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                informeLideresGUI = new InformeLideresGUI();
                dispose();
            }
        });
        btnCompras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                informeComprasGUI = new InformeComprasGUI();
                dispose();
            }
        });
    }

}
