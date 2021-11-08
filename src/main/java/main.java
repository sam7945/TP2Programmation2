import java.util.HashMap;
import java.util.Optional;

public class main {
    public static void main(String[] args) {
        Optional<TexteOriginal> optional = File.readFile();
        HashMap<String,String> hiraganas = Tableaux.hiraganas;
        HashMap<String,String> kataganas = Tableaux.kataganas;
        
        if (optional.isEmpty()) {
            System.out.println("Not found!");
        }
        else{
            TexteOriginal texteOriginal = optional.get();
        }
    }
}
