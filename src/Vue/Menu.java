package Vue;

import Vue.MF;

import javax.swing.*;
import java.io.IOException;

public class Menu extends JFrame {
    private JPanel panel1;
    private JSpinner spinner1;
    private JButton playButton;
    private JSpinner character;
    private MF gameFrame;

    public Menu(MF gameFrame) {
        setContentPane(panel1);
        this.gameFrame = gameFrame;
        setTitle("SokIOban");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        spinner1.addChangeListener(e -> {
            if ((int) spinner1.getValue() > 5) {
                spinner1.setValue((int)spinner1.getValue()-1);
            } else if ((int) spinner1.getValue() < 0) {
                spinner1.setValue(0);
            }
        });
        character.addChangeListener(e -> {
            if ((int) character.getValue() > 1) {
                character.setValue(1);
            } else if ((int) character.getValue() < 0) {
                character.setValue(0);
            }
        });
        playButton.addActionListener(e -> {
            this.setVisible(false);
            try {
                gameFrame.selectPlayer((int) character.getValue());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            gameFrame.loadlevel((int) spinner1.getValue());
        });
    }

}
