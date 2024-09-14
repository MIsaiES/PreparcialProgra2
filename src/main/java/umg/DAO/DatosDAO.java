package umg.DAO;

import umg.Model.Datos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static umg.DB.DatabaseConnection.getConnection;

public class DatosDAO {
    public void agregarDatos(Datos datos) throws SQLException {
        String sql = "INSERT INTO tb_datos (nombre, apellido, departamento, fecha_nacimiento) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, datos.getNombre());
            statement.setString(2, datos.getApellido());
            statement.setString(3, datos.getDepartamento());
            statement.setDate(4, datos.getFechaNacimiento());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Datos> obtenerTodosLosDatos() {
        List<Datos> listaDatos = new ArrayList<>();
        String sql = "SELECT * FROM tb_datos";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int codigo = resultSet.getInt("codigo");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String departamento = resultSet.getString("departamento");
                Date fechaNacimiento = resultSet.getDate("fecha_nacimiento");
                Datos datos = new Datos(codigo, nombre, apellido, departamento, fechaNacimiento);
                listaDatos.add(datos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaDatos;
    }

    public void eliminarDatos(int codigo) {
        String sql = "DELETE FROM tb_datos WHERE codigo = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, codigo);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
