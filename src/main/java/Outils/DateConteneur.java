
package Outils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateConteneur {

    // CONSTANTES FORMAT
    private static final String JOUR_ABSOLUT = "D";

    private static final String ANNEE = "yyyy";
    private static final String MOIS = "MM";
    private static final String JOUR = "dd";

    private static final String HEURE = "HH";
    private static final String MINUTE = "mm";
    private static final String SECONDES = "s";
    private static final String NANO_SECONDES = "n";

    // VARIABLES
    private int annee;
    private int mois;
    private int jour;

    private int jourAbsolut;

    private int heure;
    private int minute;
    private int seconde;

    private int nanoSecondes;

    // FORMAT
    public static int dateFormat(String format) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        LocalDateTime dateTime = LocalDateTime.now();
        return Integer.parseInt(dtf.format(dateTime));
    }

    // GETTERS
    public int getAnnee() {
        return annee;
    }

    public int getMois() {
        return mois;
    }

    public int getJour() {
        return jour;
    }

    public int getHeure() { return heure; }

    public int getMinute() { return minute; }

    public int getSeconde() { return seconde; }

    public int getNanoSecondes() {
        return nanoSecondes;
    }

    public int getJourAbsolut() {return jourAbsolut;}


    // CONSTRUCTEUR
    public DateConteneur() {
        annee = anneeCourante();
        mois = moisCourante();
        jour = jourCourante();

        heure = heureCourante();
        minute = minuteCourante();
        seconde = secondeCourante();
        nanoSecondes = nanoSecondesCourante();

        jourAbsolut = jourAbsolutCourante();
    }

    protected DateConteneur(int annee, int mois) {
        this.mois = mois;
        this.annee = annee;
    }

    public DateConteneur(int annee, int mois, int jour) {
        this.mois = mois;
        this.annee = annee;
        this.jour = jour;
    }

    //TO STRING
    @Override
    public String toString() {
        return String.format("%s-%02d-%02d",
                this.annee,
                this.mois,
                this.jour);
    }

    public String toStringComplet() {
        return String.format("%s-%02d-%02d T %02d-%02d-%02d",
                this.annee,
                this.mois,
                this.jour,
                this.heure,
                this.minute,
                this.seconde);
    }

    public String toStringClient() {
        return String.format("%s-%02d",
                this.annee,
                this.mois);
    }

    //DATE COURRANTE
    public static int anneeCourante() {
        return dateFormat(ANNEE);
    }

    public static int moisCourante() {
        return dateFormat(MOIS);
    }

    public static int jourCourante() {
        return dateFormat(JOUR);
    }

    public static int heureCourante() {
        return dateFormat(HEURE);
    }

    public static int minuteCourante() {
        return dateFormat(MINUTE);
    }

    public static int secondeCourante() {
        return dateFormat(SECONDES);
    }

    public static int nanoSecondesCourante() {
        return dateFormat(NANO_SECONDES);
    }

    public static int jourAbsolutCourante() {
        return dateFormat(JOUR_ABSOLUT);
    }

    //COMPARAISON
    public boolean precede(DateConteneur dateConteneur) {

        boolean precede = false;

        if (this.annee < dateConteneur.annee) {
            precede = true;
        } else if (this.annee == dateConteneur.annee && this.mois < dateConteneur.mois) {
            precede = true;
        } else if (this.annee == dateConteneur.annee && this.mois == dateConteneur.mois) {
            if (this.jour != 0 && this.jour < dateConteneur.jour) {
                precede = true;
            }
        }

        return precede;
    }

    public boolean precedeOuMemeMois(DateConteneur dateConteneur) {
        return (!this.precede(dateConteneur)
                && !dateConteneur.precede(this))
                || this.precede(dateConteneur);
    }

    public boolean precedeOuMemeMoisPasse(DateConteneur dateConteneur) {
        return this.precedeOuMemeMois(dateConteneur)
                && this.precedeOuMemeMois(new DateConteneur())
                && dateConteneur.precedeOuMemeMois(new DateConteneur());
    }

    public boolean memeAnneMois(DateConteneur dateConteneur) {
        return this.annee == dateConteneur.annee
                && this.mois == dateConteneur.mois;
    }

    public boolean memeAnneMoisJour(DateConteneur dateConteneur) {
        return this.annee == dateConteneur.annee
                && this.mois == dateConteneur.mois
                && this.jour == dateConteneur.jour;
    }

}

















