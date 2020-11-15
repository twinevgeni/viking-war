package viking.war.loaders;

import viking.war.generator.RandomMapGenerator;
import viking.war.objects.IGameMap;

import java.io.PrintStream;

/**
 * Random map loader
 */
public class RandomMapLoader implements IMapLoader {

    private int islandsCount;
    private PrintStream out;
    /**
     *
     * @param islandsCount island count
     * @param out
     */
    public RandomMapLoader(int islandsCount, PrintStream out)
    {
        this.islandsCount = islandsCount;
        this.out = out;
    }

    /**
     * load map
     *
     * @return game map
     * @throws Exception if map load error
     */
    @Override
    public IGameMap Load() throws Exception {

        out.println("Создание карты | количеcтво оcтровов = " + islandsCount);
        RandomMapGenerator mapGenerator = new RandomMapGenerator(islandsCount);
        mapGenerator.generate();
        mapGenerator.printMapGraph();

        return mapGenerator.gameMap;
    }
}
