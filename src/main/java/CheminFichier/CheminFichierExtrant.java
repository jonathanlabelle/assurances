
package CheminFichier;

import CheminFichier.CheminFichier;
import Fichier.FichierLire;

public class CheminFichierExtrant extends CheminFichier implements ICheminFichier {

    private static final String EXTENSION_EX = "-export";
    private static final int CHEMIN_ARGUMENT_INDEX = 1;

    protected CheminFichierExtrant(String[] cheminFichiers, ICheminFichier cheminFichierIntrant) {
        super(formatterCheminFichier(cheminFichiers, cheminFichierIntrant));
    }

    @Override
    public String toString() {
        return "Chemin Fichier Extrant : " + super.getChemin();
    }


    //    PRIVATE
    private static String formatterCheminFichier(String[] cheminFichiers,
                                                 ICheminFichier cheminFichierIntrant){

        String cheminFichier;
        cheminFichiers = empecherReecriture(cheminFichiers);

        if (cheminFichiers.length > 1 && !cheminFichiers[1].isEmpty()) {
            cheminFichier = formaterCheminFichierRecu(cheminFichiers, cheminFichierIntrant);
        } else if (cheminFichiers.length > 0 && !cheminFichiers[0].isEmpty()){
            cheminFichier = renommerExtrantSelonIntrant(cheminFichierIntrant);
        } else {
            throw new IllegalArgumentException();
        }

        return renommerSiExiste(cheminFichier);
    }

    private static String renommerExtrantSelonIntrant(ICheminFichier cheminFichierIntrant) {

        String nomCheminFichierExtrant = null;
        String nomCheminFichierInstrant = nomCheminFichierIntrant(cheminFichierIntrant);

        if (!(nomCheminFichierInstrant.equals(""))) {
            nomCheminFichierExtrant = ajouterIdentifiant(nomCheminFichierInstrant);
        }

        return nomCheminFichierExtrant;
    }

    private static String ajouterExtension(String cheminFichier) {
        return cheminFichier.concat(ARG_EXTENSION);
    }

    private static String ajouterIdentifiant(String nomFichierIn) {

        String nomFichierEx = nomFichierIn.substring(0,nomFichierIn.length()-ARG_EXTENSION.length());
        return nomFichierEx + EXTENSION_EX + ARG_EXTENSION;
    }

    private static String[] empecherReecriture(String[] cheminFichiers) {

        if (cheminFichiers.length > 1 && cheminFichiers[0].equals(cheminFichiers[1])) {
            cheminFichiers[1] = "";
        }

        return cheminFichiers;
    }

    private static String formaterCheminFichierRecu(String[] cheminFichiers,
                                                    ICheminFichier cheminFichierIntrant) {

        String cheminFichier;

        if (estCheminValide(cheminFichiers[1])) {
            cheminFichier = cheminFichiers[1];
        } else if (ajouterExtension(cheminFichiers[1]).equals(nomCheminFichierIntrant(cheminFichierIntrant))){
            cheminFichier = ajouterIdentifiant(ajouterExtension(cheminFichiers[1]));
        } else {
            cheminFichier = ajouterExtension(cheminFichiers[1]);
        }

        return cheminFichier;
    }

    private static String nomCheminFichierIntrant(ICheminFichier cheminFichierIntrant) {

        String nomCheminFichier = "";

        if (!(cheminFichierIntrant == null)) {
            nomCheminFichier = cheminFichierIntrant.getChemin();
        }

        return nomCheminFichier;
    }

    private static String renommerSiExiste(String cheminFichier) {

        String chemin = cheminFichier.substring(0, cheminFichier.length()-ARG_EXTENSION.length());
        int compteur = 0;

        if (FichierLire.fichierExiste(cheminFichier)) {
            do {
                compteur ++;
            } while(FichierLire.fichierExiste(chemin + compteur + ARG_EXTENSION));
            cheminFichier = chemin + compteur + ARG_EXTENSION;
        }

        return cheminFichier;
    }

}
