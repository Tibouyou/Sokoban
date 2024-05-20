import java.util.Observable;

public abstract class Cell extends Observable {
    private int x;
    private int y;
    public Cell() {
        this.x = 0;
        this.y = 0;
    }
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }

    public abstract boolean isSolid(Direction d);
}
