
package Erreur;

import java.util.HashMap;

public class ErreurMessage {

    private static final String ERR_DEFAUT = "Une erreur inattendue est survenue, veuillez verifier vos donnees et " +
            "reessayer";
    private static final String JSON_CHAMPS = "\n\t\"dossier\", \"mois\" et \"reclamations\" contenant : " +
                                                "\n\t\"soin\", \"date\", \"montant\"";

    private static final HashMap<String, String> erreurMessage = new HashMap<>() {
        {
            //TODO : Inclure bornes dans les messages

            //----------- FICHIERS -----------
            put("fichier.valide",       "Nom de fichier invalide, le fichier doit etre au format \".json\"");
            put("fichier.existe",       "Fichier d'entree inexistant, veuillez choisir un fichier");
            put("fichier.ecrire",       "Erreur d'écriture du fichier");
            put("fichier.lire",         "Erreur de lecture du fichier");

            //----------- DOSSIER -----------
            put("dossier.creer",        "Un dossier inexistant servant aux sauvegardes sera cree");
            put("dossier.existe",       "Le dossier de la base de donnee existante sera utilise");
            put("dossier.ecrire",       "Erreur de creation de dossier. Veuillez reassayer");

            //----------- JSON -----------
            put("json.vide",            "Le fichier ne peut pas etre vide, veuillez inclure les champs suivants : " +
                                        JSON_CHAMPS);

            put("json.client",          "Veuillez inclure le numero du client");
            put("json.contrat",         "Veuillez inclure le numero de contrat");

            put("json.dossier",         "Veuillez inclure le numero de dossier a la reclamation");
            put("json.mois",            "Veuillez inclure l'annee et le mois vise par les reclamations");
            put("json.reclamation",     "Veuillez inclure au moins une reclamation");

            put("json.soin",            "Veuillez inclure le numero de soin a trois chiffres");
            put("json.date",            "Veuillez inclure la date complete de chacunes des reclamations tel que " +
                                        "\"AAAA-MM-JJ\"");
            put("json.montant",         "Veuillez inclure le montant de chacunes des reclamations");

            put("json.champ",           "Veuillez inclure que les champs suivants : " + JSON_CHAMPS);

            //----------- CLIENT -----------
            put("client.client",        "Numero de client invalide, ne doit contenir que six (6) chiffres");
            put("client.contrat",       "Numero de contrat invalide, ne doit contenir qu'une seule lettre valide");

            put("client.dossier",       "Numero de dossier invalide, " +
                                        "\n\tdoit etre de forme suivante : \"A123456\"");
            put("client.mois",          "Mois des demandes de reclamations invalide, " +
                                        "\n\tdoit etre une date existante et de forme suivante : \"AAAA-MM\"");
            put("client.reclamation",   "Aucune reclamation valide, veuillez corriger le fichier d'entree");

            put("client.soin",          "Numero de soin invalide, ne doit contenir que trois chiffres, " +
                                        "\n\tveuillez consulter votre police d'assurance si le probleme persiste");
            put("client.soin.nombre",   "Veuillez inclure un maximum de 4 soins par jours. Veuillez entrer les soins " +
                                        "individuellements afin de verifier leur validité");
            put("client.soin.existe",   "Numero de soin non inscrit a la police d'assurance, " +
                                        "\n\tveuillez consulter votre police d'assurance si le probleme persiste");
            put("client.date",          "Date d'un soin invalide, " +
                                        "\n\tdoit etre une date existante et de forme suivante : \"AAAA-MM-JJ\"");
            put("client.mois.meme",     "Date d'un soin invalide, " +
                                        "les soins doivent etre au mois de la reclamation");
            put("client.montant",       "Montant d'une reclamation invalide, " +
                                        "\n\tdoit etre de la forme suivate : \"0,000.00$\"");
            put("client.montant.zero",  "Veuillez indiquer le montant d'un soin supperieur a 0$");
            put("client.base",          "Veuillez uniquement inclure le numero de dossier");
            put("client.supprimer",     "Le fichier de donnee client sera supprime");

            //----------- DATE -----------
            put("date.existe",          "L'une des dates est impossible d'exister");
            put("date.futur",           "L'une des dates se situe dans le futur");

            //----------- DOLLAR -----------
            put("dollar.creer",         "Impossible de creer une monaie aussi grande ou petite");
            put("dollar.string",        "Impossible de creer une monaie de la forme fournie");

            //----------- STRING -----------
            put("string.lettre",        "Lettre invalide");
            put("string.chiffre",       "Chiffre invalide");
            put("string.nombre",        "Nombre invalide");

            //----------- ARGUMENT -----------
            put("argument.valide",      "Argument invalide." +
                                        "\nEssayez les arguments : " +
                                        "\n\t\"-S\" affichage des statistiques, " +
                                        "\n\t\"-SR\" reinitialisation des statistiques, " +
                                        "\n\t\"-CR\" reinitialisation des reclamations clients, " +
                                        "\n\t\"-p\" mode prediction : calcul de la reclamation sans sauvegarde, " +
                                        "\n\t\"-help\" affichage de l'aide sur le logiciel");
            put("argument.nombre",      "Nombre d'arguments invalides");

            //----------- STATS -----------
            put("stats.lecture",        "Erreur de lecture du fichier de statistiques. Veuillez ne pas modifier directement le fichier");
            put("stats.existe",         "Le fichier de statistique est manquant. Un nouveau sera cree");
            put("stats.supprimer",      "Le fichier de statistique sera supprime");
            put("stats.modif",          "Le fichier de statistique n'est pas valide ou corrompu." +
                                        "\n\tVeuillez le supprimer et recommencer");

            //----------- DATABASE -----------
            put("database.lecture",     "Erreur de lecture du fichier de base de donnee. Veuillez ne pas modifier directement le fichier");
            put("database.nouveau",     "Le fichier de base de donnee est manquant. Un nouveau sera cree");

            //----------- DEFAUT -----------
            put("erreur.defaut",        ERR_DEFAUT);
            put("fin.normale",          "Fin normale du programme");
            put("fin.erreur",           "Le programme a du fermer a cause d'une erreur");
            put("calcul.mois",          "Le calcul du remboursement s'est fait a partir de reclamations donnees precedemment");
            put("nombre.soin",          "Nombre de soins pour les jours soumis");
            put("montant.limite",       "Chaque type de soin ne peut pas depasser un total de plus de 500.00$ par mois");



//            put("", "");
        }
    };

    public static String get(String clef) {
        return erreurMessage.getOrDefault(clef, ERR_DEFAUT);
    }

}

