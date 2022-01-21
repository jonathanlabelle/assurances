
package Outils;

public class DateFactory {

    public DateFactory() {
    }

    public static DateConteneur obtenir(String date) {

        DateConteneur dateConteneur = null;

        int nombre = date.split("-").length;
        String annee = date.split("-")[0];
        String mois = date.split("-")[1];
        String jour = "0";
        if (nombre == 3) jour = date.split("-")[2];

        if (StringAnalyse.neContientQueDesChiffres(annee + mois + jour)){

            if (nombre == 2 && estVraiDate(Integer.parseInt(annee), Integer.parseInt(mois)))
                dateConteneur = new DateConteneur(Integer.parseInt(annee), Integer.parseInt(mois));
            if (nombre == 3 && estVraiDate(Integer.parseInt(annee), Integer.parseInt(mois), Integer.parseInt(jour)))
                dateConteneur = new DateConteneur(Integer.parseInt(annee), Integer.parseInt(mois), Integer.parseInt(jour));
        }

        return dateConteneur;
    }

    // VERIFICATION
    private static boolean estVraiDate(int annee, int mois, int jour) {

        boolean estVraiDate = false;
        int longueurMois;

        if (estMoisValide(mois)) {

            longueurMois = longueurMoisEnJour(annee, mois);
            estVraiDate = estJourInclus(jour, longueurMois);
        }

        return estVraiDate;
    }

    private static boolean estVraiDate(int annee, int mois) {
        return DateFactory.estVraiDate(annee, mois, 1);
    }

    private static boolean estMoisValide(int mois) {
        return mois > 0 && mois <= 12;
    }

    private static boolean estBisextile(int annee) {
        return ((annee % 400 == 0) || ((annee % 4 == 0) && (annee % 100 != 0)));
    }

    private static boolean estJourInclus(int jour, int moisLongueur) {
        return (jour >= 1 && jour <= moisLongueur);
    }

    private static int longueurMoisEnJour(int annee, int mois) {

        int monthLength = 31;

        if ((mois == 4) || (mois == 6) || (mois == 9) || (mois == 11)) {
            monthLength = 30;
        } else if (mois == 2) {

            if (estBisextile(annee)) {
                monthLength = 29;
            } else {
                monthLength = 28;
            }
        }

        return monthLength;
    }

}
