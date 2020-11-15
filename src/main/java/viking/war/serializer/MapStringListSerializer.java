package viking.war.serializer;

import viking.war.objects.*;

import java.util.*;

public class MapStringListSerializer
        implements IMapSerializer<List<String>> {

    private static final String KEY_ISLAND = "Остров";
    private static final String KEY_EAST = "восток";
    private static final String KEY_WEST = "запад";
    private static final String KEY_NORTH = "север";
    private static final String KEY_SOUTH = "юг";

    private static final String SCHEMA_ISLAND = "Остров%d ";
    private static final String SCHEMA_WAY = "%s=Остров%d ";

    /**
     * Serialize Map
     *
     * @param map
     * @return serialized map
     */
    @Override
    public List<String> serialize(IGameMap map) {
        List<String> strings = new LinkedList<>();

        for (final IIsland island : map.getIslandList())
        {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(String.format(SCHEMA_ISLAND, island.getID()));

            for (final Map.Entry<Direction, IIsland> way : island.getWays().entrySet()) {
                String directionStr = "";
                switch (way.getKey())
                {
                    case EAST:
                        directionStr = KEY_EAST;
                        break;
                    case NORTH:
                        directionStr = KEY_NORTH;
                        break;
                    case SOUTH:
                        directionStr = KEY_SOUTH;
                        break;
                    case WEST:
                        directionStr = KEY_WEST;
                        break;
                }

                stringBuilder.append(String.format(SCHEMA_WAY, directionStr, way.getValue().getID()));
            }

            strings.add(stringBuilder.toString());
        }

        return strings;
    }

    /**
     * Deserialize Map
     *
     * @param strings
     * @return map
     */
    @Override
    public IGameMap deserialize(List<String> strings) {
        GameMap gameMap = new GameMap();
        Map<IIsland, String[]> islandWaysStr = new HashMap<>();

        for (String s : strings)
        {
            if (!s.isEmpty()) {
                String[] parts = s.split(" ");
                String islandStr = parts[0];
                if (islandStr.startsWith(KEY_ISLAND))
                {
                    String islandIdStr = islandStr.replace(KEY_ISLAND, "");
                    try {
                        int islandId = Integer.parseInt(islandIdStr);
                        Island island = new Island(islandId);
                        if (parts.length > 1) {
                            islandWaysStr.put(island, Arrays.copyOfRange(parts, 1, parts.length));
                        } else {
                            islandWaysStr.put(island, new String[] {});
                        }
                        gameMap.getIslandList().add(island);
                    } catch (Exception ignored)
                    {

                    }
                }
            }
        }

        for (IIsland island : islandWaysStr.keySet())
        {
            String[] waysStr = islandWaysStr.get(island);
            for (String wayStr : waysStr)
            {
                try {
                    String[] wayParts = wayStr.split("=");

                    String directionString = wayParts[0];
                    Direction direction = null;
                    switch (directionString) {
                        case KEY_EAST:
                            direction = Direction.EAST;
                            break;

                        case KEY_NORTH:
                            direction = Direction.NORTH;
                            break;

                        case KEY_SOUTH:
                            direction = Direction.SOUTH;
                            break;

                        case KEY_WEST:
                            direction = Direction.WEST;
                            break;
                    }

                    String islandNextIdStr = wayParts[1];
                    islandNextIdStr = islandNextIdStr.replace(KEY_ISLAND, "");
                    int islandNextId = Integer.parseInt(islandNextIdStr);
                    IIsland islandNext = gameMap.getIslandByID(islandNextId);
                    if (islandNext != null)
                    {
                        gameMap.addWay(island, direction, islandNext);
                    }

                } catch (Exception ignored)
                {

                }
            }
        }

        return gameMap;
    }
}
