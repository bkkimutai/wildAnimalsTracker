import org.bkkimutai.models.Animals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnimalsTest {
    private static final Sql2o testSql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "postgres", "Msama@012023");

    @RegisterExtension
    public static final DatabaseExtension databaseExtension = new DatabaseExtension(testSql2o);

    @Test
    public void Animals_instantiatesCorrectly_true() {
        Animals testAnimal = new Animals ("Gazel", 1);
        assertTrue(true);
    }

    @Test
    public void getName_AnimalInstantiatesWithName_Gazel() {
        Animals testAnimal = new Animals ("Gazel", 1);
        assertEquals("Gazel", testAnimal.getAnimalName());
    }
    @Test
    public void equals_returnsTrueIfNameTypeAreSame_true() {
        Animals testAnimal = new Animals ("Gazel", 1);
        Animals anotherAnimal = new Animals ("Gazel", 1);
        assertEquals(testAnimal, anotherAnimal);
    }
    @Test
    public void save_insertsObjectIntoDatabase_Animal() {
        Animals testAnimal = new Animals ("Gazel", 1);
        testAnimal.save();
        assertEquals(Animals.all().get(0), testAnimal);
    }
    @Test
    public void all_returnsAllInstancesOfAnimals_true() {
        Animals firstAnimal = new Animals ("Gazel", 1);
        firstAnimal.save();
        Animals secondAnimal = new Animals ("Lion", 2);
        secondAnimal.save();
        List<Animals> animal = Animals.all();
        assertTrue(animal.contains(firstAnimal));
        assertTrue(animal.contains(secondAnimal));
    }
    @Test
    public void save_assignsIdToObject() {
        Animals testAnimal = new Animals ("Lion", 2);
        testAnimal.save();
        List<Animals> savedAnimal = Animals.all();
        assertEquals(1, savedAnimal.get(0).getAnimalId());
    }
    @Test
    public void find_returnsItemsWithSameId_secondAnimal() {
        Animals firstAnimal = new Animals ("Gazel", 1);
        firstAnimal.save();
        Animals secondAnimal = new Animals ("Lion", 2);
        secondAnimal.save();
        assertEquals(Animals.find(firstAnimal.getAnimalId()), firstAnimal);
    }
}
