package viking.war.test.objects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import viking.war.objects.Direction;
import viking.war.objects.GameMap;
import viking.war.objects.IIsland;
import viking.war.objects.Island;

public class GameMapTest {

    @Test
    public void createTest()
    {
        GameMap gameMap = new GameMap();
        Assertions.assertNotNull(gameMap);
        Assertions.assertNotNull(gameMap.getIslandList());
    }

    private IIsland[] createIslands(GameMap gameMap, int count)
    {
        IIsland[] islands = new IIsland[count];

        for (int id = 0; id < count; id++)
        {
            Island island = new Island(id);
            gameMap.getIslandList().add(island);
            islands[id] = island;
        }

        return islands;
    }

    @Test
    public void getIslandByIDTest()
    {
        int islandsCount = 10;
        GameMap gameMap = new GameMap();
        IIsland[] islands = createIslands(gameMap, islandsCount);
        for (int islandId = 0; islandId < islandsCount; islandId++)
        {
            Assertions.assertEquals(islands[islandId], gameMap.getIslandByID(islandId));
        }
    }

    @Test
    public void addWayTest()
    {
        int islandsCount = 3;
        GameMap gameMap = new GameMap();
        IIsland[] islands = createIslands(gameMap, islandsCount);

        try {
            gameMap.addWay(islands[0], Direction.EAST, islands[1]);
            gameMap.addWay(islands[1], Direction.NORTH, islands[2]);
        } catch (Exception ignored)
        {

        }

        Assertions.assertTrue(islands[0].wayExists(Direction.EAST));
        Assertions.assertFalse(islands[0].wayExists(Direction.WEST));
        Assertions.assertFalse(islands[0].wayExists(Direction.NORTH));
        Assertions.assertFalse(islands[0].wayExists(Direction.SOUTH));

        Assertions.assertTrue(islands[1].wayExists(Direction.WEST));
        Assertions.assertFalse(islands[1].wayExists(Direction.EAST));
        Assertions.assertTrue(islands[1].wayExists(Direction.NORTH));
        Assertions.assertFalse(islands[1].wayExists(Direction.SOUTH));

        Assertions.assertFalse(islands[2].wayExists(Direction.WEST));
        Assertions.assertFalse(islands[2].wayExists(Direction.EAST));
        Assertions.assertFalse(islands[2].wayExists(Direction.NORTH));
        Assertions.assertTrue(islands[2].wayExists(Direction.SOUTH));

        Assertions.assertEquals(islands[0].getWay(Direction.EAST), islands[1]);
        Assertions.assertEquals(islands[1].getWay(Direction.WEST), islands[0]);
        Assertions.assertEquals(islands[1].getWay(Direction.NORTH), islands[2]);
        Assertions.assertEquals(islands[2].getWay(Direction.SOUTH), islands[1]);
    }

    @Test
    public void removeWayTest()
    {
        int islandsCount = 3;
        GameMap gameMap = new GameMap();
        IIsland[] islands = createIslands(gameMap, islandsCount);

        try {
            gameMap.addWay(islands[0], Direction.EAST, islands[1]);
            gameMap.addWay(islands[1], Direction.NORTH, islands[2]);
        } catch (Exception ignored)
        {

        }

        gameMap.removeWay(islands[0], Direction.EAST);

        Assertions.assertFalse(islands[0].wayExists(Direction.EAST));
        Assertions.assertFalse(islands[0].wayExists(Direction.WEST));
        Assertions.assertFalse(islands[0].wayExists(Direction.NORTH));
        Assertions.assertFalse(islands[0].wayExists(Direction.SOUTH));

        Assertions.assertFalse(islands[1].wayExists(Direction.EAST));
        Assertions.assertFalse(islands[1].wayExists(Direction.WEST));
        Assertions.assertTrue(islands[1].wayExists(Direction.NORTH));
        Assertions.assertFalse(islands[1].wayExists(Direction.SOUTH));

        Assertions.assertFalse(islands[2].wayExists(Direction.WEST));
        Assertions.assertFalse(islands[2].wayExists(Direction.EAST));
        Assertions.assertFalse(islands[2].wayExists(Direction.NORTH));
        Assertions.assertTrue(islands[2].wayExists(Direction.SOUTH));
    }

    @Test
    public void destroyLighthouseTestByID()
    {

    }

    @Test
    public void destroyLighthouseTest()
    {

    }
}
