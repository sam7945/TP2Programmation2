/**
 * Classe contenant le texte original et les informations qui y sont
 * spécifiques.
 *
 * @Auteur Samuel Dextraze
 * @Auteur Christophe Cloutier
 */
public class TexteOriginal {
    String texteString;
    String nomFichier;
    int nbLigne;

    /**
     * Initialisation de <code>TexteOriginal</code>.
     *
     * @param texteString Texte sous format pur.
     * @param nomFichier  Nom du ficher d'ou provient le texte.
     * @param nbLigne     Nombre de ligne contenue dans le texte.
     */
    public TexteOriginal(String texteString, String nomFichier, int nbLigne) {
        this.texteString = texteString;
        this.nbLigne = nbLigne;
        this.nomFichier = nomFichier;
    }
}
