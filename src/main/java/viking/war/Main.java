package viking.war;

import viking.war.generator.RandomVikingGenerator;
import viking.war.loaders.IMapLoader;
import viking.war.loaders.RandomMapLoader;
import viking.war.loaders.ResourceMapLoader;
import viking.war.objects.IGameMap;
import viking.war.serializer.MapStringSerializer;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static final String ARG_N = "-N";
    static final String ARG_RANDOM = "-random";
    static final String ARG_MAP = "-map";

    /**
     * Default arg values
     **/
    static final int ARG_VAL_N = 3;
    static final int ARG_VAL_RANDOM = 15;


    private static Map<String, String> readArgs(String[] args) {
        Map<String, String> argsMap = new HashMap<>();

        for (String arg : args) {
            if (arg.startsWith("-")) {
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

    public static void main(String[] args) throws UnsupportedEncodingException {
        PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        out.println("|--- VIKING WAR ---|");

        final Map<String, String> argsMap = readArgs(args);

        int vikingsCount = ARG_VAL_N;
        if (argsMap.containsKey(ARG_N)) {
            String argVal = argsMap.get(ARG_N);
            if (!argVal.isEmpty()) {
                try {
                    vikingsCount = Integer.parseInt(argVal);
                } catch (NumberFormatException e) {
                    out.println("Ошибка, неверно указано количеcтво викингов");
                    return;
                }
            }
        }

        IMapLoader mapLoader = null;
        if (argsMap.containsKey(ARG_RANDOM)) {
            int islandsCount = ARG_VAL_RANDOM;

            String argVal = argsMap.get(ARG_RANDOM);
            if (!argVal.isEmpty()) {
                try {
                    islandsCount = Integer.parseInt(argVal);
                } catch (NumberFormatException e) {
                    out.println("Ошибка создания карты, неверно указано количество островов");
                    return;
                }
            }

            mapLoader = new RandomMapLoader(islandsCount, out);
        } else if (argsMap.containsKey(ARG_MAP)) {
            mapLoader = new ResourceMapLoader(argsMap.get(ARG_MAP), out);
        } else {
            mapLoader = new RandomMapLoader(ARG_VAL_RANDOM, out);
        }

        try {
            if (mapLoader == null) {
                throw new Exception("Не выбрана карта");
            }

            IGameMap gameMap = mapLoader.Load();
            out.println("|--- Загружена карта ---|");
            out.println(new MapStringSerializer().serialize(gameMap));
            out.println("|---  ---|");

            new RandomVikingGenerator(gameMap, vikingsCount).generate();
            GameController controller = new GameController(gameMap, out);

            out.println("|--- Игра начата ---|");
            controller.startGame();
            out.printf("Игра окончена на дне %d", controller.getIteration());
            out.println();
            out.println("|--- Финальная карта ---|");
            out.println(new MapStringSerializer().serialize(gameMap));
        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }
}
