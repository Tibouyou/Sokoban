import Model.Grid;
import Vue.MF;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Grid g = new Grid();
        MF mf = new MF(g);
        Sound sound = new Sound();
        sound.playMusic();
    }
}