import org.junit.jupiter.api.extension.RegisterExtension;
import org.sql2o.Sql2o;

public class AnimalsTest {
    private static final Sql2o testSql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "postgres", "Msama@012023");

    @RegisterExtension
    public static final DatabaseExtension databaseExtension = new DatabaseExtension(testSql2o);
}
