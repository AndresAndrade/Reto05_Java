package org.reto5.controller;



import org.reto5.model.dao.ComprasDao;
import org.reto5.model.dao.ProyectosDao;
import org.reto5.model.dao.LiderDao;
import org.reto5.model.vo.ComprasVo;
import org.reto5.model.vo.ProyectosVo;
import org.reto5.model.vo.LiderVo;

import java.sql.SQLException;
import java.util.List;

public class ReportesController {
    private final ComprasDao comprasDao;
    private final ProyectosDao proyectosDao;
    private final LiderDao liderDao;

    public ReportesController() {
        this.comprasDao = new ComprasDao();
        this.proyectosDao = new ProyectosDao();
        this.liderDao = new LiderDao();
    }

    public List<ComprasVo> listarCompras(String proveedor, String ciudad) throws SQLException {
        return comprasDao.buscarCompras(proveedor, ciudad);
    }

    public List<ProyectosVo> listarProyectos(String clasificacion, String[] ciudades) throws SQLException {
        return proyectosDao.buscarProyectos(clasificacion, ciudades);
    }

    public List<LiderVo> listarlideres() throws SQLException {
        return liderDao.listarLideres();
    }
}

