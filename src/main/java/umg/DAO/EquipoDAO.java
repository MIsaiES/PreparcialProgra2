package umg.DAO;

import umg.Model.Equipo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static umg.DB.DatabaseConnection.getConnection;

public class EquipoDAO {

    Connection connection;

    {
        try {
            connection = getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void agregarEquipo(Equipo equipo) throws SQLException {
        String sql = "INSERT INTO equipos_champions (nombre, pais, ciudad, estadio, fundacion, entrenador, web_oficial, facebook, twitter, instagram, patrocinador_principal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, equipo.getNombre());
            pstmt.setString(2, equipo.getPais());
            pstmt.setString(3, equipo.getCiudad());
            pstmt.setString(4, equipo.getEstadio());
            pstmt.setInt(5, equipo.getFundacion());
            pstmt.setString(6, equipo.getEntrenador());
            pstmt.setString(7, equipo.getWebOficial());
            pstmt.setString(8, equipo.getFacebook());
            pstmt.setString(9, equipo.getTwitter());
            pstmt.setString(10, equipo.getInstagram());
            pstmt.setString(11, equipo.getPatrocinadorPrincipal());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                equipo.setIdEquipo(rs.getInt(1));
            }
        }
    }

    public List<Equipo> obtenerEquipos() throws SQLException {
        List<Equipo> equipos = new ArrayList<>();
        String sql = "SELECT * FROM equipos_champions";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Equipo equipo = new Equipo(
                        rs.getInt("id_equipo"),
                        rs.getString("nombre"),
                        rs.getString("pais"),
                        rs.getString("ciudad"),
                        rs.getString("estadio"),
                        rs.getInt("fundacion"),
                        rs.getString("entrenador"),
                        rs.getString("web_oficial"),
                        rs.getString("facebook"),
                        rs.getString("twitter"),
                        rs.getString("instagram"),
                        rs.getString("patrocinador_principal"),
                        rs.getTimestamp("creado_en")
                );
                equipos.add(equipo);
            }
        }
        return equipos;
    }

    public void actualizarEquipo(Equipo equipo) throws SQLException {
        String sql = "UPDATE equipos_champions SET nombre = ?, pais = ?, ciudad = ?, estadio = ?, fundacion = ?, entrenador = ?, web_oficial = ?, facebook = ?, twitter = ?, instagram = ?, patrocinador_principal = ? WHERE id_equipo = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, equipo.getNombre());
            pstmt.setString(2, equipo.getPais());
            pstmt.setString(3, equipo.getCiudad());
            pstmt.setString(4, equipo.getEstadio());
            pstmt.setInt(5, equipo.getFundacion());
            pstmt.setString(6, equipo.getEntrenador());
            pstmt.setString(7, equipo.getWebOficial());
            pstmt.setString(8, equipo.getFacebook());
            pstmt.setString(9, equipo.getTwitter());
            pstmt.setString(10, equipo.getInstagram());
            pstmt.setString(11, equipo.getPatrocinadorPrincipal());
            pstmt.setInt(12, equipo.getIdEquipo());
            pstmt.executeUpdate();
        }
    }

    public void eliminarEquipo(int idEquipo) throws SQLException {
        String sql = "DELETE FROM equipos_champions WHERE id_equipo = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idEquipo);
            pstmt.executeUpdate();
        }
    }
}
