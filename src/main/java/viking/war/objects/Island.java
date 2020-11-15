package viking.war.objects;

import java.util.HashMap;
import java.util.Map;

public class Island
        extends GameObject
        implements IIsland {

    private Map<Direction, IIsland> ways;
    private boolean lighthouse;

    private void init()
    {
        this.ways = new HashMap<>();
        this.lighthouse = true;
    }

    public Island()
    {
        super();
        this.init();
    }

    public Island(int id)
    {
        super(id);
        this.init();
    }

    @Override
    public Map<Direction, IIsland> getWays() {
        return this.ways;
    }

    /**
     * @return true if lighthouse is exists | false if not
     */
    @Override
    public boolean isLighthouse() {
        return this.lighthouse;
    }

    /**
     * set lighthouse existence
     *
     * @param status lighthouse existence status
     */
    @Override
    public void setLighthouse(boolean status) {
        this.lighthouse = status;
    }

    @Override
    public boolean wayExists(Direction direction)
    {
        return this.ways.containsKey(direction);
    }

    @Override
    public IIsland getWay(Direction direction)
    {
        return this.wayExists(direction)
                ? this.ways.get(direction)
                : null;
    }

    @Override
    public void setWay(Direction direction, IIsland island)
    {
        this.ways.put(direction, island);
    }

    @Override
    public void removeWay(Direction direction)
    {
        this.ways.remove(direction);
    }

    @Override
    public void removeAllWays() {
        for (Direction direction : Direction.values()) {
            this.removeWay(direction);
        }
    }
}
