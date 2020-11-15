package viking.war;

import viking.war.objects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Generator for random map
 */
public class RandomMapGenerator {

    static final int DEFAULT_ISLANDS_COUNT = 5;

    public int islandCount;
    public Random rnd;

    public boolean[][] mesh;
    public int[][] islandsMesh;
    public IGameMap gameMap;

    public RandomMapGenerator()
    {
        this.islandCount = DEFAULT_ISLANDS_COUNT;
        this.rnd = new Random();
    }

    public RandomMapGenerator(int islandCount)
    {
        this.islandCount = islandCount;
        this.rnd = new Random();
    }

    public RandomMapGenerator(int islandCount, long seed)
    {
        this.islandCount = islandCount;
        this.rnd = new Random(seed);
    }

    /**
     * create Mesh
     * @param X size
     * @param Y size
     * @param defaultValue default value
     * @return mesh
     */
    public boolean[][] meshCreate(int X, int Y, boolean defaultValue)
    {
        boolean[][] mesh = new boolean[X][Y];
        for (int x = 0; x < X; x++)
        {
            for (int y = 0; y < Y; y++)
            {
                mesh[x][y] = defaultValue;
            }
        }

        return mesh;
    }

    /**
     * create Mesh [false filled]
     * @param X size
     * @param Y size
     * @return mesh
     */
    public boolean[][] meshCreate(int X, int Y)
    {
        return meshCreate(X, Y, false);
    }

    /**
     * Calc islands in mesh
     * @param mesh current mesh
     * @return sum
     */
    public int meshSum(boolean[][] mesh)
    {
        int sum = 0;
        for (int x = 0; x < mesh.length; x++)
        {
            for (int y = 0; y < mesh[x].length; y++)
            {
                if (mesh[x][y])
                {
                    sum++;
                }
            }
        }

        return sum;
    }

    public boolean[][] meshFill(boolean[][] mesh)
    {
        int meshSum = meshSum(mesh);

        for (int x = 0; x < mesh.length; x++)
        {
            for (int y = 0; y < mesh[x].length; y++)
            {
                if (meshSum >= islandCount)
                {
                    return mesh;
                }

                if (!mesh[x][y]) {
                    mesh[x][y] = rnd.nextBoolean();
                    if (mesh[x][y]) {
                        meshSum++;
                    }
                }
            }
        }

        return meshFill(mesh);
    }

    public void meshGenerate()
    {
        int X = 3;
        int Y = islandCount % 2 == 0 ? islandCount / 2 : (islandCount / 2) + 1;
        this.mesh = meshFill(meshCreate(X,Y));
    }

    public void meshPrint()
    {
        System.out.println("------------ mesh ------------");

        for (int x = 0; x < mesh.length; x++)
        {
            StringBuilder meshLine = new StringBuilder();
            for (int y = 0; y < mesh[x].length; y++)
            {
                String meshStr = mesh[x][y] ? "1" : "0";
                meshLine.append(meshStr);
            }

            System.out.println(meshLine.toString());
        }

        System.out.println("------------------------------");
    }

    public void islandsMeshGenerate()
    {
        islandsMesh = new int[mesh.length][mesh[0].length];

        int islandId = 0;
        for(int x = 0; x < islandsMesh.length; x++)
        {
            for (int y = 0; y < islandsMesh[x].length; y++)
            {
                if (mesh[x][y]) {
                    islandId++;
                    islandsMesh[x][y] = islandId;
                } else {
                    islandsMesh[x][y] = 0;
                }
            }
        }
    }

    public void islandsMeshPrint()
    {
        System.out.println("------------ islands mesh ------------");

        for (int x = 0; x < islandsMesh.length; x++)
        {
            StringBuilder meshLine = new StringBuilder();
            for (int y = 0; y < islandsMesh[x].length; y++)
            {
                String meshStr;
                if (islandsMesh[x][y] == 0)
                {
                    meshStr = "    ";
                } else {
                    meshStr = GameObject.formatId(islandsMesh[x][y], 3) + " ";
                }

                meshLine.append(meshStr);
            }

            System.out.println(meshLine.toString());
        }

        System.out.println("--------------------------------------");
    }


    public static final class Coord
    {
        public int x;
        public int y;

        public Coord()
        {
            this.x = 0;
            this.y = 0;
        }

