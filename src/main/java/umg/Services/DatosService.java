package umg.Services;

import umg.DAO.DatosDAO;
import umg.Model.Datos;

import java.sql.SQLException;
import java.util.List;

public class DatosService {
    private DatosDAO datosDAO;
    public DatosService() {
        this.datosDAO = new DatosDAO();
    }

    public void agregarDatos(Datos datos) {
        try {
            datosDAO.agregarDatos(datos);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Datos> obtenerTodosLosDatos() {
        return datosDAO.obtenerTodosLosDatos();
    }

    public void eliminarDatos(int codigo) {
        datosDAO.eliminarDatos(codigo);
    }
}
