import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws IOException {
        Grid g = new Grid();
        MF mf = new MF(g);
        Sound sound = new Sound();
        sound.playMusic();
    }
}