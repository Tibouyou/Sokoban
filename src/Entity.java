import java.util.Observable;

public abstract class Entity extends Observable {
    protected int x;
    protected int y;
    public Entity() {
        this.x = 0;
        this.y = 0;
    }
    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public abstract boolean move(Direction d, Grid g);
}
