
package Client;

import Contrat.Contrat;
import DecodeurJSON.FauxClient.FauxClient;
import Erreur.ErreurSortie;
import Outils.DateConteneur;
import Outils.DateFactory;
import Outils.StringAnalyse;
import Reclamation.ReclamationsMensuelle;

import java.util.ArrayList;

public class ClientFactory {

    private static final int LONGUEUR_NUMERO = 6;

    public ClientFactory() {
    }

    public static Client obtenirClient(FauxClient fauxClient){
        verifierClientBase(fauxClient);
        DateConteneur dateMois = verifierDateMois(fauxClient);
        Client client = new Client( getNumero(fauxClient), getContrat(fauxClient), new ArrayList<>() );
        ReclamationsMensuelle reclamationsMensuelle = new ReclamationsMensuelle(dateMois, new ArrayList<>());
        client.ajouterReclamationsMensuelle(reclamationsMensuelle);
        return client;
    }

    //CLIENT

    private static void verifierClientBase(FauxClient fauxClient) {
        verifierDuplication(fauxClient);

        if (null == fauxClient.getDossier()) {
            verifierNumero(fauxClient.getNumeroClient());
            verifierContrat(fauxClient.getContrat());

        } else {
            verifierDossier(fauxClient.getDossier());
        }
    }

    private static void verifierDuplication(FauxClient fauxClient) {
        if (null != fauxClient.getDossier()
                && null != fauxClient.getContrat()
                && null != fauxClient.getNumeroClient()) {
            ErreurSortie.terminer("client.base");
        }
    }

    private static Contrat getContrat(FauxClient fauxClient) {
        if (null == fauxClient.getDossier()) {
            return Contrat.obtenirParNom(fauxClient.getContrat());
        } else {
            return Contrat.obtenirParNom(fauxClient.getDossier().substring(0,1));
        }
    }

    private static String getNumero(FauxClient fauxClient) {
        if (null == fauxClient.getDossier()) {
            return fauxClient.getNumeroClient();
        } else {
            return fauxClient.getDossier().substring(1);
        }
    }

    private static boolean verifierContrat(String contrat) {
        if (!Contrat.existe(contrat)) {
            ErreurSortie.terminer("client.contrat");
            return false;
        }
        return true;
    }

    private static boolean verifierNumero(String numeroClient) {
        if (numeroClient.length() != LONGUEUR_NUMERO || !StringAnalyse.neContientQueDesChiffres(numeroClient)) {
            ErreurSortie.terminer("client.contrat");
            return false;
        }
        return true;
    }

    private static void verifierDossier(String dossier) {
        if (dossier.length() != LONGUEUR_NUMERO + 1
                || !verifierContrat(dossier.substring(0,1))
                || !verifierNumero(dossier.substring(1))) {
            ErreurSortie.terminer("client.dossier");
        }
    }

    //RECLAMATION MENSUELLE

    private static DateConteneur verifierDateMois(FauxClient fauxClient) {
        DateConteneur dateMois = DateFactory.obtenir(fauxClient.getDateMois());
        if (null == dateMois || dateMois.getJour() != 0) {
            ErreurSortie.terminer("client.mois");
        }
        return dateMois;
    }


}
