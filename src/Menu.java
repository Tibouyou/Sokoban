import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class Menu extends JFrame {
    private JPanel panel1;
    private JSpinner spinner1;
    private JButton playButton;
    private MF gameFrame;

    public Menu(MF gameFrame) {
        setContentPane(panel1);
        this.gameFrame = gameFrame;
        setTitle("Sokoban");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        playButton.addActionListener(e -> {
            this.setVisible(false);
            gameFrame.loadlevel((int) spinner1.getValue());
        });
    }
}
