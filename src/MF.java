import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observer;
import java.util.Observable;

public class MF extends JFrame implements Observer {
    private static final int L = 10;
    private static final int H = 10;
    private Player p = new Player();
    private JPanel[][] tabC = new JPanel[L][H];
    private Grid g;


    public MF(Grid g) {
        this.g = g;
        build();
        addEC();
    }
    public void build() {
        p.addObserver(this);
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
                        p.move(Direction.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        p.move(Direction.DOWN);
                        break;
                    case KeyEvent.VK_LEFT:
                        p.move(Direction.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        p.move(Direction.RIGHT);
                        break;
                }
            }
        });
        requestFocus();
    }

    public void update(Observable o, Object arg) {
        System.out.println("update");
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < H; j++) {
                tabC[i][j].setBackground(Color.WHITE);
            }
        }
        tabC[p.getY()][p.getX()].setBackground(Color.RED);
    }
}
