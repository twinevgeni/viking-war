package viking.war.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Island
        extends GameObject
        implements IIsland {

    private Map<Direction, IIsland> ways;
    private List<IViking> vikings;
    private boolean lighthouse;

    private void init()
    {
        this.ways = new HashMap<>();
        this.vikings = new ArrayList<>();
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

    /**
     * Available ways
     * @return ways
     */
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

    /**
     * Check if way exists
     * @param direction direction
     * @return true if way exists | false if not
     */
    @Override
    public boolean wayExists(Direction direction)
    {
        return this.ways.containsKey(direction);
    }

    /**
     * get way
     * @param direction direction
     * @return next island
     */
    @Override
    public IIsland getWay(Direction direction)
    {
        return this.wayExists(direction)
                ? this.ways.get(direction)
                : null;
    }

    /**
     * add or replace new way to island
     * @param direction direction
     * @param island next island
     */
    @Override
    public void setWay(Direction direction, IIsland island)
    {
        this.ways.put(direction, island);
    }

    /**
     * remove way from island
     * @param direction direction
     */
    @Override
    public void removeWay(Direction direction)
    {
        this.ways.remove(direction);
    }

    /**
     * remove all ways from island
     */
    @Override
    public void removeAllWays() {
        for (Direction direction : Direction.values()) {
            this.removeWay(direction);
        }
    }

    /**
     * get vikings on island
     *
     * @return vikings
     */
    @Override
    public List<IViking> getVikings() {
        return new ArrayList<>(this.vikings);
    }

    /**
     * add viking to island
     *
     * @param viking viking
     */
    @Override
    public void addViking(IViking viking) {
        if (viking != null) {
            this.vikings.add(viking);
        }
    }

    /**
     * get viking by id
     *
     * @param vikingId viking id
     * @return viking
     */
    @Override
    public IViking getVikingByID(int vikingId) {
        IViking foundViking = this.vikings.stream()
                .filter(viking -> viking.getID() == vikingId)
                .findFirst().orElse(null);

        return foundViking;
    }

    /**
     * remove viking by id
     *
     * @param vikingId viking id
     */
    @Override
    public void removeViking(int vikingId) {
        this.vikings.removeIf(viking -> viking.getID() == vikingId);
    }

    /**
     * remove viking
     *
     * @param viking viking
     */
    @Override
    public void removeViking(IViking viking) {
        if (viking != null) {
            this.vikings.remove(viking);
        }
    }

    /**
     * remove all vikings
     */
    @Override
    public void removeAllVikings() {
        this.vikings.clear();
    }
}
