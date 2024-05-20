public class DirectionnalCell extends Cell {
    private Direction direction;
    public DirectionnalCell() {
        super();
    }
    public DirectionnalCell(int x, int y, Direction d) {
        super(x, y);
        this.direction = d;
    }

    public int getImage() {
        return this.direction.ordinal();
    }

    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public boolean isSolid(Direction d) {
        return this.direction != d;
    }
}

