import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class StoredProcedures {

    public static int insertarRegistros()
    {
        String sql = "{call INSERTAR_2826_REGISTROS()}";
        try (Connection conn = DBConnection.getConnection();
            CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.execute();
            return 2826;

        } catch (SQLException e) {
            System.err.println("Error al insertar: " + e.getMessage());
            return -1;
        }
    }

    public static int eliminarRegistros()
    {
        String sql = "{call ELIMINAR_2826_REGISTROS()}";
        try (Connection conn = DBConnection.getConnection();
            CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.execute();
            return 2826;

        } catch (SQLException e) {
            System.err.println("Error al eliminar: " + e.getMessage());
            return -1;
        }
    }
}
