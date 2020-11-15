package viking.war.objects;

import java.util.Objects;

public class GameObject implements IGameObject {
    private int id;

    public GameObject()
    {
        this.id = -1;
    }

    public GameObject(int id)
    {
        this.id = id;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IGameObject)) return false;
        IGameObject that = (IGameObject) o;
        return id == that.getID();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static String formatId(int objectId, int digits)
    {
        int idNums = String.valueOf(objectId).length();
        StringBuilder str = new StringBuilder();
        str.append("0".repeat(Math.max(0, (digits - idNums))));
        str.append(objectId);
        return str.toString();
    }
}
