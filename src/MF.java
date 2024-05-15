import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observer;
import java.util.Observable;


public class MF extends JFrame {
    private static final int L = 8;
    private static final int H = 15;
    private JPanel[][] tabC = new JPanel[L][H];
    private Grid g;

    private BufferedImage background = ImageIO.read(new File("data/background.png"));
    private BufferedImage wall;
    private BufferedImage player;
    private BufferedImage box = ImageIO.read(new File("data/box.png"));
    private BufferedImage sensor;


    public MF(Grid g) throws IOException {
        this.g = g;
        build();
        addEC();
    }
    public void build() {
        setTitle("Sokoban");
        setSize(H*70, L*70);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel jp = new JPanel(new BorderLayout());
        JPanel jpC = new JPanel(new GridLayout(L, H));
        JPanel jpInfo = new JPanel(new BorderLayout());
        jp.add(jpC, BorderLayout.CENTER);
        jp.add(jpInfo, BorderLayout.EAST);
        add(jp);
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < H; j++) {
                tabC[i][j] = new JPanel();
                tabC[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
                }
                update();
            }
        });
        requestFocus();
    }

    public void paint(Graphics g, BufferedImage image, int x, int y) {
        super.paint(g);
        g.drawImage(image, x, y, this);
    }

    public void update() {
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
                        Image newBoxSize = box.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(newBoxSize);
                        tabC[g.getEntity(i, j).getY()][g.getEntity(i, j).getX()].add(new JLabel(icon));
                        setVisible(true);
                    }
                    else if (g.getEntity(i, j) instanceof Player) {
                        System.out.println(g.getEntity(i, j).getX());
                        System.out.println(g.getEntity(i, j).getY());
                        tabC[g.getEntity(i, j).getY()][g.getEntity(i, j).getX()].setBackground(Color.RED);
                    }
                }
            }
        }
    }
}
