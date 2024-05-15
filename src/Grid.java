import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

public class Grid extends Observable implements Observer {
    private Case[][] cases;
    private int currentLevel = 1;
    private Entity[][] entities;

    private int width, height;

    private Player player = new Player();

    public Grid() {
        loadLevel();
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void addCase(Case c) {
        this.cases[c.getX()][c.getY()] = c;
    }

    public Entity getEntity(int x, int y) {
        return this.entities[x][y];
    }

    public Case getCase(int x,int y) {
        if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
            return null;
        }
        return this.cases[x][y];
    }

    public void movePlayer(Direction d) {
        this.player.move(d, this);
    }

    @Override
    public void update(Observable o, Object arg) {
        int x = ((Entity) o).getX();
        int y = ((Entity) o).getY();
        this.entities[x][y] = (Entity) o;
        if (arg != null) {
            ArrayList<Integer> oldcoords = (ArrayList<Integer>) arg;
            this.entities[oldcoords.get(0)][oldcoords.get(1)] = null;
        }
        if (isLevelWin() && o.getClass()==Player.class) {
            this.currentLevel++;
            loadLevel();
        }
        setChanged();
        notifyObservers();
    }

    public boolean isLevelWin() {
        boolean isWin = true;
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (this.cases[i][j] instanceof Sensor) {
                    isWin = isWin && ((Sensor) this.cases[i][j]).isActivated(this);
                }
            }
        }
        return isWin;
    }

    public void loadLevel() {
        try {
            FileReader file = new FileReader("data/level" + this.currentLevel + ".txt");
            BufferedReader reader = new BufferedReader(file);

            String line = reader.readLine();
            String[] size = line.split(" ");
            this.width = Integer.parseInt(size[0]);
            this.height = Integer.parseInt(size[1]);

            this.cases = new Case[this.width][this.height];
            this.entities = new Entity[this.width][this.height];
            line = reader.readLine();

            int compteur = 0;
            while (line != null) {
                String[] parts = line.split(" ");
                System.out.println(Arrays.toString(parts));
                for (int i = 0; i < parts.length; i++) {
                    switch (parts[i]) {
                        case "#":
                            this.cases[i][compteur] = new Wall(i, compteur);
                            break;
                        case "_":
                            this.cases[i][compteur] = new Sensor(i, compteur);
                            break;
                        case "P":
                            this.player = new Player(i, compteur);
                            this.entities[i][compteur] = this.player;
                            this.player.addObserver(this);
                            this.cases[i][compteur] = new Air(i, compteur);
                            break;
                        case "B":
                            this.entities[i][compteur] = new Box(i, compteur);
                            this.entities[i][compteur].addObserver(this);
                            this.cases[i][compteur] = new Air(i, compteur);
                            break;
                        default:
                            this.cases[i][compteur] = new Air(i, compteur);
                            break;
                    }
                }
                compteur++;
                line = reader.readLine();
            }
            reader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch (IOException e) {
            System.out.println("Error reading file");
        }
    }

    public Player getPlayer() {
        return this.player;
    }
}
