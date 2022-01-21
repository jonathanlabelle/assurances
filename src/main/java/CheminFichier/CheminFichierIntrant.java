
package CheminFichier;

import CheminFichier.CheminFichier;

public class CheminFichierIntrant extends CheminFichier{

    protected CheminFichierIntrant(String[] cheminFichiers) {
        super(formatterCheminFichier(cheminFichiers));
    }

    @Override
    public String toString() {
        return "Chemin Fichier Intrant : " + super.getChemin();
    }

    private static String formatterCheminFichier(String[] cheminFichiers){

        String cheminFichier;

        if (cheminFichiers.length > 0) {
            cheminFichier = cheminFichiers[0];
        } else {
            throw new IllegalArgumentException();
        }

        return cheminFichier;
    }

}
