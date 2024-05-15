import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observer;
import java.util.Observable;


public class MF extends JFrame {
    private static final int L = 8;
    private static final int H = 15;
    private JPanel[][] tabC = new JPanel[L][H];
    private Grid g;


    public MF(Grid g) {
        this.g = g;
        build();
        addEC();
    }
    public void build() {
        setTitle("Sokoban");
        setSize(H*50, L*50);
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

    public void update() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < L; j++) {
                if (g.getCase(i, j) instanceof Wall) {
                    tabC[g.getCase(i, j).getY()][g.getCase(i, j).getX()].setBackground(Color.BLACK);
                } else if (g.getCase(i, j) instanceof Sensor) {
                    tabC[g.getCase(i, j).getY()][g.getCase(i, j).getX()].setBackground(Color.BLUE);
                } else if (g.getCase(i, j) instanceof Air) {
                    tabC[g.getCase(i, j).getY()][g.getCase(i, j).getX()].setBackground(Color.WHITE);
                    String coords = g.getCase(i, j).getY() + " " + g.getCase(i, j).getX();
                    JLabel jlabel = new JLabel(coords);
                    jlabel.setFont(new Font("Verdana",1,20));
                    tabC[g.getCase(i, j).getY()][g.getCase(i, j).getX()].add(jlabel);
                }
            }
        }
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < L; j++) {
                if (g.getEntity(i, j) != null) {
                    if (g.getEntity(i, j) instanceof Box) {
                        tabC[g.getEntity(i, j).getY()][g.getEntity(i, j).getX()].setBackground(Color.GREEN);
                    }
                    else if (g.getEntity(i, j) instanceof Player) {
                        System.out.println("Player");
                        System.out.println(g.getEntity(i, j).getX());
                        System.out.println(g.getEntity(i, j).getY());
                        tabC[g.getEntity(i, j).getY()][g.getEntity(i, j).getX()].setBackground(Color.RED);
                    }
                }
            }
        }
    }
}
