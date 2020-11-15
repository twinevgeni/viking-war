package viking.war;

import java.util.HashMap;
import java.util.Map;

public class Main {
    static final String ARG_N = "-N";
    static final String ARG_RANDOM = "-random";
    static final String ARG_MAP = "-map";
    static final String ARG_MAP_FILE = "-mapfile";

    Map<String, String> readArgs(String[] args)
    {
        Map<String, String> argsMap = new HashMap<>();

        for (String arg : args)
        {
            if (arg.startsWith("-"))
            {
                String[] argParts = arg.split("=");
                if (argParts.length == 1) {
                    argsMap.put(argParts[0], "");
                } else if (argParts.length == 2) {
                    argsMap.put(argParts[0], argParts[1]);
                }
            }
        }

        return argsMap;
    }

    public static void main(String[] args) {
        System.out.println("|--- VIKING WAR ---|");

        RandomMapGenerator mapGenerator = new RandomMapGenerator(25);
        mapGenerator.generate();
        mapGenerator.printMapGraph();

        System.out.println("|--- Игра начата ---|");
        GameController controller = new GameController();
        controller.startGame();
    }
}
