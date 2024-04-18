import java.util.Map;

public class Grid {
    private Case[][] grid;
    private int width, height;
    private Map<Case, Position> map;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Case[width][height];
    }

    public void addCase(Case c, Position p) {
        this.grid[p.getX()][p.getY()] = c;
        this.map.put(c, p);
    }

    public Case getCase(Position p) {
        if (p.getX() < 0 || p.getX() >= this.width || p.getY() < 0 || p.getY() >= this.height) {
            return null;
        }
        return this.grid[p.getX()][p.getY()];
    }

}
