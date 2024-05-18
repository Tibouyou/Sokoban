import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;


public class MF extends JFrame implements Observer {
    private int L = 8;
    private int H = 15;
    private JPanel[][] tabC;
    private Grid g;
    JPanel jp = new JPanel(new BorderLayout());
    private BufferedImage background = ImageIO.read(new File("data/background.png"));
    private BufferedImage wall = ImageIO.read(new File("data/box.png"));
    private BufferedImage player = ImageIO.read(new File("data/player.png"));
    private BufferedImage box = ImageIO.read(new File("data/box.png"));
    private BufferedImage sensor;


    public MF(Grid g) throws IOException {
        this.g = g;
        g.addObserver(this);
        setTitle("Sokoban");
        setSize(H*100, L*100);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        build();
        addEC();
    }


    public void build() throws IOException {
        setSize(H*100, L*100);

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
                tabC[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                tabC[i][j].setBackground(Color.WHITE);
                jpC.add(tabC[i][j]);
            }
        }
        drawGame();
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
                }
            }
        });
        requestFocus();
    }

    private void drawGame() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < L; j++) {
                if (g.getCase(i, j) instanceof Wall) {
                    tabC[g.getCase(i, j).getY()][g.getCase(i, j).getX()].setBackground(Color.BLACK);
                } else if (g.getCase(i, j) instanceof Sensor) {
                    tabC[g.getCase(i, j).getY()][g.getCase(i, j).getX()].setBackground(Color.BLUE);
                } else if (g.getCase(i, j) instanceof Air) {
                    tabC[g.getCase(i, j).getY()][g.getCase(i, j).getX()].setBackground(Color.WHITE);
                    /*String coords = g.getCase(i, j).getY() + " " + g.getCase(i, j).getX();
                    JLabel jlabel = new JLabel(coords);
                    jlabel.setFont(new Font("Verdana",1,20));
                    tabC[g.getCase(i, j).getY()][g.getCase(i, j).getX()].add(jlabel);*/
                }
            }
        }
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < L; j++) {
                tabC[j][i].removeAll();
                tabC[j][i].updateUI();
                if (g.getEntity(i, j) != null) {
                    if (g.getEntity(i, j) instanceof Box) {
                        Image newBoxSize = box.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(newBoxSize);
                        tabC[g.getEntity(i, j).getY()][g.getEntity(i, j).getX()].add(new JLabel(icon));
                        setVisible(true);
                    }
                    else if (g.getEntity(i, j) instanceof Player) {
                        Image newPlayerSize = player.getScaledInstance(50, 80, Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(newPlayerSize);
                        tabC[g.getEntity(i, j).getY()][g.getEntity(i, j).getX()].add(new JLabel(icon));
                        setVisible(true);
                    }
                }
            }
        }
    }

    public void update(Observable o, Object arg) {
        if (arg != null) {
            this.L = ((ArrayList<Integer>) arg).get(1);
            this.H = ((ArrayList<Integer>) arg).get(0);
            try {
                this.build();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        drawGame();
    }
}
