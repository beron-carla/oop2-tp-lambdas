package ejercicios.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Usuarios {

    public static final String INSERT = "INSERT INTO usuarios (nombre, email) VALUES (?, ?)";
    public static final String UPDATE_USUARIOS_SET_EMAIL = "UPDATE usuarios SET email = ? WHERE id = ?";
    private final String jdbcUrl;

    public Usuarios(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public void insertar(String nombre, String email) {
        gestionarTransaccion(
                "INSERT INTO usuarios (nombre, email) VALUES (?, ?)",
                statement -> {
                    statement.setString(1, nombre);
                    statement.setString(2, email);
                }
        );
    }

    public void actualizarEmail(int id, String nuevoEmail) {
        gestionarTransaccion(UPDATE_USUARIOS_SET_EMAIL, statement -> {
            statement.setString(1, nuevoEmail);
            statement.setInt(2, id);
        });
    }

    private void gestionarTransaccion(String sql, StatementSetter setter) {
        try (Connection connection = DriverManager.getConnection(this.jdbcUrl);
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
            connection.setAutoCommit(false);
            setter.accept(statement);
            try {
                statement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException("Error al insertar usuario", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar usuario", e);
        }
    }

}
