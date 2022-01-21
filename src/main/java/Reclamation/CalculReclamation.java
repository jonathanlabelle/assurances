
package Reclamation;

import Client.Client;
import Client.Clients;
import Contrat.TauxAssurable;
import Contrat.DictionnaireSoin;
import Contrat.PoliceSoin;
import DecodeurJSON.FauxClient.FauxReclamation;
import Outils.DateConteneur;
import Outils.DateFactory;
import Dollar.Dollar;
import Dollar.DollarFactory;

import java.util.ArrayList;
import java.util.Objects;

public class CalculReclamation {

    public CalculReclamation () {
    }

    public static Client obtenir(Clients clients, Client client) {

        for (Reclamation reclamation : client.getReclamationsMensuelles().get(0).getReclamations() ) {
            reclamation.setDateCalcul(new DateConteneur());
            reclamation.setMontantRemboursement(calculRemboursement( client, clients, reclamation ));
        }

        return client;
    }


    private static Dollar calculRemboursement(Client client, Clients clients, Reclamation reclamation) {

        Dollar montantReclamation = reclamation.getMontantReclamation();
        Dollar remboursement = calculReclamation(montantReclamation, reclamation.getContratSoin());

        if (reclamation.getContratSoin().getMontantMaximumMensuel().getCents() != 0) {
            remboursement = calculReclamationAvecMaximumMensuel(client, clients, reclamation);
        }

        return calculMaximumReboursementRencontre(remboursement, reclamation.getContratSoin());
    }


    private static Dollar calculReclamation(Dollar montantReclamation, TauxAssurable tauxAssurable) {

        Dollar remboursement = Dollar.Multiplication(montantReclamation, tauxAssurable.getPourcentageMaximum());

        if (remboursement.getCents() > tauxAssurable.getMontantMaximumMensuel().getCents()
                && tauxAssurable.getMontantMaximumMensuel().getCents() != 0) {
            remboursement = tauxAssurable.getMontantMaximumMensuel();
        }

        return remboursement;
    }


    private static Dollar calculMaximumReboursementRencontre (Dollar montantRemboursement, TauxAssurable tauxAssurable) {

        if ((montantRemboursement.getCents() > tauxAssurable.getMontantMaximumRencontre().getCents())
                && tauxAssurable.getMontantMaximumRencontre().getCents() != 0) {

            montantRemboursement = tauxAssurable.getMontantMaximumRencontre();

        }

        return montantRemboursement;
    }


    private static Dollar calculReclamationAvecMaximumMensuel(Client client, Clients clients, Reclamation reclamation) {
        Dollar remboursementsAnterieurs = calculRemboursementAnterieurs(client, clients, reclamation);
        Dollar remboursement = DollarFactory.getDollar(0);

        if (verificationSousMaxmimumMensuel(remboursementsAnterieurs,
                                            reclamation.getContratSoin().getMontantMaximumMensuel())) {
            remboursement = calculRemboursementAvecMaxMensuel(remboursementsAnterieurs, reclamation);
        }

        return remboursement;
    }


    private static boolean verificationSousMaxmimumMensuel (Dollar remboursements, Dollar maximum) {
        return (remboursements.getCents() < maximum.getCents());
    }


    private static Dollar calculRemboursementAvecMaxMensuel (Dollar remboursementsAnterieurs, Reclamation reclamation) {
        Dollar remboursement = calculReclamation(reclamation.getMontantReclamation(), reclamation.getContratSoin());

        if (Dollar.Addition(remboursement, remboursementsAnterieurs).getCents() >
                reclamation.getContratSoin().getMontantMaximumMensuel().getCents()) {

            remboursement = Dollar.Soustraction(reclamation.getContratSoin().getMontantMaximumMensuel(), remboursementsAnterieurs);
        }

        return remboursement;
    }


    public static Dollar calculRemboursementAnterieurs (Client client, Clients clients, Reclamation reclamation) {
        Dollar remboursementsAnterieurs = calculRemboursementReclamationsPresentes(client, reclamation);

        if (verifierSiReclamationsAnterieures(clients)) {
            remboursementsAnterieurs = Dollar.Addition(remboursementsAnterieurs,
                    calculRemboursementsReclamationsAnterieures(client.getNumeroClient(), clients, reclamation));
        }

        return  remboursementsAnterieurs;
    }


    private static Dollar calculRemboursementReclamationsPresentes(Client client, Reclamation reclamation) {

        Dollar totalRembourse = DollarFactory.getDollar(0);

        for (int i = 0; i < client.getReclamationsMensuelles().get(0).getReclamations().size(); i++) {

            totalRembourse = calculEtVerifReclamationsPresentes (client.getPremiereReclamationMensuelle().getReclamations().get(i),
                                                                reclamation, totalRembourse);
        }

        return totalRembourse;
    }


    private static Dollar calculEtVerifReclamationsPresentes (Reclamation reclamationAnterieure, Reclamation reclamation,
                                                             Dollar totalRembourse) {

        if (verificationMemeMoisEtSoin(reclamationAnterieure, reclamation)) {
            totalRembourse = Dollar.Addition(totalRembourse, reclamationAnterieure.getMontantRemboursement());
        }

        return totalRembourse;
    }


    protected static Dollar calculRemboursementsReclamationsAnterieures (String numeroClient, Clients clients, Reclamation reclamation) {

        Dollar remboursement = DollarFactory.getDollar(0);

        if (verifierSiReclamationsAnterieures(clients)) {

            for (Client clientAnterieur: clients) {
                remboursement = verificationSiMemeClient(numeroClient, clientAnterieur, reclamation, remboursement);
            }
        }

        return remboursement;
    }


    private static Dollar verificationSiMemeClient(String numeroClient, Client clientAnterieur,
                                                  Reclamation reclamation, Dollar remboursement) {

            if (clientAnterieur.getNumeroClient().equals(numeroClient)) {
                for (ReclamationsMensuelle reclamationsMensuelle : clientAnterieur.getReclamationsMensuelles()) {
                    remboursement = verificationMemeTypeReclamationAnterieure(reclamationsMensuelle, reclamation, remboursement);
                }
            }

        return remboursement;
    }


    private static Dollar verificationMemeTypeReclamationAnterieure(ReclamationsMensuelle reclamationsMensuelle,
                                                         Reclamation reclamation, Dollar remboursement) {

        for (Reclamation reclamationAnterieure : reclamationsMensuelle.getReclamations()) {
            if (verificationMemeMoisEtSoin(reclamation, reclamationAnterieure)) {
                remboursement = Dollar.Addition(remboursement, reclamationAnterieure.getMontantRemboursement());
            }
        }

        return remboursement;
    }


    private static boolean verifierSiReclamationsAnterieures (Clients clients) {
        return (Objects.nonNull(clients) && !clients.isEmpty());
    }


    private static boolean verificationMemeMoisEtSoin(Reclamation reclamation1, Reclamation reclamation2) {

        boolean memeMoisEtSoin = false;

        if (verificationMemeMois(reclamation1.getDateReclamation(), reclamation2.getDateReclamation())
                && verificationMemeSoin(reclamation1.getPoliceSoin(), reclamation2.getPoliceSoin())) {
            memeMoisEtSoin = true;
        }

        return memeMoisEtSoin;
    }


    private static boolean verificationMemeMois(DateConteneur date1, DateConteneur date2) {
        return (date1.getAnnee() == date2.getAnnee() && date1.getMois() == date2.getMois());
    }


    private static boolean verificationMemeSoin(PoliceSoin policeSoin1, PoliceSoin policeSoin2) {
        return (policeSoin1.equals(policeSoin2));
    }

}

