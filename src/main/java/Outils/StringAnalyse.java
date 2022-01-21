
package Outils;

public class StringAnalyse {

    public static boolean neContientQueDesChiffres(String chaine) {

        boolean resultat = true;

        if (isNullOrEmpty(chaine)) {
            resultat = false;
        } else {
            for (int i = 0; i < chaine.length() && resultat; i++) {

                char num = chaine.charAt(i);
                resultat = estUnCarNum(num);
            }
        }

        return resultat;
    }

    public static boolean estUnCarNum(char car) {
        return (car >= '0' && car <= '9');
    }

    public static Boolean isNullOrEmpty(String str) {
        return (str == null || str.isEmpty());
    }

    public static int contientNCar(String chaine, char car) {

        int nbFois = 0;

        for (int i = 0; i < chaine.length(); i++) {

            char num = chaine.charAt(i);
            if (num == car) nbFois++;
        }

        return nbFois;
    }

    public static boolean estMinuscule(char character) {
        return character >= 'a' && character <= 'z';
    }

}
