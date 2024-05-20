public class Trap extends Cell{
    public Trap() {
        super();
    }
    public Trap(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isSolid(Direction d) {
        return false;
    }
}
