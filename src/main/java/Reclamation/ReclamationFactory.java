
package Reclamation;

import Client.Client;
import Client.Clients;
import Contrat.DictionnaireSoin;
import Contrat.PoliceSoin;
import Contrat.PoliceSoinFactory;
import Contrat.TauxAssurable;
import DecodeurJSON.FauxClient.FauxClient;
import DecodeurJSON.FauxClient.FauxReclamation;
import Dollar.Dollar;
import Dollar.DollarFactory;
import Erreur.ErreurMessage;
import Erreur.ErreurSortie;
import Outils.DateConteneur;
import Outils.DateFactory;

public class ReclamationFactory {

    private static final int MONTANT_LIMITE = 50000;


    //PUBLIC

    public static void ajouterReclamations(Clients clients, Client client, FauxClient fauxClient) {

        verifierDatesFauxReclamations(client.getPremiereReclamationMensuelle().getDateMois(), fauxClient);
        verifierMontantValide(fauxClient);

        ajouterReclamationsValides(client, fauxClient);

        verifierMontantLimite(clients, client);
        verifierNombreDeSoinsJournaliers(clients, client);

    }


    //VERIFIER MONTANT LIMITE

    private static void verifierMontantLimite(Clients clients, Client client) {
        Client clientExistant = clients.getClient(client);
        ReclamationsMensuelle reclamMensuelleExist = null;
        if (null != clientExistant) reclamMensuelleExist =
                clientExistant.getReclamationsMensuelle(client.getPremiereReclamationMensuelle());

        if (null != reclamMensuelleExist) verifierMontantLimiteExistant(clients, client);
        else verifierMontantLimiteRecu(client);
    }

    private static void verifierMontantLimiteExistant(Clients clients, Client client) {
        Client clientExistant = clients.getClient(client);
        ReclamationsMensuelle reclamationsMensuelleExistant = clientExistant
                .getReclamationsMensuelle(client.getPremiereReclamationMensuelle());
        calculerTotalParSoin(client, reclamationsMensuelleExistant);
    }

    private static void calculerTotalParSoin(Client client, ReclamationsMensuelle reclamationsMensuelleExistant) {

        for (Reclamation reclamation : client.getPremiereReclamationMensuelle().getReclamations()) {
            int numeroSoin = DictionnaireSoin.policeEquivalente(reclamation.getPoliceSoin()).getNumeroSoin();
            Dollar dollar = DollarFactory.getDollar(0);

            dollar = Dollar.Addition(dollar, compterMontantTotalSoin(client.getPremiereReclamationMensuelle(), numeroSoin));
            dollar = Dollar.Addition(dollar, compterMontantTotalSoin(reclamationsMensuelleExistant, numeroSoin));

            verifierMontantLimite(dollar);
        }

    }

    private static void verifierMontantLimiteRecu(Client client) {

        for (Reclamation reclamation : client.getPremiereReclamationMensuelle().getReclamations()) {
            int numeroSoin = DictionnaireSoin.policeEquivalente(reclamation.getPoliceSoin()).getNumeroSoin();
            Dollar dollar = compterMontantTotalSoin(client.getPremiereReclamationMensuelle(), numeroSoin);
            verifierMontantLimite(dollar);
        }

    }

    private static Dollar compterMontantTotalSoin(ReclamationsMensuelle reclamationsMensuelle, int numeroSoin) {
        Dollar dollar = DollarFactory.getDollar(0);

        for (Reclamation reclamation : reclamationsMensuelle.getReclamations()) {
            if (DictionnaireSoin.policeEquivalente(reclamation.getPoliceSoin()).getNumeroSoin() == numeroSoin) {
                Dollar dollarRecu = reclamation.getMontantReclamation();
                dollar = Dollar.Addition(dollar, dollarRecu);
            }
        }
        return dollar;
    }

    private static void verifierMontantLimite(Dollar dollar) {
        if (dollar.getCents() > MONTANT_LIMITE) {
            ErreurSortie.terminer("montant.limite");
        }
    }


    //VERIFIER NOMBRE DE SOINS

    private static void verifierNombreDeSoinsJournaliers(Clients clients, Client client) {
        Client clientExistant = clients.getClient(client);
        ReclamationsMensuelle reclamMensuelleExist = null;
        if (null != clientExistant) reclamMensuelleExist =
                clientExistant.getReclamationsMensuelle(client.getPremiereReclamationMensuelle());

        if (null != reclamMensuelleExist) verifierNombreDeSoinsJournaliersExistant(clients, client);
        else verifierNombreDeSoinsJournaliersRecu(client);
    }

    private static void verifierNombreDeSoinsJournaliersExistant(Clients clients, Client client) {
        Client clientExistant = clients.getClient(client);
        ReclamationsMensuelle reclamationsMensuelleExistant = clientExistant
                .getReclamationsMensuelle(client.getPremiereReclamationMensuelle());
        System.out.println("-> " + ErreurMessage.get("nombre.soin") + " :");
        compterNombreDeSoinsJournaliers(client, reclamationsMensuelleExistant);
    }

    private static void verifierNombreDeSoinsJournaliersRecu(Client client) {

        System.out.println("-> " + ErreurMessage.get("nombre.soin") + " :");
        compterNombreDeSoinsJournaliers(client);

    }

