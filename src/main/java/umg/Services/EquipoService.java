package umg.Services;

import umg.DAO.EquipoDAO;
import umg.Model.Equipo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EquipoService {
    private EquipoDAO equipoDAO;

    public EquipoService() {
        this.equipoDAO = new EquipoDAO();
    }

    public void agregarEquipo(Equipo equipo) throws SQLException {
        equipoDAO.agregarEquipo(equipo);
    }

    public List<Equipo> obtenerEquipos() throws SQLException {
        return equipoDAO.obtenerEquipos();
    }

    public void actualizarEquipo(Equipo equipo) throws SQLException {
        equipoDAO.actualizarEquipo(equipo);
    }

    public void eliminarEquipo(int idEquipo) throws SQLException {
        equipoDAO.eliminarEquipo(idEquipo);
    }
}
