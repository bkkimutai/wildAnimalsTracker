import org.bkkimutai.models.Animals;
import org.bkkimutai.models.EndangeredAnimals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EndangeredAnimalsTest {
    private static final Sql2o testSql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "postgres", "Msama@012023");

    @RegisterExtension
    public static final DatabaseExtension databaseExtension = new DatabaseExtension(testSql2o);

    @Test
    public void EndangeredAnimals_instantiatesCorrectly_true() {
        EndangeredAnimals testAnimal = new EndangeredAnimals ("Gazel", 1, "healthy","young");
        assertTrue(true);
    }
    @Test
    public void getName_EndangeredInstantiatesWithHealth_healthy() {
        EndangeredAnimals testAnimal = new EndangeredAnimals ("Gazel", 1, "healthy","young");
        assertEquals("healthy", testAnimal.getHealth());
    }
    @Test
    public void equals_returnsTrueIfNameTypeAreSame_true() {
        EndangeredAnimals firstAnimal = new EndangeredAnimals ("Gazel", 1, "healthy","young");
        EndangeredAnimals secondAnimal = new EndangeredAnimals ("Gazel", 1, "healthy","young");
        assertEquals(firstAnimal, secondAnimal);
    }
    @Test
    public void save_insertsObjectIntoDatabase_Animal() {
        EndangeredAnimals testAnimal = new EndangeredAnimals ("Gazel", 1, "healthy","young");
        testAnimal.save();
        assertEquals(EndangeredAnimals.all().get(0), testAnimal);
    }
    @Test
    public void all_returnsAllInstancesOfEndangeredAnimals_true() {
        EndangeredAnimals firstAnimal = new EndangeredAnimals ("Gazel", 1, "healthy","young");
        firstAnimal.save();
        EndangeredAnimals secondAnimal = new EndangeredAnimals ("Gazel", 1, "healthy","young");
        secondAnimal.save();
        List<EndangeredAnimals> animal = EndangeredAnimals.all();
        assertTrue(animal.contains(firstAnimal));
        assertTrue(animal.contains(secondAnimal));
    }
    @Test
    public void save_assignsIdToObject() {
        EndangeredAnimals testAnimal = new EndangeredAnimals ("Gazel", 1, "healthy","young");
        testAnimal.save();
        List<EndangeredAnimals> savedAnimals = EndangeredAnimals.all();
        assertEquals(1, savedAnimals.get(0).getAnimalId());
    }
    @Test
    public void find_returnsEndangeredAnimalWithSameId_secondAnimal() {
        EndangeredAnimals firstAnimal = new EndangeredAnimals ("Gazel", 1, "healthy","young");
        firstAnimal.save();
        EndangeredAnimals secondAnimal = new EndangeredAnimals ("Gazel", 1, "healthy","young");
        secondAnimal.save();
        assertEquals(EndangeredAnimals.find(firstAnimal.getAnimalId()), firstAnimal);
    }

}
