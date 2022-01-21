
package CheminFichier;

import CheminFichier.CheminFichier;
import Fichier.Dossier;

public class CheminFichierErreur extends CheminFichier implements ICheminFichier {

    private static final String EXTENSION_ERR = "erreur";

    protected CheminFichierErreur(ICheminFichier cheminFichierExtrant) {
        super(formatterCheminFichier(cheminFichierExtrant));
    }

    @Override
    public String getChemin() {
        setDate();
        return String.format("%s%s %s",
                Dossier.ERREUR,
                dateConteneur.toStringComplet(),
                super.getChemin());
    }

    @Override
    public String toString() {
        return "Chemin Fichier Erreur : " + getChemin();
    }

    private static String formatterCheminFichier(ICheminFichier cheminFichierExtrant) {

        String cheminFichierErreur = EXTENSION_ERR + ARG_EXTENSION;

        if (!(cheminFichierExtrant == null ||
                estNullOuVide(cheminFichierExtrant.getChemin()))) {

            cheminFichierErreur = String.format("%s-%s",
                    EXTENSION_ERR, retirerDossier(cheminFichierExtrant.getChemin()));
        }

        return cheminFichierErreur;
    }

    private static String retirerDossier(String chemin) {
        String[] chemins = chemin.split("/");
        chemin = chemins[chemins.length - 1];
        chemins = chemin.split("\\\\");
        chemin = chemins[chemins.length - 1];
        return chemin;
    }





}
