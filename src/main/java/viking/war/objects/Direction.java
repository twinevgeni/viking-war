package viking.war.objects;

/**
 * Directions
 */
public enum Direction {
    EAST("east"),
    WEST("west"),
    NORTH("north"),
    SOUTH("south");

    private String direction;

    Direction(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return direction;
    }

    /**
     * Return opposite direction
     * @return Direction
     */
    public Direction getOpposite()
    {
        return Opposite(this);
    }

    /**
     * Return opposite direction
     * @param direction
     * @return Direction
     */
    public static Direction Opposite(Direction direction) {
        switch (direction) {
            case EAST:
                return Direction.WEST;
            case WEST:
                return Direction.EAST;
            case NORTH:
                return Direction.SOUTH;
            case SOUTH:
                return Direction.NORTH;
        }

        return null;
    }
}
