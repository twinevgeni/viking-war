package viking.war;

import viking.war.objects.Direction;
import viking.war.objects.IGameMap;
import viking.war.objects.IIsland;
import viking.war.objects.IViking;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Main Game Controller
 */
public class GameController {

    public static final String VIKING_STOPS_SHCHEMA =
            "АГР!!! Викинг%d застрял на Острове%d и больше не участвует в войне";

    public static final String VIKING_WAR_SHCHEMA =
            "АГР!!! На Остров%d уничтожен маяк, благодаря %s";

    public static final String VIKING_SHCHEMA = "Викинг%d";
    public static final String VIKING_DELINETR = " и ";

    public static final int MAX_ITERATION_COUNT = 10000;

    private PrintStream out;
    private IGameMap gameMap;
    private Random rnd;
    private int iteration;

    /**
     * Constructor
     * @param gameMap game map
     */
    public GameController(IGameMap gameMap, PrintStream out)
    {
        this.out = out;
        this.iteration = -1;
        this.gameMap = gameMap;
        this.rnd = new Random();
    }

    /**
     * @return current iteration
     */
    public int getIteration()
    {
        return this.iteration;
    }

    /**
     * check if vikings still exists
     * @return true if vikings exists on islands
     */
    private boolean vikingsExists()
    {
        return this.gameMap.getIslandList().stream().anyMatch(island -> island.getVikings().size() > 0);
    }

    /**
     * check if viking can move
     * @param island island
     * @param viking viking
     * @return true if viking can move
     */
    private boolean canMove(IIsland island, IViking viking)
    {
        return island.isLighthouse() && island.getWays().size() > 0;
    }

    /**
     * get available ways for viking from island
     * @param island island
     * @param viking viking
     * @return directions
     */
    private Direction[] getAvailableMove(IIsland island, IViking viking)
    {
        return island.getWays().keySet().toArray(Direction[]::new);
    }

    private void doRandomMove(IIsland island, IViking viking)
    {
        Direction[] directions = getAvailableMove(island, viking);
        int directionIndex = rnd.nextInt(directions.length);

        Direction direction = directions[directionIndex];
        IIsland islandNext = island.getWay(direction);

        if (islandNext != null) {
            island.removeViking(viking);
            islandNext.addViking(viking);
        }
    }

    /**
     * do one iteration viking move
     */
    private void doVikingMove()
    {
        for (IIsland island : this.gameMap.getIslandList())
        {
            for (IViking viking : island.getVikings())
            { // I think it should be only one viking if all ok
                if (canMove(island, viking)) {
                    doRandomMove(island, viking);
                } else {
                    out.printf(VIKING_STOPS_SHCHEMA, viking.getID(), island.getID());
                    out.println();
                    island.removeViking(viking);
                }
            }
        }
    }

    /**
     * do war in islind
     * @param island island
     */
    private void doWar(IIsland island)
    {
        List<String> vikingsNames = new LinkedList<>();
        for (IViking viking : island.getVikings())
        {
            String vikingName = String.format(VIKING_SHCHEMA, viking.getID());
            vikingsNames.add(vikingName);
        }

        String vikingsNamesString = String.join(VIKING_DELINETR, vikingsNames);
        String message = String.format(VIKING_WAR_SHCHEMA, island.getID(), vikingsNamesString);

        out.println(message);

        island.removeAllVikings();
        this.gameMap.destroyLighthouse(island);
    }

    /**
     * check if in island more than one viking
     */
    private void checkWar()
    {
        for (IIsland island : this.gameMap.getIslandList())
        {
            if (island.getVikings().size() >= 2)
            {
                doWar(island);
            }
        }
    }

    /**
     * one game iteration
     * @return true if iteration can be continued
     * @throws Exception error
     */
    private boolean doIteration() throws Exception {
        iteration++;

        if (iteration >= MAX_ITERATION_COUNT)
        {
            out.println("Игра окончена | Прошло 10000 дней");
            return false;
        }

        if (!this.vikingsExists()) {
            out.println("Игра окончена | Больше нет викингов");
            return false;
        }

        this.doVikingMove();
        this.checkWar();

        return true;
    }

    /**
     * Start Game
     * @throws Exception error
     */
    public void startGame() throws Exception
    {
        while(doIteration())
        {
        }
    }
}
