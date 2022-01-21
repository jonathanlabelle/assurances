
package Erreur;

import CheminFichier.CheminFichierFactory;
import CheminFichier.ICheminFichier;
import Fichier.Dossier;
import Fichier.FichierEcrire;
import Fichier.FichierTemps;
import Outils.Console;
import Statistique.Statistiques;
import org.json.JSONObject;

import java.util.ArrayList;

//TODO : refactor code du main
//TODO : var ArrayList de string pour contenir les multiples erreurs possibles?

public class ErreurSortie {

    static ArrayList<String> clefMessages = new ArrayList<>();
    static ICheminFichier cheminFichierErreur;


//    static

    public static void setCheminFichierErreur(ICheminFichier cheminFichier) {
        cheminFichierErreur = cheminFichier;
    }

    //BASE

    private static void ecrireErreur(String clefMessage) {
        clefMessages.add(clefMessage);
    }

    private static void afficherErreur(String clefMessage) {
        if (clefMessage.equals("fin.erreur")) System.err.println( ErreurMessage.get(clefMessage) );
        else System.out.println("-> " + ErreurMessage.get(clefMessage) + ".");
    }

    private static void enregistrerErreur(ICheminFichier cheminFichierErreur) {
        if (clefMessages.size() != 0 && !(clefMessages.size() == 1 && clefMessages.get(0).equals("fin.normale"))) {
            if (null == cheminFichierErreur || null == cheminFichierErreur.getChemin()) {
                FichierEcrire.ecrire(getJSON(), CheminFichierFactory.getErreurGenerique().getChemin());
            } else {
                FichierEcrire.ecrire(getJSON(), cheminFichierErreur.getChemin());
            }
        }
        System.exit(0);
    }

    //FIN PROGRAMME

    public static void sauvegarder() {
        Statistiques.setReclamationsInconnues(true);
        Statistiques.terminer();
        FichierTemps.ecrire();
        enregistrerErreur(cheminFichierErreur);
    }

    public static void terminerSansSauvegarde(String clefMessage) {
        afficherErreur(clefMessage);
        ErreurSortie.erreur("fin.erreur");
        ecrireErreur(clefMessage);
        enregistrerErreur(CheminFichierFactory.getErreurGenerique());
    }

    //ERREUR + TERMINER

    public static void terminer(String clefMessage) {
        afficherErreur(clefMessage);
        ErreurSortie.erreur("fin.erreur");
        ecrireErreur(clefMessage);
        Dossier.creer(Dossier.DATABASE);
        Statistiques.setReclamationsInconnues(false);
        Statistiques.terminer();
        enregistrerErreur(cheminFichierErreur);
    }

    public static void terminerSiVrai(boolean arreter, String clefMessage) {
        if (arreter) terminer(clefMessage);
    }

    public static void terminerSiNull(Object obj, String clefMessage) {
        if (null == obj) terminer(clefMessage);
    }

    public static void terminerSiNonNull(Object obj, String clefMessage) {
        if (null != obj) terminer(clefMessage);
    }

    //ERREUR + CONTINUER

    public static void erreur(String clefMessage) {
        afficherErreur(clefMessage);
        ecrireErreur(clefMessage);
    }


    //ERREUR INNATENDUE

    public static void terminer() {
        terminer("erreur.defaut");
    }


    public static void terminerSiNull(Object obj) {
        terminerSiNull(obj, "erreur.defaut");
    }


    public static void erreur() {
        erreur("erreur.defaut");
    }

    //PRIVATE

    private static String getJSON() {

        JSONObject jsonObject = new JSONObject();

        for (int i = 0; i < clefMessages.size(); i++) {
            try {
                jsonObject.put(clefMessages.get(i), ErreurMessage.get(clefMessages.get(i)));
            } catch (Exception e) {
            }
        }
        return jsonObject.toString(4);
    }

}
