public class Box extends Entity{
    public Box() {
        super();
    }
    public Box(int x, int y) {
        super(x, y);
    }
    public boolean move(Direction d, Grid g) {
        switch (d) {
            case UP:
                if (this.getY() > 0) {
                    if (g.getEntity(this.getX(), this.getY() - 1) == null) {
                        this.y--;
                        setChanged();
                        notifyObservers();
                        return true;
                    }
                }
                break;
            case DOWN:
                if (this.getY() < 9) {
                    if (g.getEntity(this.getX(), this.getY() + 1) == null) {
                        this.y++;
                        setChanged();
                        notifyObservers();
                        return true;
                    }
                }
                break;
            case LEFT:
                if (this.getX() > 0) {
                    if (g.getEntity(this.getX() - 1, this.getY()) == null) {
                        this.x--;
                        setChanged();
                        notifyObservers();
                        return true;
                    }
                }
                break;
            case RIGHT:
                if (this.getX() < 9) {
                    if (g.getEntity(this.getX() + 1, this.getY()) == null) {
                        this.x++;
                        setChanged();
                        notifyObservers();
                        return true;
                    }
                }
                break;
        }
        return false;
    }
}
