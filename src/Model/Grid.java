package Model;

import Cell.*;
import Entity.*;
import Enum.Direction;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Grid extends Observable implements Observer {
    private int currentLevel = 0;
    private Cell[][] cells;
    private Entity[][] entities;
    private int width, height;
    private Player player = new Player();
    public Grid() {
        loadLevel();
    }
    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return this.height;
    }
    public Entity getEntity(int x, int y) {
        if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
            return null;
        }
        return this.entities[x][y];
    }
    public Cell getCell(int x, int y) {
        if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
            return null;
        }
        return this.cells[x][y];
    }
    public void movePlayer(Direction d) {
        this.player.move(d, this);
    }
    @Override
    public void update(Observable o, Object arg) {
        int x = ((Entity) o).getX();
        int y = ((Entity) o).getY();
        this.entities[x][y] = (Entity) o;
        // If the entity is a box and is on a trap, remove the box and the trap
        if (o.getClass() == Box.class) {
            if (cells[x][y] instanceof Trap) {
                this.entities[x][y] = null;
                this.cells[x][y] = new Air(x, y);
            }
        }
        // If the entity is a player and is on a trap, reload the level
        else if (o.getClass() == Player.class) {
            if (cells[x][y] instanceof Trap) {
                loadLevel();
                return;
            }
        }
        if (isLevelWin() && o.getClass()== Player.class) {
            this.currentLevel++;
            loadLevel();
            return;
        }
        ArrayList<Integer> oldcoords = (ArrayList<Integer>) arg;
        this.entities[oldcoords.get(0)][oldcoords.get(1)] = null;
        ArrayList<Integer> coords = new ArrayList<Integer>();
        coords.add(x);
        coords.add(y);
        coords.add(oldcoords.get(0));
        coords.add(oldcoords.get(1));
        setChanged();
        notifyObservers(coords);
    }

    public boolean isLevelWin() {
        boolean isWin = true;
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (this.cells[i][j] instanceof Sensor) {
                    isWin = isWin && ((Sensor) this.cells[i][j]).isActivated(this);
                }
            }
        }
        return isWin;
    }

    public void loadLevel() {
        try {
            FileReader file = new FileReader("data/levels/level" + this.currentLevel + ".txt");
            BufferedReader reader = new BufferedReader(file);

            String line = reader.readLine();
            String[] size = line.split(" ");
            this.width = Integer.parseInt(size[0]);
            this.height = Integer.parseInt(size[1]);

            this.cells = new Cell[this.width][this.height];
            this.entities = new Entity[this.width][this.height];
            line = reader.readLine();

            int compteur = 0;
            while (line != null) {
                String[] parts = line.split(" ");
                for (int i = 0; i < parts.length; i++) {
                    switch (parts[i]) {
                        case "#":
                            this.cells[i][compteur] = new Wall(i, compteur);
                            break;
                        case "_":
                            this.cells[i][compteur] = new Sensor(i, compteur);
                            break;
                        case "X":
                            this.cells[i][compteur] = new Trap(i, compteur);
                            break;
                        case "P":
                            this.player = new Player(i, compteur);
                            this.entities[i][compteur] = this.player;
                            this.player.addObserver(this);
                            this.cells[i][compteur] = new Air(i, compteur);
                            break;
                        case "B":
                            this.entities[i][compteur] = new Box(i, compteur);
                            this.entities[i][compteur].addObserver(this);
                            this.cells[i][compteur] = new Air(i, compteur);
                            break;
                        case "↑":
                            this.cells[i][compteur] = new DirectionnalCell(i, compteur, Direction.UP);
                            break;
                        case "↓":
                            this.cells[i][compteur] = new DirectionnalCell(i, compteur, Direction.DOWN);
                            break;
                        case "←":
                            this.cells[i][compteur] = new DirectionnalCell(i, compteur, Direction.LEFT);
                            break;
                        case "→":
                            this.cells[i][compteur] = new DirectionnalCell(i, compteur, Direction.RIGHT);
                            break;
                        default:
                            this.cells[i][compteur] = new Air(i, compteur);
                            break;
                    }
                }
                compteur++;
                line = reader.readLine();
            }
            reader.close();
            setChanged();
            notifyObservers(true);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch (IOException e) {
            System.out.println("Error reading file");
        }
    }
}
