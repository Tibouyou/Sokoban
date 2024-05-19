import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;

import static java.lang.Math.min;


public class MF extends JFrame implements Observer {
    private Menu menu = new Menu(this);
    private int cellSize = 100;
    private int L = 8;
    private int H = 15;
    private JPanel[][] tabC;
    private Grid g;
    JPanel jp = new JPanel(new BorderLayout());
    private BufferedImage player = ImageIO.read(new File("data/player0.png"));
    private BufferedImage box = ImageIO.read(new File("data/box.png"));
    private BufferedImage sensor = ImageIO.read(new File("data/sensor.png"));
    private BufferedImage trap = ImageIO.read(new File("data/trap.png"));


    public MF(Grid g) throws IOException {
        this.g = g;
        g.addObserver(this);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.cellSize = (int) min(dim.height*0.9 / L, (double) dim.width / H);
        setTitle("Sokoban");
        setSize(cellSize * H, cellSize * L);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(jp);
        addEC();
    }

    public void loadlevel(int level) {
        g.setCurrentLevel(level);
        g.loadLevel();
        menu.setVisible(false);
        setVisible(true);
    }

    public void build() throws IOException {
        setSize(cellSize *H, cellSize *L);
        tabC = new JPanel[L][H];

        jp.removeAll();

        JPanel jpC = new JPanel(new GridLayout(L, H));
        JPanel jpInfo = new JPanel(new BorderLayout());
        jp.add(jpC, BorderLayout.CENTER);
        jp.add(jpInfo, BorderLayout.EAST);
        add(jp);

        for (int i = 0; i < L; i++) {
            for (int j = 0; j < H; j++) {
                tabC[i][j] = new JPanel();
                tabC[i][j].setBackground(Color.WHITE);
                jpC.add(tabC[i][j]);
            }
        }
    }

    public void addEC() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        g.movePlayer(Direction.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        g.movePlayer(Direction.DOWN);
                        break;
                    case KeyEvent.VK_LEFT:
                        g.movePlayer(Direction.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        g.movePlayer(Direction.RIGHT);
                        break;
                    case KeyEvent.VK_R:
                        g.loadLevel();
                        break;
                    case KeyEvent.VK_ESCAPE:
                        menu.setVisible(true);
                        setVisible(false);
                        break;
                }
            }
        });
    }

    private void drawGame() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < L; j++) {
                tabC[j][i].removeAll();
                tabC[j][i].updateUI();
                if (g.getEntity(i, j) != null) {
                    if (g.getEntity(i, j) instanceof Box) {
                        Image newBoxSize = box.getScaledInstance((int) (cellSize *0.88), (int) (cellSize *0.88), Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(newBoxSize);
                        tabC[g.getEntity(i, j).getY()][g.getEntity(i, j).getX()].add(new JLabel(icon));
                        setVisible(true);
                    }
                    else if (g.getEntity(i, j) instanceof Player) {
                        Image newPlayerSize = player.getScaledInstance((int) (cellSize *0.7), (int) (cellSize *0.88), Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(newPlayerSize);
                        tabC[g.getEntity(i, j).getY()][g.getEntity(i, j).getX()].add(new JLabel(icon));
                        setVisible(true);
                    }
                }
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < L; j++) {
                if (g.getCell(i, j) instanceof Wall) {
                    tabC[g.getCell(i, j).getY()][g.getCell(i, j).getX()].setBackground(Color.BLACK);
                } else if (g.getCell(i, j) instanceof Sensor) {
                    Image newSensorSize = sensor.getScaledInstance((int) (cellSize *0.75), (int) (cellSize *0.75), Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(newSensorSize);
                    tabC[g.getCell(i, j).getY()][g.getCell(i, j).getX()].add(new JLabel(icon));
                    setVisible(true);
                } else if (g.getCell(i, j) instanceof Trap) {
                    Image newTrapSize = trap.getScaledInstance((int) (cellSize *0.75), (int) (cellSize *0.75), Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(newTrapSize);
                    tabC[g.getCell(i, j).getY()][g.getCell(i, j).getX()].add(new JLabel(icon));
                    setVisible(true);
                } else if (g.getCell(i, j) instanceof Air) {
                    tabC[g.getCell(i, j).getY()][g.getCell(i, j).getX()].setBackground(Color.WHITE);
                }
            }
        }
    }

    public void update(Observable o, Object arg) {
        if (arg != null) {
            this.L = ((ArrayList<Integer>) arg).get(1);
            this.H = ((ArrayList<Integer>) arg).get(0);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.cellSize = (int) min(dim.height*0.9 / L, (double) dim.width / H);
            try {
                this.build();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        drawGame();
    }

    public void selectPlayer(int value) throws IOException {
        this.player = ImageIO.read(new File("data/player"+value+".png"));
    }
}
