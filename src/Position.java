public class Position {
    private int x;
    private int y;
    public Position() {
        this.x = 0;
        this.y = 0;
    }
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public boolean equals(Position p) {
        return this.x == p.getX() && this.y == p.getY();
    }
    public boolean equals(int x, int y) {
        return this.x == x && this.y == y;
    }
    public boolean equals(Object o) {
        if (o instanceof Position) {
            Position p = (Position) o;
            return this.x == p.getX() && this.y == p.getY();
        }
        return false;
    }
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

}
