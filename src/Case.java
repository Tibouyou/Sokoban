import java.util.Observable;

public class Case extends Observable {
    private int x;
    private int y;
    public Case() {
        this.x = 0;
        this.y = 0;
    }
    public Case(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void move(Direction d) {
        switch (d) {
            case UP:
                this.y--;
                break;
            case DOWN:
                this.y++;
                break;
            case LEFT:
                this.x--;
                break;
            case RIGHT:
                this.x++;
                break;
        }
        setChanged();
        notifyObservers();
    }
}
