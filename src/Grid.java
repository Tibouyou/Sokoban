import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class Grid extends Observable implements Observer {
    private Case[][] grid;
    private Entity[][] entities;

    private int width, height;

    private Player player = new Player();

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Case[width][height];
        this.entities = new Entity[width][height];
        this.entities[3][3] = new Box(3, 3);
        this.entities[4][4] = new Box(4, 4);
        this.entities[0][0] = this.player;
        player.addObserver(this);
    }

    public void addCase(Case c) {
        this.grid[c.getX()][c.getY()] = c;
    }

    public Entity getEntity(int x, int y) {
        return this.entities[x][y];
    }

    public Case getCase(int x,int y) {
        if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
            return null;
        }
        return this.grid[x][y];
    }

    public void movePlayer(Direction d) {
        this.player.move(d, this);
    }

    @Override
    public void update(Observable o, Object arg) {
        Entity[][] newEntities = new Entity[this.width][this.height];
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (this.entities[i][j] != null) {
                    int x = this.entities[i][j].getX();
                    int y = this.entities[i][j].getY();
                    newEntities[x][y] = this.entities[i][j];
                }
            }
        }
        this.entities = newEntities;
        return;
    }

    public Player getPlayer() {
        return this.player;
    }
}
