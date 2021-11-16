import java.io.IOException;
import java.util.Optional;
/**
 * @Auteur Samuel Dextraze
 * @Auteur Christophe Cloutier
 */

public class main {
    public static void main(String[] args) {
        Optional<TexteOriginal> optional = File.lectureFichierTxt();

        if (optional.isEmpty()) {
            System.out.println("Not found!");
        } else {
            TexteOriginal texteOriginal = optional.get();
            Convertisseur convertisseur = new Convertisseur(texteOriginal);
            String codesRetour =  convertisseur.conversionCode();
            try {
                File.ecritureFichierHTML(codesRetour, texteOriginal.nomFichier);
            } catch (IOException e) {
                System.err.println("Une erreur est survenue lors de " +
                        "l'écriture du fichier.");
            }
        }
    }


}
