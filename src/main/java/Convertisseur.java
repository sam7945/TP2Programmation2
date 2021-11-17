import java.util.ArrayList;

/**
 * Classe servant à convertir un texte écrit en Japonais sous format de
 * caractère ascii vers unicode HTML.
 *
 * @Auteur Samuel Dextraze
 * @Auteur Christophe Cloutier
 */
public class Convertisseur {
    TexteOriginal texteOriginal;

    /**
     * Initialise la classe avec un objet de type <code>texteOriginal</code>
     *
     * @param texteOriginal Objet contenant le texte à convertir, son nom et
     *                      le nombre de ligne qu'il contient.
     */
    public Convertisseur(TexteOriginal texteOriginal) {
        this.texteOriginal = texteOriginal;
    }

    /**
     * Convertie le texte original passé dans le constructeur et le retourne
     * en <code>String</code> sous forme de texte HTML.
     *
     * @return Retourne le texte originale en format HTML.
     */
    public String conversionCode() {
        String retour = "";

        ArrayList<Ligne> lignes = new ArrayList<>();
        ArrayList<String> codes = new ArrayList<>();
        int ligneActuelle = 0;
        String syllabeTemp = "";

        for (Character c : texteOriginal.texteString.toCharArray()) {
            syllabeTemp += c;
            if (c == '\n') {
                lignes.add(new Ligne(ligneActuelle, codes));
                ligneActuelle++;
                codes = new ArrayList<>();
                syllabeTemp = "";
            } else if (c == ' ')
                syllabeTemp = "";
            else if (Tableaux.voyelleMin.contains(c) ||
                    Tableaux.voyelleMaj.contains(c) || c == '\'') {
                if (syllabeTemp.length() > 2 && (syllabeTemp.charAt(1) == 'y'
                        || syllabeTemp.charAt(1) == 'Y')) {
                    convMultiple(codes, syllabeTemp);
                    syllabeTemp = "";
                } else {
                    codes.add(convSyllabe(syllabeTemp));
                    syllabeTemp = "";
                }
            }
        }
        retour = debutHtml();
        retour += textToHtml(lignes);
        retour += finHtml();

        return retour;
    }

    /**
     * Prend la liste des lignes de code et le renvoie sous format HTML.
     *
     * @param lignesCodes La liste des lignes contenant les codes de chaques
     *                    syllabes.
     * @return Retourne la liste sous format HTML.
     */
    private String textToHtml(ArrayList<Ligne> lignesCodes) {
        String texteHtml = "";
        int nbLigne = lignesCodes.stream().map(i -> i.getCodes().size())
                .max(Integer::compareTo).get();
        int nbColonne = lignesCodes.size();

        for (int i = 0; i < nbLigne; i++) {
            texteHtml += "<tr>";
            for (int y = nbColonne - 1; y >= 0; y--) {
                if (lignesCodes.get(y).getCodes().size() <= i)
                    texteHtml += "<td></td>";
                else
                    texteHtml += "<td>" + lignesCodes.get(y).getCodes().get(i)
                            + "</td>";
            }
            texteHtml += "</tr>";
        }
        return texteHtml;
    }

    /**
     * Convertie 2 syllabes collées ensemble et les ajoutes à la liste de
     * codes passée en paramètre. Ne doit être appelé que si le
     * deuxième caractère de la syllabe est un y ou Y.
     *
     * @param codes   Liste des codes d'une ligne spécifique.
     * @param syllabe La "syllabe" à décortiquer pour y récupérer les 2
     *                syllabes séparées.
     */
    private void convMultiple(ArrayList<String> codes,
                              String syllabe) {
        String str1 = syllabe.substring(0, 1);
        String str2 = syllabe.substring(1);
        str1 += 'i';
        if (Tableaux.consonneMaj.contains(str1.charAt(0))) {
            str1 = str1.substring(0, 1).toUpperCase() + str1.substring(1)
                    .toLowerCase();
            str2 = str2.substring(0, 1).toUpperCase() + str2.substring(1)
                    .toLowerCase();
        } else {
            str1 = str1.toLowerCase();
            str2 = str2.toLowerCase();
        }
        codes.add(convSyllabe(str1)+convSyllabe(str2));
    }

    /**
     * Sert à convertir une syllabe individuel.
     *
     * @param syllabe Syllabe qui sera convertie en HTML unicode japonais.
     * @return Retourne l'unicode HTML correspondant à la syllabe ou ? si la
     * syllabe est introuvable.
     */
    private String convSyllabe(String syllabe) {
        if (Tableaux.consonneMaj.contains(syllabe.charAt(0)) ||
                Tableaux.voyelleMaj.contains(syllabe.charAt(0)))
            return convMaj(syllabe);
        else if (Tableaux.consonneMin.contains(syllabe.charAt(0)) ||
                Tableaux.voyelleMin.contains(syllabe.charAt(0)))
            return convMin(syllabe);
        return "?";


    }

    /**
     * Convertie une syllabe minuscule spécifique.
     *
     * @param syllabe Syllabe en minuscule.
     * @return Retourne l'unicode HTML correspondant à la syllabe.
     */
    private String convMin(String syllabe) {
        syllabe = syllabe.toLowerCase();
        return "&#" + Tableaux.hiraganas.get(syllabe) + ";";
    }

    /**
     * Convertie une syllabe majuscule spécifique.
     *
     * @param syllabe Syllabe en majuscule.
     * @return Retourne l'unicode HTML correspondant à la syllabe.
     */
    private String convMaj(String syllabe) {
        syllabe = syllabe.substring(0, 1).toUpperCase() + syllabe.substring(1)
                .toLowerCase();
        return "&#" + Tableaux.kataganas.get(syllabe) + ";";
    }

    /**
     * Code de début de fichier HTML
     *
     * @return Retourne le début du code HTML pour faire le tableau.
     */
    private String debutHtml() {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                " <head>\n" +
                " <title>TP 2</title>\n" +
                " </head>\n" +
                " <body>\n" +
                " <hr>\n" +
                " <table>";
    }

    /**
     * Code de fin de fichier HTML
     *
     * @return Retourne le fin du code HTML pour faire le tableau.
     */
    private String finHtml() {
        return "</table>\n" +
                " <hr>\n" +
                " </body>\n" +
                "</html>";
    }
}
