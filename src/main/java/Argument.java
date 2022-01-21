
import CheminFichier.CheminFichierFactory;
import Erreur.ErreurMessage;
import Erreur.ErreurSortie;
import Fichier.FichierLire;
import Fichier.FichierTemps;
import Statistique.Statistiques;

import javax.sound.midi.Soundbank;
import java.io.File;

public class Argument {

    public static String[] args;
    public static TypeArgument typeArgument;

    public Argument(String[] args) {
        Argument.args = args;
        setTypeArgument();
    }


    //PUBLIC
    public void executer() {
        verifierBorne();
        typeArgument.action.invoquer();
    }


    //EXECUTION
    static boolean afficherStatistique() {
        System.out.println(Statistiques.afficher());
        return true;
    }

    static boolean supprimerStatistique() {
        System.out.println(ErreurMessage.get("stats.supprimer"));
        Statistiques.recommencerStats();
        Statistiques.terminer();
        return true;
    }

    static boolean supprimerClients() {
        System.out.println(ErreurMessage.get("client.supprimer"));
        try {
            File file = new File(CheminFichierFactory.getSource().getChemin());
            file.delete();
            file = new File(CheminFichierFactory.getTimeStamp().getChemin());
            file.delete();

        } catch (Exception e) {
        }
        return true;
    }

    static boolean afficherAide() {
        System.out.println("\n" + MSG_AIDE + FichierLire.JSON_TEST + "\n" + MSG_AUTEUR);
        return true;
    }

    static void modePrediction() {
        retirerPremierArgument();
        Main.calculReclamationPrediction();
    }

    static void calculReclamation() {
        Main.calculReclamation();
    }

    static void aucunArgument() {
        ErreurSortie.terminerSansSauvegarde("argument.valide");
    }

    public static void invalideArgument() {
        ErreurSortie.terminerSansSauvegarde("argument.valide");
    }


    //OUTILS
    private static boolean estNullOuVide( String[] args ) {
        return ( null == args || args.length == 0 || null == args[0] || args[0].isEmpty() );
    }

    private void verifierBorne(){
        if (!(args.length >= typeArgument.borne.inferieur && args.length <= typeArgument.borne.superieur)) {
            ErreurSortie.erreur("argument.nombre");
            ErreurSortie.terminerSansSauvegarde("argument.valide");
        }
    }

    private static void retirerPremierArgument() {
        String[] argumentsRecu = new String[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            argumentsRecu[i-1] = args[i];
        }
        args = argumentsRecu;
    }

    private void setTypeArgument() {
        if (estNullOuVide(args)) typeArgument = TypeArgument.aucunArgument;
        else typeArgument = TypeArgument.obtenir(args[0]);
    }


    //MESSAGE
    private static final String MSG_AIDE = "Ceci est un programme de calcul de reclamations d'assurance.\n" +
            "- Il est obligatoire de donner un nom de fichier intrant. \n" +
            "- Le nom du fichier intrant doit être un .json\n" +
            "- Le fichier .json doit être bien formé. \n" +
            "\n" +
            "Syntax\n" +
            "Refund.jar [fichier_intrant.json] [fichier_extrant.json]\n" +
            "\n" +
            "Exemple de fichier intrant \n";

    private static final String MSG_AUTEUR = "Droits d'auteur\n" +
            "* Copyright (C) 2021 by Etienne Comtois, David Daoud, Loic Dion-Plourde, Jonathan Labelle All right reserved.\n" +
            "* Released under the terms of the GNU general Public License version 2 or later.\n" +
            "\n" +
            "Effectué dans le cadre du cours UQAM INF2050-211 G20\n";

}
