public class Box extends Entity{
    public Box() {
        super();
    }
    public Box(int x, int y) {
        super(x, y);
    }
    public boolean move(Direction d) {
        switch (d) {
            case UP:
                if (this.getY() > 0) {
                    this.y--;
                    setChanged();
                    notifyObservers();
                    return true;
                }
                break;
            case DOWN:
                if (this.getY() < 9) {
                    this.y++;
                    setChanged();
                    notifyObservers();
                    return true;
                }
                break;
            case LEFT:
                if (this.getX() > 0) {
                    this.x--;
                    setChanged();
                    notifyObservers();
                    return true;
                }
                break;
            case RIGHT:
                if (this.getX() < 9) {
                    this.x++;
                    setChanged();
                    notifyObservers();
                    return true;
                }
                break;
        }
        return false;
    }
}
