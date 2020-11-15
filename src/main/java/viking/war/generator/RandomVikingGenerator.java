package viking.war.generator;

import viking.war.objects.IGameMap;
import viking.war.objects.IIsland;
import viking.war.objects.IViking;
import viking.war.objects.Viking;

import java.util.Random;

/**
 * Generates Vikings on Islands
 */
public class RandomVikingGenerator {

    static final int DEFAULT_VIKINGS_COUNT = 5;

    private final int vikingsCount;
    private final Random rnd;

    private IGameMap gameMap;

    /**
     * Constructor
     * @param gameMap game map
     */
    public RandomVikingGenerator(IGameMap gameMap)
    {
        this.vikingsCount = DEFAULT_VIKINGS_COUNT;
        this.rnd = new Random();
        this.gameMap = gameMap;
    }

    /**
     * Constructor
     * @param gameMap game map
     * @param vikingsCount vikings count on map
     */
    public RandomVikingGenerator(IGameMap gameMap, int vikingsCount)
    {
        this.vikingsCount = vikingsCount;
        this.rnd = new Random();
        this.gameMap = gameMap;
    }

    /**
     * Constructor
     * @param gameMap game map
     * @param vikingsCount vikings count on map
     * @param seed Random seed
     */
    public RandomVikingGenerator(IGameMap gameMap, int vikingsCount, long seed)
    {
        this.vikingsCount = vikingsCount;
        this.rnd = new Random(seed);
        this.gameMap = gameMap;
    }

    /**
     * put viking to random island
     * @param viking viking
     * @return true if success
     */
    private boolean putVikingToIsland(IViking viking)
    {
        for (IIsland island : this.gameMap.getIslandList())
        {
            if (island.getVikings().size() == 0)
            {
                if (rnd.nextBoolean())
                {
                    island.addViking(viking);
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Generate Vikings
     * @throws Exception if error
     */
    public void generate() throws Exception {
        if (this.vikingsCount > this.gameMap.getIslandList().size())
        {
            throw new Exception("Hе выходит размеcтить викиногов. Количеcтво викингов больше чем оcтровов");
        }

        for (int vikingId = 1; vikingId <= this.vikingsCount; vikingId++)
        {
            Viking viking = new Viking(vikingId);

            int tryCount = 0;
            final int MAX_TRY_COUNT = 1000;
            while (!putVikingToIsland(viking)) {
                if (tryCount >= MAX_TRY_COUNT)
                {
                    break;
                }

                tryCount++;
            }
        }
    }
}
