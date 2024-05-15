import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Grid g = new Grid();
        MF mf = new MF(g);
        mf.setVisible(true);
        mf.update();
    }
}