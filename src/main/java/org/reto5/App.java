package org.reto5;


import org.reto5.view.ReportesGUI;
import org.reto5.view.ReportesView;

import java.sql.SQLException;

public class App {

    public static void main(String[] args) throws SQLException {
        //ReportesView reportesView = new ReportesView();

        ReportesGUI reportesGUI = new ReportesGUI();

        //1. Primer Informe
        //reportesView.listarlideres();

        //2. Segundo Informe
        /*String clasificacion = "Casa Campestre";
        String[] ciudades = {"Santa Marta", "Cartagena", "Barranquilla"};
        reportesView.proyectosClasificacionYCiudad(clasificacion, ciudades);*/

        //3. Tercer Informe
        /*String proveedor = "Homecenter";
        String ciudad = "Salento";
        reportesView.comprasSegunProveedorYCiudad(proveedor, ciudad);*/

    }
}
