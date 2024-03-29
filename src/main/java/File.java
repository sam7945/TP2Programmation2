import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

/**
 * Classe servant à la lectures et écriture de fichier.
 *
 * @Auteur Samuel Dextraze
 * @Auteur Christophe Cloutier
 */
public class File {

    /**
     * Lis le fichier en entrée et récupère les données nécessaire au traitement.
     *
     * @return Retourne un Optional contenant soit l'objet espéré soit un
     * empty pour dire que le fichier est vide.
     */
    public static Optional<TexteOriginal> lectureFichierTxt() {
        String nom = demanderNomFichier();
        StringBuilder fileContent = new StringBuilder();
        Optional<TexteOriginal> optional = Optional.empty();

        try {
            Path path = FileSystems.getDefault().getPath(nom + ".txt");
            Scanner sc = new Scanner(Files.newBufferedReader(path));
            int nbLigne = 0;
            sc.useLocale(Locale.CANADA);
            while (sc.hasNext()) {
                fileContent.append(sc.nextLine() + '\n');
                nbLigne++;
            }
            TexteOriginal texteOriginal =
                    new TexteOriginal(fileContent.toString(), nom, nbLigne);
            optional = Optional.of(texteOriginal);
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println("Le fichier spécifié est introuvable.");
        } catch (IOException e) {
            System.err.println("Une erreur est survenue lors de la lecture du"
                    + " fichier.");
        }
        return optional;
    }

    /**
     * Prend un texte en String et écrit le fichier.
     *
     * @param texte      Texte complet sous format HTML
     * @param nomFichier Nom du fichier dans lequel écrire(Sans extension).
     * @throws IOException
     */
    public static void ecritureFichierHTML(String texte, String nomFichier)
            throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter
                (nomFichier + ".html"));
        writer.write(texte);

        writer.close();
    }

    /**
     * Demande à l'utilisateur d'entrer le nom d'un fichier à l'écran.
     *
     * @return le nom du fichier entré par l'utilisateur.
     */
    private static String demanderNomFichier() {
        String nom;

        System.out.println("Veuillez entrer le nom de votre fichier texte " +
                "sans mettre l'extension \".txt\":");
        Scanner clavier = new Scanner(System.in);
        nom = clavier.nextLine();
        clavier.close();
        return nom;
    }
}
