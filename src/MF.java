import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observer;
import java.util.Observable;

public class MF extends JFrame implements Observer {
    private static final int L = 10;
    private static final int H = 10;
    private Case c;
    private JPanel[][] tabC = new JPanel[L][H];


    public MF() {
       build();
       addEC();
    }
    public void build() {
        JPanel jp = new JPanel(new BorderLayout());
        JPanel jpC = new JPanel(new GridLayout(L, H));
        JPanel jpInfo = new JPanel(new BorderLayout());
        jp.add(jpC, BorderLayout.CENTER);
        jp.add(jpInfo, BorderLayout.EAST);
        add(jp);
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < H; j++) {
                tabC[i][j] = new JPanel();
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
                        c.move(Direction.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        c.move(Direction.DOWN);
                        break;
                    case KeyEvent.VK_LEFT:
                        c.move(Direction.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        c.move(Direction.RIGHT);
                        break;
                }
            }
        });
        requestFocus();
    }

    @Override
    public void update(Observable o, Object arg) {
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < H; j++) {
                    tabC[i][j].setBackground(Color.BLACK);
                }
            }
    }

}