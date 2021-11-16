import java.util.ArrayList;

/**
 * Classe servant à stocker les symbole unicode Japonais(code &#.....) sous
 * forme de liste.
 *
 * @Auteur Samuel Dextraze
 * @Auteur Christophe Cloutier
 */
public class Ligne {
    private int position;
    private ArrayList<String> codes;

    /**
     * Initialisation de Ligne.
     *
     * @param position Position de la ligne de 0 à infinie dans le texte.
     * @param codes    Liste des codes unicode HTML Japonais.
     */
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
