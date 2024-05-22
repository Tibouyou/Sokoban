package Cell;

import Enum.Direction;

public class Air extends Cell {
    public Air() {
        super();
    }
    public Air(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isSolid(Direction d) {
        return false;
    }
}