    private static void compterNombreDeSoinsJournaliers(Client client, ReclamationsMensuelle reclamationsMensuelleExistant) {
        for (Reclamation reclamation : client.getPremiereReclamationMensuelle().getReclamations()) {
            int nombreReclamation = 0;

            nombreReclamation += getNombreReclamationRecu(client, reclamation);
            nombreReclamation += getNombreReclamationExistantes(reclamationsMensuelleExistant, reclamation);
            afficherNombreReclamationJournaliere(reclamation, nombreReclamation);
        }
    }

    private static void compterNombreDeSoinsJournaliers(Client client) {
        for (Reclamation reclamation : client.getPremiereReclamationMensuelle().getReclamations()) {
            int nombreReclamation = 0;

            nombreReclamation += getNombreReclamationRecu(client, reclamation);
            afficherNombreReclamationJournaliere(reclamation, nombreReclamation);
        }
    }

    private static int getNombreReclamationExistantes(ReclamationsMensuelle reclamationsMensuelleExistant,
                                                      Reclamation reclamationComparaison) {
        int nombreReclamation = 0;
        for (Reclamation reclamationExistantes : reclamationsMensuelleExistant.getReclamations()) {
            if (reclamationComparaison.getDateReclamation().memeAnneMoisJour(reclamationExistantes.getDateReclamation()))
                nombreReclamation++;
        }
        return nombreReclamation;
    }

    private static int getNombreReclamationRecu(Client client, Reclamation reclamationComparaison) {
        int nombreReclamation = 0;
        for (Reclamation reclamationRecu : client.getPremiereReclamationMensuelle().getReclamations()) {
            if (reclamationComparaison.getDateReclamation().memeAnneMoisJour(reclamationRecu.getDateReclamation()))
                nombreReclamation++;
        }
        return nombreReclamation;
    }

    private static void afficherNombreReclamationJournaliere(Reclamation reclamation, int nombreReclamation) {
        System.out.printf("\t%s : %s\n", reclamation.getDateReclamation().toString(), nombreReclamation);
        if (nombreReclamation > 4) ErreurSortie.terminer("client.soin.nombre");
    }


    //VERIFIER DATES

    private static void verifierDatesFauxReclamations(DateConteneur dateMois, FauxClient fauxClient) {
        ErreurSortie.terminerSiVrai(fauxClient.getReclamations().size() == 0, "json.reclamation");

        for (FauxReclamation fauxReclamation : fauxClient.getReclamations()) {
            DateConteneur dateConteneur = DateFactory.obtenir(fauxReclamation.getDateReclamation());
            ErreurSortie.terminerSiNull(dateConteneur, "client.date");
            ErreurSortie.terminerSiVrai(!dateMois.memeAnneMois( dateConteneur ), "client.mois.meme");
            ErreurSortie.terminerSiVrai(dateConteneur.getJour()==0, "client.date");
        }

    }


    //VERIFIER MONTANT PAS ZERO

    private static void verifierMontantValide(FauxClient fauxClient) {

        for (FauxReclamation fauxReclamation : fauxClient.getReclamations()) {
            if (DollarFactory.getDollar( fauxReclamation.getMontantReclamation() ).getCents() <= 0 ) {
                ErreurSortie.terminer("client.montant.zero");
            }
        }

    }


    //VERIFIER TAUX ASSURABLE

    private static void verifierTauxAssurable(PoliceSoin policeSoin) {
        TauxAssurable tauxAssurable = DictionnaireSoin.getTauxAssurable(policeSoin);
        ErreurSortie.terminerSiNull(tauxAssurable, "client.soin.existe");
    }

    private static Reclamation getReclamation(FauxReclamation fauxReclamation, PoliceSoin policeSoin) {
        TauxAssurable tauxAssurable = DictionnaireSoin.getTauxAssurable(policeSoin);
        DateConteneur dateReclamation = DateFactory.obtenir(fauxReclamation.getDateReclamation());

        Reclamation reclamation = new Reclamation(); {
            reclamation.setTauxAssurable(tauxAssurable);
            reclamation.setPoliceSoin(policeSoin);
            reclamation.setMontantReclamation(DollarFactory.getDollar(fauxReclamation.getMontantReclamation()));
            reclamation.setDateReclamation(dateReclamation);
            reclamation.setMontantRemboursement(DollarFactory.getDollar(0));
            reclamation.setDateCalcul(new DateConteneur());
        }

        return reclamation;
    }


    //AJOUTER RECLAM

    private static boolean ajouterReclamationsValides(Client client, FauxClient fauxClient) {

        for (FauxReclamation fauxReclamation : fauxClient.getReclamations()) {

            PoliceSoin policeSoin = PoliceSoinFactory.obtenir(client.getContrat(), fauxReclamation.getSoin());
            verifierTauxAssurable(policeSoin);
            Reclamation reclamation = getReclamation(fauxReclamation, policeSoin);
            ErreurSortie.terminerSiNull(reclamation, "client.reclamation");

            client.getReclamationsMensuelles().get(0).ajouterReclamation(reclamation); //ameliorer
        }

        return true; //TODO: pour methodes void
    }

}
