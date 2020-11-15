package viking.war.test.objects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import viking.war.objects.GameObject;

public class GameObjectTest {

    @Test
    public void createDefaultTest()
    {
        GameObject gameObject = new GameObject();
        Assertions.assertEquals(gameObject.getID(), -1);
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3, 4, 10, 100 })
    public void createTest(int id)
    {
        GameObject gameObject = new GameObject(id);
        Assertions.assertEquals(gameObject.getID(), id);
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3, 4, 10, 100 })
    public void setIDTest(int id) {
        GameObject gameObject = new GameObject();
        gameObject.setID(id);
        Assertions.assertEquals(gameObject.getID(), id);
    }
}
