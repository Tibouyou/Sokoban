public class Sensor extends Cell {
    public Sensor() {
        super();
    }
    public Sensor(int x, int y) {
        super(x, y);
    }
    public boolean getType() {
        return true;
    }

    public boolean isActivated(Grid g) {
        return g.getEntity(this.getX(), this.getY()) != null && g.getEntity(this.getX(), this.getY()) instanceof Box;
    }
}
