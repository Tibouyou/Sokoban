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
                if (this.getY() > 0) {
                    if (g.getEntity(this.getX(), this.getY() - 1) == null) {
                        if (!(g.getCase(this.getX(), this.getY() - 1) instanceof Wall)) {
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
                if (this.getY() < g.getHeight()-1) {
                    if (g.getEntity(this.getX(), this.getY() + 1) == null) {
                        if (!(g.getCase(this.getX(), this.getY() + 1) instanceof Wall)) {
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
                if (this.getX() > 0) {
                    if (g.getEntity(this.getX() - 1, this.getY()) == null) {
                        if (!(g.getCase(this.getX() - 1, this.getY()) instanceof Wall)) {
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
                if (this.getX() < g.getWidth()-1) {
                    if (g.getEntity(this.getX() + 1, this.getY()) == null) {
                        if (!(g.getCase(this.getX() + 1, this.getY()) instanceof Wall)) {
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
