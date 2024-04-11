
public class Main {
    public static void main(String[] args) {
        Case c = new Case();
        MF mf = new MF();
        c.addObserver(mf);
        mf.setVisible(true);
    }
}