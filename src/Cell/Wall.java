package Cell;

import Enum.Direction;
public class Wall extends Cell {
    public Wall() {
        super();
    }
    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isSolid(Direction d) {
        return true;
    }
}
