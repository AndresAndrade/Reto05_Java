package org.reto5.util;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TableUtil extends DefaultTableCellRenderer {

    private Color colorUno;
    private Color colorDos;
    private Color colorSel;

    public Color getColorDos() {
        return colorDos;
    }

    public void setColorDos(Color colorDos) {
        this.colorDos = colorDos;
    }

    public Color getColorUno() {
        return colorUno;
    }

    public void setColorUno(Color colorUno) {
        this.colorUno = colorUno;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (!isSelected){
            if (evaluarPar(row)) {
                this.setBackground(colorUno);
            } else {
                this.setBackground(colorDos);
            }
        }

        return this;
    }

    public boolean evaluarPar(int num) {
        return num % 2 == 0;
    }

}
