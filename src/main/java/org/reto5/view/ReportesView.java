package org.reto5.view;



import org.reto5.controller.ReportesController;
import org.reto5.model.vo.ComprasVo;
import org.reto5.model.vo.ProyectosVo;
import org.reto5.model.vo.LiderVo;

import java.sql.SQLException;
import java.util.List;

public class ReportesView {

    private final ReportesController controller;

    public ReportesView() {
        this.controller = new ReportesController();
    }

    private String repitaCaracter(Character caracter, Integer veces) {
        String respuesta = "";
        for (int i = 0; i < veces; i++) {
            respuesta += caracter;
        }
        return respuesta;
    }

    public void listarlideres() throws SQLException {
        System.out.println(repitaCaracter('=', 13) + " LISTADO DE LIDERES "
                + repitaCaracter('=', 13));

        System.out.println(String.format("%3s %-10s %-10s %-25s",
                "ID", "NOMBRE", "APELLIDO", "CIUDAD DE RESIDENCIA"));
        System.out.println(repitaCaracter('-', 46));

        List<LiderVo> listalideres = controller.listarlideres();
        for (LiderVo lider : listalideres) {
            System.out.println(lider);
        }

    }

    public void proyectosClasificacionYCiudad(String clasificacion, String[] ciudades) throws SQLException {
        System.out.println(repitaCaracter('=', 15) + " PROYECTOS POR CLASIFICACIÓN Y CIUDAD "
                + repitaCaracter('=', 15));

        if (clasificacion != null && ciudades != null) {
            System.out.println(String.format("%3s %15s %25s %15s", "ID", "CONSTRUCTORA", "NÚMERO DE HAB.", "CIUDAD"));
            System.out.println(repitaCaracter('-', 68));

            List<ProyectosVo> listaDeudas = controller.listarProyectos(clasificacion, ciudades);
            for (ProyectosVo proyecto : listaDeudas) {
                System.out.println(proyecto);
            }
        }
    }

    public void comprasSegunProveedorYCiudad(String proveedor, String ciudad) throws SQLException {
        System.out.println(repitaCaracter('=', 9) + " COMPRAS SEGUN PROVEEDOR Y CIUDAD "
                + repitaCaracter('=', 9));

        if (proveedor != null && ciudad != null) {
            System.out.println(String.format("%3s %15s %28s", "ID", "CONSTRUCTORA", "BANCO VINCULADO"));
            System.out.println(repitaCaracter('-', 52));

            List<ComprasVo> listaCompras = controller.listarCompras(proveedor, ciudad);
            for (ComprasVo proyecto : listaCompras) {
                System.out.println(proyecto);
            }
        }
    }
}
