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
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.grid[i][j] = new Air(i, j);
            }
        }
        this.entities = new Entity[width][height];
        this.entities[3][3] = new Box(3, 3);
        this.entities[4][4] = new Box(4, 4);
        this.entities[0][0] = this.player;
        this.grid[1][1] = new Sensor(1, 1);
        this.grid[2][2] = new Sensor(2, 2);
        this.grid[7][3] = new Wall(7, 3);
        player.addObserver(this);
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
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
        System.out.println(this.isLevelWin());
        setChanged();
        notifyObservers();
    }

    public boolean isLevelWin() {
        boolean isWin = true;
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (this.grid[i][j] instanceof Sensor) {
                    isWin = isWin && ((Sensor) this.grid[i][j]).isActivated(this);
                }
            }
        }
        return isWin;
    }

    public Player getPlayer() {
        return this.player;
    }
}
