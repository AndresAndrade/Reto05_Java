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

    //Metodos correspondientes a ComprasDao
    public List<ComprasVo> listarCompras(String proveedor, String ciudad) throws SQLException {
        return comprasDao.buscarCompras(proveedor, ciudad);
    }

    public List<ComprasVo> listarCompras(String proveedor) throws SQLException {
        return comprasDao.buscarCompras(proveedor);
    }

    public List<ComprasVo> listarComprasCiudad(String ciudad) throws SQLException {
        return comprasDao.buscarComprasCiudad(ciudad);
    }

    public List<ComprasVo> listarCompras() throws SQLException {
        return comprasDao.buscarCompras();
    }

    //Metodos correspondientes a ProyectosDao
    public List<ProyectosVo> listarProyectos(String clasificacion, String[] ciudades) throws SQLException {
        return proyectosDao.buscarProyectos(clasificacion, ciudades);
    }

    public List<ProyectosVo> listarProyectos(String clasificacion) throws SQLException {
        return proyectosDao.buscarProyectos(clasificacion);
    }

    public List<ProyectosVo> listarProyectos(String[] ciudades) throws SQLException {
        return proyectosDao.buscarProyectos(ciudades);
    }

    public List<ProyectosVo> listarProyectos() throws SQLException {
        return proyectosDao.buscarProyectos();
    }

    //Metodos correspondientes a LiderDao
    public List<LiderVo> listarlideres() throws SQLException {
        return liderDao.listarLideres();
    }

    public List<LiderVo> listarlideres(String cargo) throws SQLException {
        return liderDao.listarLideres(cargo);
    }

    public List<LiderVo> listarLideresCiudad(String ciudad) throws SQLException {
        return liderDao.listarLideresCiudad(ciudad);
    }

    public List<LiderVo> listarlideres(String cargo, String ciudad) throws SQLException {
        return liderDao.listarLideres(cargo, ciudad);
    }
}

