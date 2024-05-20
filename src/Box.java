import java.util.ArrayList;

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
                if (g.getCell(this.getX(), this.getY()) instanceof DirectionnalCell) {
                    if (((DirectionnalCell) g.getCell(this.getX(), this.getY())).getDirection() != Direction.UP) {
                        return false;
                    }
                }
                if (this.getY() > 0) {
                    if (g.getEntity(this.getX(), this.getY() - 1) == null) {
                        if (!(g.getCell(this.getX(), this.getY() - 1).isSolid(Direction.UP))) {
                            this.y--;
                            ArrayList<Integer> oldcoords = new ArrayList<Integer>();
                            oldcoords.add(this.getX());
                            oldcoords.add(this.getY() + 1);
                            setChanged();
                            notifyObservers(oldcoords);
                            return true;
                        }
                    }
                }
                break;
            case DOWN:
                if (g.getCell(this.getX(), this.getY()) instanceof DirectionnalCell) {
                    if (((DirectionnalCell) g.getCell(this.getX(), this.getY())).getDirection() != Direction.DOWN) {
                        return false;
                    }
                }
                if (this.getY() < g.getHeight()-1) {
                    if (g.getEntity(this.getX(), this.getY() + 1) == null) {
                        if (!(g.getCell(this.getX(), this.getY() + 1).isSolid(Direction.DOWN))) {
                            this.y++;
                            ArrayList<Integer> oldcoords = new ArrayList<Integer>();
                            oldcoords.add(this.getX());
                            oldcoords.add(this.getY() - 1);
                            setChanged();
                            notifyObservers(oldcoords);
                            return true;
                        }
                    }
                }
                break;
            case LEFT:
                if (g.getCell(this.getX(), this.getY()) instanceof DirectionnalCell) {
                    if (((DirectionnalCell) g.getCell(this.getX(), this.getY())).getDirection() != Direction.LEFT) {
                        return false;
                    }
                }
                if (this.getX() > 0) {
                    if (g.getEntity(this.getX() - 1, this.getY()) == null) {
                        if (!(g.getCell(this.getX() - 1, this.getY()).isSolid(Direction.LEFT))) {
                            this.x--;
                            ArrayList<Integer> oldcoords = new ArrayList<Integer>();
                            oldcoords.add(this.getX() + 1);
                            oldcoords.add(this.getY());
                            setChanged();
                            notifyObservers(oldcoords);
                            return true;
                        }
                    }
                }
                break;
            case RIGHT:
                if (g.getCell(this.getX(), this.getY()) instanceof DirectionnalCell) {
                    if (((DirectionnalCell) g.getCell(this.getX(), this.getY())).getDirection() != Direction.RIGHT) {
                        return false;
                    }
                }
                if (this.getX() < g.getWidth()-1) {
                    if (g.getEntity(this.getX() + 1, this.getY()) == null) {
                        if (!(g.getCell(this.getX() + 1, this.getY()).isSolid(Direction.RIGHT))) {
                            this.x++;
                            ArrayList<Integer> oldcoords = new ArrayList<Integer>();
                            oldcoords.add(this.getX() - 1);
                            oldcoords.add(this.getY());
                            setChanged();
                            notifyObservers(oldcoords);
                            return true;
                        }
                    }
                }
                break;
        }
        return false;
    }
}
