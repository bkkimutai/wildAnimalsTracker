import org.bkkimutai.models.Sightings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.sql2o.Sql2o;

import java.util.List;

import static java.time.LocalTime.now;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SightingsTest {

    private static final Sql2o testSql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "postgres", "Msama@012023");

    @RegisterExtension
    public static final DatabaseExtension databaseExtension = new DatabaseExtension(testSql2o);

    @Test
    public void getSigtingName_SightingsInstantiatesWithLocation_ZoneA() {
        Sightings testSight = new Sightings (1, 1, 1);
        assertEquals("Zone A", testSight.getLocationId());
    }
    @Test
    public void equals_returnsTrueIfNameTypeAreSame_true() {
        Sightings firstSight = new Sightings (1, 1, 1);;
        Sightings secondSight = new Sightings (1, 1, 1);
        assertEquals(firstSight, secondSight);
    }

    @Test
    public void save_insertsObjectIntoDatabase_Sighting() {
        Sightings testSight = new Sightings (1, 1, 1);
        testSight.save();
        assertEquals(Sightings.all().get(0), testSight);
    }
    @Test
    public void all_returnsAllInstancesOfEndangeredAnimals_true() {
        Sightings firstSight = new Sightings (1, 1, 1);;
        firstSight.save();
        Sightings secondSight = new Sightings (1, 1, 1);;
        secondSight.save();
        List<Sightings> sights = Sightings.all();
        assertTrue(sights.contains(firstSight));
        assertTrue(sights.contains(secondSight));
    }
    @Test
    public void save_assignsIdToObject() {
        Sightings testSight = new Sightings (1, 1, 1);;
        testSight.save();
        List<Sightings> savedSightings = Sightings.all();
        assertEquals(2, savedSightings.get(0).getSightingId());
    }
    @Test
    public void find_returnsSightingsWithSameId_firstSight() {
        Sightings firstSight = new Sightings (1, 1, 1);
        firstSight.save();
        Sightings secondSight = new Sightings (2, 2, 1);
        secondSight.save();
        assertEquals(Sightings.find(firstSight.getSightingId()), firstSight);
    }
}
