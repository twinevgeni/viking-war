package viking.war.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import viking.war.RandomMapGenerator;

public class RandomMapGeneratorTest {

    private void meshTest(boolean[][] mesh, boolean expected)
    {
        for (int x = 0; x < mesh.length; x++)
        {
            for (int y = 0; y < mesh[x].length; y++)
            {
                Assertions.assertEquals(expected, mesh[x][y]);
            }
        }
    }

    @Test
    public void meshCreateTest()
    {
        int X = 10;
        int Y = 20;
        RandomMapGenerator mapGenerator = new RandomMapGenerator();
        meshTest(mapGenerator.meshCreate(X, Y), false);
        meshTest(mapGenerator.meshCreate(X, Y, false), false);
        meshTest(mapGenerator.meshCreate(X, Y, true), true);
    }

    @Test
    public void meshSumTest() {
        int X = 10;
        int Y = 20;
        RandomMapGenerator mapGenerator = new RandomMapGenerator();

        boolean[][] mesh;
        int sum;

        mesh = mapGenerator.meshCreate(X, Y, false);
        sum = mapGenerator.meshSum(mesh);
        Assertions.assertEquals(0, sum);

        mesh = mapGenerator.meshCreate(X, Y, true);
        sum = mapGenerator.meshSum(mesh);
        Assertions.assertEquals(X * Y, sum);

        mesh = mapGenerator.meshCreate(X, Y, false);
        for (int x = 0; x < X; x++)
            for (int y = 0; y < Y; y++)
                if (x == y) mesh[x][y] = true;

        sum = mapGenerator.meshSum(mesh);
        Assertions.assertEquals(X, sum);
    }

    @Test
    public void meshFillTest() {

        int X = 10;
        int Y = 20;

        for (int islandCount = 0; islandCount < 20; islandCount++)
        {
            RandomMapGenerator mapGenerator = new RandomMapGenerator(islandCount);
            boolean[][] mesh = mapGenerator.meshCreate(X, Y);
            mesh = mapGenerator.meshFill(mesh);
            int sum = mapGenerator.meshSum(mesh);
            Assertions.assertEquals(islandCount, sum);
        }
    }
}
