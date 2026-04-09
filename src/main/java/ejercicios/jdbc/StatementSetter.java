package ejercicios.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementSetter {
    void accept(PreparedStatement statement) throws SQLException;
}
