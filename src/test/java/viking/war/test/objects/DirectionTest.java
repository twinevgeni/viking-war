package viking.war.test.objects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import viking.war.objects.Direction;

public class DirectionTest {

    @Test
    void toStringTest()
    {
        Assertions.assertEquals(Direction.WEST.toString(), "west");
        Assertions.assertEquals(Direction.EAST.toString(), "east");
        Assertions.assertEquals(Direction.NORTH.toString(), "north");
        Assertions.assertEquals(Direction.SOUTH.toString(), "south");
    }

    @Test
    public void getOppositeTest()
    {
        Assertions.assertEquals(Direction.NORTH.getOpposite(), Direction.SOUTH);
        Assertions.assertEquals(Direction.SOUTH.getOpposite(), Direction.NORTH);
        Assertions.assertEquals(Direction.WEST.getOpposite(), Direction.EAST);
        Assertions.assertEquals(Direction.EAST.getOpposite(), Direction.WEST);
    }

    @Test
    public void OppositeTest()
    {
        Assertions.assertEquals(Direction.Opposite(Direction.NORTH), Direction.SOUTH);
        Assertions.assertEquals(Direction.Opposite(Direction.SOUTH), Direction.NORTH);
        Assertions.assertEquals(Direction.Opposite(Direction.WEST), Direction.EAST);
        Assertions.assertEquals(Direction.Opposite(Direction.EAST), Direction.WEST);
    }
}
