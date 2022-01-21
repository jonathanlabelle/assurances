
package CheminFichier;

import Erreur.ErreurMessage;
import Erreur.ErreurSortie;
import Outils.DateConteneur;

class CheminFichier implements ICheminFichier{

    // VARIABLE STATIC
    protected static final String ARG_EXTENSION = ".json";
    protected static final int ARG_LONGUEUR_MIN = ARG_EXTENSION.length() + 1;

    // VARIABLE DE CLASSE
    protected String cheminFichier;
    protected DateConteneur dateConteneur;


    // CONSTRUCTEUR
    protected CheminFichier(String cheminFichier) {
        setCheminFichier(cheminFichier);
        setDate();
    }


    //GET
    public String getChemin() {
        return cheminFichier;
    }

    public DateConteneur getDate() {
        return dateConteneur;
    }

    protected void setCheminFichier(String cheminFichier) {

        if (estCheminValide(cheminFichier)) {
            this.cheminFichier = cheminFichier;
        } else {
            throw new IllegalArgumentException();
        }
    }


    // TO STRING
    @Override
    public String toString() {
        return "Chemin Fichier : " + getChemin();
    }


    // METHODES PRIVATE
    protected static boolean estCheminValide(String argument) {
        return !estNullOuVide(argument) &&
                estExtensionValide(argument) &&
                estLongeurValide(argument);
    }

    private static boolean estLongeurValide(String argument) {
        return argument.length() >= ARG_LONGUEUR_MIN;
    }

    private static boolean estExtensionValide(String argument) {
        return argument.endsWith(ARG_EXTENSION);
    }

    protected static boolean estNullOuVide(String argument) {
        return argument == null || argument.isEmpty();
    }

    protected void setDate() {
        this.dateConteneur = new DateConteneur();
    }

}
