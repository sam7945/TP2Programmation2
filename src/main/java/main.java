import java.io.IOException;
import java.util.Optional;

public class main {
    public static void main(String[] args) {
        Optional<TexteOriginal> optional = File.readFile();

        if (optional.isEmpty()) {
            System.out.println("Not found!");
        } else {
            TexteOriginal texteOriginal = optional.get();
            Convertisseur convertisseur = new Convertisseur(texteOriginal);
            String codesRetour =  convertisseur.conversionCode(texteOriginal);
            try {
                File.writeFile(codesRetour, texteOriginal.nomFichier);
            } catch (IOException e) {
                System.err.println("Une erreur est survenue lors de " +
                        "l'écriture du fichier.");
            }
        }
    }


}
