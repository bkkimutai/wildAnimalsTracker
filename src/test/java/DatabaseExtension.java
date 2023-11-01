import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DatabaseExtension implements BeforeEachCallback, AfterEachCallback {
    private final Sql2o sql2o;

    public DatabaseExtension(Sql2o sql2o) {
        this.sql2o = sql2o;
    }
    @Override
    public void beforeEach(ExtensionContext context) {
    }
    @Override
    public void afterEach(ExtensionContext context) {
        try (Connection con = sql2o.open()) {
            String deleteAnimalsQuery = "DELETE FROM animals *;";
            String deleteInventorySightingsQuery = "DELETE FROM sightings *;";
            String deleteRangersQuery = "DELETE FROM rangers *;";
            con.createQuery(deleteAnimalsQuery).executeUpdate();
            con.createQuery(deleteInventorySightingsQuery).executeUpdate();
            con.createQuery(deleteRangersQuery).executeUpdate();
        }
    }
}
