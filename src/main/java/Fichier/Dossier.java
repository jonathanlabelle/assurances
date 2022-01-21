
package Fichier;

import Erreur.ErreurSortie;

import java.io.*;


public class Dossier {

    public static final String ERREUR = ".erreur/";
    public static final String DATABASE = ".database/";

    public static void creer(String nomDossier){
        try {

            File file = new File(nomDossier);
            if ( file.mkdir() ) ErreurSortie.erreur("dossier.creer");

        } catch (Exception e) {
            ErreurSortie.terminer("dossier.ecrire");
        }

    }

}
