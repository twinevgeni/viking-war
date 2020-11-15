package viking.war.test.objects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import viking.war.objects.Viking;

public class VikingTest {

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3, 4, 10, 100 })
    public void createTest(int id)
    {
        Viking viking = new Viking(id);
        Assertions.assertEquals(viking.getID(), id);
    }
}
