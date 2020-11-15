package viking.war.test.objects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import viking.war.objects.Direction;
import viking.war.objects.IIsland;
import viking.war.objects.Island;

import java.util.Arrays;

public class IslandTest {

    @Test
    public void createTest()
    {
        Island island = new Island();
        Assertions.assertNotNull(island);
        Assertions.assertNotNull(island.getWays());
        Assertions.assertTrue(island.isLighthouse());

        island = new Island(2);
        Assertions.assertEquals(2, island.getID());
    }

    @Test
    public void setLighthouseTest()
    {
        Island island = new Island();
        Assertions.assertTrue(island.isLighthouse());

        island.setLighthouse(false);
        Assertions.assertFalse(island.isLighthouse());

        island.setLighthouse(true);
        Assertions.assertTrue(island.isLighthouse());
    }

    /**
     * test that directions exists
     * @param island
     * @param directions
     */
    private void assertTrueDirections(IIsland island, Direction[] directions)
    {
        for (Direction direction : directions)
        {
            Assertions.assertTrue(island.wayExists(direction));
        }
    }

    /**
     * test that directions not exists
     * @param island
     * @param directions
     */
    private void assertFalseDirections(IIsland island, Direction[] directions)
    {
        for (Direction direction : directions)
        {
            Assertions.assertFalse(island.wayExists(direction));
        }
    }

    @Test
    public void wayExistsTest()
    {
        Island island;
        Island island2;

        island = new Island(1);
        this.assertFalseDirections(island, Direction.values());

        for (Direction direction : Direction.values())
        {
            island = new Island(1);
            island2 = new Island(2);
            island.getWays().put(direction, island2);

            Direction[] trueDirections = {direction};
            Direction[] falseDirections = Arrays.stream(Direction.values())
                                            .filter(x -> !x.equals(direction)).toArray(Direction[]::new);

            assertTrueDirections(island, trueDirections);
            assertFalseDirections(island, falseDirections);
        }
    }

    @Test
    public void getWayTest()
    {
        int id = 1;
        Island island = new Island(id++);

        for (Direction direction : Direction.values())
        {
            Assertions.assertNull(island.getWay(direction));

            Island islandNext = new Island(id++);
            island.getWays().put(direction, islandNext);
            Assertions.assertEquals(islandNext, island.getWay(direction));
        }

        Assertions.assertNotEquals(island.getWay(Direction.SOUTH), island.getWay(Direction.NORTH));
        Assertions.assertNotEquals(island.getWay(Direction.SOUTH), island.getWay(Direction.EAST));
        Assertions.assertNotEquals(island.getWay(Direction.SOUTH), island.getWay(Direction.WEST));
    }

    @Test
    public void setWayTest()
    {
        int id = 1;
        Island island = new Island(id++);

        for (Direction direction : Direction.values())
        {
            Assertions.assertNull(island.getWay(direction));

            Island islandNext = new Island(id++);
            island.setWay(direction, islandNext);
            Assertions.assertEquals(islandNext, island.getWay(direction));
        }

        Assertions.assertNotEquals(island.getWay(Direction.SOUTH), island.getWay(Direction.NORTH));
        Assertions.assertNotEquals(island.getWay(Direction.SOUTH), island.getWay(Direction.EAST));
        Assertions.assertNotEquals(island.getWay(Direction.SOUTH), island.getWay(Direction.WEST));
    }

    @Test
    public void removeWayTest()
    {
        int id = 1;
        Island island = new Island(id++);

        for (Direction direction : Direction.values())
        {
            Island islandNext = new Island(id++);
            island.setWay(direction, islandNext);
        }

        for (Direction direction : Direction.values())
        {
            island.removeWay(direction);
            this.assertFalseDirections(island, new Direction[] {direction});
        }
    }

    @Test
    public void removeAllWaysTest()
    {
        int id = 1;
        Island island = new Island(id++);

        for (Direction direction : Direction.values())
        {
            Island islandNext = new Island(id++);
            island.setWay(direction, islandNext);
        }

        island.removeAllWays();
        this.assertFalseDirections(island, Direction.values());
    }
}