        public Coord(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }

    public Coord getIslandCoord(int islandId)
    {
        for (int x = 0; x < islandsMesh.length; x++)
        {
            for (int y = 0; y < islandsMesh[x].length; y++)
            {
                if (islandsMesh[x][y] == islandId)
                {
                    return new Coord(x,y);
                }
            }
        }

        return new Coord(-1, -1);
    }

    public boolean checkIslandInMesh(int x, int y)
    {
        int X = mesh.length;
        int Y = mesh[0].length;

        if (x >= 0 && y >= 0 && x < X && y < Y)
        {
            if (islandsMesh[x][y] > 0){
                return true;
            }
        }

        return false;
    }


    public static final class Way
    {
        public int islandId;
        public Direction direction;

        public Way(int islandId, Direction direction)
        {
            this.islandId = islandId;
            this.direction = direction;
        }
    }

    /**
     * find possible ways for island
     * @param islandId id of island for find possible ways
     * @return list of islands ids
     */
    public List<Way> getPossibleWays(int islandId)
    {
        List<Way> ways = new ArrayList<>();
        Coord coord = getIslandCoord(islandId);
        if (coord.x != -1 && coord.y != -1) {
            Coord w1 = new Coord(coord.x - 1, coord.y);
            if (checkIslandInMesh(w1.x, w1.y))
            {
                Way way = new Way(islandsMesh[w1.x][w1.y], Direction.NORTH);
                ways.add(way);
            }

            Coord w2 = new Coord(coord.x, coord.y - 1);
            if (checkIslandInMesh(w2.x, w2.y))
            {
                Way way = new Way(islandsMesh[w2.x][w2.y], Direction.WEST);
                ways.add(way);
            }

            Coord w3 = new Coord(coord.x + 1, coord.y);
            if (checkIslandInMesh(w3.x, w3.y))
            {
                Way way = new Way(islandsMesh[w3.x][w3.y], Direction.SOUTH);
                ways.add(way);
            }

            Coord w4 = new Coord(coord.x, coord.y + 1);
            if (checkIslandInMesh(w4.x, w4.y))
            {
                Way way = new Way(islandsMesh[w4.x][w4.y], Direction.EAST);
                ways.add(way);
            }
        }

        return ways;
    }

    public void generate()
    {
        this.meshGenerate();
        this.islandsMeshGenerate();

        this.gameMap = new GameMap();
        for (int x = 0; x < mesh.length; x++)
        {
            for (int y = 0; y < mesh[x].length; y++)
            {
                if (mesh[x][y])
                {
                    Island island = new Island(islandsMesh[x][y]);
                    this.gameMap.getIslandList().add(island);
                }
            }
        }

        for (IIsland island : this.gameMap.getIslandList())
        {
            List<Way> possibleWays = this.getPossibleWays(island.getID());
            for (Way possibleWay : possibleWays)
            {
                if (!island.wayExists(possibleWay.direction))
                {
                    if (rnd.nextBoolean())
                    {
                        IIsland islandTo = this.gameMap.getIslandByID(possibleWay.islandId);

                        try {
                            this.gameMap.addWay(island, possibleWay.direction, islandTo);
                        } catch (Exception ignore)
                        {

                        }
                    }
                }
            }
        }
    }

    public void printMapGraph()
    {
        System.out.println("------------ map graph ------------");

        for (int x = 0; x < islandsMesh.length; x++)
        {
            StringBuilder line = new StringBuilder();
            StringBuilder line2 = new StringBuilder();
            for (int y = 0; y < islandsMesh[x].length; y++)
            {
                int id = islandsMesh[x][y];
                if (id > 0)
                {
                    IIsland island = this.gameMap.getIslandByID(id);
                    line.append(GameObject.formatId(id, 3));
                    if (island.wayExists(Direction.EAST))
                    {
                        line.append("-");
                    } else {
                        line.append(" ");
                    }

                    if (island.wayExists(Direction.SOUTH))
                    {
                        line2.append(" |  ");
                    } else {
                        line2.append("    ");
                    }
                } else {
                    line.append("    ");
                    line2.append("    ");
                }
            }

            System.out.println(line.toString());
            System.out.println(line2.toString());
        }

        System.out.println("------------ map graph ------------");
    }
}
