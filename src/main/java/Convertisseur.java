import java.util.ArrayList;

public class Convertisseur {
    TexteOriginal texteOriginal;

    public Convertisseur(TexteOriginal texteOriginal) {
        this.texteOriginal = texteOriginal;
    }

    public String conversionCode(TexteOriginal texteOriginal) {
        String retour = "";

        ArrayList<Ligne> lignes = new ArrayList<>();
        ArrayList<String> codes = new ArrayList<>();
        int ligneActuelle = 0;
        String syllabeTemp = "";

        for (Character c : texteOriginal.texteString.toCharArray()) {
            syllabeTemp += c;
            if (Tableaux.voyelleMin.contains(c) || Tableaux.voyelleMaj.contains(c)) {
                if (syllabeTemp.length() > 2 && (syllabeTemp.charAt(1) == 'y' || syllabeTemp.charAt(1) == 'Y'))
                    convMultiple(codes, syllabeTemp);
                else
                    codes.add(convSyllabe(syllabeTemp));
            } else if (c == '\n') {
                lignes.add(new Ligne(ligneActuelle, codes));
                ligneActuelle++;
                codes = new ArrayList<>();
            }
        }
        retour = textToHtml(lignes);


        return retour;
    }

    private String textToHtml(ArrayList<Ligne> lignesCodes){
        
    }

    private void convMultiple(ArrayList<String> codes,
                                           String syllabe) {
        String str1 = syllabe.substring(0, 1);
        String str2 = syllabe.substring(1);
        str1 += 'i';
        if (Tableaux.consonneMaj.contains(str1.charAt(0))) {
            str1 = str1.substring(0, 1).toUpperCase() + str1.substring(1).toLowerCase();
            str2 = str2.substring(0, 1).toUpperCase() + str2.substring(1).toLowerCase();
        } else {
            str1 = str1.toLowerCase();
            str2 = str2.toLowerCase();
        }
        codes.add(convSyllabe(str1));
        codes.add(convSyllabe(str2));

    }

    private String convSyllabe(String syllabe) {
        if (Tableaux.consonneMaj.contains(syllabe.charAt(0)))
            return convMaj(syllabe);
        else if (Tableaux.consonneMin.contains(syllabe.charAt(0)))
            return convMin(syllabe);
        return "?";


    }

    private String convMin(String syllabe) {
        syllabe = syllabe.toLowerCase();
        return Tableaux.hiraganas.get(syllabe);
    }

    private String convMaj(String syllabe) {
        syllabe = syllabe.substring(0, 1).toUpperCase() + syllabe.substring(1);
        return Tableaux.kataganas.get(syllabe);
    }

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

    private String finHtml() {
        return "</table>\n" +
                " <hr>\n" +
                " </body>\n" +
                "</html>";
    }
}
