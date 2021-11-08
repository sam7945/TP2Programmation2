import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

/**
 * @Auteur Samuel Dextraze
 * @Auteur Christophe Cloutier
 */
public class File {

    /**
     * Lis le fichier en entrée et récupère les données nécessaire au traitement.
     */
    public static Optional<TexteOriginal> readFile() {
        String nom = demanderNomFichier();
        StringBuilder fileContent = new StringBuilder();
        Optional<TexteOriginal> optional = Optional.empty();

        try {
            Path path = FileSystems.getDefault().getPath(nom);
            Scanner sc = new Scanner(Files.newBufferedReader(path));
            int nbLigne = 0;
            sc.useLocale(Locale.CANADA);
            while(sc.hasNext()){
                fileContent.append(sc.nextLine());
                nbLigne++;
            }
            TexteOriginal texteOriginal = new TexteOriginal(fileContent.toString(), nbLigne);
            optional =  Optional.of(texteOriginal);
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("Le fichier spécifié est introuvable.");
        } catch (IOException e) {
            System.err.println("Une erreur est survenue lors de la lecture du" +
                    " fichier.");
        }
        return optional;
    }

    /**
     * Demande à l'utilisateur d'entrer le nom d'un fichier à l'écran.
     *
     * @return le nom du fichier entré par l'utilisateur.
     */
    private static String demanderNomFichier() {
        String nom;

        System.out.println("Veuillez entrer le nom de votre fichier :");
        Scanner clavier = new Scanner(System.in);
        nom = clavier.nextLine();
        clavier.close();
        return nom;
    }
}
