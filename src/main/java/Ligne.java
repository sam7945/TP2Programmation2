import java.util.ArrayList;

public class Ligne {
    private int position;
    private ArrayList<String> codes;

    public Ligne(int position, ArrayList<String> codes) {
        this.position = position;
        this.codes = codes;
    }

    public int getPosition() {
        return position;
    }

    public ArrayList<String> getCodes() {
        return codes;
    }
}
