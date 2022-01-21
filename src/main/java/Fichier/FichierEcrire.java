
package Fichier;

import CheminFichier.ICheminFichier;

import java.io.FileWriter;

public class FichierEcrire {

    private String contenuFichier;
    private ICheminFichier cheminFichier;
    private String erreur;

    //CONSTRUCTEUR
    public FichierEcrire(String contenuFichier, ICheminFichier cheminFichier) {
        this.contenuFichier = contenuFichier;
        this.cheminFichier = cheminFichier;
        this.erreur = ecrire(contenuFichier, cheminFichier.getChemin());
    }

    //STATIC
    public static String ecrire(String contenuFichier, String cheminFichier) {

        try (FileWriter fichier = new FileWriter(cheminFichier)) {

            fichier.write(contenuFichier);
            fichier.flush();
        } catch (Exception e) {
            return e.toString();
        }

        return null;
    }

    //GET
    public String getErreur() {
        return erreur;
    }

    public String getContenuFichier() {
        return contenuFichier;
    }

    public ICheminFichier getCheminFichier() {
        return cheminFichier;
    }
}

